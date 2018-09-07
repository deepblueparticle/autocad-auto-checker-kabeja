package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import domain.Logs;

public class MainController{

    public static void main(String[] args) throws FileNotFoundException {
        Logs.printLog("Starting project...");
        String sourcepath ="testdxf\\drawing-area-shape.dxf";
        InputStream source = new FileInputStream(sourcepath);
        Logs.printLog("--------------------");

        DrawingProcessor drawing = new DrawingProcessor(source);

    }
}