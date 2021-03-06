package se.cambio.cds.openehr.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvQuantity;

import se.cambio.cds.openehr.util.CDSNumberFormat;
import se.cambio.cds.openehr.util.DVUIUtil;
import se.cambio.cds.openehr.util.ExceptionHandler;
import se.cambio.cds.openehr.view.applicationobjects.Units;

public class DVQuantityPanel extends DVGenericPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JFormattedTextField magnitudeTextField;
    private JComboBox unitsComboBox;
    private boolean _enableUnits = false;

    public DVQuantityPanel(String idElement, String idTemplate, boolean allowNull, boolean enableUnits, boolean requestFocus){
	super(idElement, idTemplate, allowNull, requestFocus);
	_enableUnits = enableUnits;
	this.setLayout(new FlowLayout(FlowLayout.LEFT));
	this.add(getMagnitudeTextField());
	this.add(Box.createHorizontalStrut(5));
	this.add(getUnitsComboBox());
    }

    protected JFormattedTextField getMagnitudeTextField(){
	if (magnitudeTextField==null){
	    DecimalFormat format = CDSNumberFormat.getDecimalFormat();
	    magnitudeTextField = new JFormattedTextField(format){  
		private static final long serialVersionUID = 1L;
		@Override  
		protected void processFocusEvent(final FocusEvent e) {  
		    if (e.isTemporary()) {  
			return;  
		    }  

		    if (e.getID() == FocusEvent.FOCUS_LOST) {  
			if (getText() == null || getText().isEmpty()) {  
			    setValue(null);  
			}  
		    }  
		    super.processFocusEvent(e);  
		}
	    };
	    magnitudeTextField.setPreferredSize(new Dimension(100,18));
	    if (isRequestFocus()){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			magnitudeTextField.requestFocus();
		    }
		});
	    }
	}
	return magnitudeTextField;
    }


    protected JComboBox getUnitsComboBox(){
	if (unitsComboBox==null){
	    unitsComboBox = new JComboBox();
	    for (String unit : Units.getUnits(getIdTemplate(), getIdElement())) {
		unitsComboBox.addItem(unit);
	    }
	    if (!_enableUnits){
		unitsComboBox.setFocusable(false);
		unitsComboBox.setUI(new DisabledComboUI());
		ComboBoxEditor editor = unitsComboBox.getEditor();
		if (editor!=null && editor.getEditorComponent() instanceof JTextComponent){
		    ((JTextComponent)editor.getEditorComponent()).setEditable(false);
		}
	    }
	}
	return unitsComboBox;
    }

    public class DisabledComboUI extends BasicComboBoxUI{
	protected ComboPopup createPopup()
	{
	    BasicComboPopup popup = (BasicComboPopup)super.createPopup();
	    popup.removeAll();
	    return popup;
	}

	public void configureArrowButton() {
	    super.configureArrowButton();
	    if ( arrowButton != null ) {
		arrowButton.setVisible(false);
	    }
	}

    }

    private int getPrecision(){
	String valueStr = getMagnitudeTextField().getText().trim();
	int index = valueStr.lastIndexOf(".");
	if (index<=0){
	    return 0;
	}else{
	    return valueStr.length()-(index+1);
	}
    }

    public void setDataValue(DataValue dataValue) {
	String magnitude = "";
	String units = "";
	if (dataValue instanceof DvQuantity){
	    DvQuantity dvQuantity = (DvQuantity)dataValue;
	    magnitude = DVUIUtil.roundToStr(dvQuantity.getMagnitude(), dvQuantity.getPrecision());
	    units = dvQuantity.getUnits();
	}
	getMagnitudeTextField().setText(magnitude);
	getUnitsComboBox().setSelectedItem(units);
    }

    public DataValue getDataValue(){
	if (getMagnitudeTextField().getText().isEmpty()){
	    return null;
	}else{
	    try {
		return new DvQuantity(
			getUnitsComboBox().getSelectedItem().toString(), 
			CDSNumberFormat.getDecimalFormat().parse(getMagnitudeTextField().getText()).doubleValue(), 
			getPrecision());
	    } catch (ParseException e) {
		ExceptionHandler.handle(e);
		return null;
	    }
	}
    }

    public Collection<JComponent> getJComponents() {
	Collection<JComponent> components = new ArrayList<JComponent>();
	components.add(getMagnitudeTextField());
	components.add(getUnitsComboBox());
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