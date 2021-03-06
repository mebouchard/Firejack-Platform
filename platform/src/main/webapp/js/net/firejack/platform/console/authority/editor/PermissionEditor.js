/*
 * Firejack Platform - Copyright (c) 2012 Firejack Technologies
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

Ext.ns('FJK.platform.clds.ui');

/**
 *
 */
Ext.define('OPF.console.authority.editor.PermissionEditor', {
    extend: 'OPF.core.component.editor.BaseEditor',

    title: 'PERMISSION: [New]',

    infoResourceLookup: 'net.firejack.platform.authority.permission',

    editableWithChild: true,

    /**
     *
     */
    initComponent: function() {

        this.nodeBasicFields = Ext.create('OPF.core.component.editor.BasicInfoFieldSet', this);

        this.descriptionFields = Ext.create('OPF.core.component.editor.DescriptionInfoFieldSet', this);

        this.staticBlocks = [
            this.nodeBasicFields
        ];

        this.additionalBlocks = [
            this.descriptionFields
        ];

        this.callParent(arguments);
    },

    hideEditPanel: function() {
        OPF.console.authority.editor.PermissionEditor.superclass.hideEditPanel.call(this);
        this.managerLayout.tabPanel.setActiveTab(this.managerLayout.permissionGridView);
    },

    onSuccessSaved: function(method, vo) {
    }

});