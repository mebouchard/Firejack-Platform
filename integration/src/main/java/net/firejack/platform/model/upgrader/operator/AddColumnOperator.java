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

package net.firejack.platform.model.upgrader.operator;

import net.firejack.platform.api.registry.model.FieldType;
import net.firejack.platform.model.upgrader.dbengine.bean.Column;
import net.firejack.platform.model.upgrader.dbengine.bean.Table;
import net.firejack.platform.model.upgrader.operator.bean.AddColumnType;

public class AddColumnOperator extends AbstractOperator<AddColumnType> {

    @Override
    protected String[] sqlCommands(AddColumnType columnType) {
        Table table = new Table(columnType.getTable());
        FieldType fieldType = FieldType.findByName(columnType.getType());
        Column column = new Column(columnType.getName(), fieldType);
        column.setDefaultValue(columnType.getDefault());
        column.setAutoIncrement(columnType.getPrimaryKey() == Boolean.TRUE);
        column.setNotNull(columnType.getRequired() == Boolean.TRUE);
        return new String[] { dialect.addColumn(table, column) };
    }
}
