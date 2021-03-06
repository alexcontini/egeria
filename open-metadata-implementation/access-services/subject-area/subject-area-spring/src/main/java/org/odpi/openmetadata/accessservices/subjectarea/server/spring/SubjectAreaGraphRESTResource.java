/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.subjectarea.server.spring;


import org.odpi.openmetadata.accessservices.subjectarea.properties.enums.Status;
import org.odpi.openmetadata.accessservices.subjectarea.properties.enums.StatusFilter;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.LineType;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.NodeType;
import org.odpi.openmetadata.accessservices.subjectarea.responses.SubjectAreaOMASAPIResponse;
import org.odpi.openmetadata.accessservices.subjectarea.server.services.SubjectAreaGraphRESTServices;
import org.odpi.openmetadata.accessservices.subjectarea.server.services.SubjectAreaRESTServicesInstance;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The SubjectAreaRESTServicesInstance provides the org.odpi.openmetadata.accessservices.subjectarea.server-side implementation of the SubjectAreaDefinition Open Metadata
 * Assess Service (OMAS).  This interface provides term authoring interfaces for subject area experts.
 */
@RestController
@RequestMapping("/servers/{serverName}/open-metadata/access-services/subject-area")
public class SubjectAreaGraphRESTResource extends SubjectAreaRESTServicesInstance
{
    private SubjectAreaGraphRESTServices restAPI = new SubjectAreaGraphRESTServices();
    /**
     * Default constructor
     */
    public SubjectAreaGraphRESTResource() {
        //SubjectAreaRESTServicesInstance registers this omas.
    }

    /**
     * Get the graph of nodes and lines radiating out from a node.
     *
     * Return the nodes and lines that radiate out from the supplied node (identified by a GUID).
     * The results are scoped by types of Lines, types of nodes and classifications as well as level.
     *
     * @param serverName         serverName under which this request is performed, this is used in multi tenanting to identify the tenant
     * @param userId  userId under which the request is performed
     * @param guid the starting point of the query.
     * @param nodeFilterStr Comma separated list of node names to include in the query results.  Null means include
     *                          all entities found, irrespective of their type.
     * @param lineFilterStr comma separated list of line names to include in the query results.  Null means include
     *                                all lines found, irrespective of their type.
     * @param asOfTime Requests a historical query of the relationships for the entity.  Null means return the
     *                 present values.
     * @param statusFilter By default only active instances are returned. Specify ALL to see all instance in any status.
     * @param level the number of the lines (relationships) out from the starting node that the query will traverse to
     *              gather results. If not specified then it defaults to 3.
     * @return A graph of nodeTypes.
     *
     * <ul>
     * <li> UnrecognizedGUIDException            the supplied guid was not recognised</li>
     * <li> UserNotAuthorizedException           the requesting user is not authorized to issue this request.</li>
     * <li> MetadataServerUncontactableException not able to communicate with a Metadata respository service.</li>
     * <li> InvalidParameterException            one of the parameters is null or invalid.</li>
     * <li> FunctionNotSupportedException        Function not supported this indicates that a find was issued but the repository does not implement find functionality in some way.</li>
     * </ul>
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/nodes/{guid}")
    public  SubjectAreaOMASAPIResponse getGraph(@PathVariable String serverName,
                                                @PathVariable String userId,
                                                @PathVariable String guid,
                                                @RequestParam(value = "asOfTime", required=false) Date asOfTime,
                                                @RequestParam(value = "nodeFilter", required=false)String nodeFilterStr,
                                                @RequestParam(value = "lineFilter", required=false)String lineFilterStr,
                                                @RequestParam(value = "statusFilter", required=false)StatusFilter statusFilter,   // may need to extend this for controlled terms
                                                @RequestParam(value = "level", required=false) Integer level ) {

        return restAPI.getGraph(serverName,userId,guid,asOfTime,nodeFilterStr,lineFilterStr,statusFilter,level);
    }
}
