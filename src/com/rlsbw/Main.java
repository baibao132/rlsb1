package com.rlsbw;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;

public class Main{

    public static void main(String[] args) {
	// write your code here
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture VC = new VideoCapture();
        if(!VC.open(0)) {
            System.out.println("Open Video Error");
        }
        while (true){
            Mat img = new Mat();
            if(!VC.read(img)){
                return;
            }
            Mat rgb = new Mat();
            Mat gray = new Mat();
            Imgproc.cvtColor(img,rgb,Imgproc.COLOR_BGRA2RGB);
            Imgproc.cvtColor(rgb,gray,Imgproc.COLOR_BGR2GRAY);
            CascadeClassifier CC = new CascadeClassifier("E:/2020/opencv/opencv/build/etc/haarcascades/haarcascade_frontalface_default.xml");
            MatOfRect rect = new MatOfRect();
            CC.detectMultiScale(gray,rect);
            for(Rect r : rect.toArray()){
                Imgproc.rectangle(img,new Point(r.x,r.y),new Point(r.x + r.width,r.y + r.height),new Scalar(255,0,0));
            }
            HighGui.imshow("imshow",img);
            HighGui.waitKey(10);
        }
    }
}
