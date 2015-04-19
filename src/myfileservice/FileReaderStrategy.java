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
public interface FileReaderStrategy {
    
    /**
     * Reads all of the records in a file and outputs them in a LinkedHashMap
     * @param path = Path of the file being written to
     * @return - Returns a LinkedHashMap of string keys and values
     * @throws IOException  = throws an IOException if the file is null
     */
    
    public abstract List<LinkedHashMap<String, String>> readAll(String path) throws IOException;
      
    
}
