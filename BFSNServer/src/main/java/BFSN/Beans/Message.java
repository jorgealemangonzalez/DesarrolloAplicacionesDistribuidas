package BFSN.Beans;

/**
 *Object that stores the necessary information
 * about a message for Telegram
 * @author Jorge Aleman , Arnau Guinart
 */
public class Message {
    
    private long chat_id;
    private String text;
    
    public Message(){
        
    }
     /**
     * Constructor of the Message
     * @param chat_id telegram token
     * @param text the message you want to send
     */
    public Message(long chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }
    
    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        
        this.chat_id = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

   
    
}
