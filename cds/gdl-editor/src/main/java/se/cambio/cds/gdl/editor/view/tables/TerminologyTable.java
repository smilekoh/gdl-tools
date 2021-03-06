package se.cambio.cds.gdl.editor.view.tables;

import java.awt.Component;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.jxpath.JXPathContext;

import se.cambio.cds.gdl.editor.util.LanguageManager;

public class TerminologyTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JXPathContext _context = null;

    public TerminologyTable(JXPathContext context){
	_context = context;
	this.setModel(new TerminologyTableModel());
	Vector<String> columnIdentifiers = new Vector<String>();
	columnIdentifiers.add(LanguageManager.getMessage("Code"));
	columnIdentifiers.add(LanguageManager.getMessage("Text"));
	columnIdentifiers.add(LanguageManager.getMessage("Description"));
	getTerminologyTableModel().setColumnIdentifiers(columnIdentifiers);
	this.getColumnModel().getColumn(0).setMaxWidth(80);
	OntologyTableCellEditor cellEditor = 
		new OntologyTableCellEditor();
	this.getColumnModel().getColumn(1).setCellEditor(cellEditor);
	this.getColumnModel().getColumn(2).setCellEditor(cellEditor);
    }

    public TerminologyTableModel getTerminologyTableModel(){
	return (TerminologyTableModel)getModel();
    }


    public boolean isCellEditable(int row, int column) {
	if (column==0){
	    return false;
	}else{
	    return true;
	}
    }

    public class TerminologyTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
    }

    class OntologyTableCellEditor extends DefaultCellEditor{

	private int _row;
	private int _column;

	OntologyTableCellEditor() {
	    super(new JTextField());
	    this.addCellEditorListener(new CellEditorListener() {
		public void editingStopped(ChangeEvent e) {
		    Object obj = e.getSource();
		    if (obj instanceof OntologyTableCellEditor){
			updateResults((OntologyTableCellEditor)obj);
		    }
		}

		public void editingCanceled(ChangeEvent e) {
		}

		public void updateResults(OntologyTableCellEditor otce){
		    String value = (String)otce.getCellEditorValue();
		    int row = otce.getRow();
		    int column = otce.getColumn();
		    String gtCode = (String)getTerminologyTableModel().getValueAt(row, 0);
		    String attribute = column==1?"text":"description";
		    _context.setValue(gtCode+"/"+attribute, value);
		}
	    });
	}

	public int getRow(){
	    return _row;
	}

	public int getColumn(){
	    return _column;
	}
	public Component getTableCellEditorComponent(JTable table, Object value,
		boolean isSelected,
		int row, int column) {
	    _row = row;
	    _column = column;
	    return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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