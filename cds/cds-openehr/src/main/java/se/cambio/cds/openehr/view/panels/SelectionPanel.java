/*
 * Created on 26-oct-2006
 *


 */
package se.cambio.cds.openehr.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;

import se.cambio.cds.openehr.util.ImageUtil;
import se.cambio.cds.openehr.util.OpenEHRLanguageManager;
import se.cambio.cds.openehr.view.trees.SelectableNode;
import se.cambio.cds.openehr.view.trees.SelectionTree;
import se.cambio.cds.openehr.view.util.NodeConversor;


/**
 * @author icorram
 *


 */
public class SelectionPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -7877248591456367314L;
    private SelectionTree jTree = null;
    private JScrollPane jScrollPane = null;
    private JPanel jPanelFiltro;
    private SelectableNode<?> _nodoRaiz = null;
    private TextWithCleanButtonPanel textWithCleanButtonPanel = null;
    private JButton expandButton = null;
    private JButton contractButton = null;
    private boolean _useEditor = true;
    private boolean _bigList = false;
    private JButton searchButton;

    /**
     * This is the default constructor
     */
    public SelectionPanel(SelectableNode<?> nodoRaiz) {
	super();
	_nodoRaiz = nodoRaiz;
	initialize();
    }

    public SelectionPanel(SelectableNode<?> nodoRaiz, boolean useEditor) {
	super();
	_nodoRaiz = nodoRaiz;
	_useEditor = useEditor;
	initialize();
    }

    public SelectionPanel(SelectableNode<?> nodoRaiz, boolean useEditor, boolean bigList) {
	super();
	_nodoRaiz = nodoRaiz;
	_useEditor = useEditor;
	_bigList = bigList;
	initialize();
    }
    /**
     * This method initializes this
     * 

     */
    private  void initialize() {
	this.setLayout(new BorderLayout());
	this.add(getFilterPanel(), BorderLayout.NORTH);
	this.add(getJScrollPane(), BorderLayout.CENTER);
    }

    public SelectableNode<?> getNode(){
	return _nodoRaiz;
    }

    public JPanel getFilterPanel() {
	if (jPanelFiltro == null) {
	    jPanelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    jPanelFiltro.add(new JLabel(OpenEHRLanguageManager.getMessage("Filter")+" :"));
	    jPanelFiltro.add(getTextWithCleanButtonPanel());
	    if (_bigList){
		jPanelFiltro.add(getSearchButton());
	    }
	    jPanelFiltro.add(getExpandButton());
	    jPanelFiltro.add(getContractButton());
	}
	return jPanelFiltro;
    }

    public TextWithCleanButtonPanel getTextWithCleanButtonPanel(){
	if (textWithCleanButtonPanel == null){
	    textWithCleanButtonPanel = new TextWithCleanButtonPanel();
	    textWithCleanButtonPanel.setPreferredSize(new Dimension(100,20));
	    if (_bigList){
		textWithCleanButtonPanel.getJButton().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			filter();
		    }
		});
		textWithCleanButtonPanel.getJTextField().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			filter();
		    }
		});
	    }else{
		textWithCleanButtonPanel.addKeyListener(new FiltroSeleccionKeyListener());
	    }
	}
	return textWithCleanButtonPanel;
    }

    private JButton getSearchButton(){
	if (searchButton==null){
	    searchButton = new JButton();
	    searchButton.setIcon(ImageUtil.SEARCH_ICON);
	    searchButton.setToolTipText("Search");
	    searchButton.setBackground(null);
	    searchButton.setContentAreaFilled(false);
	    searchButton.setBorderPainted(false);
	    searchButton.setPreferredSize(new Dimension(16, 16));
	    searchButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    filter();
		}
	    });
	}
	return searchButton;
    }


    public JButton getExpandButton(){
	if (expandButton == null){
	    expandButton = new JButton(new ExpandTreeAction());
	    expandButton.setIcon(ImageUtil.EXPAND_ICON);
	    expandButton.setToolTipText(OpenEHRLanguageManager.getMessage("ExpandTreeD"));
	    expandButton.setBackground(null);
	    expandButton.setContentAreaFilled(false);
	    expandButton.setBorderPainted(false);
	    expandButton.setPreferredSize(new Dimension(16, 16));
	}
	return expandButton;
    }

    private class ExpandTreeAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public void actionPerformed(ActionEvent e) {
	    getJTree().expand(_nodoRaiz);
	}
    }

    public JButton getContractButton(){
	if (contractButton == null){
	    contractButton = new JButton(new ContractTreeAction());
	    contractButton.setIcon(ImageUtil.CONTRACT_ICON);
	    contractButton.setToolTipText(OpenEHRLanguageManager.getMessage("ContractTreeD"));
	    contractButton.setBackground(null);
	    contractButton.setContentAreaFilled(false);
	    contractButton.setBorderPainted(false);
	    contractButton.setPreferredSize(new Dimension(16, 16));
	}
	return contractButton;
    }
    private class ContractTreeAction extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent e) {
	    getJTree().collapse(_nodoRaiz);
	}
    }
    /**
     * This method initializes jTable	
     * 	
     * @return javax.swing.JTable	
     */    
    public SelectionTree getJTree() {
	if (jTree == null) {
	    jTree = new SelectionTree(_nodoRaiz, _useEditor);
	}
	return jTree;
    }
    /**
     * This method initializes jScrollPane	
     * 	
     * @return javax.swing.JScrollPane	
     */    
    public JScrollPane getJScrollPane() {
	if (jScrollPane == null) {
	    jScrollPane = new JScrollPane();
	    jScrollPane.setViewportView(getJTree());
	}
	return jScrollPane;
    }

    public void changeRootNode(SelectableNode<?> node){
	_nodoRaiz = node;

	//Guardamos los mouse listeners
	ArrayList<MouseListener> mListeners = getJTree().getExtraMouseListeners();
	//Guardamos los tree selection listeners
	ArrayList<TreeSelectionListener> tsListeners = getJTree().getExtraTreeSelectionListeners();
	jTree = null;
	getJScrollPane().remove(getJTree());
	for (MouseListener listener : mListeners) {
	    getJTree().addExtraMouseListener(listener);
	}
	for (TreeSelectionListener listener : tsListeners) {
	    getJTree().addExtraTreeSelectionListener(listener);
	}
	getJScrollPane().setViewportView(getJTree());
	getJScrollPane().revalidate();
	getJScrollPane().repaint();
    }

    public class FiltroSeleccionKeyListener implements KeyListener{

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	    filter();
	}

	public void keyTyped(KeyEvent e) {

	}
    }

    private void filter(){
	NodeConversor.setAllVisible(_nodoRaiz);
	NodeConversor.filterByText(
		_nodoRaiz, getTextWithCleanButtonPanel().getJTextField().getText());
	changeRootNode(_nodoRaiz);
	getJTree().expand(_nodoRaiz);
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
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