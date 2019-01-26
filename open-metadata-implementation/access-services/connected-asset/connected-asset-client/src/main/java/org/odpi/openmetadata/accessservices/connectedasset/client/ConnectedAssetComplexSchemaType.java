/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.connectedasset.client;

import org.odpi.openmetadata.frameworks.connectors.properties.AssetComplexSchemaType;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ComplexSchemaType;

public class ConnectedAssetComplexSchemaType extends AssetComplexSchemaType
{
    /**
     * Typical constructor creates an AssetSchema object primed with information to retrieve an asset's schema
     * information.
     *
     * @param serverName  name of the server.
     * @param userId user id to use on server calls.
     * @param omasServerURL url root of the server to use.
     * @param parentAsset descriptor of parent asset.
     * @param maxCacheSize maximum number of elements that should be retrieved from the property server and
     *                     cached in the element list at any one time.  If a number less than one is supplied, 1 is used.
     * @param schemaBean details of the schema object.
     */
    ConnectedAssetComplexSchemaType(String                serverName,
                                    String                userId,
                                    String                omasServerURL,
                                    ConnectedAssetUniverse parentAsset,
                                    int                   maxCacheSize,
                                    ComplexSchemaType     schemaBean)
    {
        super(parentAsset);

        this.setBean(schemaBean);

        if (schemaBean != null)
        {
            int  schemaAttributeCount = schemaBean.getAttributeCount();

            if (schemaAttributeCount > 0)
            {
                super.schemaAttributes = new ConnectedAssetSchemaAttributes(serverName,
                                                                            userId,
                                                                            omasServerURL,
                                                                            schemaBean.getGUID(),
                                                                            parentAsset,
                                                                            maxCacheSize,
                                                                            schemaAttributeCount);

            }
        }
    }
}
