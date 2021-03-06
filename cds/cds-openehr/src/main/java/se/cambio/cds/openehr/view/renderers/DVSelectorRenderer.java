package se.cambio.cds.openehr.view.renderers;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import se.cambio.cds.openehr.util.OpenEHRDataValuesUI;

public class DVSelectorRenderer extends JLabel implements ListCellRenderer{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
	    int index, boolean isSelected, boolean cellHasFocus) {
	if (isSelected) {
	    setBackground(list.getSelectionBackground());
	    setForeground(list.getSelectionForeground());
	}else {
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	}
	if (value instanceof String){
	    String idDataValue = (String)value;
	    setText(OpenEHRDataValuesUI.getName(idDataValue));
	    setToolTipText(OpenEHRDataValuesUI.getDescription(idDataValue));
	    setIcon(OpenEHRDataValuesUI.getIcon(idDataValue));
	}else{
	    setText(null);
	    setToolTipText(null);
	    setIcon(null);
	}
	return this;
    }

}
/*
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