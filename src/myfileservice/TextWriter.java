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
public class TextWriter implements FileWriterStrategy  {
    private FileFormatStrategy formatStrategy;
    private String tempString;
            
    
    
    /**
     * Constructor that creates a TextWriter with a formatStrategy.
     * @param formatStrategy - the kind of format Strategy that the textWriter will become.
     */
    
    public TextWriter(FileFormatStrategy formatStrategy){
        this.formatStrategy = formatStrategy;
    }
    
    /**
     * Sets the format strategy to be used for formatting data that is to be written.
     * 
     * @param format - accepts a format object based on the formatStrategy interface.
     * @throws IllegalArgumentException - If the formatStrategy is null
     */
    
    
    public void setFormat(FileFormatStrategy format) throws IllegalArgumentException {
        if(format == null){
            throw new IllegalArgumentException();
        }
        this.formatStrategy = format;
    }
    
    /**
     * Writes a new file containing the data passed in as a list of LinkedHashMaps
     * with String K&A
     * @param path - A string representing the file path
     * @param data - the data to be written to the file. 
     * @throws IOException - If the PrintWriter can't print to the file
     * @throws Exception - If the PrintWrter can't close
     */
    
    @Override
    public void writeNewFile(String path, List<LinkedHashMap<String, String>> data)
            throws IOException, Exception {
        
        if(path == null || path.length() == 0 || data == null || data.isEmpty()){
            throw new IllegalArgumentException();
        }
        
        tempString = formatStrategy.encodeAll(data);
        final boolean APPEND = false;
        File file = new File(path);
        PrintWriter out = null;
        
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file, APPEND)));
            out.print(tempString);
        } catch(IOException ioe){
            throw new IOException(ioe);
        } finally {
            try{
                out.close();
            }catch(Exception e){
                throw new Exception(e);
            }
        }
    }
    
    /**
     * Writes a single record into an existing file.  If no file exist where
     * the path points, it will write a new files.
     * @param path - The path where the file will be appended to or written
     * @param newData - The new record or data to be written
     * @param useHeader - the file to be written may or ma not use a header
     * @throws IOException - If the file can't be written to
     * @throws Exception - If the file can't be closed
     * @throws Ille
     */
    
    public void addNewRecords(String path, List<LinkedHashMap<String,String>> newData)
            throws IOException, Exception {
        if (path== null || path.length() == 0 || newData == null || newData.size() == 0){
            throw new IllegalArgumentException();
        }
        String tempString = "";
        
        for(LinkedHashMap<String, String> Record: newData){
            tempString += formatStrategy.encodeRecord(Record);
        }    
            final boolean APPEND = true;
            File file = new File(path);
        PrintWriter out = null;
        
        try{
             out = new PrintWriter(new BufferedWriter(new FileWriter(file, APPEND)));
             out.print(tempString);
        }catch(IOException ioe){
            throw new IOException(ioe);
        }finally {
            try{
                out.close();
            }catch(Exception e){
                throw new Exception(e);
            }
        }
    }  
}
