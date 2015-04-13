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
    private String readFilePath;
    private String writeFilePath;
    private FileFormatStrategy readFormat;
    private FileFormatStrategy writeFormat;
    
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        this(reader);
        this.writer = writer;
        this.writeFilePath = writer.getFilePath();
        this.writeFormat = writer.getFormatStrategy();
    }

    public FileService(FileReaderStrategy reader) {
        this.reader = reader;
        this.readFilePath = reader.getFilePath();
        this.readFormat = reader.getFormatStrategy();
    }
    
    public FileService(FileWriterStrategy writer){
        this.writer = writer;
        this.writeFilePath = writer.getFilePath();
        this.writeFormat = writer.getFormatStrategy();
    }
    
    public List<LinkedHashMap<String, String>> getAllRecords() throws IOException {
        return reader.getAllRecords();
    }
    
    public void writeOverRecords(List<LinkedHashMap<String, String>> data, boolean useHeader) throws IOException {
        writer.saveOrUpdate(data, useHeader);
    }
    
    public void addNewRecords(List<LinkedHashMap<String, String>> data, boolean useHeader) throws IOException {
        writer.addNewRecords(data, useHeader);
    } 
        
        
    
    
    
    
    
    
    
}
