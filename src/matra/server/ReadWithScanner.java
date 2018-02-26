package matra.server;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


public class ReadWithScanner {
    static Logger log = Logger.getLogger(ReadWithScanner.class.getName());
    BufferedReader file;
    String line;
    StringBuffer inputBuffer = new StringBuffer();
    FileOutputStream fileOut;
    String path="";



    public void initFile(String s){
        try {
            file = new BufferedReader(new FileReader(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa vytvorit BufferReader");
            log.debug("Nepodarilo sa vytvorit BufferReader");
        }
    }

    public ReadWithScanner(String aFileName){
        initFile(aFileName);
        path = aFileName;
    }


    public void getLine(String serial) throws IOException {
        boolean gps = false;
        while ((line = file.readLine()) != null) {
            if (line.contains("serialPort")){
                line="serialPort= "+serial;   //TODO: dopisat port
                gps=true;
            }
            if (line.contains("port=/dev/serial/by-path")){
                line="port=/dev/serial/by-path/"+serial; //TODO: dopisat port
            }
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        String inputStr = inputBuffer.toString();
        file.close();
        writeLine(inputStr,gps);
    }

    private void writeLine(String inputStr, boolean gps) {
        try {
            fileOut = new FileOutputStream(path);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa vytvorit vystupny subor");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa zapisat vystupny subor");
        }
        if (gps)
        {
            System.out.println("GPS sa podarilo nakonfigurovat.");
        }
        else {
            System.out.println("Vysielačka sa podarila nakonfigurovat.");
            log.info("Vysielačka sa podarila nakonfigurovat");

        }
    }


}