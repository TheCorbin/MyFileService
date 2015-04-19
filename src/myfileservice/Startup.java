/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfileservice;

import service.format.CsvFileFormat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ryancorbin
 */
public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] names;
        
        FileService filer = 
                new FileService(new TextReader(new CsvFileFormat(",", true)),
                new TextWriter(new CsvFileFormat(",", true)));
        
        List<LinkedHashMap<String, String>> inData = new ArrayList<>();
        
        try{
            
        inData = filer.getAllRecords("src" + File.separatorChar  + "files" + File.separatorChar + "mydata.csv");
        
            for (int i =0; i<inData.size(); i++){   
                System.out.println(inData.get(i).toString());
            }
        } catch(IOException ioe){
            System.out.println(ioe.getMessage());
        } catch(IllegalArgumentException ie){
            System.out.println(ie.getMessage());
        }
        
        System.out.println("File Read!");
        
        try {
            filer.writeNewFile("src" + File.separatorChar  + "files" + File.separatorChar + "mydata2.csv", inData);
        } catch (IllegalArgumentException i){
            System.out.println(i.getMessage() + "ILLEGAL!");
        } catch (IOException io){
            System.out.println(io.getMessage() + "IO Problemo");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Hello");
        }
        
        List<LinkedHashMap<String,String>> updatedFileContent =
                new ArrayList<LinkedHashMap<String, String>>();
        
        LinkedHashMap<String, String> Person =
                new LinkedHashMap<String, String>();
        
        Person.put("firstName", "Sally");
        Person.put("lastName", "Jones");
        Person.put("age", "33");
        updatedFileContent.add(Person);
        
        Person = new LinkedHashMap<String, String>();
        Person.put("firstName", "Bill");
        Person.put("lastName", "Clinton");
        Person.put("age", "44");
        updatedFileContent.add(Person);
        
        try {
            filer.addNewRecords("src" + File.separatorChar  + "files" + File.separatorChar + "mydata2.csv", updatedFileContent);
            System.out.println("writing done...");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    
        
        
    }
}