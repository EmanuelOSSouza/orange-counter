/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdi.util;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Emanuel
 */
public class FindOrangeAndDrawCircles {
    
    
    public Mat findOrangeAndDrawCircles(Mat src, Mat original){
        //Convert BGR to Gray 
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
       // Detect circles
        Mat circles = new Mat();
        Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1.2, 
                (double)gray.rows()/12, // change this value to detect circles with different distances to each other
                10.0, 11.0, 1, 50); // change the last two parameters
                // (min_radius & max_radius) to detect larger circles 
        for (int x = 0; x < circles.cols(); x++) {
            double[] c = circles.get(0, x);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            // circle center
            Imgproc.circle(original, center, 1, new Scalar(0,100,100), 3, 8, 0 );
            // circle outline
            int radius = (int) Math.round(c[2]);
            //Draw circles
            Imgproc.circle(original, center, radius, new Scalar(255,0,255), 3, 8, 0 );
            System.out.println("Circles/Oranges:" +(x+1));
        }
       
    return original;
    }
}
