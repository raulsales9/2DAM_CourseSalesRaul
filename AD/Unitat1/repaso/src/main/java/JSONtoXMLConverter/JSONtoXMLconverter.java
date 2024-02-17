
package JSONtoXMLConverter;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author pc-raul
 */
public class JSONtoXMLconverter {
    public static void main(String[] args) {
        try {
            JSONObject json = new JSONObject("{\"key\": \"value\"}");
            String xmlString = convertJSONToXML(json);
            System.out.println(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String convertJSONToXML(JSONObject json) {
        StringBuilder xml = new StringBuilder();
        xml.append("<root>\n");
        for (String key : json.keySet()) {
            Object value = json.get(key);
            xml.append("<").append(key).append(">");
            if (value instanceof JSONObject) {
                xml.append(convertJSONToXML((JSONObject) value));
            } else {
                xml.append(value.toString());
            }
            xml.append("</").append(key).append(">\n");
        }
        xml.append("</root>");
        return xml.toString();
    }
}