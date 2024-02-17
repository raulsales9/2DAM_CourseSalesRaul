
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 *
 * @author pc-raul
 */

//Clase principal para trabajar con ficheros xml
public class ModulesXML {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException,
            FileNotFoundException, TransformerException {
        FileXML fileXML = new FileXML();

        //verificamos el contenido de el xml y sus argumentos
        if (args.length < 2) {
            System.err.println("Uso incorrecto. Debes proporcionar al menos 2 argumentos: [read/write] [archivo.xml]");
            System.exit(1);
        }

        String operation = args[0];
        String fileName = args[1];

        // dependiendo de el caso a ejecutar se hará una cosa u otra
        switch (operation.toLowerCase()) {
            case "read":
                // Si la operación es "read", abrimos el archivo XML y mostramos los módulos
                Document doc = fileXML.openXML(fileName);
                fileXML.printModules(doc.getDocumentElement());
                break;
            case "write":
                // Si la operación es "write", creamos un nuevo archivo XML y escribimos un módulo en él
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileXML.writeModules(file);
                System.out.println("Módulo agregado y archivo actualizado.");
                break;
            default:
                // Si se especifica una operación no válida, mostramos un mensaje de error
                System.err.println("Operación no válida. Debes especificar 'read' o 'write'.");
                break;
        }
    }
}
