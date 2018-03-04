/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author davil, wildyson
 */

 

import java.util.List;

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

import service.ServiceDeteccaoFacesImagem;
import model.PropriedadesFace;


public class Face {
 
    public static void main(String[] args) {
 
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //String opencvpath = System.getProperty("user.dir") + "\\files\\";
        // libPath = System.getProperty("java.library.path");
       
    	System.loadLibrary("opencv_java341");
        System.out.println("\nRunning FaceDetector");
        Mat image = Imgcodecs.imread( "C:/Users/wildyson/Documents/recFace/reconhecimentoface/foto02.jpg");
        CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/wildyson/Documents/recFace/reconhecimentoface/haarcascade_frontalface_alt.xml");  
       
        // MatOfRect faceDetections = new MatOfRect();
        //faceDetector.detectMultiScale(image, faceDetections);
        
        ServiceDeteccaoFacesImagem serviceExtractFaces = new ServiceDeteccaoFacesImagem();
		MatOfRect matOfRect = serviceExtractFaces.detectarFaces(faceDetector, image);
		
		//exibe quantas faces foram detectadas
        System.out.println(String.format("Detected %s faces", matOfRect.toArray().length));
		
		
		//obtem os dados de onde estão as faces (altura, largura, posição x e y)
		List<PropriedadesFace> propsFaces = serviceExtractFaces.obterDadosFaces(matOfRect);
        
		//Mostra as propriedas da face
        for( PropriedadesFace p : propsFaces){
        	//PropriedadesFace p = new PropriedadesFace();
        	System.out.println(p.getX()); //posicaoX da face
        	System.out.println(p.getY()); //posicao Y da face

        }
        
        //desenha o retangulo nas faces encontradas
        for (Rect rect : matOfRect.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        
        //salva a imagem com o retangulo no computador
        String filename = "gato-maestria.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}
