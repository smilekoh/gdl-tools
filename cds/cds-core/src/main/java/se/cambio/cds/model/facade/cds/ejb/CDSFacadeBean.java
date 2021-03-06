
package se.cambio.cds.model.facade.cds.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.Stateless;

import se.cambio.cds.controller.guide.ElementInstanceCollection;
import se.cambio.cds.controller.guide.GuideManager;
import se.cambio.cds.model.facade.execution.vo.ElementInstance;
import se.cambio.cds.model.facade.execution.vo.ExecutionMode;
import se.cambio.cds.model.facade.execution.vo.RuleExecutionResult;
import se.cambio.cds.model.guide.dto.GuideDTO;
import se.cambio.cds.util.exceptions.GuideNotFoundException;
import se.cambio.cds.util.exceptions.InternalErrorException;
import se.cambio.cds.util.exceptions.PatientNotFoundException;

/**
 * @author icorram
 *
 */
@Stateless(mappedName = "ejb/CDSFacadeBean")
public class CDSFacadeBean implements CDSFacade {

    public RuleExecutionResult monitor(String idPatient, ElementInstanceCollection elementInstanceCollection) 
	    throws InternalErrorException, PatientNotFoundException{
	return new RuleExecutionResult(null, null, null);
    }

    public RuleExecutionResult monitor(
	    String idPatient, 
	    ElementInstanceCollection elementInstanceCollection,
	    Calendar date, 
	    ExecutionMode executionMode) 
		    throws InternalErrorException, PatientNotFoundException{
	return null;
    }

    public Collection<ElementInstance> getEHRElements(String idGuide)
	    throws InternalErrorException, GuideNotFoundException{
	return null;
    }

    public Collection<ElementInstance> getAllEHRElements()
	    throws InternalErrorException{
	return null;
    }
    
    public Collection<ElementInstance> getElements(String idGuide)
	    throws InternalErrorException, GuideNotFoundException{
	return null;
    }

    public Collection<ElementInstance> getAllElements()
	    throws InternalErrorException{
	return null;
    }

    public RuleExecutionResult monitor(String idPatient,
	    Collection<GuideDTO> guides,
	    ElementInstanceCollection elementInstanceCollection, Calendar date)
	    throws InternalErrorException, PatientNotFoundException,
	    RemoteException {
	// TODO Auto-generated method stub
	return null;
    }

    public GuideManager getGuideManager() throws InternalErrorException,
	    RemoteException {
	// TODO Auto-generated method stub
	return null;
    }

    public void setGuideManager(GuideManager guideManager)
	    throws InternalErrorException {
	// TODO Auto-generated method stub
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