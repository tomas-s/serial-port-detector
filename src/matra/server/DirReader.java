package matra.server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class DirReader {
    static Logger log = Logger.getLogger(ReadWithScanner.class.getName());
    String pathTest = "/home/tomas/programovanie/test";
    String path = "/dev/serial/by-path";
    DirectoryStream<Path> oldStream;
    DirectoryStream<Path> newStrean;
    DirOpener oldDir = new DirOpener(path);     //TODO: prepisat na path
    DirOpener newDir;
    ArrayList<Path> arrayListOld = new ArrayList<>();
    ArrayList<Path> arrayListNew = new ArrayList<>();
    BasicFileAttributes attr;


    public void makeOldArray()
    {
        oldStream = oldDir.getDir();
        this.setNoveUsb();
        for (Path file : oldStream) {
            try {
                attr = Files.readAttributes(file, BasicFileAttributes.class);
            } catch (IOException e) {
                e.printStackTrace();
                log.debug("chyba pri parsovani atributov v triede "+this.getClass());
            }

            if (attr.isOther()){
            arrayListOld.add(file);
            }

            if(arrayListOld.size()==1){
                initGps(arrayListOld.get(0).toString());
            }

        }
    }

    private void initGps(String usb) {
        System.out.println("uloz matru :" +usb);
        ReadWithScanner readWithScanner = new ReadWithScanner(StringPaths.getPathToGpsTest());
        try {
            readWithScanner.getLine(usb);
        } catch (IOException e) {
            e.printStackTrace();
            log.debug("Chyba pri otvarani suboru "+StringPaths.getPathToGpsTest()+" volane z class:"+this.getClass().toString());
        }

        log.info("ukladanie matry: "+usb);
    }

    public void makeNewArray(){
         arrayListNew = new ArrayList<>();
        for (Path file : newStrean) {
            try {
                attr = Files.readAttributes(file, BasicFileAttributes.class);
            } catch (IOException e) {
                System.out.println("chyba pri parsovani atributov v triede "+this.getClass());
            }

            if (attr.isOther()) {
                arrayListNew.add(file);
            }

        }
    }

    public String compareArray(){
        if (arrayListOld.size() == arrayListNew.size()){
            return "Nepripojili ste ziadne zariadenie na seriovy port";
        }
        else {
            if ((arrayListNew.size() - arrayListOld.size())==1) {
                arrayListNew.removeAll(arrayListOld);
                String vystup =  arrayListNew.get(0).getFileName().toString();
                SuborWriter suborWriter= new SuborWriter();
                suborWriter.write(vystup);
                return "OK";
            }
            else
                return "Pripojili ste viac ako jedno zariadenie";
        }
    }



    public void setNoveUsb(){
        newDir = new DirOpener(path);   // TODO: zmenit path na path..
        newStrean = newDir.getDir();
    }

}
