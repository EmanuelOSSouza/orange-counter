/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pdi.util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Emanuel
 */
public class FindOrangeAndDraw {
    
    
    public Mat findOrangeAndDraw(Mat maskedImage, Mat image){
	//Find Contours
        Imgproc.cvtColor(maskedImage, maskedImage, Imgproc.COLOR_BGR2GRAY);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0){
            //Draw Contours
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0]){
                    Imgproc.drawContours(image, contours, idx, new Scalar(246, 85, 27), 2);
            }
        }	
	return image;
    }
}
