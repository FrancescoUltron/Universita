package io;

import java.io.*;
import java.util.HashMap;

public class LettoreFile {
    private HashMap<String, String> map;
    public LettoreFile(String file) throws IOException, FiletypeNotFoundException {
        map = new HashMap<String,String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
           String[] elements = line.split(":");
           map.put(elements[0], elements[1] );
        }
        if (! map.containsKey("filetype")) {
            throw new FiletypeNotFoundException("Filetype not found");
        }
    }

    public String getValue(String key) throws keyNotFoundException {
        String value = map.get(key);
        if (value == null) {
            throw new keyNotFoundException(key);
        }
        return map.get(key);
    }

}
