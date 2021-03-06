/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ISMTransition"
 * keywords:    "composition"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/ISMTransition.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.*;

/**
 * Model of a transition in the Instruction State machine, caused by a careflow step.
 * The attributes document the careflow step as well as the ISM transition.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class ISMTransition extends RMObject {

	/**
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param parent
	 */
	@FullConstructor
	public ISMTransition(
            @Attribute(name = "currentState", required = true) DvCodedText currentState,
            @Attribute(name = "transition") DvCodedText transition,
            @Attribute(name = "careflowStep") DvCodedText careflowStep,
            @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService)  {
		if (currentState == null) {
			throw new IllegalArgumentException("null currentState");
		}
		if (terminologyService == null) {
			throw new IllegalArgumentException("null terminologyService");
		}
		if (!terminologyService.terminology(TerminologyService.OPENEHR)
                        .codesForGroupName(OpenEHRTerminologyGroupIdentifiers
                        		.INSTRUCTION_STATES.getValue(), "en")
                        .contains(currentState.getDefiningCode())) {
			throw new IllegalArgumentException("unknown currentState:" + currentState);
		}
		if (transition != null && !terminologyService.terminology(TerminologyService.OPENEHR)
                        .codesForGroupName(OpenEHRTerminologyGroupIdentifiers
                        		.INSTRUCTION_TRANSITIONS.getValue(), "en")
                        .contains(transition.getDefiningCode())) {
			throw new IllegalArgumentException("unknown transition:" + transition);
		}
		this.currentState = currentState;
		this.transition = transition;
		this.careflowStep = careflowStep;		
	}

	/**
	 * The step in the careflow process which occurred as part of generating 
	 * this action, e.g. "dispense", "start_administration". This attribute 
	 * represents the clinical label for the activity, as opposed to 
	 * currentState which represents the state machine computable form.
	 * 
	 * @return careflowStep
	 */
	public DvCodedText getCareflowStep() {
		return careflowStep;
	}

	/**
	 * The ISM current state. Coded by openEHR terminology group "ISM states"
	 * 
	 * @return currentState
	 */
	public DvCodedText getCurrentState() {
		return currentState;
	}

	/**
	 * The ISM transition which occurred to arrive in the currentState. Coded by 
	 * openEHR terminology group "ISM transitions"
	 * 
	 * @return transition
	 */
	public DvCodedText getTransition() {
		return transition;
	}

	//POJO start
	ISMTransition() {
	}
	
	void setCareflowStep(DvCodedText careflowStep) {
		this.careflowStep = careflowStep;
	}

	void setCurrentState(DvCodedText currentState) {
		this.currentState = currentState;
	}

	void setTransition(DvCodedText transition) {
		this.transition = transition;
	}
	//POJO end

	/* fields */
	private DvCodedText currentState;
	private DvCodedText transition;
	private DvCodedText careflowStep;

}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is ISMTransition.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
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