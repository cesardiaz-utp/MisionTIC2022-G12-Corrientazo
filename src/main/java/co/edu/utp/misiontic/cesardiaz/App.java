package co.edu.utp.misiontic.cesardiaz;

import co.edu.utp.misiontic.cesardiaz.excepcion.PagoInsuficienteException;
import co.edu.utp.misiontic.cesardiaz.modelo.Adicional;
import co.edu.utp.misiontic.cesardiaz.modelo.Bandeja;
import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Completo;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Principio;
import co.edu.utp.misiontic.cesardiaz.modelo.Sopa;

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
        var mesa = new Mesa("01");

        var sopa = new Sopa("Pastas");
        var principio = new Principio("Frijoles");
        var carne = new Carne("Res a la plancha");
        var ensalada = new Ensalada("Tomate");
        var jugo = new Jugo("Guayaba");

        var precio = 12_000;
        var opcion1 = new Completo(precio, sopa, principio, carne, ensalada, jugo);

        var pedido = new Pedido("Cesar Diaz", opcion1);
        pedido.agregarAdicional(new Adicional("Chicharron", 3_000));
        mesa.agregarPedido(pedido);

        precio = 10_000;
        var opcion2 = new Bandeja(precio, principio, carne, ensalada, jugo);
        pedido = new Pedido("Francisco Lozada", opcion2);
        mesa.agregarPedido(pedido);

        System.out.printf("Total de la mesa: $ %,d. %n", mesa.calcularTotal());

        var efectivo = 25_000;
        try {
            var devuelta = mesa.pago(efectivo);
            System.out.printf("Paga la cuenta con $ %,d y recibe una devuelta de $ %,d %n",
                    efectivo, devuelta);
        } catch (PagoInsuficienteException ex) {
            System.err.printf("Creo que va a tener que estregar algunos platos!!. Pagó solo $ %,d. %n", efectivo);
        }

        System.out.printf("Ahora el total de la mesa: $ %,d. %n", mesa.calcularTotal());


    }
}
