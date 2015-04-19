/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfileservice;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ryancorbin
 */
public interface FileWriterStrategy {
    
    /**
     * Writes a completely new file
     * @param path = file to be written to or created
     * @param updatedFileContent - List of LinkedHashMaps that contain string keys and values, will become the new file
     * @param useHeader - a boolean to determine if the formatting should use a header
     * @throws IOException - If the file isn't able to be created
     */
    
   void writeNewFile(String path, List<LinkedHashMap<String, String>> updatedFileContent) throws IOException, Exception;
   
   
   /**
    * Adds a single new record
    * @param path -  The location of the file to which the new record will be added
    * @param newData - The single record that will be added to the file.
    * @throws IOException - If the file can't be opened or written to
    */
   
   
   void addNewRecords(String path, List<LinkedHashMap<String, String>> newData) throws IOException, Exception;      
   
     
}
