package com.javaElm.extrLearnMach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class principal{
	
	
	public static void main(String args[]) throws IOException{  //static method  
	
		int hneurons = 10000;
		String name_datatrain = args[0];
		String name_datatest = args[1];
		int seed = Integer.parseInt(args[2])*5;
		
		//PARTE DE ENTRasdENAMIENTO
		long startTime = System.nanoTime();
		List myArrayList = new ArrayList();
  
		myArrayList.add(1);	
		myArrayList.add(hneurons);
		myArrayList.add("sig");
		myArrayList.add(58);
		myArrayList.add(0);
		myArrayList.add(seed);
		myArrayList.add(name_datatrain);
		
		main e = new main();  
		String vect = e.exec(myArrayList);  
		
		//PARTE DE VALIDACION
		
		myArrayList = new ArrayList();		
		myArrayList.add(1);
		myArrayList.add(hneurons);
		myArrayList.add("sig");
		myArrayList.add(58);
		myArrayList.add(1);
		myArrayList.add(seed);
		myArrayList.add(name_datatest);
		myArrayList.add(vect);
		e = new main();  
		String result = e.exec(myArrayList);  
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000000000;
		//HACER UN PRINTF DE LOS RESULTADOS
		System.out.println(name_datatrain+" "+hneurons+" "+result.replaceAll("\\[", "").replaceAll("\\]","")+" "+totalTime);
		
	}
}