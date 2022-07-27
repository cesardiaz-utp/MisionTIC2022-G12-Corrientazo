package co.edu.utp.misiontic.cesardiaz.vista;

import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteController;
import co.edu.utp.misiontic.cesardiaz.modelo.Bandeja;
import co.edu.utp.misiontic.cesardiaz.modelo.Carne;
import co.edu.utp.misiontic.cesardiaz.modelo.Completo;
import co.edu.utp.misiontic.cesardiaz.modelo.Ensalada;
import co.edu.utp.misiontic.cesardiaz.modelo.Jugo;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionPedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Principio;
import co.edu.utp.misiontic.cesardiaz.modelo.Sopa;

public class PedidoView {

    private Scanner entrada;
    private RestauranteController controller;

    public PedidoView(Scanner entrada, RestauranteController controller) {
        this.entrada = entrada;
        this.controller = controller;
    }

    public Pedido cargarPedido() {

        // Pedir el nombre del cliente
        System.out.print("Ingrese el nombre (descripcion) del cliente: ");
        var cliente = entrada.nextLine();

        // Elejir entre diferentes opciones
        OpcionPedido opcion = null;
        do {
            System.out.print("Cual es su elección de almuerzo? C -> Completo, B -> Bandeja\n(C/B): ");
            var valorOpcion = entrada.nextLine();
            switch (valorOpcion.toUpperCase()) {
                case "C":
                    opcion = new Completo(12_000);
                    ((Completo) opcion).setSopa(elejirOpcionSopa());
                    opcion.setPrincipio(elejirOpcionPrincipio());
                    opcion.setCarne(elejirOpcionCarne());
                    opcion.setEnsalada(elejirOpcionEnsalada());
                    opcion.setJugo(elejirOpcionJugo());
                    break;
                case "B":
                    opcion = new Bandeja(10_000);
                    opcion.setPrincipio(elejirOpcionPrincipio());
                    opcion.setCarne(elejirOpcionCarne());
                    opcion.setEnsalada(elejirOpcionEnsalada());
                    opcion.setJugo(elejirOpcionJugo());
                    break;
                default:
                    System.err.println("Ha ingresado una opción no válida");
                    System.err.println("Vuelve a intentar");
                    break;
            }
        } while (opcion == null);

        return new Pedido(cliente, opcion);
    }

    private Sopa elejirOpcionSopa() {
        Sopa respuesta = null;
        var opciones = controller.listarSopas();
        do {
            System.out.println(".: OPCIONES DE SOPAS :. ");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n",
                        i, opciones.get(i));
            }
            System.out.print("¿Cual es su elección?: ");
            var opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion >= 0 && opcion < opciones.size()) {
                respuesta = opciones.get(opcion);
            } else {
                System.err.println("Ha ingresado una opción no válida");
                System.err.println("Vuelve a intentar");
            }
        } while (respuesta == null);
        return respuesta;
    }

    private Principio elejirOpcionPrincipio() {
        Principio respuesta = null;
        var opciones = controller.listarPrincipios();
        do {
            System.out.println(".: OPCIONES DE PRINCIPIOS :. ");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n",
                        i, opciones.get(i));
            }
            System.out.print("¿Cual es su elección?: ");
            var opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion >= 0 && opcion < opciones.size()) {
                respuesta = opciones.get(opcion);
            } else {
                System.err.println("Ha ingresado una opción no válida");
                System.err.println("Vuelve a intentar");
            }
        } while (respuesta == null);
        return respuesta;
    }

    private Carne elejirOpcionCarne() {
        Carne respuesta = null;
        var opciones = controller.listarCarnes();
        do {
            System.out.println(".: OPCIONES DE CARNES :. ");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n",
                        i, opciones.get(i));
            }
            System.out.print("¿Cual es su elección?: ");
            var opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion >= 0 && opcion < opciones.size()) {
                respuesta = opciones.get(opcion);
            } else {
                System.err.println("Ha ingresado una opción no válida");
                System.err.println("Vuelve a intentar");
            }
        } while (respuesta == null);
        return respuesta;
    }

    private Ensalada elejirOpcionEnsalada() {
        Ensalada respuesta = null;
        var opciones = controller.listarEnsaladas();
        do {
            System.out.println(".: OPCIONES DE ENSALADAS :. ");
            System.out.printf("%d -> %s %n", -1, "Sin ensalada");

            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n",
                        i, opciones.get(i));
            }
            System.out.print("¿Cual es su elección?: ");
            var opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion == -1) {
                break;
            } else if (opcion >= 0 && opcion < opciones.size()) {
                respuesta = opciones.get(opcion);
            } else {
                System.err.println("Ha ingresado una opción no válida");
                System.err.println("Vuelve a intentar");
            }
        } while (respuesta == null);
        return respuesta;
    }

    private Jugo elejirOpcionJugo() {
        Jugo respuesta = null;
        var opciones = controller.listarJugos();
        do {
            System.out.println(".: OPCIONES DE JUGOS :. ");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n",
                        i, opciones.get(i));
            }
            System.out.print("¿Cual es su elección?: ");
            var opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion >= 0 && opcion < opciones.size()) {
                respuesta = opciones.get(opcion);
            } else {
                System.err.println("Ha ingresado una opción no válida");
                System.err.println("Vuelve a intentar");
            }
        } while (respuesta == null);
        return respuesta;
    }

}
