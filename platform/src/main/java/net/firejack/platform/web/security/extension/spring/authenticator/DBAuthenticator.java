/*
 * Firejack Platform - Copyright (c) 2011 Firejack Technologies
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

package net.firejack.platform.web.security.extension.spring.authenticator;

import net.firejack.platform.core.model.user.UserModel;
import net.firejack.platform.web.security.cridential.IPasswordEncryptionStrategy;
import net.firejack.platform.web.security.permission.IPermissionContainer;
import net.firejack.platform.web.security.permission.IPermissionContainerFactory;
import net.firejack.platform.web.security.spring.SpringSecurityAuthenticationRoleType;
import net.firejack.platform.web.security.spring.authenticator.Authenticator;
import net.firejack.platform.web.security.spring.authenticator.DefaultAuthenticationDetails;
import net.firejack.platform.web.security.spring.authenticator.IAuthenticationDetails;
import net.firejack.platform.web.security.spring.authenticator.IAuthenticationSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;


public abstract class DBAuthenticator extends Authenticator {

    @Autowired
    @Qualifier("passwordEncryptionStrategy")
    public IPasswordEncryptionStrategy passwordEncryptionStrategy;

    private IPermissionContainerFactory permissionContainerFactory;

    /**
     * @param permissionContainerFactory
     */
    public void setPermissionContainerFactory(IPermissionContainerFactory permissionContainerFactory) {
        this.permissionContainerFactory = permissionContainerFactory;
    }

    @Override
    public IAuthenticationDetails authenticate(IAuthenticationSource authenticationSource) {
        String userName = authenticationSource.getPrincipal();
        String password = authenticationSource.getCredential();

        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            String encryptedPassword = passwordEncryptionStrategy.encryptPassword(password);

            UserModel user = findUserByUsernameAndPassword(userName, encryptedPassword);
            if (user != null) {
                List<SpringSecurityAuthenticationRoleType> roles = new ArrayList<SpringSecurityAuthenticationRoleType>();
                roles.add(SpringSecurityAuthenticationRoleType.ROLE_USER); //todo: eliminate such usages
                IPermissionContainer permissionContainer = permissionContainerFactory.producePermissionContainer();
                return new DefaultAuthenticationDetails(provideGrantedAuthorities(roles), authenticationSource, user, permissionContainer);
            }
        }
        return null;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public abstract UserModel findUserByUsernameAndPassword(String username, String password);

}