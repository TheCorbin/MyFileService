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
    public static void main(String[] args) throws IOException {
        String[] names;
        
        FileService fileService = new FileService(new TextFileReader("src" + File.separatorChar + "myData.csv", new CsvFileFormat()),
                                                  new TextFileWriter("src" + File.separatorChar + "myData.csv", new CsvFileFormat()));
        
        List<LinkedHashMap<String, String>> inData = fileService.getAllRecords();
        List<String> strList = new ArrayList<>();
        
        for (Map record: inData){
            String firstName = record.get("firstName").toString();
            String lastName = record.get("lastName").toString();
            String fullName = firstName + " " + lastName;
            strList.add(fullName);
        }
        
        names = strList.toArray(new String[strList.size()]);
        
        System.out.println("File Read!");
        System.out.println(inData);
        
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
        
        fileService.addNewRecords(updatedFileContent, false);
        System.out.println("Hello!");
        System.out.println("writing done...");
    }
    
}
