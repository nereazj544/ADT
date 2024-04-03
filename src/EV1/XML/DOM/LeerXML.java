package EV1.XML.DOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeerXML {
    public static void main(String[] args) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

        try {
            String ruta = "C:\\Users\\nzjha\\Desktop\\Clonaciones\\ADT\\src\\EV1\\XML\\FICHEROS";

            File fXml = new File(ruta + "\\Empleados.xml");

            DocumentBuilder dbuilder = f.newDocumentBuilder();

            Document d = dbuilder.parse(fXml);

            d.getDocumentElement().normalize();

            System.out.printf("Elemento raiz: %s %n", d.getDocumentElement().getNodeName());
            NodeList em = d.getElementsByTagName("empleado");
            System.out.printf("nodeos empleado a recorrer: %d %n", em.getLength());

            for (int i = 0; i < em.getLength(); i++) {
                Node emp = em.item(i);
                if (emp.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) emp;
                    System.out.printf("ID = %s %n", element.getElementsByTagName("id").item(0).getTextContent());
					System.out.printf(" * Apellido = %s %n", element.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.printf(" * Departamento = %s %n", element.getElementsByTagName("departamento").item(0).getTextContent());
					System.out.printf(" * Salario = %s %n", element.getElementsByTagName("salario").item(0).getTextContent());
                }
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
