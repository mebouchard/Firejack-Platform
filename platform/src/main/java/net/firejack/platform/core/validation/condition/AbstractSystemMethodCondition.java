/*
 * Firejack Open Flame - Copyright (c) 2012 Firejack Technologies
 *
 * This source code is the product of the Firejack Technologies
 * Core Technologies Team (Benjamin A. Miller, Oleg Marshalenko, and Timur
 * Asanov) and licensed only under valid, executed license agreements
 * between Firejack Technologies and its customers. Modification and / or
 * re-distribution of this source code is allowed only within the terms
 * of an executed license agreement.
 *
 * Any modification of this code voids any and all warranties and indemnifications
 * for the component in question and may interfere with upgrade path. Firejack Technologies
 * encourages you to extend the core framework and / or request modifications. You may
 * also submit and assign contributions to Firejack Technologies for consideration
 * as improvements or inclusions to the platform to restore modification
 * warranties and indemnifications upon official re-distributed in patch or release form.
 */

package net.firejack.platform.core.validation.condition;

import net.firejack.platform.core.model.registry.RegistryNodeModel;
import net.firejack.platform.core.model.registry.domain.PackageModel;
import net.firejack.platform.core.model.registry.domain.SystemModel;
import net.firejack.platform.core.store.registry.IPackageStore;
import net.firejack.platform.core.store.registry.IRegistryNodeStore;
import net.firejack.platform.core.validation.constraint.vo.Constraint;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class AbstractSystemMethodCondition extends AbstractCondition {

    @Autowired
    @Qualifier("registryNodeStore")
    private IRegistryNodeStore<RegistryNodeModel> registryNodeStore;

    @Autowired
    @Qualifier("packageStore")
    private IPackageStore packageStore;

    @Override
    public List<Constraint> generateConstraints(Map<String, String> params) {
        return null;
    }

    @Override
    public String defaultValue(Map<String, String> params) {
        String defaultValue = null;
        if (params != null) {
            String sParentId = params.get("parentId");
            if (StringUtils.isNumeric(sParentId)) {
                Long registryNodeId = Long.parseLong(sParentId);
                List<Long> registryNodeIds = new ArrayList<Long>();
                List<Object[]> collectionArrayIds = registryNodeStore.findAllIdAndParentId();
                registryNodeStore.findCollectionParentIds(registryNodeIds, registryNodeId, collectionArrayIds);
                SystemModel system = null;
                for (Long parentId : registryNodeIds) {
                    RegistryNodeModel registryNode = registryNodeStore.findById(parentId);
                    if (registryNode instanceof SystemModel) {
                        system = (SystemModel) registryNode;
                        break;
                    }
                    if (registryNode instanceof PackageModel) {
                        PackageModel packageRN = packageStore.findWithSystemById(parentId);
                        system = packageRN.getSystem();
                        break;
                    }
                }
                if (system != null) {
                    defaultValue = getDefaultValue(system);
                }
            }
        }
        return defaultValue;
    }

    protected abstract String getDefaultValue(SystemModel system);

}