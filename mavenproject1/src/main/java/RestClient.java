
import com.mycompany.mavenproject1.Item;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class RestClient {

    public static void main(String[] args) {
// Items
        Item item1 = new Item("iPhone6", 699, 10);
        Item item2 = new Item("iPhone5", 399, 500);
        Client client = ClientBuilder.newClient();
        WebTarget targetAdd = client.target("http://localhost:15000").path("item/add");
// Add items
        Item response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(item1, MediaType.APPLICATION_JSON), Item.class);
        Item response2 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(item2, MediaType.APPLICATION_JSON), Item.class);
// Get all items
        WebTarget targetGet = client.target("http://localhost:15000").path("item/getAll");
        List<Item> items = targetGet.request(
                MediaType.APPLICATION_JSON_TYPE).get(new GenericType< List<Item> >() {
                });
        
        for(Item i : items)
            System.out.println("Item: " + i.toString());
        
    }
}
