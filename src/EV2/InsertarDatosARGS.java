package EV2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertarDatosARGS {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error");
            return;
        }

        String ruta = args[0];

        try {
            Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");
            insertarARGS (conexion, ruta);
            ListarDatos(conexion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    private static void insertarARGS(Connection conexion, String ruta) {
    String sql = "INSERT INTO DISTRIBUYE (CIF, COD_PRO, CANTIDAD) VALUES (?, ?, ?)";
    try(PreparedStatement st = conexion.prepareStatement(sql);
    BufferedReader br = new BufferedReader(new FileReader(ruta))) {

        String l;

        while ((l = br.readLine()) != null) {
            String [] datos = l.split(", ");
            if (datos.length == 3) {
                st.setString(1, datos[0].trim());
                st.setString(2, datos[1].trim());
                st.setInt(3, Integer.parseInt(datos[2].trim()));
                st.executeUpdate();
            }else{
                System.out.println("ERROR");
            }
        }
        System.out.println("DATOS INSERTADOS");
    }catch(Exception e){
        e.printStackTrace();
    }

    }

    private static void ListarDatos(Connection conexion) {
        String sql = "SELECT * FROM DISTRIBUYE";
        try (PreparedStatement st = conexion.prepareStatement(sql);
        ResultSet r = st.executeQuery()) {
            System.out.println("DATOS EN LA TABLA DISTRIBUYE: ");
            System.out.println("CIF\tCOD_PRO\tCANTIDAD");

            while (r.next()) {
                String cif = r.getString("CIF");
                String cod = r.getString("COD_PRO");
                String can = r.getString("CANTIDAD");
System.out.println(cif + "\t" + cod + "\t" + can);
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
