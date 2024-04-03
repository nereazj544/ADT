package EV1;

import java.io.File;
import java.util.Scanner;

public class ConString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = "src\\R";
        File file = new File(ruta);
        String n = "src\\Carpeta\\DocNuevo.txt";
        String c = "src\\Carpeta";


        while (true) {
            System.out.println("\n seleccione una opcion");
            System.out.println("1- borrar");
            System.out.println("2- crear(Documento)");
            System.out.println("3- crear(Carpeta)");

            int e = sc.nextInt();
            switch (e) {
                case 1:
                    borrar(file);
                    break;
                case 2:
                    crear(n);
                case 3:
                    crearCar(c);
                default:
                    break;
            }
            sc.close();
        }



        
    }
    private static void crearCar(String c) {
        File foulder = new File(c);

        if (!foulder.exists()) {
            if (foulder.mkdir()) {
                System.out.println("Carpeta creada: " + foulder.getName());
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.println("Ya existe");
        }
    }
    public static void crear(String n) {
        File f = new File(n);
        try {
            if (f.createNewFile()) {
                System.out.println("Fichero creado: " + f.getName());
            }else{
                System.out.println("Ya existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void borrar(File file) {
        
        if (file.exists() && file.isDirectory()) {
            borrar(file);
            System.err.println("Eliminado");
        } else{
            System.err.println("error");
        }

        File[] files = file.listFiles(); //Te borra los archivos que haya dentro de la carpeta

        for (File fs : files) {
            if (fs.isFile()) {
                System.err.println("Archivo borrar: " + fs.getName());
                fs.delete();
            } else {
                borrar(fs);
                fs.delete();
            }
        }
    }

    
}
