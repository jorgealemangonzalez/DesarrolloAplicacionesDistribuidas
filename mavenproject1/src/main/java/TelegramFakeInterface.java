/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class TelegramFakeInterface {
    
    /**
     *
     * @param id
     * @param text
     * @return
     */
    static boolean sendMesage(String id , String text){
        System.out.println("Sending message through telegram...");
        return true;
    }
}
