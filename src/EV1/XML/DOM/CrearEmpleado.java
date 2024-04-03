package EV1.XML.DOM;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class CrearEmpleado {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\nzjha\\Desktop\\Clonaciones\\ADT\\src\\EV1\\XML\\FICHEROS\\Empleados.dat");

        RandomAccessFile file = new RandomAccessFile(f, "rw");

        String [] a  = {"KURAI", "FARKAS", "UCHIHA", "EVANS"};
        int [] dep  = {10,10,20,30,10};
        Double [] salario = {1000.30, 3000.2, 8999.98, 786523.90};

        StringBuffer b = null;

        int n=a.length;


        for (int i = 0; i < n; i++) {
            file.writeInt(i+1);
            b = new StringBuffer(a[i]);
            b.setLength(10);
            file.writeChars(b.toString());
            file.writeInt(dep[i]);
            file.writeDouble(salario[i]);
        }
        file.close();

        System.out.println("Se ha creado");
    }
    
}
