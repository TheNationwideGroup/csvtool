package csvtool;

import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class App {

	public static void main(String[] args) {
		
		File directory = new File("C://123//coupa");

		File[] allFiles = directory.listFiles();
		for (File file : allFiles)  {
			//System.out.println(file.getPath());
			//System.out.println(file.getName());
			
			convertOneFile(file.getPath(), file.getName());
		}
		
	}

	
	public static void convertOneFile(String pathToFile, String outputName) {
		List<String[]> currentFile = null;
		try (Reader reader = Files.newBufferedReader(Path.of(pathToFile))) {
	        try (CSVReader csvReader = new CSVReader(reader)) {
	        	currentFile = csvReader.readAll();
	        	for (String[] row : currentFile) {
	        		
	        		for (int i=0; i<row.length; i++) {
	        			if (row[i] != null && row[i].trim().equals("R29401")) {
	        				row[i] = "875434";
	        			}
	        		}
	        	}
	        } catch (CsvException e) {
				e.printStackTrace();
			}
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String[] row : currentFile) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("---");
		
		
		String output = "C:\\Users\\EricChan\\output\\"+outputName;
		
		try {
			File file= new File(output);
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			@SuppressWarnings("resource")
			CSVWriter writer = new CSVWriter(filewriter);
			writer.writeAll(currentFile);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
