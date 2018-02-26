package matra.server;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DirOpener {
    Path dir;
    DirectoryStream<Path> stream;

    public DirOpener(String path) {
        dir= Paths.get(path);



        try {
            stream = Files.newDirectoryStream(dir, "*");
        } catch (IOException e) {
            System.out.println("Nepodarilo sa otvorit adresar "+dir);
        }
    }
    public DirectoryStream<Path> getDir(){
        return stream;
    }

}
