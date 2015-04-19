package service.format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ryancorbin
 */

public class CsvFileFormat implements FileFormatStrategy {
    private final String deliminator;
    private final String BR = "\n";
    private final String QUOTES = "\"";
    private boolean hasHeader = false;
            

    /**
     * Constructor for the CSVFormatStrategy.  Must be given a deliminator.
     * @param deliminator - the item to be split upon 
     */
    
    public CsvFileFormat(String deliminator) {
        this.deliminator = deliminator;
    }
    
    public CsvFileFormat(String deliminator, Boolean hasHeader) {
        this.deliminator = deliminator;
        this.hasHeader = hasHeader;
    }
    
    /**
     * Transforms all the data from a file into a generic list of LinkedHashMaps
     * with string K&V.  
     * @param data
     * @return Returns a generic list of LinkedHashMaps with string K&V.
     */
      
    @Override
    public List<LinkedHashMap<String, String>> decodeAll(String data) {
        List<LinkedHashMap<String, String>> records = new ArrayList<>();
        
        String[] lines = data.split("\\n");
        
        if(hasHeader){
            String[] header = lines[0].split(deliminator);
            if(header[0].startsWith(QUOTES)){
                for(int i = 0; i<header.length; i++){
                    header[i] = header[i].substring(1, header[i].length()-1);
                }
            }
            
            for (int i=1; i < lines.length; i++){
                LinkedHashMap<String, String> record = new LinkedHashMap<>();
                String[] rowData = lines[i].split(",");
                if(rowData[0].startsWith(QUOTES)){
                for(int x = 0; x<rowData.length; x++){
                    rowData[x] = rowData[x].substring(1, rowData[x].length()-1);
                }
                for(int j=0; j < rowData.length; j++){
                    record.put(header[j], rowData[j]);
                }
                records.add(record);
                }
            }
        } else {
            for (int i=1; i < lines.length; i++){
                LinkedHashMap<String, String> record = new LinkedHashMap<>();
                String[] rowData = lines[i].split(",");
                for(int x = 0; x<rowData.length; x++){
                    rowData[x] = rowData[x].substring(1, rowData[x].length()-1);
                }
                for(int j=0; j < rowData.length; j++){
                    record.put(Integer.toString(j), rowData[j]);
                }
                records.add(record);
            }
        }
        return records;   
    }
    
   /**
    * Transforms a List of LinkedHashMaps containing String K&V into a String.
    * Each map is one record.
    * @param updatedFileContent - The information to be encoded
    * @return Returns a string representing all of the records in the List with proper
    * formatting and separation between data.
    */
    
    @Override
    public String encodeAll(List<LinkedHashMap<String, String>> updatedFileContent) {
        StringBuilder encodedData = new StringBuilder();
        System.out.println(updatedFileContent.toString() + "1");
        
        LinkedHashMap<String, String> headerRec = updatedFileContent.get(0);
        Set<String> Headerfields = headerRec.keySet();
        
        if (hasHeader){ 
            for(Iterator i = Headerfields.iterator(); i.hasNext();) {
                encodedData.append("\"").append(i.next()).append("\"").append(",");
            }
            int lastChar = encodedData.length()-1;
            encodedData.replace(lastChar, lastChar+1, BR); 
        }
        
        for(LinkedHashMap<String, String> dataRow : updatedFileContent) {
            for(Iterator i = Headerfields.iterator(); i.hasNext();){
                encodedData.append("\"").append(dataRow.get(i.next().toString()))
                        .append("\"").append(",");     
            }
            
            int lastChar2 = encodedData.length()-1;
            encodedData.replace(lastChar2, lastChar2+1, BR); 
        }
        
        System.out.println(encodedData);
        return encodedData.toString();     
    }
    
    
    /**
     * Transforms a LinkedHashMap representing a single record to be encoded. 
     * 
     * @param newData - A LinkedHashMap representing the record
     * 
     * @return A string representing the encoded data
     */
    
    
    @Override
    public String encodeRecord(LinkedHashMap<String, String> newData) {
        StringBuilder encodedData = new StringBuilder();
        
        Set<String> fields = newData.keySet();
        
        for(Iterator i = fields.iterator(); i.hasNext();) {
            encodedData.append("\"").append(newData.get(i.next()))
                    .append("\"").append(",");
        }
        
        int lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, BR);
        
        return encodedData.toString();
    }   
}
