package io;

import java.io.File;
import java.io.IOException;

public class FileInfo {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java FileInfo <filename>");
            System.exit(0);
        }

        File f = new File(args[0]);
        if(f.exists()){
            System.out.println("length : " + f.length() );
            System.out.println("canRead : " + f.canRead() );
            System.out.println("canWrite : " + f.canWrite() );
            System.out.println("canExecute : " + f.canExecute() );
            System.out.println("isDirectory : " + f.isDirectory());
            try{
                System.out.println("getCanonicalPath : " + f.getCanonicalPath());
            } catch(IOException e){
                System.out.println(e);
            }
            System.out.println("getName : " + f.getName());
            System.out.println("getParent : " + f.getParent());
            System.out.println("getPath : " + f.getPath());
        }

    }
}
