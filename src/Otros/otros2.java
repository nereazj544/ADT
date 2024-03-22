package Otros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class otros2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");

            System.out.println("Introduce el numero del departamento");
            int num = sc.nextInt();

            if (depOk(num, conexion)) {

                String sql = " SELECT e.apellido, e.salario , d.nombre FROM slq.EMPLEADOS e INNER JOIN slq.DEPARTAMENTOS d ON e.departamento_id = d.id WHERE d.id =  ?";

                PreparedStatement st = conexion.prepareStatement(sql);
                // st.executeQuery();
                st.setInt(1, num);

                // ResultSet r = st.getResultSet();
                ResultSet r = st.executeQuery();

                if (r.next()) {
                    int nEmp = 0;
                    float media = 0;

                    // r.next();

                    System.out.println("==============");
                    System.out.println("DEPARTAMENTO " + num + " = " + r.getString(3));
                    System.out.println("==============");
                    // r.beforeFirst();

                    while (r.next()) {
                        // System.out.printf("%s, %s, %s%n", r.getString(1), r.getString(2),
                        // r.getString(3));

                        System.out.println("Nombre: " + r.getString(1));
                        System.out.println("Salario: " + r.getFloat(2));
                        System.out.println("Departamento: " + r.getString(3));

                        nEmp++;
                        media += r.getFloat(2);
                    }

                    System.out.println("------------");
                    System.out.println("Salario: " + media / nEmp);
                    System.out.println("Nº empleados: " + nEmp);

                } else {
                    System.out.println("El departamento " + num + " no contiene empleados");
                }
                st.close();
            } else {
                System.out.println("NO EXISTE");
            }
            sc.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean depOk(int num, Connection conexion) {
        try {
            String sql = "Select * FROM departamentos d WHERE d.id = ?";
            PreparedStatement st = conexion.prepareStatement(sql);

            st.setInt(1, num);

            // st.executeQuery();

            ResultSet r = st.executeQuery();
            return r.next();
            // return r.first();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }

        return false;
    }
}
