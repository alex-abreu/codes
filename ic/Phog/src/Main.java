
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




/**
 * PHOG usig OPENIMAJ
 *by Alex Abreu
 */
public class Main {

    public static void main( String[] args ) throws ClassNotFoundException {
    	Class.forName("java.awt.color.ICC_ColorSpace");
    	Class.forName("sun.java2d.cmm.lcms.LCMS");
    	String fp = null;//stores the name of the file containing the image list or its path
    	Scanner myObj = new Scanner(System.in);
    	
    	System.out.println("Digite o nome do arquivo que contem a lista de imagens:");
    	
    	fp = myObj.nextLine();
    	myObj.close();
    	
    	FileOP objI = new FileOP();//opens the image list file
    	
    	try {
    			File file = new File(fp);

    			BufferedReader br = new BufferedReader(new FileReader (file));

    			String imageName;

    			while((imageName = br.readLine())!= null){
    				Phog obj = new Phog(imageName);
    				System.gc();
    			}
    		        
    		}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    			System.out.println("Programa executado com sucesso!");

    	
    	
    	
    	
    	
    	
    	
    }
}
