
package uses;

import java.io.File;


public class uses {
    public static void Test(String[] args){
        System.out.println("Este ejercicio es de prueba");
        String route = args[0];
        File file = new File(route);
        
        if(file.exists()){
            if(file.isFile()){
                System.out.println("Size" + file.length());
                System.out.println("exec" + file.canExecute());
                System.out.println("Read" + file.canRead());
                System.out.println("Write" + file.canWrite());
                
                
            }
        }
        else{
            System.out.println("Ha habido un error");
        }
    }
    
    
    
}
