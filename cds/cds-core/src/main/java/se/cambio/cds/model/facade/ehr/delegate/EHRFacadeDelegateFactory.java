package se.cambio.cds.model.facade.ehr.delegate;

import se.cambio.cds.util.exceptions.InternalErrorException;
import se.cambio.cds.util.misc.ConfigurationParametersManager;
/**
 * @author iago.corbal
 *
 */
public class EHRFacadeDelegateFactory {

    private static String DELEGATE_CLASS_PATIENT_DATA = "EHRFacadeDelegate/Class";

    private EHRFacadeDelegateFactory() {
    }

    private static Class<?> getDelegateClass() throws InternalErrorException {
	Class<?> theClass = null;
	try {
	    String delegateClassName = 
		    ConfigurationParametersManager.getParameter(DELEGATE_CLASS_PATIENT_DATA);
	    theClass = Class.forName(delegateClassName);
	} catch (Exception e) {
	    new InternalErrorException(e);
	}
	return theClass;
    }

    public static EHRFacadeDelegate getDelegate()
	    throws InternalErrorException {
	try {
	    return (EHRFacadeDelegate)getDelegateClass().newInstance();
	} catch (InstantiationException e) {
	    throw new InternalErrorException(e);
	} catch (IllegalAccessException e) {
	    throw new InternalErrorException(e);
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