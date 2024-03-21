package EV2.CLASES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listarDatos {
    public static void main(String[] args) throws SQLException {
       
        try{
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

     public static void listarDatosPedidos(Connection conexion) throws SQLException {

        String sql2 = "SELECT * FROM pedidos";
        Statement st = conexion.createStatement();
        ResultSet r = st.executeQuery(sql2);

        while (r.next()) {
            System.out.println("--------------------------------------");
            System.out.println("TABLA PEDIDOS");
            System.out.println("--------------------------------------");
            System.out.println("Pedido Id: " + r.getInt("id"));
            System.out.println("User_Id: " + r.getString("usuario_id"));
            System.out.println("Producto: " + r.getString("producto"));
            System.out.println("cantidad: " + r.getInt("cantidad"));
            System.out.println("Fecha: " + r.getDate("fecha"));

        }

        r.close();
        st.close();
    }

    // * LISTAR DATOS
    public static void listarDatosUsuarios(Connection conexion) throws SQLException {
        String sql = "Select * from usuarios";
        Statement st = conexion.createStatement();
        ResultSet r = st.executeQuery(sql);

        while (r.next()) {
            System.out.println("--------------------------------------");
            System.out.println("TABLA USUARIOS");
            System.out.println("--------------------------------------");
            System.out.println("User Id: " + r.getInt("id"));
            System.out.println("Nombre: " + r.getString("nombre"));
            System.out.println("Email: " + r.getString("email"));
            System.out.println("telefono: " + r.getInt("telefono"));
            System.out.println("--------------------------------------\n");
        }

    }

}
