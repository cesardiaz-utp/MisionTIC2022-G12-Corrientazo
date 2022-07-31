package co.edu.utp.misiontic.cesardiaz.vista;

import java.sql.SQLException;
import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteController;
import co.edu.utp.misiontic.cesardiaz.excepcion.ObjetoNoExistenteException;
import co.edu.utp.misiontic.cesardiaz.excepcion.PagoInsuficienteException;

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
            try {
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
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error con la base de datos.");
                System.err.println("\t" + ex.getMessage());
            }
        }
    }

    private void gestionDatosMaestros() throws SQLException {
        var mostrarMenu = true;
        while (mostrarMenu) {
            System.out.println(".: GESTION DE DATOS MAESTROS :.");
            System.out.println("1 -> Creacion de mesas");
            System.out.println("2 -> Creacion de Opciones de Sopa");
            System.out.println("3 -> Creacion de Opciones de Principio");
            System.out.println("4 -> Creacion de Opciones de Carne");
            System.out.println("5 -> Creacion de Opciones de Ensalada");
            System.out.println("6 -> Creacion de Opciones de Jugo");
            System.out.println("7 -> Creacion de Adicionales");
            System.out.println("0 -> Volver al menú");
            var opcion = leerEntero("Ingrese su opcion: ");
            switch (opcion) {
                case 0:
                    mostrarMenu = false;
                    break;
                case 1:
                    controller.crearMesa();
                    break;
                case 2:
                    // TODO: Implementar
                    break;
                case 3:
                    // TODO: Implementar
                    break;
                case 4:
                    // TODO: Implementar
                    break;
                case 5:
                    // TODO: Implementar
                    break;
                case 6:
                    // TODO: Implementar
                    break;
                case 7:
                    // TODO: Implementar
                    break;
                default:
                    System.out.println("Opcion inválida. Intente de nuevo");
                    break;
            }
            esperarEnter();
        }
    }

    private void gestionPedidos() throws SQLException {
        var mesa = controller.seleccionarMesa();

        System.out.println(".: GESTION DE PEDIDOS :.");
        System.out.println("1 -> Agregar pedido a mesa");
        System.out.println("2 -> Agregar adicional a mesa");
        System.out.println("3 -> Entregar pedido");
        System.out.println("4 -> Pagar deuda mesa");
        System.out.println("5 -> Consultar estado de la mesa");
        System.out.println("0 -> Volver al menu principal");
        var opcion = leerEntero("Ingrese su opcion: ");
        switch (opcion) {
            case 0:
                break;
            case 1:
                controller.agregarPedido(mesa);
                break;
            case 2:
                // TODO: Agregar adicional a pedido
                break;
            case 3:
                controller.entregarPedido(mesa);
                break;
            case 4:
                try {
                    var devuelta = controller.pagarCuenta(mesa);
                    System.out.printf("La devuelta es de $ %,d %n", devuelta);
                } catch (PagoInsuficienteException e) {
                    mostrarError(e.getMessage());
                } catch (ObjetoNoExistenteException e) {
                    mostrarError("La información de los pedidos es inconsistente");
                }
                break;
            case 5:
                controller.mostrarEstadoMesa(mesa);
                break;
            default:
                System.out.println("Opcion inválida. Intente de nuevo");
                break;
        }
        esperarEnter();
    }

    private void esperarEnter() {
        System.out.print("Presione Enter para continuar.");
        entrada.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.err.println(error);
    }

    public Integer leerEntero(String mensaje) {
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
