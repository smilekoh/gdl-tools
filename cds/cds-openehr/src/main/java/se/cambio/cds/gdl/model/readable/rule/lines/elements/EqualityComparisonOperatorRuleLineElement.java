package se.cambio.cds.gdl.model.readable.rule.lines.elements;

import java.util.ArrayList;
import java.util.Collection;

import se.cambio.cds.gdl.model.expression.OperatorKind;
import se.cambio.cds.gdl.model.readable.rule.lines.RuleLine;
import se.cambio.cds.openehr.util.OpenEHRLanguageManager;


public class EqualityComparisonOperatorRuleLineElement extends RuleLineElementWithValue<OperatorKind> implements SingleSelectionRuleElement<OperatorKind>{

    private Collection<OperatorKind> _operators = null;

    public EqualityComparisonOperatorRuleLineElement(RuleLine ruleLine) {
	super(ruleLine, "??");
	_operators = new ArrayList<OperatorKind>();
	_operators.add(OperatorKind.EQUALITY);
	_operators.add(OperatorKind.INEQUAL);
    }

    public String getResolvedName(OperatorKind item) {
	if (item!=null){
	    return item.getSymbol();
	}else{
	    return null;
	}
    }

    public String getResolvedDescription(OperatorKind item) {
	if (item!=null){
	    return item.getName();
	}else{
	    return null;
	}
    }

    public Collection<OperatorKind> getItems() {
	return _operators;
    }

    public String toString(){
	if (getValue()!=null){
	    switch(getValue()){
	    case EQUALITY: return OpenEHRLanguageManager.getMessage("EqualsRLE");
	    case INEQUAL: return OpenEHRLanguageManager.getMessage("NotEqualsRLE");
	    default: return "??";
	    }
	}else{
	    return "??";
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