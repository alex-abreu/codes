import java.util.*;
import java.io.*; 

// Class that creates and handles the medical specialties

public class Especialidades{

	private ArrayList <String> listEspec;//list of specialties

	Especialidades(){//creates an empty list

		this.listEspec = new ArrayList<String>();
	}

	public Object[] getEsp(){//returns the current list of specialties

		return this.listEspec.toArray();
	}
	/*

	generates a file name especialidades.txt and fills the file with all the specialties listed 
	by cfm, this is only used in case no especialidades.txt file is found.
	
	*/
	private void createNew(){

		String [] espec = {"Acupuntura","Alergia e imunologia","Anestesiologia","Angiologia","Cancerologia",
                            "Cardiologia","Cirurgia cardiovascular","Cirurgia da mão","Cirurgia de cabeça e pescoço",
                            "Cirurgia do aparelho digestivo","Cirurgia geral","Cirurgia pediátrica","Cirurgia plástica",
                            "Cirurgia torácica","Cirurgia vascular","Clínica médica","Coloproctologia","Dermatologia",
                            "Endocrinologia e metabologia","Endoscopia","Gastroenterologia","Genética médica","Geriatria",
                            "Ginecologia e obstetrícia","Hematologia e hemoterapia","Homeopatia","Infectologia","Mastologia",
                            "Medicina de emergência","Medicina de família e comunidade","Medicina do trabalho","Medicina de tráfego",
                            "Medicina esportiva","Medicina física e reabilitação","Medicina intensiva","Medicina legal e perícia médica",
                            "Medicina nuclear","Medicina preventiva e social","Nefrologia","Neurocirurgia","Neurologia","Nutrologia",
                            "Oftalmologia","Ortopedia e traumatologia","Otorrinolaringologia","Patologia",
                            "Patologia clínica/medicina laboratorial","Pediatria","Pneumologia","Psiquiatria",
                            "Radiologia e diagnóstico por imagem","Radioterapia","Reumatologia","Urologia"};
 
 
 
 
                try{
                File file = new File("especialidades.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
           
                for (String str : espec){
                    listEspec.add(str);
                    writer.write(str);
                    writer.write('\n');
                   
                    }
 
                writer.close();
                }
                catch(Exception err){
                    String error = err.toString();
                }


	}


	public boolean searchEsp(String s){//checks whether or not an specialty is in the list

		for(String str : listEspec){

			if(s.equals(str))//returns True if it is
				return true;
		}
		return false;//returns False othwerwise

	}

	public int addEsp(String s){//adds an new specialty to the list
		
		if(s.length() > 0){
		
			if(searchEsp(s))//in case the specialty already exist in the list return the error code
			
				return 0;

			else{

				listEspec.add(s);//otherwise adds and returns -1
				return -1;
			}
		}
		return 2;
	}

	public int delEsp(String s){//removes and specialty from the list
		
		if(s.length() > 0){
			
			if(listEspec.remove(s)){//returns -1 if it was removed successfully
			
				return -1;
			}
			else
				return 1;//returns the error code if the elemn isn't in the list
		}
		return 2;
	}

	public void setEsp() {//loads the file of specialties into the list
		
		try{
			
			File file = new File("especialidades.txt");
			BufferedReader br = new BufferedReader(new FileReader (file));
			String espec = br.readLine();

			while((espec = br.readLine())!= null){
			
				addEsp(espec);
				
				}

			}
		catch (IOException e){       
 
	        createNew();//in case there wasnt a file called especialidades.txt
		}

	}

	public void atualizaEsp(){//overrides the especialidades.txt file with the current set 
											  //of specialties
		try{
			File file = new File("especialidades.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
			for (String str : listEspec){

				writer.write(str);
				writer.write('\n');
				
				}

			writer.close();
			}
			catch(Exception e){
				String error = e.toString();
			}
	}

	public ArrayList<String> generateSpecMed(){

		ArrayList<String> medSpec = new ArrayList <String>();

		Scanner myObj = new Scanner(System.in);
		String specialty = new String();
		do{
			specialty = myObj.nextLine();
			System.out.println(specialty);
			if(specialty.equals("0"))
				break;
			if(searchEsp(specialty)){
				medSpec.add(specialty);
			}
			else{
				String error = "Especialidade nao encontrada";
				System.out.println(error);
			}
		}while(true);
		return medSpec;
	}

}