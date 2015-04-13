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
public class TextFileReader implements FileReaderStrategy {
    private FileFormatStrategy formatStrategy;
    private String filePath;
    
    public TextFileReader(){}
    
    public TextFileReader(String filePath, FileFormatStrategy formatStrategy){
        this.filePath = filePath;
        this.formatStrategy = formatStrategy;         
    }
    
    @Override
    public List<LinkedHashMap<String, String>> getAllRecords() throws IOException {
        List<Map<String, String>> records =
                new ArrayList<Map<String, String>>();
        
        String rawData = "";
        
        File file = new File(filePath);
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            while(line != null){
                rawData += (line + "\n");
                line = in.readLine();
            }
        } catch(IOException ioe){
            throw ioe;
        } finally {
            try {
                in.close();
            } catch (Exception e){
                throw new IOException(e.getMessage());
            }
        }
        return formatStrategy.decodeAll(rawData);
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
