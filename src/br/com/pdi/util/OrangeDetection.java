/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdi.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Emanuel
 */
public class OrangeDetection {
 
    public int hueStart = 5; // range: 0 - 180
    public int hueStop = 15; // range: 0 - 180
    public int saturationStart = 100; // range: 0 - 255
    public int saturationStop = 255; // range: 0 - 255
    public int valueStart = 70; // range: 0 - 255
    public int valueStop = 255; // range: 0 - 255
    
    public Mat orangeDetection(Mat src){
        // Mat Objects
        Mat blurredImage = new Mat();
        Mat hsvImage = new Mat();
        Mat mask = new Mat();
        Mat morphOutput = new Mat();
        Mat image = new Mat();
        //Blur image - Desfocar
        Imgproc.blur(src, blurredImage, new Size(13, 13));
        // Convert to HSV
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
        // Select HSV threshold range  
        Scalar minValues = new Scalar(hueStart, saturationStart, valueStart);
        Scalar maxValues = new Scalar(hueStop, saturationStop, valueStop);
        // show the current selected HSV range
        String valuesToPrint = "Hue range: " + minValues.val[0] + "-" + maxValues.val[0]
        + "\tSaturation range: " + minValues.val[1] + "-" + maxValues.val[1] + "\tValue range: "
        + minValues.val[2] + "-" + maxValues.val[2];
         System.out.println(valuesToPrint);
        // threshold HSV image to select oranges
        Core.inRange(hsvImage, minValues, maxValues, mask);
        // morphological operators (dilatation and erosion)
        // dilate with large element, erode with small ones        
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(24, 24));
        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(100, 100));

        Imgproc.erode(mask, morphOutput, erodeElement);
        Imgproc.erode(mask, morphOutput, erodeElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);
        //Convert HSV to BGR
        Imgproc.cvtColor(morphOutput, image, Imgproc.COLOR_GRAY2BGR);
        return image;
     }
}
