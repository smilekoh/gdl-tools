/*
 * Created on 30-ago-2006
 *


 */
package se.cambio.cds.gdl.editor.view.menubar;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;

import se.cambio.cds.gdl.editor.util.LanguageManager;
import se.cambio.cds.openehr.util.ExceptionHandler;
import se.cambio.cds.util.UserConfigurationManager;



public class ViewReleaseNotesMenuAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -3561842193285119707L;

    public ViewReleaseNotesMenuAction(){
	super();
	putValue(NAME, LanguageManager.getMessage("ReleaseNotes"));
	putValue(SMALL_ICON, null);
	putValue(SHORT_DESCRIPTION, LanguageManager.getMessage("ReleaseNotesD"));
	putValue(LONG_DESCRIPTION, LanguageManager.getMessage("ReleaseNotesD"));
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
	try {
	    String path = 
		    UserConfigurationManager.getDocumentsFolder().getCanonicalPath()+
		    File.separator+
		    "release-notes.txt";
	    Desktop.getDesktop().open(new File(path));
	} catch (IOException e1) {
	    ExceptionHandler.handle(e1);
	}
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