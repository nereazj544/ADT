package EV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class e1 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        try {
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");

            // Listar datos de las tablas

            while (true) {
                System.err.println("\n Selecciona una opcion: ");
                System.out.println("1. Listar Datos (Usuarios).");
                System.out.println("2. Listar Datos (Pedidos).");
                System.out.println(
                        "3. Introducir datos en la tabla de Usuarios.");
                System.out.println("4. Introducir datos en la tabla de Pedidos");
                System.out.println("5. Borrar (NO FUNCIONA)");
                System.out.println("6. Actualizar (NO FUNCIONA) ");
                System.out.println("7. EXIT");

                int e = sc.nextInt();
                switch (e) {
                    case 1:
                        e1.listarDatos(conexion);
                        break;

                    case 2:
                        e1.listarDatosPedidos(conexion);
                        break;
                    case 3:
                        e1.introducirDatos(conexion, sc);
                        break;

                    case 4:
                        e1.introducirDatosProductos(conexion, sc);
                        break;

                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:
                        System.err.println("¡¡ADIOSSSSS!!");
                        System.exit(0);

                    default:
                        System.err.println("ERROR");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // * LISTAR DATOS (PEDIDOS)
    private static void listarDatosPedidos(Connection conexion) throws SQLException {

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
    private static void listarDatos(Connection conexion) throws SQLException {
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

    // * INTRODUCIR DATOS EN USUARIOS
    private static void introducirDatos(Connection conexion, Scanner sc) throws SQLException {
        System.out.println("Ingrese los datos del usuario: ");
        System.out.println("--------------------------------------");
        System.out.println("ID: ");
        int id = sc.nextInt();
        System.out.println("--------------------------------------");
        System.out.println("Confirma nombre con ENter");
        String enter = sc.nextLine();
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("--------------------------------------");
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("--------------------------------------");
        System.out.println("Telefono: ");
        int telefono = sc.nextInt();
        System.out.println("--------------------------------------");

        String sql = "INSERT INTO usuarios (id, nombre, email, telefono) VALUES (?,?,?,?)";
        PreparedStatement st = conexion.prepareStatement(sql);

        st.setInt(1, id);
        st.setString(2, nombre);
        st.setString(3, email);
        st.setInt(4, telefono);

        int insetar = st.executeUpdate();

        if (insetar > 0) {
            System.out.println("Insertado");
        } else {
            System.out.println("Error");
        }
        st.close();

    }

    // * INTRODUCIR DATOS EN PEDIDOS

    private static void introducirDatosProductos(Connection conexion, Scanner sc) throws SQLException {
        System.out.println("Ingrese los datos del Pedidos: ");
        System.out.println("--------------------------------------");
        System.out.println("ID: ");
        int id = sc.nextInt();
        System.out.println("--------------------------------------");
        System.out.println("Confirma nombre con ENter");
        String enter = sc.nextLine();
        System.out.println("Usuario_Id:");
        int usuarioId = sc.nextInt();
        System.out.println("--------------------------------------");
        System.out.println("Confirma nombre con ENter");
        String enter2 = sc.nextLine();
        System.out.println("Producto:");
        String producto = sc.nextLine();
        System.out.println("--------------------------------------");
        System.out.println("Cantidad: ");
        Double cantidad = sc.nextDouble();
        System.out.println("--------------------------------------");
        System.out.println("Precio: ");
        int precio = sc.nextInt();
        System.out.println("--------------------------------------");

        String sql = "INSERT INTO pedidos (id, usuario_id, producto, cantidad, precio) VALUES (?,?,?,?,?)";
        PreparedStatement st = conexion.prepareStatement(sql);

        st.setInt(1, id);
        st.setInt(2, usuarioId);
        st.setString(3, producto);
        st.setDouble(4, cantidad);
        st.setInt(5, precio);

        int insetar = st.executeUpdate();

        if (insetar > 0) {
            System.out.println("Insertado");
        } else {
            System.out.println("Error");
        }
        st.close();

    }

}
