package EV2;

import java.io.File;
import java.io.FileNotFoundException;
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
            // * Conexion con MySql
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/slq? UseSSL=true & serverTimezone=UTC", "sql", "123");

            while (true) {
                System.err.println("\n Selecciona una opcion: ");
                System.out.println("1. Listar Datos (Usuarios).");
                System.out.println("2. Listar Datos (Pedidos).");
                System.out.println("3. Introducir datos en la tabla de Usuarios.");
                System.out.println("4. Introducir datos en la tabla de Pedidos");
                System.out.println("5. Intorudcir datos desde un fichero (Usuarios)");
                System.out.println("6. Intorudcir datos desde un fichero (Pedidos) (NO FUNCIONA)");
                System.out.println("7.EXIT");

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
                        e1.introducirDatosFICHERO(conexion, sc, args);
                        break;

                    case 6:
                        e1.introducirDatosFICHEROPedidos(conexion, sc, args);

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

    private static void introducirDatosFICHERO(Connection conexion, Scanner sc, String[] args)
            throws SQLException, FileNotFoundException, Exception {
        // ! METODO UNO CON SCANNER
        System.out.println("Enter para continuar: ");
        String e = sc.nextLine();
        System.out.println("INTRODUCE LA RUTA DEL FICHERO DE TXT (USUARIOS): ");
        String ruta = sc.nextLine();

        File f = new File(ruta);

        if (!f.exists()) {
            System.err.println("El fichero no existe: " + ruta);
            return;
        }

        Scanner leer = new Scanner(f);
        String l;

        String sql = "INSERT INTO usuarios (id, nombre, email, telefono) VALUES (?,?,?,?)";
        PreparedStatement st = conexion.prepareStatement(sql);

        try {
            while (leer.hasNextLine()) {
                l = leer.nextLine();

                if (l.isEmpty()) {
                    continue;
                }

                String[] datos = l.replaceAll("[()']", "").split(", ");

                if (datos.length < 4) {
                    System.err.println("Error");
                    continue;
                }
                st.setString(1, datos[0]);
                st.setString(2, datos[1]);
                st.setString(3, datos[2]);
                st.setString(4, datos[3]);
                st.executeUpdate();
            }
            System.out.println("Datos");
        } finally {
            leer.close();
            st.close();
        }

        // ! METODO DOS CON AGRS + BUFFEREDREADER (ESTE SE MATENDRA COMENTADO).

        /*
         * if (args.length < 1) {
         * System.err.println("Uso de una ruta");
         * return;
         * }
         * String ruta = args[0];
         * 
         * File f = new File(ruta);
         * 
         * if (!f.exists()) {
         * System.err.println("El fichero no existe: " + ruta);
         * }
         * 
         * FileReader fileReader = new FileReader(f);
         * BufferedReader br = new BufferedReader(fileReader);
         * 
         * String linea;
         * String sql =
         * "INSERT INTO usuarios (id, nombre, email, telefono) VALUES (?,?,?,?)";
         * PreparedStatement st = conexion.prepareStatement(sql);
         * 
         * while ((linea = br.readLine()) != null) {
         * String[] datos = linea.split(",");
         * 
         * st.setString(1, datos[0]);
         * st.setString(2, datos[1]);
         * st.setString(3, datos[2]);
         * st.setString(4, datos[3]);
         * 
         * st.executeUpdate();
         * }
         * fileReader.close();
         * br.close();
         * st.close();
         * 
         */
    }

    private static void introducirDatosFICHEROPedidos(Connection conexion, Scanner sc, String[] args)
            throws SQLException, FileNotFoundException {

        System.out.println("Enter para continuar: ");
        String e = sc.nextLine();

        System.out.println("INTRODUCE LA RUTA DEL FICHERO DE TXT (PEDIDOS): ");
        String ruta = sc.nextLine();

        File f = new File(ruta);

        if (!f.exists()) {
            System.err.println("El fichero no existe: " + ruta);
            return;
        }

        
    }



}
