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
            mostrarMenu();
            var opcion = leerEntero("Ingrese su opcion: ");
            switch (opcion) {
                case 1: // Agregar Mesas
                    
                    break;
            
                default:
                    break;
            }

        }
    }

    private void mostrarMenu() {

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
                if(opcion > maxValue) {
                    System.err.println("Opcion inválida\n Intente de nuevo");
                    opcion = null;
                }
            } catch (Exception e) {
                System.err.println("Opcion inválida\n Intente de nuevo");
            } finally {
                entrada.nextLine();
            }
        } while(opcion == null);
        return opcion;
    }
}
