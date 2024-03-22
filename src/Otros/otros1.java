package Otros;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class otros1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");

            String sql = "SELECT nombre, apellido, salario from empleados WHERE departamento_id = ?";

            PreparedStatement st = conexion.prepareStatement(sql);
            /*
             * String sql =
             * "SELECT nombre, apellido, salario from empleados WHERE departamento_id = 1";
             * Statement st = conexion.prepareStatement();
             * 
             * ResultSet r = st.executeQuery(sql);
             * 
             */

            System.out.println("Introduce el numero del departamento: ");
            int num = sc.nextInt();
            st.setInt(1, num);

            ResultSet r = st.executeQuery();

            while (r.next()) {
                // System.out.println("==================");
                // System.out.printf("%s, %s, %s%n", r.getString(1),
                // r.getString(2),
                // r.getString(3)
                // );

                System.out.println("==================");
                System.out.println("Nombre: " + r.getString(1));
                System.out.println("Apellido: " + r.getString(2));
                System.out.println("Salario: " + r.getString(3));
                System.out.println("==================\n");

            }

            r.close();
            st.close();
            conexion.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
