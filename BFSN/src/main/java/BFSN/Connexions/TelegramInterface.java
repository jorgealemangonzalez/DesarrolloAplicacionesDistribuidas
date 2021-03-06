package BFSN.Connexions;


import BFSN.Beans.Message;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Middleware para connectar con la API de Telegram
 * @author jorgeAleman
 */
public class TelegramInterface {
    
    /**
     * Enviar mensajes a un usuario especifico con
     * la información deseada
     * @param id Token del usuario
     * @param text Mensaje a enviar
     * @return
     */
    public static boolean sendMesage(String id , String text){
        Client client = ClientBuilder.newClient();
        WebTarget targetSendMessage = client.target("https://api.telegram.org").path("/bot231813123:AAGgLXMBNeEIsNBFuQCrPNoL4hJ0pUZqhw0/sendMessage");
        Message mensaje = new Message(Long.parseLong(id),text);
        try{
            targetSendMessage.request()
                .post(Entity.entity(mensaje, MediaType.APPLICATION_JSON_TYPE), String.class);
        }catch(BadRequestException e){
            e.getMessage();
        }
        return true;
    }
}
