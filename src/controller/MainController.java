package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
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

public class MainController{
	
	public static void main(String[] args) throws FileNotFoundException {

		String sourcepath ="testdxf\\rectangle-exploded.dxf";
		InputStream source = new FileInputStream(sourcepath);
		
		ArrayList<Vector3D> points = new ArrayList<Vector3D>();
		Parser parser = ParserBuilder.createDefaultParser();
		try {

			parser.parse(source, DXFParser.DEFAULT_ENCODING);
			
//			ArrayList<CSG> parts = new ArrayList<CSG>(s);
			DXFDocument doc = parser.getDocument();
			Iterator<DXFLayer> layerIterable = doc.getDXFLayerIterator();
			double extrudeDistance = 5;
			
			while (layerIterable.hasNext()) {
				DXFLayer layer = (DXFLayer) layerIterable.next();
				
				System.out.println(layer.getLineType());
				Iterator entityIterator = layer.getDXFEntityTypeIterator();
				
				if (entityIterator != null) {
					while (entityIterator.hasNext()) {
						String entityType = (String) entityIterator.next();						
						
						if (entityType.contentEquals(DXFConstants.ENTITY_TYPE_POLYLINE)) {
							List plines = layer.getDXFEntities(entityType);
							if (plines != null) {
								for (Object p : plines) {
									DXFPolyline pline = (DXFPolyline) p;
									for (int i = 0; i < pline.getVertexCount(); i++) {
										DXFVertex vertex = pline.getVertex(i);
										Point point = vertex.getPoint();
										System.out.println("X = " + point.getX() + ", Y = " + point.getY() + ",Z = " +point.getZ());
//										Vector3D center=new Vector3D(point.getX(), point.getY(), point.getZ());
//										points.add(center);
//										BowlerStudioController.addCsg(new Cube(1,1,1).setCenter(center).toCSG());
									}
								}
							}
						}
						else if (entityType.contentEquals(DXFConstants.ENTITY_TYPE_LINE)) {
							// get all polylines from the layer
							List plines = layer.getDXFEntities(entityType);
							if (plines != null) {
								for (Object p : plines) {
									DXFLine pline = (DXFLine) p;
									
									Point point = pline.getStartPoint();
									System.out.println("LINE");
									System.out.println("X = " + point.getX() + ", Y = " + point.getY());
									System.out.println("length: " + pline.getLength());
									
									points.add(new Vector3D(point.getX(), point.getY(), point.getZ()));
									point = pline.getEndPoint();
									Vector2D vector=new Vector2D(point.getX(), point.getY());
//									points.add(vector);
									System.out.println("vector point:" + vector);
									//BowlerStudioController.addCsg(new Cube(1,1,1).setCenter(center).toCSG());
									System.out.print("\n");
								}
							}
//							if(points.size()>0)
//								parts.add(Extrude.points(new Vector3D(0, 0, extrudeDistance), points));
							points.clear();
						}
						else if (entityType.contentEquals(DXFConstants.ENTITY_TYPE_SPLINE)) {
							// get all polylines from the layer
							List plines = layer.getDXFEntities(entityType);
							if (plines != null) {
								for (Object p : plines) {
									DXFSpline pline = (DXFSpline) p;
									Iterator splinePointIterator = pline.getSplinePointIterator();
									if(splinePointIterator!=null)
										for (;splinePointIterator.hasNext();) {
											SplinePoint point =(SplinePoint) splinePointIterator.next();
											Vector3D vector = new Vector3D(point.getX(), point.getY(), point.getZ());
											points.add(vector);
											System.out.println("ENTITY_TYPE_SPLINE Point at " + vector);
											//BowlerStudioController.addCsg(new Cube(1,1,1).setCenter(center).toCSG());
										}
								}
							}
							System.out.println("Done with Object ");
//							if(points.size()>0)
//								parts.add(extrude(new Vector3D(0, 0, extrudeDistance), points));
							points.clear();
						}
						else if (entityType.contentEquals(DXFConstants.ENTITY_TYPE_CIRCLE)) {
							List plines = layer.getDXFEntities(entityType);
							if (plines != null) {
								for (Object p : plines) {
									System.out.println("CIRCLE");
//									System.out.println("X = " + point.getX() + ", Y = " + point.getY());
//									System.out.println("length: " + pline.getLength());
								}
							}
						}
					}
				}
			}
			
			
//			System.out.println(doc.getDXFLayer(""));
//			System.out.println(doc.getDXFLayer(""));
//			for(int i = 0; i < doc.getDXFLayerIterator(); i++) {
//				
//			}
//			DXFLayer layer = doc.getDXFLayer("");

			//get all polylines from the layer
//			List plines = layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE);
//			System.out.println(plines);
			//work with the first polyline
//			DXFPolyline pline = plines;
//			for (int i = 0; i < pline.getVertexCount(); i++) {
//				    
//			     DXFVertex vertex = pline.getVertex(i);
//			                    
//			     //do something like collect the data and
//			     //build a mesh for a FEM system
//		    }

		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	
	public void  read(InputStream in, String layerid) {

   
	}

	public void doSomething(DXFPolyline pline) {

	    //iterate over all vertex of the polyline
	    for (int i = 0; i < pline.getVertexCount(); i++) {
			    
	     DXFVertex vertex = pline.getVertex(i);
	                    
	     //do something like collect the data and
	     //build a mesh for a FEM system
	    }
	}
}