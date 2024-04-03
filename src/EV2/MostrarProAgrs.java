package EV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MostrarProAgrs {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error, datos mal introducidos");
            return;
        }
        String codigo = args[0];
        try {
            if (FValido(codigo)) {
                listarProgramas(codigo);
            } else {
                System.out.println("ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarProgramas(String codigo) {
        
        try (Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123")){
            

                String sql = "Select p.nombre, p.ver FROM PROGRAMAS p INNER JOIN DESARROLLA d ON d.COD_PRO = p.COD_PRO WHERE d.COD_FAB = ?";

            PreparedStatement st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            ResultSet r = st.executeQuery();

            while (r.next()) {
                System.out.println("==================================");
                System.out.println("FABRICANTE: " + codigo);
                System.out.println("==================================");
                    System.out.println("Nombre del programa: " + r.getString("nombre"));
                    System.out.println("Version del programa: " + r.getString("ver"));

                
                

            }
        } catch (Exception e) {
            System.err.println("Error al listar a los fabricantes: " + e.getMessage());
        }
}

    private static boolean FValido(String codigo) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123")) {
            String sql = "SELECT COUNT(*) FROM FABRICANTES WHERE COD_FAB=?";

            PreparedStatement st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            ResultSet r = st.executeQuery();
            r.next();
            int count = r.getInt(1);
            return count > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
