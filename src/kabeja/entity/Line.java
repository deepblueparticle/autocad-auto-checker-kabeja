package kabeja.entity;

import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.helpers.Point;

import domain.Logs;

public class Line {
    Point startPoint;
    Point endPoint;
    private double topY;
    private double bottomY;
    private double leftX;
    private double rightX;

    public Line(DXFLine entity) {
        // TODO Auto-generated constructor stub
        startPoint = entity.getStartPoint();
        endPoint = entity.getEndPoint();

        if (startPoint.getX() <= endPoint.getX()) {
            rightX = endPoint.getX();
            leftX = startPoint.getX();
        }
        else{
            leftX= endPoint.getX();
            rightX = startPoint.getX();
        }

        if (startPoint.getY() < endPoint.getY()) {
            topY = startPoint.getY();
            bottomY = endPoint.getY();
        }
        else{
            bottomY = startPoint.getY();
            topY = endPoint.getY();
        }

        Logs.printLog("Start point: " + startPoint);
        Logs.printLog("End point: " + endPoint);
        Logs.printLog("Start pointX = " + startPoint.getX() + ", Start pointY = " + startPoint.getY());
        Logs.printLog("End pointX = " + endPoint.getX() + ", End pointY = " + endPoint.getY());
        Logs.printLog("length: " + entity.getLength());

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
