package kmeans;

import java.util.Arrays;
import java.lang.*;
import org.openimaj.data.RandomData;
import org.openimaj.ml.clustering.ByteCentroidsResult;
import org.openimaj.ml.clustering.assignment.HardAssigner;
import org.openimaj.ml.clustering.kmeans.ByteKMeans;

import org.openimaj.ml.clustering.kmeans.FloatKMeans;
import org.openimaj.ml.clustering.FloatCentroidsResult;

import org.openimaj.ml.clustering.kmeans.DoubleKMeans;
import org.openimaj.ml.clustering.DoubleCentroidsResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class FloatKmeans{
	/**
	 * Main method for the example.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args)throws Exception {
		// Set up the variables needed to define the clustering operation
		final int dimensionality = 128;
		final int numItems = 960461;//num total de sifts(linhas totais) dos sifts do dicionario
		final int K = 100000;
		
		//double[][]teste = RandomData.getRandomDoubleArray(50,128,-150.0,150.0);
		
		 long start = System.currentTimeMillis();
		// Create the clusterer; there are specific types for all kinds of data
		// (we're using byte data here). The clusters provide a couple of
		// static methods to quickly create an exact or approximate (using a
		// KD-Tree ensemble) K-Means algorithm. Alternatively, the K-Means
		// clusterer can be constructed with a KMeansConfiguration object which
		// offers fine control over the k-means algorithm, including control
		// over the nearest-neighbour implementation (i.e. approximate or not;
		// different distance functions, etc).
		final DoubleKMeans kmeans = DoubleKMeans.createKDTreeEnsemble(K);

		// Settings for things like the number of iterations can be set through
		// the KMeansConfiguration either at construction time, or afterwards:
		//kmeans.getConfiguration().setMaxIterations(100);

		// Generate some random data to cluster
		//final double[][] data = RandomData.getRandomDoubleArray(numItems, dimensionality, -120.0, 120.0);
		getSifts get = new getSifts();
		System.out.println("getting list for dictionary");
 	 	String [] l1 = get.getList("list");
 	 	System.out.println("getting list of features for clustering");
 	 	final double [][] data = get.getDesc(l1);
		// Perform the clustering
 	 	System.out.println("clustering");
		final DoubleCentroidsResult result = kmeans.cluster(data);

		// Get an assigner to assign vectors to the closest cluster. You could
		// also construct an assigner implementation manually if you want
		// more control over the assigner.
		final HardAssigner<double[], ?, ?> assigner = result.defaultHardAssigner();
		System.out.println("getting assigner");
		
		StandAlone st = new StandAlone();
		st.set_size(K);
		st.setName("fName.txt");
        st.setList();
        ArrayList<String> listS = st.getList();
        
        for(int a = 0; a < listS.size();a++) {
        	System.out.println("computing for "+ Integer.toString(a));
        	double[][]m = st.read_IMG_DES(listS.get(a));
        	int []nn = st.get_neighbour(assigner, m);
    		int []output = st.calculate(nn);
    		String Name = "IMG_" +Integer.toString(a)+"_Bag";
    		File file = new File(Name);
    		FileWriter writer = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(writer);
			
			for(int b = 0; b < output.length;b++ ) {
				bw.write(Integer.toString(output[b]));
				bw.write(System.getProperty("line.separator"));
			}
			bw.close();
    		
    		
        }

		/*
		 * 	int []nn = st.get_neighbour(assigner, teste);
		int []output = st.calculate(nn);
		
		for(int a = 0; a < output.length;a++) {
			System.out.println(output[a]);
		}
		System.out.println(output.length);
		*/
		
		/*	

		// Now investigate which cluster each original data item belonged to:
		for (int i = 0; i < numItems; i++) {
			final double[] vector = data[i];

			// Each leaf-node of the hierarchy is assigned a unique index number
			// between 0 and the total number of leaf-nodes.
			final int globalClusterNumber = assigner.assign(vector);

			System.out.format("%s was assigned to cluster %d\n", Arrays.toString(vector),
					globalClusterNumber);
			try {
				File file = new File("log.txt");
				File file2 = new File("cluster.txt");
				
				FileWriter writer = new FileWriter(file,true);
				BufferedWriter bw = new BufferedWriter(writer);
				
				FileWriter writer2 = new FileWriter(file2,true);
				BufferedWriter bw2 = new BufferedWriter(writer2);
				
				
				bw.write( Arrays.toString(vector));
				bw.write(System.getProperty("line.separator"));
				
				bw2.write(Integer.toString(globalClusterNumber));
				bw2.write(System.getProperty("line.separator"));
				
				bw.close();
				bw2.close();
			}
			catch(Exception e){
				
			}
			
			
		}*/
		long end = System.currentTimeMillis();
		float minutes = (end - start) / 60000F; 
		System.out.println(minutes + " minutes");
		System.out.println("fim");
	
		
	}
}
