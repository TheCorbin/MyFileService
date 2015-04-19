/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfileservice;

import service.format.FileFormatStrategy;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ryancorbin
 */
class FileService {
    private FileReaderStrategy reader;
    private FileWriterStrategy writer;
    
    /**
     * Empty Constructor with no initial values.  Need to set reader or writer before you can set files
     */
    public FileService() {
    }
    
    /**
     * Constructer with full barrels
     * 
     * @param - reader - A read object based off of the ReadStrategy interface.
     * @param - writer - A writer object based off of the WriterStrategy interface.
     * 
     * 
     */
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        this.reader = reader;
        this.writer = writer;
    }
    
    /**
     * Setter for the Reader Strategy
     * 
     * 
     * @param reader - A reader strategy based on the FileReaderStrategy interface 
     * to properly read files
     * @throws IllegalArgumentException - if no reader object is passed in it will
     * throw a IllegalArgumentException
     */
    public void setReader(FileReaderStrategy reader) throws IllegalArgumentException{
        if(reader == null){
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }
    
    /** 
     * Setter for the Writer Strategy
     * 
     * 
     * @param writer - A writer strategy based on the FileWriterStrategy interface 
     * to properly read files
     * @throws IllegalArgumentException - if no reader object is passed in it will
     * throw a IllegalArgumentException
     */
    public void setWriter(FileWriterStrategy writer) throws IllegalArgumentException{
        if(writer == null){
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    /**
     * Reads all the lines from a file and returns a list of LinkedHashMaps with String
     * keys and values.  
     * 
     * @param path - String of the path of the file being read
     * @return - Returns a LinkedHashmap with String key and values
     * @throws IOException = If something goes wrong with reading the file
     * @throws IllegalArgumentException - If the path is null or zero characters
     */
    public List<LinkedHashMap<String, String>> getAllRecords(String path) throws IOException, IllegalArgumentException {
        if(path == null || path.length() == 0) {
            throw new IllegalArgumentException();
        }
        return reader.readAll(path);
    }
    
    /**
     * Writes a new File containing the data that is passed in through a LinkedHashMap
     * 
     * @param path - Where to create the new file in string form
     * @param data - the data that will be added into that new file
     * @throws Exception - Throws IOException of file writing problems and IllArgumentException
     * if an incorrect path or data is passed in.
     */
    
    
    public void writeNewFile(String path, List<LinkedHashMap<String, String>> data) throws Exception, IllegalArgumentException, IOException{
        if(path == null || path.length() == 0 || data == null || data.isEmpty()){
            throw new IllegalArgumentException();
        }
        writer.writeNewFile(path, data);
    }
   
    /**
     * Appends a new record to the end of a file.  
     * 
     * @param path - A string representing the files location
     * @param newRecord - A LinkedHashMap with String keys and values
     * @throws IOException - Something goes wrong with writing to the file
     * @throws IllegalArgumentException - thrown is the path or newRecord is bad
     */
    
    public void addNewRecords(String path, List<LinkedHashMap<String, String>> newRecord) throws Exception, IllegalArgumentException {
        if(path == null || path.length() == 0 || newRecord == null || newRecord.size() == 0){
            throw new IllegalArgumentException();
        }
        writer.addNewRecords(path, newRecord);
    } 
        
}
