
package com.mycompany.testfile;
import java.io.File;

public class TestFile {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String fileseparator = System.getProperty("file.separator");
        //para que diga     System.out.println(System.getProperty("file.separator"));
        File newfile = new File( "firstfile.txt");
    }
}
