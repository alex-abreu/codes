package kmeans;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import org.openimaj.data.RandomData;
import org.openimaj.ml.clustering.ByteCentroidsResult;
import org.openimaj.ml.clustering.assignment.HardAssigner;
import org.openimaj.ml.clustering.kmeans.ByteKMeans;

import org.openimaj.ml.clustering.kmeans.FloatKMeans;
import org.openimaj.ml.clustering.FloatCentroidsResult;

import org.openimaj.ml.clustering.kmeans.DoubleKMeans;
import org.openimaj.ml.clustering.DoubleCentroidsResult;

import java.io.*;
import java.util.ArrayList;

public class StandAlone{

	private int size;
	private ArrayList<String> listS = new ArrayList<String>();
	private String fName;
	
	public StandAlone(String fName) {
		this.fName = fName;
		
	}
	public StandAlone() {}
	public void setName(String fName) {
		this.fName = fName;
	}
	

	public ArrayList<String> getList(){
		return this.listS;
	}
	public void setList()throws Exception {
		File file = new File(this.fName); 
		 Scanner sc = new Scanner(file);
		 
		 while (sc.hasNextLine()){ 
		   
		   this.listS.add(sc.nextLine());
		   
			}
	}

	public double [][] read_IMG_DES(String F)throws Exception{
		File file = new File(F);
		Scanner sc = new Scanner(file);
		ArrayList<Double> temp = new ArrayList<Double>();
		int size = 0;
		int j = 0;
		int k = 0;
		while(sc.hasNextLine()){
			String [] parts = sc.nextLine().split(" ");

			for(int c = 0; c < 128;c++){
				double value = Double.parseDouble(parts[c]);
				temp.add(value);
			}

		}
		size = (temp.size()/128);
		
		System.out.println(size);

		double [][] m = new double[size][128];

		for(int i = 0; i < temp.size(); i++){
			m[j][k] = temp.get(i);
			k = k+1;
			if(k == 128){
				k = 0;
				j = j+1;
			}
		}
		System.out.println(j);
		return m ;
	}

	public void set_size(int size){
		this.size = size;
	}

	public int[] get_neighbour(HardAssigner<double[], ?, ?> assigner , double[][] input){
		int [] output = assigner.assign(input);
		return output;

	}
	public int[] calculate(int arr[]){
		int index;
		int [] output = new int[this.size];
		Arrays.fill(output, 0);

		 for(int i = 0; i < arr.length; i++){
		 	index = arr[i];
		 	output[index] = output[index] + 1;
		 }
		 return output;
	}


}
