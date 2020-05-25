import java.util.*;
import java.io.*;
import java.io.IOException; 
import java.nio.file.*; 

public class ExameHandle{

	private ArrayList <String> listExames;
	private ArrayList <String> listCodes;
	private Exame objEx;

	ExameHandle(){//creates both list (empty) and and object of Exame

		this.listCodes = new ArrayList<String>();
		setCode();
		this.listExames = new ArrayList<String>();
		setExam();
		objEx = new Exame();
	}

	public void loadExam(int index){//loads the exame's info into an Object
		
		try{
			
			if (index >= 0) {
				
				File file = new File(listExames.get(index));

				BufferedReader br = new BufferedReader(new FileReader (file));

				String line;

				line = br.readLine();
				objEx.setName(line);

				line = br.readLine();
				objEx.setCode(line);

				line = br.readLine();
				objEx.setObs(line);

				line = br.readLine();
				objEx.setDuration(line);

				line = br.readLine();
				objEx.setEstResult(line);
			
			}
		}
		catch(Exception e){

			String error = e.toString();
		}

	}

	public int searchExam(String s){//checks whether or not an exam belongs to ListExames
		
		int count = 0;
		
		for(String str : listExames){

			if(s.equals(str)){//if does returns the position he is in
				return count;
			}

			count++;
		}
		return -1;//returns -1 othwerwise

	}

	public int searchCode(String code){//checks whether or not a code belongs to listCodes

		return listCodes.indexOf(code);
	}
	
	private void setCode(){//loads the codeList with the file's content

		try{
			File file = new File("codeList.txt");

			BufferedReader br = new BufferedReader(new FileReader (file));

			String code;

			while((code = br.readLine())!= null){
				
				addCodeToList(code);
			}
		}
		catch(Exception e){
			
			String error = e.toString();
		}
	}

