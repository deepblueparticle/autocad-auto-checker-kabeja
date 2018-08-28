package kabeja.entity;

import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.helpers.Point;

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

        System.out.println("center point: X,Y(" + centerPoint.getX() + ", " + centerPoint.getY() +")");
        System.out.println("radius: " + radius);
        System.out.println("topY: " + topY);
        System.out.println("bottomY: " + bottomY);
        System.out.println("leftX: " + leftX);
        System.out.println("rightX: " + rightX);
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
