package us.kardol.ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileParser {
    public PaintingsList parse(){
        File f = new File("/Users/misterclipping/NetBeansProjects/REST JAX-RS Tomcat/web/WEB-INF/data/paintings.db");
        String line;
        PaintingsList result = new PaintingsList();
        if(!f.exists()){
            System.out.println("No such file");
            return null;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                String[] fields = line.split(",");
                Painting p = new Painting(fields[0], fields[1], 
                        Integer.valueOf(fields[2]));
                result.add(p);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
