package co.edu.utp.misiontic.cesardiaz.vista;

import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteController;

public class PrincipalView {

    private Scanner entrada;
    private RestauranteController controller;

    public PrincipalView(Scanner entrada, RestauranteController controller) {
        this.entrada = entrada;
        this.controller = controller;
    }

    public void iniciarAplicacion() {
        var mostrarMenu = true;
        while (mostrarMenu) {
            System.out.println(".: RESTAURANTE EL CORRIENTAZO :.");
            System.out.println("1 -> Gestion de pedidos");
            System.out.println("2 -> Gestion de datos maestros");
            System.out.println("0 -> Salir de la aplicación");

            var opcion = leerEntero("Ingrese su opcion: ");
            switch (opcion) {
                case 0:
                    mostrarMenu = false;
                    System.out.println("Hasta pronto!");
                    break;
                case 1:
                    gestionPedidos();
                    break;
                case 2:
                    gestionDatosMaestros();
                    break;
                default:
                    System.out.println("Opcion inválida. Intente de nuevo");
                    break;
            }

        }
    }

    private void gestionDatosMaestros() {
        System.out.println(".: GESTION DE DATOS MAESTROS :. ");
    }

    private void gestionPedidos() {
        var mesa = controller.seleccionarMesa();
        
        System.out.println(".: GESTION DE PEDIDOS :.");
        System.out.println("1 -> Agregar pedido a mesa");
        System.out.println("2 -> Agregar adicional a mesa");
        System.out.println("3 -> Pagar deuda mesa");
        System.out.println("4 -> Consultar estado de la mesa");
        System.out.println("0 -> Volver al menu principal");
        var opcion = leerEntero("Ingrese su opcion: ");
        switch (opcion) {
            case 0:
                esperarEnter();
                break;
            case 1:
                
                break;
            case 2:
                gestionDatosMaestros();
                break;
            default:
                System.out.println("Opcion inválida. Intente de nuevo");
                break;
        }
    }

    private void esperarEnter() {
        System.out.print("Presione Enter para continuar.");
        entrada.nextLine();
    }

    private Integer leerEntero(String mensaje) {
        return leerEntero(mensaje, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private Integer leerEntero(String mensaje, Integer minValue, Integer maxValue) {
        Integer opcion = null;
        do {
            try {
                System.out.print(mensaje);
                opcion = entrada.nextInt();
                if (opcion > maxValue) {
                    System.err.println("Opcion inválida\n Intente de nuevo");
                    opcion = null;
                }
            } catch (Exception e) {
                System.err.println("Opcion inválida\n Intente de nuevo");
            } finally {
                entrada.nextLine();
            }
        } while (opcion == null);
        return opcion;
    }
}
