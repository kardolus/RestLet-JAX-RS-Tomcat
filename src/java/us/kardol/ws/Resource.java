package us.kardol.ws;

import javax.servlet.ServletContext;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {

    @Context
    private ServletContext sctx; // dependency injection

    public Resource() {
    }
    private static PaintingsList pList = new PaintingsList();
    
    static{
        pList = new FileParser().parse();
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/plain")
    public Response getPlain() {
        return Response.ok(pList.toString(), "text/plain").build();
    }
    
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/plain/{id: \\d+}")
    public Response getPlain(@PathParam("id") Integer id) {
        if(pList.get(id) != null){
            return Response.ok(pList.get(id).toString(), "text/plain").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("No such ID" + System.lineSeparator()).type(MediaType.TEXT_PLAIN).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/xml")
    public Response getXml() {
        return Response.ok(pList, "application/xml").build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("/xml/{id: \\d+}")
    public Response getXml(@PathParam("id") Integer id) {
        if(pList.get(id) != null){
            return Response.ok(pList.get(id), "application/xml").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("No such ID" + System.lineSeparator()).type(MediaType.TEXT_PLAIN).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json")
    public Response getJson() {
        return Response.ok(pList, "application/json").build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/json/{id: \\d+}")
    public Response getJson(@PathParam("id") Integer id) {
        if(pList.get(id) != null){
            return Response.ok(pList.get(id), "application/json").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("No such ID" + System.lineSeparator()).type(MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/create")
    public Response create(@FormParam("painter") String painter,
            @FormParam("title") String title,
            @FormParam("year") Integer year) {
        if(painter == null || title == null || year == null){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("Missing parameter: " + System.lineSeparator() +
                            "painter: " + painter + System.lineSeparator() + 
                            "title: " + title + System.lineSeparator() + 
                            "year: " + year).type(MediaType.TEXT_PLAIN).build();
        }
        Painting p = new Painting(painter, title, year);
        Integer newId = pList.add(p);
        return Response.ok("Created record: " + newId + System.lineSeparator(), "text/plain").build();
    }
    
    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/update/{id: \\d+}")
    public Response update(@PathParam("id") Integer id,
            @FormParam("painter") String painter,
            @FormParam("title") String title,
            @FormParam("year") Integer year) {
        if(painter == null && title == null && year == null){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("Missing parameter: " + System.lineSeparator() +
                            "painter: " + painter + System.lineSeparator() + 
                            "title: " + title + System.lineSeparator() + 
                            "year: " + year).type(MediaType.TEXT_PLAIN).build();
        }
        if(pList.get(id) == null){
           return Response.status(Response.Status.BAD_REQUEST).entity("No such ID" + System.lineSeparator()).type(MediaType.TEXT_PLAIN).build(); 
        }
        
        Painting p = pList.get(id);
        if(painter != null){
            p.setPainter(painter);
        }
        if(title != null){
            p.setTitle(title);
        }
        if(year != null){
            p.setYear(year);
        }
        return Response.ok("Updated record: " + id + System.lineSeparator(), "text/plain").build();
    }
    
    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/update/{id: \\d+}")
    public Response delete(@PathParam("id") Integer id){
        if(pList.get(id) == null){
           return Response.status(Response.Status.BAD_REQUEST).entity("No such ID" + System.lineSeparator()).type(MediaType.TEXT_PLAIN).build(); 
        }
        pList.remove(pList.get(id)); 
        return Response.ok("Deleted record: " + id + System.lineSeparator(), "text/plain").build();
    }
  
}
