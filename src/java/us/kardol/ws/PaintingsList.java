package us.kardol.ws;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paintingsList")
public class PaintingsList {
    private List<Painting> paintings = new CopyOnWriteArrayList<Painting>();
    private final AtomicInteger id = new AtomicInteger();
    
    public Integer add(Painting p){
        p.setId(id.incrementAndGet());
        paintings.add(p);
        return id.get();
    }
    
    public void remove(Painting p){
        paintings.remove(p);
    }
    
    public Painting get(Integer id){
        for(Painting p : paintings){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    
    @Override 
    public String toString(){
        String result = "";
        for(Painting p : paintings){
            result += p.toString() + System.lineSeparator();
        }
        return result;
    }
    
    // uncomment to add extra wrapper for <painting> elements
    //@XmlElementWrapper(name = "paintings") 
    @XmlElement(name = "painting")
    public List<Painting> getPaintings(){
        return this.paintings;
    }
    public void setPaintings(List<Painting> paintings){
        this.paintings = paintings;
    }
}
