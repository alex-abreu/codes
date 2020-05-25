
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.feature.dense.gradient.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.FImage;
import org.openimaj.math.statistics.distribution.Histogram;

public class Phog {
	
	private String imageName;
	
	private void setName(String name) {
		this.imageName = name;
	}
	
	Phog(String img){
		try {
		setName(img);
		
		FileOP objI = new FileOP();
		String imgPath = objI.getImagePath(this.imageName);
		// the line that reads the image file
		System.out.println("Working on :"+imgPath);
		BufferedImage image = ImageIO.read(new File(imgPath));
		
		// work with the image here ...
  	
		FImage fimg = ImageUtilities.createFImage(image);// creates an FImage from a BufferedImage
		
		
		//Construct with the values used in the paper: 4 levels (corresponds to l=3 in the paper),
		//40 orientation bins (interpolated), signed gradients (called "shape360" in the original paper)
		//and Canny edge detection.
		PHOG obj = new PHOG();
		
		obj.analyseImage(fimg);//analyse the FImage
		
		Histogram hist = obj.getFeatureVector();//gets the feature vector as Histogram type
		
		double [] featvec = hist.asDoubleVector();//gets a double array with the features from the Histogram type
		
		//creates the file to store the feature vector and adds
		//the file name to the PHOG.txt file
		objI.writeFeature(featvec,this.imageName);
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	


		
		
	}
}

