import org.openimaj.image.ImageUtilities;
import org.openimaj.image.feature.dense.gradient.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.FImage;
import org.openimaj.feature.FloatFV;
import org.openimaj.image.feature.global.*;
import java.util.*;

public class GIST{
	
	GIST(String imageName){
		
		try
	    {
			
			FileOP objI = new FileOP();
			String imgPath = objI.getImagePath(imageName);
			System.out.println("Working on :"+imgPath);
			
			// the line that reads the image file
			BufferedImage image = ImageIO.read(new File(imgPath));
			
	      // work with the image here ...
	      	
	  		FImage fimg = ImageUtilities.createFImage(image);// converts from BufferedImage to FImage
	  		
	  		Gist obj = new Gist();//creates a GIST object
	  		
	  		obj.analyseImage(fimg);//analyse the FImage
	  		
	  		FloatFV response = obj.getResponse();//gets the feat vector as FloatFV type
	  		
	  		double [] featvec = response.asDoubleVector();//gets the feature vector as a double[] array
	  		
	  		
	  		objI.writeFeature(featvec,imageName);
			}
	   
	    catch (IOException e)
	    {
	    	System.out.println(e);
	    }
		
	}
	
}
