package controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLWPolyline;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFSpline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.dxf.helpers.Point;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import domain.Logs;
import kabeja.entity.Circle;
import kabeja.entity.LWPolyline;
import kabeja.entity.Polyline;
import kabeja.entity.SPLine;

public class DrawingProcessor {
    double maximumX;
    double minimumX;
    double maximumY;
    double minimumY;

    public DrawingProcessor(InputStream source) {
        this.maximumX = 0;
        this.minimumX = 0;
        this.maximumY = 0;
        this.minimumY = 0;

        Parser parser = ParserBuilder.createDefaultParser();
        try {

            parser.parse(source, DXFParser.DEFAULT_ENCODING);
            DXFDocument doc = parser.getDocument();
            Iterator<DXFLayer> layerIterable = doc.getDXFLayerIterator();

            while (layerIterable.hasNext()) {
                DXFLayer layer = (DXFLayer) layerIterable.next();

                Logs.printLog("LAYER: " + layer.getName());
                Iterator entityIterator = layer.getDXFEntityTypeIterator();

                Logs.printLog("--------------------");

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
                            case DXFConstants.ENTITY_TYPE_LWPOLYLINE:
                                Logs.printLog("Entity LWPOLYLINE in progress!!!");
                                getLWPolylineList(layer, entityType);
                                break;
                            default:
                                Logs.printLog("Entity " + entityType + " not found in the program!!!");
                                Logs.printLog("--------------------");
                                break;
                        }
                    }
                }
            }
            Logs.printLog("maximumX" + this.maximumX);
            Logs.printLog("minimumX" + this.minimumX);
            Logs.printLog("maximumY" + this.maximumY);
            Logs.printLog("minimumY" + this.minimumY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDrawing() {

    }

    private boolean isDrawing() {
        return false;

    }

    private void isTemplate() {

    }

    private ArrayList getPolylineList(DXFLayer layer, String entityType) {
        ArrayList<Polyline> polylineList = new ArrayList<Polyline>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                Logs.printLog("ENTITY: " + entityType);
                DXFPolyline entity = (DXFPolyline) e;
//                Polyline  polyline = new Polyline(entity);
                isDrawing(entity.getBounds().getMaximumX(),
                    entity.getBounds().getMinimumX(),
                    entity.getBounds().getMaximumY(),
                    entity.getBounds().getMinimumY());
            }
        }
        return polylineList;
    }

    private ArrayList getLineList(DXFLayer layer, String entityType) {
        ArrayList<DXFLine> lineList = new ArrayList<DXFLine>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                Logs.printLog("ENTITY: " + entityType);
                DXFLine entity = (DXFLine) e;
                //set initial position

                isDrawing(entity.getBounds().getMaximumX(),
                                entity.getBounds().getMinimumX(),
                                entity.getBounds().getMaximumY(),
                                entity.getBounds().getMinimumY());

                lineList.add(entity);
            }
        }
        return lineList;
    }

    private ArrayList getSPLineList(DXFLayer layer, String entityType) {
        ArrayList<SPLine> spLineList = new ArrayList<SPLine>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                Logs.printLog("ENTITY: " + entityType);
                DXFSpline entity = (DXFSpline) e;
//                SPLine line = new SPLine(entity);
                isDrawing(entity.getBounds().getMaximumX(),
                    entity.getBounds().getMinimumX(),
                    entity.getBounds().getMaximumY(),
                    entity.getBounds().getMinimumY());
            }
        }
        return spLineList;
    }

    private ArrayList getCircleList(DXFLayer layer, String entityType) {
        ArrayList<Circle> circleList = new ArrayList<Circle>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                Logs.printLog("ENTITY: " + entityType);
                DXFCircle entity = (DXFCircle) e;
//                Circle circle = new Circle(entity);
                isDrawing(entity.getBounds().getMaximumX(),
                    entity.getBounds().getMinimumX(),
                    entity.getBounds().getMaximumY(),
                    entity.getBounds().getMinimumY());
            }
        }
        return circleList;
    }

    private ArrayList getLWPolylineList(DXFLayer layer, String entityType) {
        ArrayList<DXFLWPolyline> lwPolylineList = new ArrayList<DXFLWPolyline>();
        List entities = layer.getDXFEntities(entityType);
        if (entities != null) {
            for (Object e : entities) {
                Logs.printLog("ENTITY: " + entityType);
                DXFLWPolyline entity = (DXFLWPolyline) e;
//                LWPolyline circle = new LWPolyline(entity);
                boolean isDrawing = isDrawing(entity.getBounds().getMaximumX(),
                    entity.getBounds().getMinimumX(),
                    entity.getBounds().getMaximumY(),
                    entity.getBounds().getMinimumY());

                if(isDrawing) {
                    lwPolylineList.add(entity);
                }
            }
        }
        return lwPolylineList;
    }

    private boolean isDrawing(double maximumX,
                                double minimumX,
                                double maximumY,
                                double minimumY) {
        if(this.maximumX == 0 && this.minimumX == 0 && this.maximumY == 0 && this.minimumY == 0) {
            this.maximumX = maximumX;
            this.minimumX = minimumX;
            this.maximumY = maximumY;
            this.minimumY = minimumY;
            return false;
        }
        else if(maximumX > this.maximumX) {
            this.maximumX = maximumX;
            return false;
        }
        else if(minimumX < this.minimumX) {
            this.minimumX = minimumX;
            return false;
        }
        else if(maximumY > this.maximumY) {
            this.maximumY = maximumY;
            return false;
        }
        else if(minimumY < this.minimumY) {
            this.minimumY = minimumY;
            return false;
        }
        return true;
    }
}
