package com.myntra.qa.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.HashSet;

import org.testng.annotations.Test;
public class LoadCSV {

	
	private static final String CSV_FILE_PATH = "./src/main/resources";
	private static FileReader fReader = null;
	private static BufferedReader bReader = null;
	private static Scanner scan = null;
	private static FileWriter fw = null;
	
	public void createCSVFile()
	{
		//create CSV file...
		try {
			fw = new FileWriter(CSV_FILE_PATH + "/" +"NewCSVFile.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void loadCSVFile()
	{
		//read CSV file....
		try {
			fReader = new FileReader(CSV_FILE_PATH + "/" +"EmpDetailsCSVFile.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bReader = new BufferedReader(fReader);
		
	}
	
	//read CSV file.....
	public void removeDuplicateRecordsInCSVFile() throws IOException
	{
		HashSet<String> empHM = new HashSet<>();
		String line1 = null;
		String line2 = null;
		int index = 0;
		
		this.loadCSVFile();
		this.createCSVFile();
		
		//FileReader fReader1 = new FileReader(CSV_FILE_PATH + "/" +"NewCSVFile.csv");
		
		//BufferedReader bReader1 = new BufferedReader(fReader1);
		
		
		
		//read data line by line...
		line1 = bReader.readLine();
		
		while(line1 != null)
		{
			empHM.add(line1);
			line1 = bReader.readLine();
			
			//increment index++
			index++;
		}
		
		
		Iterator<String> empItr = empHM.iterator();
		
		while(empItr.hasNext())
		{
			fw.append(empItr.next());
			fw.append("\n");
			
		}
		
		
		/*while(line1 != null)
		{
			boolean availFlag = false;
			
			line2 = bReader1.readLine();
			
			System.out.println("line 1 is : " + line1);
			
			while(line2 != null)
			{
				System.out.println("line 1 is : " + line2);
				if(line1.equals(line2))
				{
					availFlag = true;
					break;
				}
				
				line2 = bReader1.readLine();
			}
			
			if(!availFlag)
			{
				System.out.println("flag value : " + availFlag);
				//fw.write(line1);
				fw.append(line1);
				fw.append("\n");
			}
			line1 = bReader.readLine();
		}*/
		
		fw.flush();
		fw.close();
		bReader.close();
		//bReader1.close();
	}
	
	@Test
	public void test() throws IOException
	{
		this.removeDuplicateRecordsInCSVFile();
	}
	
	
	
}
