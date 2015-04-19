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
public class TextReader implements FileReaderStrategy {
    private FileFormatStrategy formatStrategy;
    private final String BR = "\n";
    
    /**
     * Constructor that sets the FileFormatStrategy when an instance is created
     * 
     * @param formatStrategy - Accepts a FormatStrategy based off the formatStrategy interface
     * @throws IllegalargumentException - If no formatter object is found, you'll have a problem
     */
    
    public TextReader(FileFormatStrategy formatStrategy) throws IllegalArgumentException{
        setFormatStrategy(formatStrategy);       
    }
    
    @Override
    public List<LinkedHashMap<String, String>> readAll(String path) throws IOException, IllegalArgumentException{
        if(path == null || path.length() == 0){
            throw new IllegalArgumentException();
        }
        
        List<Map<String, String>> records =
                new ArrayList<Map<String, String>>();
        
        String rawData = "";
        
        File file = new File(path);
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            while(line != null){
                rawData += line + BR;
                line = in.readLine();
            }
        } catch(IOException ioe){
            throw new IOException(ioe);
        } finally {
            try {
                in.close();
            } catch (Exception e){
                throw new IOException(e.getMessage());
            }
        }
        return formatStrategy.decodeAll(rawData);
    }
    
    /**
     * Sets a format object based on the FormatStrategy interface.  Can be used if you change your
     * mind after the initial construction
     * 
     * @param formatStrategy - Accepts a Format object based off the FormatStrategy
     * interface.
     * @throws IllegalArgumentException - Throws an IllegalArgumentException if
     * no format object is passed in.
     */
    
    public void setFormatStrategy(FileFormatStrategy formatStrategy) throws IllegalArgumentException {
        if(formatStrategy == null){
            throw new IllegalArgumentException();
        }
        
        this.formatStrategy = formatStrategy;
    }
    
    
    
    
}
