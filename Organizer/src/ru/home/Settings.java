package ru.home;

public class Settings {
   private static String dir = "C:\\Projects";

    public static String getDir() {
        return dir;
    }

    public static void setDir(String dir) {
        Settings.dir = dir;
    }
}
