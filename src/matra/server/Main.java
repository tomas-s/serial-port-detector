package matra.server;

import java.io.Console;
import java.io.IOException;

public class Main {

    public static void main (String [] args){

        int enter=0;
        SuborWriter suborWriter = new SuborWriter();
        DirReader dirReader = new DirReader();
        dirReader.makeOldArray();
        System.out.println("Vloz USB pockaj 10 seckund a stlac ENTER");
        try {
            enter = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (enter==10){
            dirReader.makeNewArray();

            System.out.println(dirReader.compareArray());
        }

        System.out.println("stlac ENTER pre ukoncenie programu");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (enter==10){
            System.out.println("Koniec");
        }


    }
}
