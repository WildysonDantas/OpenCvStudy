/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectionface;

/**
 *
 * @author davil
 */

 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
//import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
 
public class FaceDetector {
 
    public static void main(String[] args) {
 
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String opencvpath = System.getProperty("user.dir") + "\\files\\";
        String libPath = System.getProperty("java.library.path");
        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
        System.out.println("\nRunning FaceDetector");
        Mat image = Imgcodecs.imread( "C:\\Users\\davil\\Documents\\imagens\\image.jpg");
        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));  
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
 
        String filename = "gato-maestria.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}