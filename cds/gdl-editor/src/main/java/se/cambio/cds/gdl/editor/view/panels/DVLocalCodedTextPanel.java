package se.cambio.cds.gdl.editor.view.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvCodedText;

import se.cambio.cds.gdl.editor.controller.EditorManager;
import se.cambio.cds.gdl.editor.controller.GDLEditor;
import se.cambio.cds.gdl.editor.view.dialog.DialogGTCodeSelection;
import se.cambio.cds.gdl.model.Term;
import se.cambio.cds.openehr.util.ImageUtil;
import se.cambio.cds.openehr.util.OpenEHRLanguageManager;
import se.cambio.cds.openehr.util.OpenEHRConst;
import se.cambio.cds.openehr.view.panels.DVGenericPanel;

public class DVLocalCodedTextPanel extends DVGenericPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String _selectedCode = null;
    private JButton codedTextButton = null;
    private GDLEditor _controller = null;

    public DVLocalCodedTextPanel(GDLEditor controller){
	super(null, null, false, false);
	_controller = controller;
	this.setLayout(new FlowLayout(FlowLayout.LEFT));
	this.add(getCodedTextButton());
    }

    protected JButton getCodedTextButton(){
	if (codedTextButton==null){
	    codedTextButton = new JButton(OpenEHRLanguageManager.getMessage("SelectTerm"), ImageUtil.DV_CODED_TEXT_ICON);
	    codedTextButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    DialogGTCodeSelection dialog = 
			    new DialogGTCodeSelection(EditorManager.getActiveEditorWindow(), _controller);
		    dialog.setVisible(true);
		    if (dialog.getAnswer()){
			setSelection(dialog.getSelectedObject());
		    }
		}
	    });
	}
	return codedTextButton;
    }

    public void setDataValue(DataValue dataValue) {
	if (dataValue instanceof DvCodedText){
	    setSelection(((DvCodedText)dataValue).getDefiningCode().getCodeString());
	}else{
	    setSelection(null);
	}
    }

    private void setSelection(String selectedCode){
	_selectedCode = selectedCode;
	Term term = null;
	if (_selectedCode!=null){
	    term = _controller.getCurrentTermDefinition().getTerms().get(selectedCode);
	    if (term!=null){
		getCodedTextButton().setText(term.getText());
		getCodedTextButton().setToolTipText(term.getDescription());
	    }
	}
	if(term==null){
	    String label = OpenEHRLanguageManager.getMessage("SelectTerm");
	    getCodedTextButton().setText(label);
	    getCodedTextButton().setToolTipText(label);
	}
    }

    public DataValue getDataValue(){
	if (_selectedCode!=null){
	    Term term = _controller.getCurrentTermDefinition().getTerms().get(_selectedCode);
	    return new DvCodedText(term.getText(), OpenEHRConst.LOCAL, _selectedCode);
	}else{
	    return null;
	}
    }

    public Collection<JComponent> getJComponents() {
	Collection<JComponent> components = new ArrayList<JComponent>();
	components.add(getCodedTextButton());
	return components;
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