package kabeja.entity;

import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.helpers.Point;

import domain.Logs;

public class Line {

    public Line(DXFLine entity) {
        // TODO Auto-generated constructor stub
        Point startPoint = entity.getStartPoint();
        Point endPoint = entity.getEndPoint();

        Logs.printLog("Start point: " + startPoint);
        Logs.printLog("End point: " + endPoint);
        Logs.printLog("LINE");
        Logs.printLog("X = " + startPoint.getX() + ", Y = " + startPoint.getY());
        Logs.printLog("length: " + entity.getLength());



    }

}
