package kabeja.entity;

import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.helpers.Point;

import domain.Logs;

public class Circle {
    private DXFCircle dxfCircle;
    private Point centerPoint;
    private double radius;
    private double topY;
    private double bottomY;
    private double leftX;
    private double rightX;

    public Circle(DXFCircle dxfCircle) {
        this.dxfCircle = dxfCircle;
        centerPoint = dxfCircle.getCenterPoint();
        radius = dxfCircle.getRadius();
        topY = centerPoint.getY() - radius;
        bottomY = centerPoint.getY() + radius;
        leftX = centerPoint.getX() - radius;
        rightX = centerPoint.getX() + radius;

        Logs.printLog("center point: X,Y(" + centerPoint.getX() + ", " + centerPoint.getY() +")");
        Logs.printLog("radius: " + radius);
        Logs.printLog("topY: " + topY);
        Logs.printLog("bottomY: " + bottomY);
        Logs.printLog("leftX: " + leftX);
        Logs.printLog("rightX: " + rightX);
    }

    public DXFCircle getDxfCircle() {
        return dxfCircle;
    }

    public Point getCenterPoint() {
        return centerPoint;
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

    public double getRadius() {
        return this.radius;
    }


}
