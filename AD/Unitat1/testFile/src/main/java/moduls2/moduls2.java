package moduls2;
        
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class moduls2{

    // several arrays with modules data
    String[] moduls={"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimèdia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
    int[]  hores={6, 3, 6, 5, 5, 3};
    double[] notes={8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

    public void readFiLe(String name) throws IOException {
        // Per lleginr el fitxer binari, creem un DataInputStream
        // a partir del FileInputStream creat a partir del nom
        DataInputStream f = new DataInputStream(new FileInputStream(name));

        // Mentre el DataInputStream tinga dades disponibles
        while (f.available()>0){
            // Llegirem del fitxer cada dada, amb l'ordre corresponent
            // en funció del tipus
            // (per tant, hem de saber l'ordre en què guardem!)
            System.out.println("Mòdul: " + f.readUTF());
            System.out.println("Hores: " + f.readInt());
            System.out.println("Notes: " + f.readDouble());
            System.out.println();
        }
        f.close();
    }

    public void writeFile(String nom) throws IOException{
        // Per escriure el fitxer, fem ús de DataOutputStream
        DataOutputStream f = new DataOutputStream(new FileOutputStream(nom));

        // Recorrerem qualsevol dels vectors (tots haurien de tindre)
        // la mateixa longitud
        for (int i=0;i<this.moduls.length;i++){
            // I per a cada posició, escriurem en funció del tipus de dada
            f.writeUTF(this.moduls[i]);
            f.writeInt(this.hores[i]);
            f.writeDouble(this.notes[i]);

        }
        f.close();
    }

    public static void main(String[] args) throws IOException {

        // Comprovem els arguments
        if (args.length!=2){
            System.out.println("Nombre d'arguments incorrecte.\n\nSintaxi: \n\t java Moduls [read | write] fitxer.dat");
            System.exit(0);
        }

        // Defining the class
        moduls2 moduls=new moduls2();

        // Depending the args, we will proceed
        if (args[0].equals("read")) 
          moduls.readFiLe(args[1]);
        else if (args[0].equals("write")) 
          moduls.writeFile(args[1]);
        else 
          System.out.println("No entenc l'ordre "+args[0]+"\n");
    }
}