package se.cambio.cds.model.recommendation.dao;

import java.sql.Connection;
import java.util.Collection;

import se.cambio.cds.model.recommendation.dto.RecommendationDTO;
import se.cambio.cds.util.exceptions.InternalErrorException;
import se.cambio.cds.util.exceptions.ModelException;

/**
 * @author iago.corbal
 */
public interface SQLRecommendationDAO {

    public RecommendationDTO search(Connection connection, Long idRecommendation)
	    throws InternalErrorException, ModelException;

    public Collection<RecommendationDTO> searchUnresolved(Connection connection)
	    throws InternalErrorException, ModelException;

    public RecommendationDTO add(Connection connection, RecommendationDTO RecommendationDTO)
	    throws InternalErrorException, ModelException;

    public void update(Connection connection, RecommendationDTO RecommendationDTO)
	    throws InternalErrorException, ModelException;
    
    public void remove(Connection connection, Long idRecommendation)
	    throws InternalErrorException, ModelException;
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