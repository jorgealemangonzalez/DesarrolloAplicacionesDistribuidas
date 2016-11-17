package BFSN.PublicAPI;


import BFSN.Beans.Station;
import BFSN.Beans.StationsStatistics;
import BFSN.Beans.Stations;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
@Path("/stations")
public class RESTStationsService {
    
    //Es una cache que se actualiza cada 60s
    private static Stations stations;
    
    @GET
    @Path("/getStations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations(){
        System.out.println("Get stations request...");
        return Response.status(200).entity(RESTStationsService.getStaticStations()).build();
    }
    
    //Para testear , no para la practica
    @POST
    @Path("/addStation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(Station station){
        System.out.println("Add station request...");
        System.out.println(station);
        stations.getStations().add(station);
        return Response.status(200).build();
    }
    //Para testear , no para la practica
    @POST
    @Path("/setStations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStations(Stations stations){
        System.out.println("Set stations request...");
        RESTStationsService.stations = stations;
        return Response.status(200).build();
    }
    
    
    
    @GET
    @Path("/getStationsStatistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStationsStatics(){
        System.out.println("Get station statistics request...");
        StationsStatistics ss = new StationsStatistics(stations);
        return Response.status(200).entity(ss).build();
    }
    
    static public Stations getStaticStations(){
        return stations;
    }
    
    static public void uploadStaticStations(Stations stationsU){
        stations = stationsU;
    }
}
