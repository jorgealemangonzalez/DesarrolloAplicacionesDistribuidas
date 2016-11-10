/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class BicingFakeInterface {
    static Stations getStations(){
        System.out.println("Downloading bicing stations...");
        Stations s = new Stations();
        return s;
    }
}
