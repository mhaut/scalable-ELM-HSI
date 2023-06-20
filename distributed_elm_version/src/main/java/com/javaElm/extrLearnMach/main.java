package com.javaElm.extrLearnMach;

import no.uib.cipr.matrix.NotConvergedException;
import org.apache.pig.data.DataType;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.EvalFunc;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import java.io.IOException;
import java.lang.Exception;
import weka.core.Instance;
import weka.core.Instances;
import com.javaElm.extrLearnMach.DataParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.amazonaws.AmazonServiceException;   
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class main extends EvalFunc<String> {


	private final DataParser dataParser = new DataParser();
	
	String bucketName = "dataelm3";
    String key = "/out_weigths";
    //String key = "diabe_tr.csv";
	
	/**
	 * @param args
	 * @throws NotConvergedException 
	 */ 
	 
	@Override  
	public String exec(Tuple input) throws IOException {
		try{
            
			Integer typeELM = DataType.toInteger(input.get(0));
			Integer numHNeurons = DataType.toInteger(input.get(1));
			String funct = DataType.toString(input.get(2));
			Integer outNeurons = DataType.toInteger(input.get(3));	
			Integer execution = DataType.toInteger(input.get(4));
			Integer seed = DataType.toInteger(input.get(5));
			Object objData = input.get(input.size()-1);
        			
			Instances trainData = dataParser.parseData(objData);
			
			List<double[]> dataList = new ArrayList<double[]>();
			
			int n_inst = trainData.numInstances();
			
			for(int i=0; i<n_inst; i++)
			{
				Instance inst = trainData.instance(i);
				double[]m = inst.toDoubleArray();
				
				dataList.add(this.removeLastElement(m));
				
			}
			
			
			elm ds = new elm(typeELM, numHNeurons, funct, outNeurons,seed);
            DenseMatrix matrix = null;
			if(execution == 0){
                matrix = ds.train(dataList);
			}else {
		    try {
		        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIARIRYLIGX33SSAA5B", "F/XEJYbGumH/9wJcG90zqtyD9mTctcO5yDbsR9/z");
		        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
		        							.withRegion("eu-west-3")
		        	                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
		        	                        .build();
		        List<double[]> w = new ArrayList<double[]>();	
	        	List<String> files = listFolderObjects(bucketName, s3Client);
	        	
	        	files.remove(0);
	        	
	        	for (String file : files) {
                    S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, file));
                    S3ObjectInputStream inputStream = object.getObjectContent();
                    List<double[]> we = getDataAmazon(inputStream);
                    w.add(we.get(0));
                }
	        	double[] r = avg(w);
	        	matrix = this.set_matrix_weights(r,numHNeurons,outNeurons);
	        	ds.test(dataList, matrix);
	        	System.out.println("TestAcc:"+ds.getTestingAccuracy());
				
	        	
		    } catch (AmazonServiceException e) {
	            e.printStackTrace();
	        }
		    
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
                System.out.println(v[(i*outNeurons)+j]);
                matrix.set(i,j, v[(i*outNeurons)+j]);
			}	
		}
		
		return matrix;
	}

	private List<double[]> getDataAmazon(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        List<double[]> dataList = new ArrayList<double[]>();
        boolean header = false;
        String line = null;
        while ((line = reader.readLine()) != null) {
        	if(!header) {
        		String[] numbers = line.split(",");
        		double[] parsed = new double[numbers.length];
        		for (int i = 0; i<numbers.length; i++) parsed[i] = Double.valueOf(numbers[i]);
        		dataList.add(parsed);
        	}
        	else {
        		header = false;
        	}
        }
        return dataList;
    }

	public List<String> listFolderObjects(String bucketName, AmazonS3 s3Client){
    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName)
                .withPrefix("out_weights/");
    	ObjectListing objects = s3Client.listObjects(listObjectsRequest);
    	List<S3ObjectSummary> summaries = objects.getObjectSummaries();
    	List<String> keys = new ArrayList<String>();
        for(S3ObjectSummary aa : summaries)
        {
        	keys.add(aa.getKey());
        }
        return keys;
	}
	
	public double[] avg(List<double[]> w) {
		double[] result = new double[w.get(0).length];
		//inicializate
		for(int i = 0; i<result.length;i++)
		{
			result[i]=0.0;
		}
		
		for(double[] tmp: w) { 
			for(int i = 0; i<result.length;i++)
			{
				result[i]= result[i] + tmp[i];
			}
		}
		
		for(int i = 0; i<result.length;i++)
		{
			result[i]=result[i]/w.size();
		}
		
		return result;
	}
}


