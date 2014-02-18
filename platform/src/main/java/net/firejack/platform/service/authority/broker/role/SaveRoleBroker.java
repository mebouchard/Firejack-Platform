/**
 * Firejack Open Flame - Copyright (c) 2011 Firejack Technologies
 * <p/>
 * This source code is the product of the Firejack Technologies
 * Core Technologies Team (Benjamin A. Miller, Oleg Marshalenko, and Timur
 * Asanov) and licensed only under valid, executed license agreements
 * between Firejack Technologies and its customers. Modification and / or
 * re-distribution of this source code is allowed only within the terms
 * of an executed license agreement.
 * <p/>
 * Any modification of this code voids any and all warranties and indemnifications
 * for the component in question and may interfere with upgrade path. Firejack Technologies
 * encourages you to extend the core framework and / or request modifications. You may
 * also submit and assign contributions to Firejack Technologies for consideration
 * as improvements or inclusions to the platform to restore modification
 * warranties and indemnifications upon official re-distributed in patch or release form.
 */

package net.firejack.platform.service.authority.broker.role;


import net.firejack.platform.api.authority.domain.Role;
import net.firejack.platform.core.broker.SaveBroker;
import net.firejack.platform.core.model.registry.authority.RoleModel;
import net.firejack.platform.core.store.registry.IRoleStore;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@TrackDetails
@Component("saveRoleBrokerEx")
public class SaveRoleBroker extends SaveBroker<RoleModel, Role, Role> {

    @Autowired
    @Qualifier("roleStore")
    private IRoleStore roleStore;

    @Override
    protected RoleModel convertToEntity(Role dto) {
        return factory.convertFrom(RoleModel.class, dto);
    }

    @Override
    protected Role convertToModel(RoleModel model) {
        return factory.convertTo(Role.class, model);
    }

    @Override
    protected void save(RoleModel entity) throws Exception {
        roleStore.save(entity);
    }

}