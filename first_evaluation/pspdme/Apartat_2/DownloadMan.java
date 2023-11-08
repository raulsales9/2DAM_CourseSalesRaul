import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.FileOutputStream;

import java.net.*;

public class DownloadMan {
    public void download(String url, String filename){
        try {
            URL myURL=new URL(url);

            // Determinem el tipus de fitxer
            URLConnection uc = myURL.openConnection();
            System.out.println(uc.getContentType());
            String type = (uc.getContentType().split("/"))[0];
            System.out.println("type:"+type);

            // Obrim el flux:
            InputStream is=myURL.openStream();

            if (type.equals("text")){
                // Descarreguem el fitxer com a text
                System.out.println(url+" és un text. Descarregant... ");

                InputStreamReader reader=new InputStreamReader(is);
                BufferedReader bReader=new BufferedReader(reader);
                FileWriter fWriter=new FileWriter(filename);
                String line;
                while ((line=bReader.readLine())!=null){
                    System.out.println(line);
                    fWriter.write(line);
                }
                fWriter.close();
                bReader.close();
                reader.close();

            } else {
                // Si no és un text, descarreguem el fitxer com a stream
                System.out.println(url+" és un fitxer. Descarregant... ");

                int bytes, bytesCopied=0;
                FileOutputStream fout=new FileOutputStream(filename);
                do {
                    bytes=is.read();
                    // Escrivim el byte al fitxer d'eixida
                    if (bytes!=-1) fout.write(bytes);
                    // Actualitzem el nº de bytes copiats
                    bytesCopied++;
                }while (bytes!=-1);
                System.out.println("Descarregats "+bytesCopied+ "bytes");
                fout.close();
            }
            // Tanquem l'stream de lectura
            is.close();
        } catch (MalformedURLException e) {
            System.out.println("Excepció: URL mal formatada!");
            return ;
        } catch (IOException e) {
            System.out.println("Excepció no controlada: "+e.toString());
            return ;
        }
    }

    public static void main (String[] arguments){
        DownloadMan dMan=new DownloadMan();

        if (arguments.length!=2) 
            System.out.println("Sintaxi incorrecta. Heu d'indicar una URL i un nom de fitxer");
        else{
            // Exemles d'urls:
            //String url="http://joamuran.net/docencia/psp";
            //String url2="http://joamuran.net/docencia/psp/u1/img/estats.png";
            dMan.download(arguments[0], arguments[1]);
        }

    }
}