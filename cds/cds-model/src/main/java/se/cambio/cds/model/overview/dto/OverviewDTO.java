package se.cambio.cds.model.overview.dto;

import java.io.Serializable;

public class OverviewDTO implements Serializable{

    private static final long serialVersionUID = 20120412L;
    private String idOverview;
    private String name;
    private String description;
    private String overviewSrc;

    public OverviewDTO(String idOverview, String name, String description, String overviewSrc) {
	super();
	this.idOverview = idOverview;
	this.name = name;
	this.description = description;
	this.overviewSrc = overviewSrc;
    }
    public String getIdOverview() {
	return idOverview;
    }
    public void setIdOverview(String idOverview) {
	this.idOverview = idOverview;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getOverviewSrc() {
	return overviewSrc;
    }
    public void setOverviewSrc(String guideSrc) {
	this.overviewSrc = guideSrc;
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