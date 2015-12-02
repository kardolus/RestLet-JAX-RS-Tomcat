package us.kardol.ws;

public class Program {
    public static void main(String... args){
        FileParser fp = new FileParser();
        PaintingsList pl = fp.parse();
        System.out.println(System.getProperty("user.dir"));
        
        // parse method should go into static init block PaintingList class
        if(pl != null){
            System.out.println(pl.toString());
        }
    }
}
