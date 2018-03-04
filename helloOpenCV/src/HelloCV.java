import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class HelloCV {
        public static void main(String[] args){
               String opencvpath = System.getProperty("user.dir") + "\\files\\";
               String libPath = System.getProperty("java.library.path");
               System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
                //System.out.println(System.getProperty("java.library.path"));
                Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
                System.out.println("mat = " + mat.dump());
        }
}