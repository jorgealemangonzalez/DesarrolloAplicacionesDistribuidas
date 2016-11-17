
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
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
public class TelegramInterface {
    
    /**
     *
     * @param id
     * @param text
     * @return
     */
    static boolean sendMesage(String id , String text){
        Client client = ClientBuilder.newClient();
        WebTarget targetSendMessage = client.target("https://api.telegram.org").path("/bot231813123:AAGgLXMBNeEIsNBFuQCrPNoL4hJ0pUZqhw0/sendMessage");
        Message mensaje = new Message(Long.parseLong(id),text);
        String response = targetSendMessage.request()
                .post(Entity.entity(mensaje, MediaType.APPLICATION_JSON_TYPE), String.class);
        return true;
    }
}
