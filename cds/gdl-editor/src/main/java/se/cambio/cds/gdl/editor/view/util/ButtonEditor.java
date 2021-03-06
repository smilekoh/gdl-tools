
package se.cambio.cds.gdl.editor.view.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import se.cambio.cds.gdl.editor.util.LanguageManager;

public abstract class ButtonEditor extends DefaultCellEditor {

    private static final long serialVersionUID = 3538959536078591524L;
    protected JButton button;

    public ButtonEditor() {
	super(new JCheckBox());
	button = new JButton();
	button.setOpaque(true);
	button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
	    }
	});
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
	    boolean isSelected, int row, int column) {
	if (isSelected) {
	    button.setForeground(table.getSelectionForeground());
	    button.setBackground(table.getSelectionBackground());
	} else {
	    button.setForeground(table.getForeground());
	    button.setBackground(table.getBackground());
	}
	if (value==null || ((String)value).trim().isEmpty()){
	    button.setText(LanguageManager.getMessage("Select"));
	}else{
	    button.setText((String)value);
	}	
	performAction(row);
	return button;
    }

    public abstract Object getCellEditorValue();

    public abstract void performAction(int row);
}/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 2.0/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  2.0 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *
 *  The Initial Developers of the Original Code are Iago Corbal and Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2012-2013
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */