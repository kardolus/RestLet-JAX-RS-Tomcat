package us.kardol.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "painting")
public class Painting implements Comparable<Painting> {
    private Integer id;
    private String painter;
    private String title;
    private Integer year;
    
    public Painting(){
        
    }
    public Painting(Integer id, String painter, String title, Integer year){
        this.id = id;
        this.painter = painter;
        this.title = title;
        this.year = year;
    }
    public Painting(String painter, String title, Integer year){
        this.painter = painter;
        this.title = title;
        this.year = year;
    }
    
    @Override
    public String toString(){
        return this.id + ": " + this.painter + ", " + this.title + ", " + this.year;
    }
    @Override
    public boolean equals(Object o){
        return o instanceof Painting && this.id.equals(((Painting)o).getId());
    }
    @Override
    public int hashCode(){
        return id;
    }
    @Override
    public int compareTo(Painting o) {
        return this.id.compareTo(o.id);
    }
    
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getPainter(){
        return this.painter;
    } 
    public void setPainter(String name){
        this.painter = name;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String name){
        this.title = name;
    }
    public Integer getYear(){
        return this.year;
    }
    public void setYear(Integer year){
        this.year = year;
    }
}
