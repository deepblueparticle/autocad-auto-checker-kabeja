package kabeja.entity;

import java.util.Iterator;

import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.helpers.SplinePoint;

import domain.Logs;

public class SPLine {
    private double topY;
    private double bottomY;
    private double leftX;
    private double rightX;

    public SPLine(DXFSpline entity) {
        // TODO Auto-generated constructor stub
        Iterator splinePointIterator = entity.getSplinePointIterator();
        SplinePoint tempPoint = null;
        if(splinePointIterator!=null) {
            for (;splinePointIterator.hasNext();) {
                SplinePoint point = (SplinePoint) splinePointIterator.next();
                if (tempPoint != null) {
                    if (point.getX() <= tempPoint.getX()) {
                        rightX = point.getX();
                    }
                    else{
                        leftX= point.getX();
                    }

                    if (point.getY() <= tempPoint.getY()) {
                        bottomY = point.getX();
                    }
                    else{
                        topY= point.getX();
                    }
                }
                else {
                    tempPoint = point;
                }
            }
        }
    }

    public double getTopY() {
        return topY;
    }

    public double getBottomY() {
        return bottomY;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getRightX() {
        return rightX;
    }

}
