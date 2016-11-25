import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.*;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class hello
{
   public static void main( String[] args )
   {
	   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	   VideoCapture cap = new VideoCapture(1);
	   if (!cap.isOpened()) System.out.println("Failed!");
	   
	   Size sz = new Size(320,240);
	   Scalar bottom = new Scalar(60, 100, 100);
	   Scalar top = new Scalar(100, 255, 255);
	   int minArea = 100;
	   
	   while (true) {
		   try{
		   Mat frame = new Mat();
		   Mat frame1 = new Mat();
		   cap.retrieve(frame);
		   cap.retrieve(frame1);
		   Imgproc.resize(frame, frame, sz);
		   Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);
		   Core.inRange(frame, bottom, top, frame);

		   // Imgproc.GaussianBlur(frame, frame, new Size(7,7), 1.5, 1.5);
		   // Imgproc.Canny(frame, frame, 0, 30);
		   
			BufferedImage bi = (BufferedImage) toBufferedImage(frame);
			File outputfile = new File("saved.jpg");
			ImageIO.write(bi,  "jpg",  outputfile);
			
			bi = (BufferedImage) toBufferedImage(frame1);
			outputfile = new File("saved1.jpg");
			ImageIO.write(bi,  "jpg", outputfile);
		   
		   List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		   Imgproc.findContours(frame, contours, frame1, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		   
		   List<MatOfPoint> filteredContours = new ArrayList<MatOfPoint>();
		   for (MatOfPoint mop : contours) {
			   if (Imgproc.contourArea(mop) > minArea) filteredContours.add(mop);
		   }

		   Moments M = Imgproc.moments(filteredContours.get(0));
		   int cX = (int)(M.get_m10() / M.get_m00());
		   int cY = (int)(M.get_m01() / M.get_m00());
		   
		   // System.out.println("here");
		   
		   System.out.println(cX + " " + cY);
		   } catch (Exception e) {}
	   }
   }
   
   static public Image toBufferedImage(Mat m){
	      int type = BufferedImage.TYPE_BYTE_GRAY;
	      if ( m.channels() > 1 ) {
	          type = BufferedImage.TYPE_3BYTE_BGR;
	      }
	      int bufferSize = m.channels()*m.cols()*m.rows();
	      byte [] b = new byte[bufferSize];
	      m.get(0,0,b); // get all the pixels
	      BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	      final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      System.arraycopy(b, 0, targetPixels, 0, b.length);  
	      return image;
	  }
}