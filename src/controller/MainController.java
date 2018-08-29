package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.dxf.helpers.SplinePoint;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import kabeja.entity.Circle;
import kabeja.entity.Line;
import kabeja.entity.Polyline;
import kabeja.entity.SPLine;

public class MainController{

    public static void main(String[] args) throws FileNotFoundException {

        String sourcepath ="testdxf\\circle.dxf";
        InputStream source = new FileInputStream(sourcepath);

        Parser parser = ParserBuilder.createDefaultParser();
        try {

            parser.parse(source, DXFParser.DEFAULT_ENCODING);

//          ArrayList<CSG> parts = new ArrayList<CSG>();
            DXFDocument doc = parser.getDocument();
            Iterator<DXFLayer> layerIterable = doc.getDXFLayerIterator();

            while (layerIterable.hasNext()) {
                DXFLayer layer = (DXFLayer) layerIterable.next();

                System.out.println("LAYER: " + layer.getName());
                Iterator entityIterator = layer.getDXFEntityTypeIterator();

                if (entityIterator != null) {
                    while (entityIterator.hasNext()) {
                        String entityType = (String) entityIterator.next();
                        switch(entityType) {
                            case DXFConstants.ENTITY_TYPE_POLYLINE:
                                getPolylineList(layer, entityType);
                                break;
                            case DXFConstants.ENTITY_TYPE_LINE:
                                getLineList(layer, entityType);
                                break;
                            case DXFConstants.ENTITY_TYPE_SPLINE:
                                getSPLineList(layer, entityType);
                                break;
                            case DXFConstants.ENTITY_TYPE_CIRCLE:
                                getCircleList(layer, entityType);
                                break;
                            default:
                                System.out.println("Entity " + entityType + " not found!!!");
                                break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static ArrayList getPolylineList(DXFLayer layer, String entityType) {
        ArrayList<Polyline> polylineList = new ArrayList<Polyline>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                DXFPolyline entity = (DXFPolyline) e;
                for (int i = 0; i < entity.getVertexCount(); i++) {
                    DXFVertex vertex = entity.getVertex(i);
                    Point point = vertex.getPoint();
                    System.out.println("X = " + point.getX() + ", Y = " + point.getY() + ",Z = " +point.getZ());
                }
            }
        }
        return polylineList;
    }

    private static ArrayList getLineList(DXFLayer layer, String entityType) {
        ArrayList<Line> lineList = new ArrayList<Line>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                DXFLine entity = (DXFLine) e;
                Point point = entity.getStartPoint();
                System.out.println("LINE");
                System.out.println("X = " + point.getX() + ", Y = " + point.getY());
                System.out.println("length: " + entity.getLength());

                point = entity.getEndPoint();
                System.out.print("\n");
            }
        }
        return lineList;
    }

    private static ArrayList getSPLineList(DXFLayer layer, String entityType) {
        ArrayList<SPLine> spLineList = new ArrayList<SPLine>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                DXFSpline entity = (DXFSpline) e;
                Iterator splinePointIterator = entity.getSplinePointIterator();
                if(splinePointIterator!=null)
                    for (;splinePointIterator.hasNext();) {
                        SplinePoint point =(SplinePoint) splinePointIterator.next();
                    }
            }
        }
        return spLineList;
    }

    private static ArrayList getCircleList(DXFLayer layer, String entityType) {
        ArrayList<Circle> circleList = new ArrayList<Circle>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                System.out.println("ENTITY: " + entityType);
                DXFCircle entity = (DXFCircle) e;
                Circle circle = new Circle(entity);
            }
        }
        return circleList;
    }
}