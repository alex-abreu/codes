package kmeans;

//Java Program to illustrate reading from Text File 
//using Scanner Class 
import java.util.*;
import java.io.File; 
import java.util.Scanner;

public class getSifts{
	private String[] list = new String[906];

	public String[] getList(String fName)throws Exception{
		File file = new File(fName); 
 Scanner sc = new Scanner(file);
 
 int i = 0;

 while (sc.hasNextLine()){ 
   //System.out.println(sc.nextLine()); 
   list[i]= sc.nextLine();
   i++;
	}
	return list;
	}

	public double[][] getDesc(String [] input)throws Exception{
		double[][] m = new double[960461][128];
		int i = 0;
		for (int h = 0;h < 906 ;h++ ) {


			    File file = 
			      new File(input[h]); 
			    Scanner sc = new Scanner(file);
			    
			    while (sc.hasNextLine()){ 
			      //System.out.println(sc.nextLine()); 
			      String [] parts = sc.nextLine().split(" ");
			      for (int j = 0; j < 128; j++) {

			        double value = Double.parseDouble(parts[j]);
			        m[i][j] = value;
			        
			      }
			      i++;
			      
			  
			    }
			    
			    }
			    return m;

	}

}
