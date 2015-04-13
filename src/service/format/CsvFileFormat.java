package service.format;

import java.util.*;

/**
 *
 * @author ryancorbin
 */

public class CsvFileFormat implements FileFormatStrategy {
    
    
    @Override
    public List<LinkedHashMap<String, String>> decodeAll(String data) {
        List<LinkedHashMap<String, String>> records =
                new ArrayList<LinkedHashMap<String, String>>();
        
        String[] lines = data.split("\\n");
        String[] header = lines[0].split(",");
        
        for (int i=1; i < lines.length; i++){
            LinkedHashMap<String, String> record = new LinkedHashMap<String, String>();
            String[] rowData = lines[i].split(",");
            for(int j=0; j < rowData.length; j++){
                record.put(header[j], rowData[j]);
            }
            records.add(record);
        }
        return records;   
    }
    
    @Override
    public String encodeAll(List<LinkedHashMap<String, String>> updatedFileContent, boolean useHeader) {
        StringBuilder encodedData = new StringBuilder();
        
        LinkedHashMap<String, String> headerRec = updatedFileContent.get(0);
        Set<String> fields = headerRec.keySet();
        
        for(Iterator i = fields.iterator(); i.hasNext();) {
            encodedData.append(i.next()).append("\"").append(",");
        }
        
        int lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, "\n");
        
        if(!useHeader){
            encodedData = new StringBuilder();
        }
        
        for(LinkedHashMap<String, String> dataRow : updatedFileContent) {
            for(Iterator i = fields.iterator(); i.hasNext();){
                encodedData.append("\"").append(dataRow.get(i.next().toString()))
                        .append("\"").append(",");        
            }
            lastChar = encodedData.length()-1;
            encodedData.replace(lastChar, lastChar+1, "\n");    
        }
        
        lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar-1, null);
        
        return encodedData.toString();     
    }
    
    @Override
    public String encodeRecord(LinkedHashMap<String, String> newData) {
        StringBuilder encodedData = new StringBuilder();
        
        Set<String> fields = newData.keySet();
        
        for(Iterator i = fields.iterator(); i.hasNext();) {
            encodedData.append("\"").append(newData.get(i.next()))
                    .append("\"").append(",");
        }
        
        int lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, "\n");
        
        return encodedData.toString();
    }   
}
