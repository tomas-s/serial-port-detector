package matra.server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/*
serialPort = /dev/serial/by-path/pci-0000:00:14.0-usb-0:1:1.1
tcpListenPort = 50005
logLevel = info

logPath =
 */
public class SuborWriter {
    PrintWriter out;
    String pathToMatra="/home/vbox/URC/matraserver.properties";
    String pathToGps="/home/vbox/URC/"; //upravit este


    public void write(String usb)
    {
        try {
            out = new PrintWriter(new java.io.FileWriter(StringPaths.getPathToMatra(),false));
        } catch (IOException e) {
            System.out.println("adresar /home/vbox/URC sa nenasiel");
            e.printStackTrace();
        }

        out.println("serialPort = "+usb);
        out.println("tcpListenPort = 50005");
        out.println("logLevel = info");
        out.println();
        out.println("!TODO: Add path to logging");
        out.println("logPath =");
        out.close();
        System.out.println("Najdene USB  zariadenie => [" + usb + "]");
        System.out.println("Matra server uspesne nakonfigurovany");
    }

}
