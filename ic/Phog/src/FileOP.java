
import java.util.*;
import java.io.*;
import java.io.IOException; 
import java.nio.file.*; 
public class FileOP {
	

	
	public String getImagePath(String img) {

			return  new File(img).getAbsolutePath();
			
		
	}
	
	
	
	private boolean duplicate(String name) {
		try{
			File file = new File("PHOG.txt");

			BufferedReader br = new BufferedReader(new FileReader (file));

			String imageName;

			while((imageName = br.readLine())!= null){
				
				if(imageName.equals(name)) {
					br.close();
					return true;
				}
				
			}
		
			br.close();
		}
		catch(Exception e) {
			
		}
		
		return false;
}
	public void addOutList(String name) {
		if(!duplicate(name)) {
		try{
			File file = new File("PHOG.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			
			
			
			writer.write(name);
			writer.write('\n');
			writer.close();
		}
		catch(Exception e){

			
		}
	}
}
	public void writeFeature(double[] vec, String name) {
		try{
			String nameFile = name+"-PHOG.txt";
			File file = new File(nameFile);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < vec.length ;i++){
				String str = Double.toString(vec[i]);
				writer.write(str);
				writer.write('\n');
			}
			writer.close();
			addOutList(nameFile);

		}
		catch(Exception e){

			
		}
		
	}
	
}

