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


/**
 *
 */
Ext.define('OPF.console.resource.view.resource.TextResourceEditor', {
    extend: 'OPF.console.resource.view.resource.BaseResourceEditor',
    alias: 'widget.text-resource-editor',

    title: 'TEXT: [New]',

    infoResourceLookup: 'net.firejack.platform.content.abstract-resource.resource.text-resource',

    /**
     *
     */
    initComponent: function() {
        var instance = this;

        this.resourceVersionTextField = Ext.ComponentMgr.create({
            xtype: 'textarea',
            anchor: '100%',
            hideLabel: true,
            height: 100,
            name: 'resourceVersionText',
            emptyText: 'No Content Defined'
        });

        this.additionalFieldSet = Ext.ComponentMgr.create({
            xtype: 'label-container',
            fieldLabel: 'Text',
            subFieldLabel: '',
            layout: 'anchor',
            items :[
                this.resourceVersionTextField
            ]
        });

        this.callParent(arguments);
    },

    onAfterSetSpecificValue: function(jsonData) {
        this.resourceVersionTextField.setValue(jsonData.resourceVersion.text);
    },

    onBeforeSpecificDataSave: function(formData) {
        formData.resourceVersion.text = formData.resourceVersionText;
        delete formData.resourceVersionText;
    },

    onReloadResourceVersionFailure: function() {
        this.resourceVersionTextField.setValue('');
    },

    onSuccessDeleteResourceVersion: function() {
        this.resourceVersionTextField.setValue('');
    }


});