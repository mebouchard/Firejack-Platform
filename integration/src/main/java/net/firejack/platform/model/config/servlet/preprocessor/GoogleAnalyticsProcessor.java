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

package net.firejack.platform.model.config.servlet.preprocessor;

import net.firejack.platform.api.config.domain.Config;
import net.firejack.platform.api.site.domain.NavigationElement;
import net.firejack.platform.processor.cache.ConfigCacheManager;
import net.firejack.platform.web.security.model.OpenFlameSecurityConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class GoogleAnalyticsProcessor implements IGatewayPreProcessor {

    @Override
    public void execute(Map<String, String> map, HttpServletRequest request, HttpServletResponse response, NavigationElement currentNavigationElement) {
        ConfigCacheManager configCacheManager = ConfigCacheManager.getInstance();
        Config config = configCacheManager.getConfig(OpenFlameSecurityConstants.getPackageLookup() + ".company-tweeter-account");
        if (config != null) {
            map.put("companyTweeterAccount", "'" + config.getValue() + "'");
        }
    }

}