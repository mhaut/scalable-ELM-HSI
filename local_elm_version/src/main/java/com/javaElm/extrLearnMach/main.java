package com.javaElm.extrLearnMach;

import no.uib.cipr.matrix.NotConvergedException;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.EvalFunc;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import no.uib.cipr.matrix.DenseMatrix;
import java.io.IOException;
import java.lang.Exception;

import java.io.InputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main{
	
    //String key = "diabe_tr.csv";
	
	/**
	 * @param args
	 * @throws NotConvergedException 
	 */   
	  
	public String exec(List params) throws IOException {
		try{ 
            
			Integer typeELM = DataType.toInteger(params.get(0));
			Integer numHNeurons = DataType.toInteger(params.get(1));
			String funct = DataType.toString(params.get(2));
			Integer outNeurons = DataType.toInteger(params.get(3));	
			Integer execution = DataType.toInteger(params.get(4));
			Integer seed = DataType.toInteger(params.get(5));
			String name = DataType.toString(params.get(6));
			
			elm ds = new elm(typeELM, numHNeurons, funct, outNeurons,seed);
            DenseMatrix matrix = null;
			if(execution == 0){
                matrix = ds.train(name);

			}else {
				String v = ((String)params.get(7));
		    	double[] vect = getDoubleVector(v);
		    	
	        	matrix = this.set_matrix_weights(vect,numHNeurons,outNeurons);
	        	ds.test(name, matrix);
	        	
		    
			}
			
			double[] vector;
			if(execution == 0) {  
				vector = this.get_vector_weights(matrix);
	        	
			}else {
				vector = new double[1];
				vector[0]=ds.getTestingAccuracy();
			}
			
			return Arrays.toString(vector);
        
		}catch (Exception e) {
			throw new IOException("Caught exception processing input row ", e);
		}
	
	}
	
	public double[] getDoubleVector(String v2) {
		String[] v = v2.split(",");
		double[] r = new double[v.length];
		for(int i = 0; i<v.length; i++) {
			String str = v[i].replaceAll("\\[", "").replaceAll("\\]","");
			r[i] = Double.parseDouble(str);
		}
		return r;
	}
	
	public double[] read_weights(BufferedReader bf, int hn, int on){
            double[]m = new double[hn*on];
            int i = 0;
            try {
                String strCurrentLine;
                while ((strCurrentLine = bf.readLine()) != null) {
                        m[i] = Double.parseDouble(strCurrentLine);
                        i++;
                }
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                    try {
                        if (bf != null)
                            bf.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                }
            return m;
        }
	
	
	public static double[] removeLastElement(double[] arr) {
        	return Arrays.copyOf(arr, arr.length - 1);
    	}

	public double[] get_vector_weights(DenseMatrix matrix){
		double[] vector = new double[matrix.numRows()*matrix.numColumns()];

		int k = 0;
		for(int i=0; i<matrix.numRows(); i++){
			for(int j=0; j<matrix.numColumns(); j++){
				vector[k] = matrix.get(i,j);
				k++;
			}
		}
		return vector;

	}
	
	public DenseMatrix set_matrix_weights(double[] v, int numHNeurons, int outNeurons){
		DenseMatrix matrix = new DenseMatrix(numHNeurons, outNeurons);
        for(int i = 0; i<numHNeurons; i++){
            for(int j = 0; j<outNeurons; j++){
                matrix.set(i,j, v[(i*outNeurons)+j]);
			}	
		}
		
		return matrix;
	}
	
}


