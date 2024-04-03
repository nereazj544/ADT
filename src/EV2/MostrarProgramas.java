package EV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MostrarProgramas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introcude el codigo del fabricante:");
        String codigo = sc.nextLine();

        if (!Valido(codigo)) {
            System.err.println("Error");
            return;
        }

    }

    private static boolean Valido(String codigo) {
        try (Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");) {

            String sql = "Select p.nombre, p.ver FROM PROGRAMAS p INNER JOIN DESARROLLA d ON d.COD_PRO = p.COD_PRO WHERE d.COD_FAB = ?";

            PreparedStatement st = conexion.prepareStatement(sql);
            st.setString(1, codigo);
            ResultSet r = st.executeQuery();

            if (r.next()) {
                do {
                    System.out.println("==================================");
                System.out.println("FABRICANTE: " + codigo);
                System.out.println("==================================");
                    System.out.println("Nombre del programa: " + r.getString("nombre"));
                    System.out.println("Version del programa: " + r.getString("ver") + "\n");

                } while (r.next());
                return true;

            } else {
                System.out.println("No se encontraron programas asocisados.");
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return false;
    }
}
