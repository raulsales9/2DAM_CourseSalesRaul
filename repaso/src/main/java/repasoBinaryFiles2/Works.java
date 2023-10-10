
package repasoBinaryFiles2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author pc-raul
 */
public class Works {
    // definim les dades
    String[] works = {"Administrativo","cajero","comercial","dependiente","programador","basurero"};
    int[] cantidad = {34, 35, 44, 28, 14, 32};
    double[] sueldos = { 942.67, 856.42, 743.21, 572.21, 999.67, 534.21, 865.78};
    
    public void ReadFile(String name) throws IOException {
        DataInputStream file = new DataInputStream(new FileInputStream(name));
        
        while(file.available()>0){
            System.out.println("Trabajo: " + file.readUTF());
            System.out.println("horas/sem: " + file.readInt());
            System.out.println("Sueldo: " + file.readDouble());
            System.out.println();
        }
        file.close();
    }
    
    public void WriteFile(String name) throws IOException{
        DataOutputStream file = new DataOutputStream(new FileOutputStream(name));
        for(int i=0;i<this.works.length;i++){
            file.writeUTF(this.works[i]);
            file.writeInt(this.cantidad[i]);
            file.writeDouble(this.sueldos[i]);
        }
        file.close();
    }
    public static void main(String[] args) throws IOException{
        // arguments
        if(args.length!=2){
            System.out.println("");
            System.exit(0);
        }
        
        Works works = new Works();
        
        if(args[0].equals("read")){
            works.ReadFile(args[1]);
        }else if(args[0].equals("write")){
            works.WriteFile(args[1]);
        }else{
            System.out.println("No entenc l'ordre " +args[0]+"\n");
        }
    }
    
}
