package se.cambio.cds.formgen.view.dialog;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import se.cambio.cds.formgen.controller.FormGeneratorController;
import se.cambio.cds.formgen.controller.FormGeneratorViewer;
import se.cambio.cds.openehr.util.OpenEHRLanguageManager;



/**
 * @author iago.corbal
 *
 */

public class CDSFormGenDialog extends JDialog implements FormGeneratorViewer {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JScrollPane jScrollPane;
    private InfoDialog _infoDialog = null;


    /**
     * This is the default constructor
     */
    public CDSFormGenDialog(Window owner) {
	super(owner, OpenEHRLanguageManager.getMessage("CDSCalculator"));
	initialize();
    }

    /**
     * This method initializes this
     */
    private void initialize() {
	Dimension screenSize =
		Toolkit.getDefaultToolkit().getScreenSize();
	Dimension labelSize = this.getSize();
	this.setSize(new Dimension(500, 700));
	int locx = (screenSize.width/2) - (labelSize.width/2) - (this.getWidth()/2);
	int locy = (screenSize.height/2) - (labelSize.height/2) - (this.getHeight()/2);
	this.setLocation(locx,locy);
	this.setResizable(true);
	this.addWindowListener(new CloseAction());
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void setFormGeneratorController(FormGeneratorController controller){
	jScrollPane = null;
	getJScrollPane().setViewportView(controller.getCDSFormPanel());
	this.setContentPane(getJScrollPane());
	controller.setViewer(this);
	this.setTitle(controller.getName());
    }

    public class CloseAction extends WindowAdapter{

	public void windowOpened(WindowEvent e){
	}

	public void actionPerformed(ActionEvent e) {
	    dispose();
	}

	public void windowClosing(WindowEvent we) {
	    dispose();
	}
    }
    
    public void setBusy(String description, SwingWorker<?, ?> sw){
	getInfoDialog().setCurrentThread(sw);
	setBusy(description);
    }

    public void setBusy(String description){
	getInfoDialog().changeLoadingText(description);
	getInfoDialog().start();
    }

    public void changeBusyText(String description){
	getInfoDialog().changeLoadingText(description);
    }

    private InfoDialog getInfoDialog(){
	if (_infoDialog==null){
	    _infoDialog = new InfoDialog(this);
	}
	return _infoDialog;
    }

    public void setFree(){
	getInfoDialog().stop();
    }

    private JScrollPane getJScrollPane(){
	if (jScrollPane==null){
	    jScrollPane = new JScrollPane();
	}
	return jScrollPane;
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