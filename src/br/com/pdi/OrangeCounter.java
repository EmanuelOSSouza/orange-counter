/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdi;

import br.com.pdi.util.FindOrangeAndDrawCircles;
import br.com.pdi.util.FindOrangeAndDraw;
import br.com.pdi.util.OrangeDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Emanuel
 */

public class OrangeCounter { 
    
    public void run() {
     
        //Load image
        String filename =  "src//resource//image1.jfif";
        Mat src = Imgcodecs.imread(filename);
        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(1);
        }
        // Instances and methods
        Mat srcOriginal = src.clone();
        Mat srcDraw = src.clone();
        Mat srcCircles = src.clone();
        Mat segmentation = new OrangeDetection().orangeDetection(src);
        Mat segmented = segmentation.clone();
        Mat orangeFound = new FindOrangeAndDraw().findOrangeAndDraw(segmentation, srcDraw);
        Mat output = new FindOrangeAndDrawCircles().findOrangeAndDrawCircles(segmented, srcCircles);
        //Show images
        HighGui.imshow("Original", srcOriginal);
        HighGui.imshow("Segmentation",  segmentation);
        HighGui.imshow("Oranges detected", orangeFound);
        HighGui.imshow("Output", output);
        HighGui.waitKey();
        System.exit(0);
    }
    
    
    public static void main(String[] args) {
       
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Use in case whith you prefere to use VM Options arguments: -Djava.library.path
        System.load(System.getProperty("user.dir")+"\\lib\\"+Core.NATIVE_LIBRARY_NAME+".dll");
        new OrangeCounter().run();
        }
    }
    

