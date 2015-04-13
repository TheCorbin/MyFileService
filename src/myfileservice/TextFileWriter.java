package myfileservice;

import service.format.FileFormatStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ryancorbin
 */
public class TextFileWriter implements FileWriterStrategy  {
    private FileFormatStrategy formatStrategy;
    private String filePath;
    
    public TextFileWriter(){}
    
    public TextFileWriter(String filePath, FileFormatStrategy formatStrategy){
        this.filePath = filePath;
        this.formatStrategy = formatStrategy;
    }
    
    public void saveOrUpdate(List<LinkedHashMap<String, String>> updatedFileContent, boolean useHeader)
            throws IOException {
        
        final boolean NO_APPEND = false;
        File file = new File(filePath);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, NO_APPEND)));
        
        String rawData = formatStrategy.encodeAll(updatedFileContent, useHeader);
        
        out.print(rawData);
        out.close();
    }
    
    public void addNewRecords(List<LinkedHashMap<String,String>> newData, boolean useHeader)
            throws IOException {
        
        final boolean APPEND = true;
        File file = new File(filePath);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, APPEND)));
        
        String rawData = formatStrategy.encodeAll(newData, useHeader);
        
        out.print(rawData);
        out.close();
    }
    
    @Override
    public String getFilePath(){
        return filePath;
    }
    
    @Override
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    
    @Override
    public FileFormatStrategy getFormatStrategy(){
        return formatStrategy;
    }
    
    @Override
    public void setFormatStrategy(FileFormatStrategy formatStrategy){
        this.formatStrategy = formatStrategy;
    }
    
    
}
