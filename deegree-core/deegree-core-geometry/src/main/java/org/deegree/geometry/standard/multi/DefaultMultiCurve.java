//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 and
 lat/lon GmbH

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.geometry.standard.multi;

import java.util.List;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.multi.MultiCurve;
import org.deegree.geometry.precision.PrecisionModel;
import org.deegree.geometry.primitive.Curve;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

/**
 * Default implementation of {@link MultiCurve}.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class DefaultMultiCurve extends DefaultMultiGeometry<Curve> implements MultiCurve<Curve> {

    /**
     * Creates a new {@link DefaultMultiCurve} from the given parameters.
     * 
     * @param id
     *            identifier, may be null
     * @param crs
     *            coordinate reference system, may be null
     * @param pm
     *            precision model, may be null
     * @param members
     */
    public DefaultMultiCurve( String id, ICRS crs, PrecisionModel pm, List<Curve> members ) {
        super( id, crs, pm, members );
    }

    @Override
    public double getLength() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MultiGeometryType getMultiGeometryType() {
        return MultiGeometryType.MULTI_CURVE;
    }

    @Override
    protected MultiLineString buildJTSGeometry() {
        LineString[] jtsMembers = new LineString[size()];
        int i = 0;
        for ( Curve geometry : members ) {
            jtsMembers[i++] = (LineString) getAsDefaultGeometry( geometry ).getJTSGeometry();
        }
        return jtsFactory.createMultiLineString( jtsMembers );
    }
}
