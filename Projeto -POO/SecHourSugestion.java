// Java program to demonstrate delete using Files class 
import java.io.IOException; 
import java.nio.file.*; 
import java.util.*;
  
public class SecHourSugestion
{ 
   public int[] fill_open_close(int open, int close){
        
        int [] arrayH = new int[24];
        
        for(int i = 0; i < 24;i++ ) {
            if((i < open) || (i >=close ))
                arrayH[i] = 1;//set the non working hours of the clinic to 1
            else
                arrayH[i] = 0;//sets the working hours to 0
        }
        return arrayH;

    }

    public ArrayList<Integer>fill_sec_working(int open, int close, int []arrayH, int hMany,
        String [] info,int maior, ArrayList<Integer> startShift){
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for (int i = 0; i < (hMany) ;i++ ) {
            
            String parts[] = info[i].split(" ");
            int start = Integer.parseInt(parts[0]);//gets the start of the shift for sec[i]
            int finish = Integer.parseInt(parts[2]);//gets the finish of the shift for sec[i]
            startShift.add(start);
            if(maior < finish){//saves when the last turn ends
                maior = finish;
            }
            
            for(int j = start; j <= finish;j++){
                arrayH[j] = 1;//means there's a sec working at j hour

            }
        }
      
    
        for (int i = 0;i < 24 ;i++ ) {//adds the hoours without sec working into a list, checks and adds the upper and lower bounds 
            if(arrayH[i]== 0){
                if((i > 1) &&(arrayH[i-1] == 1) && (i > open ) ){
                    temp.add(i-1);
                    
                    }

                temp.add(i);
                
            }
            else{
                if(i >= 1){
                    if((arrayH[i] == 1) && (arrayH[i-1] == 0) && i!=close ){
                    temp.add(i);
                    
                    }

                }
            }
        }

        
        if(maior == (close-1)){//manually adding the last hour of work in case it wasnt added and it's required
            temp.add(maior);
            
        }



        return temp;
    }

    public String[] format(ArrayList<Integer> temp, ArrayList<Integer> startShift){
        ArrayList<String> tempString = new ArrayList<String>();
        for(int item : temp){
            int count2 = 0;
            int i = -1;
            for(int num : startShift){
                i++;
                if(num == item){
                    count2++;
                }
                if(i == (startShift.size())-1){
                    if(count2 == 0){
                        tempString.add(Integer.toString(item));
                    }
                }
            }
        }

        
        
        String[] sug = tempString.toArray(new String[tempString.size()]); 
        return sug;
    }

    public String[] sugestion(String[] info, int open, int close){
        
        ArrayList<Integer> startShift = new ArrayList<Integer>();
        ArrayList<Integer> temp = new ArrayList<Integer>();//to store the hours where thre isnt any sec working
        int hMany = info.length;//number of registered secretaries
        int [] arrayH = new int[24];//array to store the worked / non worked hours of the day
        int count = 0;//to save the amount of hours without sec working
        int maior = 0;
      
        arrayH = fill_open_close(open,close);
        temp = fill_sec_working(open,close,arrayH,hMany,info,maior,startShift);
        /*for (int i = 0; i < (hMany) ;i++ ) {
            
            String parts[] = info[i].split(" ");
            int start = Integer.parseInt(parts[0]);//gets the start of the shift for sec[i]
            int finish = Integer.parseInt(parts[2]);//gets the finish of the shift for sec[i]
            startShift.add(start);
            if(maior < finish){//saves when the last turn ends
                maior = finish;
            }
            
            for(int j = start; j <= finish;j++){
                arrayH[j] = 1;//means there's a sec working at j hour

            }
        }
      
    
        for (int i = 0;i < 24 ;i++ ) {//adds the hoours without sec working into a list, checks and adds the upper and lower bounds 
            if(arrayH[i]== 0){
                if((i > 1) &&(arrayH[i-1] == 1) && (i > open ) ){
                    temp.add(i-1);
                    count++;
                    }

                temp.add(i);
                count++;
            }
            else{
                if(i >= 1){
                    if((arrayH[i] == 1) && (arrayH[i-1] == 0) && i!=close ){
                    temp.add(i);
                    count++;
                    }

                }
            }
        }

        
        if(maior == (close-1)){//manually adding the last hour of work in case it wasnt added and it's required
            temp.add(maior);
            count++;
        }*/
        
       /* for (int num: startShift ) {
            System.out.println(num);
           //testing porpuses 
        }*/
        /*ArrayList<String> tempString = new ArrayList<String>();
        for(int item : temp){
            int count2 = 0;
            int i = -1;
            for(int num : startShift){
                i++;
                if(num == item){
                    count2++;
                }
                if(i == (startShift.size())-1){
                    if(count2 == 0){
                        tempString.add(Integer.toString(item));
                    }
                }
            }
        }

        
        
        String[] sug = tempString.toArray(new String[tempString.size()]); 
        */
        return format(temp,startShift);
            
        }
    }
