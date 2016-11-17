package BFSN.Beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Message {
    
    private long chat_id;
    private String text;
    
    public Message(){
        
    }
    
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
