package co.edu.utp.misiontic.cesardiaz;

import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteConsolaController;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        try (var sc = new Scanner(System.in)) {

            var controlador = new RestauranteConsolaController(sc);

            controlador.iniciarAplicacion();


        } catch (Exception ex) {
            System.err.println("Ocurrio un error y salgo de la aplicación: \n\t" + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
