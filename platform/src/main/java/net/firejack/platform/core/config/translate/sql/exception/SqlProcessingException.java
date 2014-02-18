/*
 * Firejack Open Flame - Copyright (c) 2011 Firejack Technologies
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

package net.firejack.platform.core.config.translate.sql.exception;

import net.firejack.platform.core.exception.OpenFlameRuntimeException;


public class SqlProcessingException extends OpenFlameRuntimeException {

    private static final long serialVersionUID = -6734563657294584338L;

    /***/
    public SqlProcessingException() {
    }

    /**
     * @param message
     */
    public SqlProcessingException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SqlProcessingException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SqlProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}