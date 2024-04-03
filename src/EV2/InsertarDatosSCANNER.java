package EV2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class InsertarDatosSCANNER {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta: ");
        String ruta = sc.nextLine();

        try {
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");

            insetar(conexion, ruta);
            listar(conexion);

            sc.close();
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insetar(Connection conexion, String ruta) throws SQLException {
        // * Usando Scanner
        try (Scanner scF = new Scanner(new File(ruta))) {
            String sql = "INSERT INTO DISTRIBUYE (CIF, COD_PRO, CANTIDAD) VALUES (?, ?, ?)";

            PreparedStatement st = conexion.prepareStatement(sql);

            String cif = "";
            String codPro = "";
            int cantiada = 0;

            while (scF.hasNextLine()) {
                cif = scF.nextLine();
                if (!scF.hasNextLine()) break;
                    codPro = scF.nextLine();
                if (!scF.hasNextLine()) break; 
                    cantiada = Integer.parseInt(scF.nextLine());

                st.setString(1, cif);
                st.setString(2, codPro);
                st.setInt(3, cantiada);

                st.executeUpdate();
                }
System.out.println("INSERTADOS");
            }catch(FileNotFoundException e){
            e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            

        } 
    


    private static void listar(Connection conexion) {
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
