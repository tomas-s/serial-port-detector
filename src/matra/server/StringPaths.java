package matra.server;

public class StringPaths {
    private static String  pathToMatra ="/home/vbox/URC/matraserver.properties";
    private static String pathToGps="/home/vbox/URC/master.ini";
    private static String pathToGpsTest ="/home/tomas/programovanie/test/master.ini";
    private static String pathToMatraTest="/home/tomas/programovanie/test/matraserver.properties";


    public static String getPathToMatra() {
        return pathToMatra;
    }

    public String getPathToGps() {
        return pathToGps;
    }

    public static String getPathToGpsTest() {
        return pathToGpsTest;
    }

    public static String getPathToMatraTest() {
        return pathToMatraTest;
    }
}