	private void addCodeToFile(String code){//adds a code to the file code
		
		try{

			File file = new File("codeList.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			
			
			writer.write('\n');
			writer.write(code);

			writer.close();
		}
		catch(Exception e){

			String error = e.toString();
		}
	}

	private int addCodeToList(String code){//adds a code to the codeList

		if(code.length() > 0){

			if(searchCode(code) >= 0)
				return 0;
			else{
				listCodes.add(code);
				return -1;
			}

		}
		return 2;

	}

	private void delOnFile(String fName, ArrayList <String> list){//overwrites a file with its correspondent list
		
		try{

			File file = new File(fName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (String str : list){

				writer.write(str);
				writer.write('\n');
			}
			writer.close();
		}
		catch(Exception e ){

			String error = e.toString();
		}
	}

	//deletes the file named Fname, which has a exam's info and it's in the current directory
	public void delFile(String fName){
		
		try
        { 
            Files.deleteIfExists(Paths.get(fName)); 
        } 
        catch(NoSuchFileException e) 
        { 
            String error1 = "No such file/directory exists"; 
        } 
        catch(DirectoryNotEmptyException e) 
        { 
            String error2 = "Directory is not empty."; 
        } 
        catch(IOException e) 
        { 
            String error3 = "Invalid permissions."; 
        } 
	}

	public int delExame(String exam){//deles a Exam by its code or name

		try{
			int index;

			if(exam.length() > 0){

				if( (index = searchExam((exam+".txt"))) >= 0){//in case the exam name is provided

					delFile((exam+".txt"));//deletes the file
					listExames.remove(index);//remove from the exam List
					listCodes.remove(index);//remove from the code List
					delOnFile("exameList.txt",listExames);//updates the exameList File
					delOnFile("codeList.txt",listCodes);//updates the listCode File
					
					return -1;

				}
				else if( (index = searchCode(exam) ) >= 0 ){//in case the exam's code is provided

					delFile(listExames.get(index));//deletes the file
					listExames.remove(index);//remove from the exam list
					listCodes.remove(index);//remove from code list
					delOnFile("exameList.txt",listExames);//updates the examelist File
					delOnFile("codeList.txt",listCodes);//updates the codeList File

					return -1;
					

				}	
			}
			return 7;
		}
		catch(Exception e){
			String error = e.toString();
			System.out.println(error);
			return 10;
		}
	}

	private int addExam(String s){//adds an new Exam to the list
		
		if(s.length() > 0){
		
			if(searchExam(s) >= 0 )//in case the exam already exists in the list, return the error code
				return 0;

			else{

				listExames.add(s);//otherwise adds and returns -1
				return -1;
			}
		}
		return 2;
	}

	public ArrayList<String> getCodeList(){//return the loaded listCode

		return this.listCodes;

	}

	public void setExam(){//loads both the exam and code files into the correspondent list
		
		try{
			File file = new File("exameList.txt");

			BufferedReader br = new BufferedReader(new FileReader (file));

			String exam;

			while((exam = br.readLine())!= null){
				
				addExam(exam);
				System.out.println(exam);
			}

			setCode();

		}
		catch(Exception e){

			String error = e.toString();
		}
	}
	private void addListEx(String name){//adds an exam name to the end of the exam file
		
		try{

			File file = new File("exameList.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			
			
			writer.write('\n');
			writer.write(name);

			writer.close();

		}
		catch(Exception e){

			String error = e.toString();
		}

	}

	private void addInfo(String nameFile, ArrayList <String> info){//overwrites a file with it's correpondent List
		
		try{
			
			File file = new File(nameFile);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (String str : info){

				writer.write(str);
				writer.write('\n');
			}
			writer.close();

		}
		catch(Exception e){

			String error = e.toString();
		}

	}

	public int addExame(Object data[]){//creates a file with the exam's info, File name to the exameList.txt, and the exam's code to the codeList.txt file
		
		try{

			ArrayList <String> info = new ArrayList<String>();
			
	    	String exName = (String) data[0]; // get name from param
			if(exName.length() == 0)
				return 8; //if the exName is empty, the function returns a error
 
	    	if(searchExam((exName+".txt")) < 0){//tries to add the exam name to the exameList
				if(listCodes.indexOf(data[1]) >= 0){//case the code informed is already in use
					return 4;
				}
				//adding directly to array
				info.add((String) data[1]);
				info.add((String) data[2]);
				info.add((String) data[3]);
				info.add((String) data[4]);
				
				//updates both lists and write the Exam's info
	    		addExam(exName+".txt");
	    		addListEx((exName+".txt"));//adds the exam's name to the exameList.txt file
	    		info.add(0,exName);
	    		addInfo((exName+".txt"),info);//creates a file with the exam's info
	    		addCodeToList(info.get(1));//adds the code to the list
	    		addCodeToFile(info.get(1));//adds the code to the codeList.txt file
	    		return -1;
	    	}
			
			return 5;

		}
		catch(Exception e){
			
			String error = e.toString();
			return 10;
		}
	}

	public ArrayList<String> getExam(){//returns The current listExames;
		return this.listExames;

	}


	//creates a table with all the registered exams and their info
	public String[][] createTable(){
		System.out.println(listExames.size());
		String[][] table = new String[listExames.size()][5]; //creates the table
		for (int i = 0 ; i < listExames.size(); i++  ) {//fill the table with all the exam's data
			loadExam(i);
			table[i][0] = objEx.getName();
			table[i][1] = objEx.getCode();
			table[i][2] = objEx.getObs();
			table[i][3] = objEx.getDuration();
			table[i][4] = objEx.estResult();

			
		}
		
		return table;
	}

	public Object[] createExamList()
	{
		Object[] list = new Object[this.listExames.size()];
		for(int i = 0; i < this.listExames.size(); i++)
		{
			loadExam(i);
			list[i] = objEx.getName();
		}
		return list;
	}

	public int getSizeList(){//returns the amout of registered exams
		return listExames.size();
	}

	public int getExamIndex(String exam_name)//gets the positino of an exam in the list
	{
		int i = 0;
		for(String s : listExames)
		{
			if(exam_name.equals(s))
				break;
			i++;
		}
		return i;
	}

	public Exame getObj(){//Returns the created obj of Exame
		return objEx;
	}
}