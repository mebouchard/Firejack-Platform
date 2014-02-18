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

package net.firejack.platform.service.authority.broker;

import net.firejack.platform.core.broker.ServiceBroker;
import net.firejack.platform.core.domain.NamedValues;
import net.firejack.platform.core.request.ServiceRequest;
import net.firejack.platform.core.response.ServiceResponse;
import net.firejack.platform.web.security.session.UserSessionManager;
import net.firejack.platform.web.statistics.annotation.TrackDetails;
import org.springframework.stereotype.Component;


@TrackDetails
@Component("signOutBrokerEx")
public class SignOutBroker extends ServiceBroker<ServiceRequest<NamedValues<String>>, ServiceResponse> {

    public static final String PARAM_STS_TOKEN = "PARAM_STS_TOKEN";

    @Override
    protected ServiceResponse perform(ServiceRequest<NamedValues<String>> request)
		    throws Exception {
        String token = request.getData().get(PARAM_STS_TOKEN);
        UserSessionManager sessionManager = UserSessionManager.getInstance();
        boolean sessionClosed = sessionManager.closeUserSession(token);
        return new ServiceResponse(sessionClosed ? "Token was signed out" : "Token was not signed out", sessionClosed);
    }

}