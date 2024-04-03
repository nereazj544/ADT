package EV1;

import java.io.File;

public class ConArgs {
    public static void main(String[] args) {
        String ruta = ".";

        if (args.length == 1) {
            ruta = args[0];

            File file = new File(ruta);

            if (file.exists()) {
                System.out.println("eliminar: " + ruta);

                borrar(file);
            } else
                System.err.println("No hay");
        }
    }

    private static void borrar(File file) {
        File[] files = file.listFiles();

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
