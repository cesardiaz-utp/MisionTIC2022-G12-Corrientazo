package co.edu.utp.misiontic.cesardiaz;

import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteController;

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

            var controlador = new RestauranteController(sc);

            controlador.iniciarBaseDatos();


            var mesa = controlador.consultarMesa("01");
            System.out.println("La mesa consultada es: " + mesa);

            controlador.agregarPedido(mesa);
            System.out.println("El total de la mesa es: " + mesa.calcularTotal());

            var devuelta = controlador.pagarCuenta(mesa);
            System.out.printf("Los clientes de la mesa %s pagaron la cuenta y se devolvió $ %,d %n",
                mesa, devuelta);

            // pruebaVenta();
        } catch (Exception ex) {
            System.err.println("Ocurrio un error y salgo de la aplicación: \n\t" + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
