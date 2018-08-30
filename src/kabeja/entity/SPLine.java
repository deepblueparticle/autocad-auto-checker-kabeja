package kabeja.entity;

import java.util.Iterator;

import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.helpers.SplinePoint;

public class SPLine {

    public SPLine(DXFSpline entity) {
        // TODO Auto-generated constructor stub
        Iterator splinePointIterator = entity.getSplinePointIterator();
        if(splinePointIterator!=null) {
            for (;splinePointIterator.hasNext();) {
                SplinePoint point = (SplinePoint) splinePointIterator.next();
            }
        }
    }

}
