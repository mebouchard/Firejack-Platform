package net.firejack.platform.generate.beans.ipad;
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

import net.firejack.platform.core.utils.FileUtils;

public class GridController implements iPad {
    private String suffix;
    private String name;
    private EditController edit;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name + suffix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EditController getEdit() {
        return edit;
    }

    public void setEdit(EditController edit) {
        this.edit = edit;
    }

    @Override
    public String getFilePosition() {
        return FileUtils.construct("${name}", "controllers");
    }
}