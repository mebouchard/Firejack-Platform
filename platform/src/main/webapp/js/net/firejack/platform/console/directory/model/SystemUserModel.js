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

Ext.define('OPF.console.directory.model.SystemUserModel', {
    extend: 'Ext.data.Model',

    statics: {
        pageSuffixUrl: 'console/directory',
        restSuffixUrl: 'directory/system_user',
        editorClassName: 'OPF.console.directory.editor.SystemUserEditor',
        constraintName: 'OPF.directory.SystemUser'
    },

    idProperty: 'id',

    fields: [
        { name: 'id', type: 'int', useNull: true },
        { name: 'username', type: 'string' },
        { name: 'password', type: 'string' },
        { name: 'passwordConfirm', type: 'string' },
        { name: 'email', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'middleName', type: 'string' },
        { name: 'lastName', type: 'string' },
        { name: 'guest', type: 'boolean' },
        { name: 'registryNodeId', type: 'int' },
        { name: 'created', type: 'int' },
        { name: 'canUpdate', type: 'boolean' },
        { name: 'canDelete', type: 'boolean' },
        { name: 'roles', persist: false }
    ],

    hasMany: [
        { model: 'OPF.console.authority.model.Role', name: 'roles' }
    ]

});

