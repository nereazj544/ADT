package EV1.LeerDatos;

import java.io.File;

public class ConString {
    public static void main(String[] args) {
        String ruta = "src\\R";
        File file = new File(ruta);

        if (file.exists() && file.isDirectory()) {
            borrar(file);
            System.err.println("Eliminado");
        } else{
            System.err.println("error");
        }
    
    }
    private static void borrar(File file) {
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
