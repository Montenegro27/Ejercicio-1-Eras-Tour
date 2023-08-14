// Universidad del Valle de Guatemala - POO
// Mauricio Enrique Montenegro González - 23679
// Ejercicio 1 - The eras tour
// Este programa tiene como finalidad el poder cotizar y ver asientos en sus distintas localidades para el tour.

import java.util.Scanner;
    // Definición de la clase Localidad
    class Localidad {
        // Atributos de la clase Localidad
        private String nombre;           // Nombre de la localidad
        private double precio;           // Precio del boleto en esta localidad
        private int espaciosDisponibles; // Número de boletos disponibles en esta localidad
        private int boletosVendidos;     // Número de boletos vendidos en esta localidad

        // Constructor de la clase Localidad
        public Localidad(String nombre, double precio, int espaciosDisponibles) {
            this.nombre = nombre;
            this.precio = precio;
            this.espaciosDisponibles = espaciosDisponibles;
            this.boletosVendidos = 0; // Inicialmente no hay boletos vendidos
        }

        // Métodos de acceso para los atributos de la clase Localidad
        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public int getEspaciosDisponibles() {
            return espaciosDisponibles;
        }

        public int getBoletosVendidos() {
            return boletosVendidos;
        }

        // Método para verificar si hay suficientes espacios disponibles
        public boolean validarEspacio() {
            return espaciosDisponibles > 0;
        }

        // Método para verificar si hay suficientes boletos disponibles
        public boolean validarDisponibilidad(int cantidad) {
            return espaciosDisponibles >= cantidad;
        }

        // Método para verificar si el precio del boleto es aceptable para el comprador
        public boolean validarPrecio(double presupuesto) {
            return precio <= presupuesto;
        }

        // Método para vender boletos y actualizar la disponibilidad y boletos vendidos
        public void venderBoletos(int cantidad) {
            espaciosDisponibles -= cantidad;
            boletosVendidos += cantidad;
        }
    }

    // Definición de la clase Comprador
    class Comprador {
        // Atributos de la clase Comprador
        private String nombre;                   // Nombre del comprador
        private String email;                    // Correo electrónico del comprador
        private int cantidadBoletosDeseados;      // Cantidad de boletos que el comprador desea comprar
        private double presupuestoMaximo;         // Presupuesto máximo del comprador para la compra de boletos
        private int boletosComprados;             // Número de boletos comprados por el comprador

        // Constructor de la clase Comprador
        public Comprador(String nombre, String email, int cantidadBoletosDeseados, double presupuestoMaximo) {
            this.nombre = nombre;
            this.email = email;
            this.cantidadBoletosDeseados = cantidadBoletosDeseados;
            this.presupuestoMaximo = presupuestoMaximo;
            this.boletosComprados = 0; // Inicialmente no ha comprado boletos
        }

        // Métodos de acceso para los atributos de la clase Comprador
        public String getNombre() {
            return nombre;
        }

        public String getEmail() {
            return email;
        }

        public int getCantidadBoletosDeseados() {
            return cantidadBoletosDeseados;
        }

        public double getPresupuestoMaximo() {
            return presupuestoMaximo;
        }

        public int getBoletosComprados() {
            return boletosComprados;
        }

        // Método para que el comprador compre boletos en una localidad específica
        public void comprarBoletos(Localidad localidad, int cantidad) {
            // Verificar si hay espacio, disponibilidad y el precio es aceptable
            if (localidad.validarEspacio() && localidad.validarDisponibilidad(cantidad) && localidad.validarPrecio(presupuestoMaximo)) {
                localidad.venderBoletos(cantidad); // Vender los boletos en la localidad
                boletosComprados += cantidad;     // Actualizar la cantidad de boletos comprados por el comprador
                System.out.println("Compra exitosa de " + cantidad + " boletos en la localidad " + localidad.getNombre());
            } else {
                System.out.println("No se pudo completar la compra.");
            }
        }
    }
    // Clase principal que maneja el programa de venta de boletos
    public class DriverProgram {
        public static void main(String[] args) {
            // Crear un objeto Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Crear tres objetos Localidad con diferentes atributos
            Localidad localidad1 = new Localidad("Localidad 1", 100, 20);
            Localidad localidad5 = new Localidad("Localidad 5", 500, 20);
            Localidad localidad10 = new Localidad("Localidad 10", 1000, 20);

            // Inicializar la variable que guarda al comprador actual como null
            Comprador compradorActual = null;

            int opcion;

            // Ciclo para mostrar el menú y ejecutar acciones basadas en la opción del usuario
            do {
                mostrarMenu(); // Mostrar las opciones disponibles
                opcion = scanner.nextInt(); // Leer la opción ingresada por el usuario

                // Usar una estructura switch para manejar diferentes opciones
                switch (opcion) {
                    case 1:
                        compradorActual = crearComprador(scanner); // Crear un nuevo comprador
                        break;
                    case 2:
                        if (compradorActual == null) {
                            System.out.println("Primero registre un comprador."); // Verificar si se ha creado un comprador
                            break;
                        }
                        comprarBoletos(scanner, compradorActual, localidad1, localidad5, localidad10); // Realizar una compra
                        break;
                    case 3:
                        consultarDisponibilidadTotal(localidad1, localidad5, localidad10); // Consultar disponibilidad total de boletos
                        break;
                    case 4:
                        if (compradorActual == null) {
                            System.out.println("Primero registre un comprador."); // Verificar si se ha creado un comprador
                            break;
                        }
                        consultarDisponibilidadIndividual(scanner, localidad1, localidad5, localidad10); // Consultar disponibilidad individual
                        break;
                    case 5:
                        generarReporteCaja(localidad1, localidad5, localidad10); // Generar un reporte de caja
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!"); // Salir del programa
                        break;
                    default:
                        System.out.println("Opción inválida"); // Manejar opciones no válidas
                }

            } while (opcion != 6); // Continuar el ciclo mientras la opción no sea 6

            scanner.close(); // Cerrar el objeto Scanner
        }

        // Método para mostrar el menú de opciones
        private static void mostrarMenu() {
            System.out.println("Opciones:");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
        }

        // Método para crear un nuevo comprador
        private static Comprador crearComprador(Scanner scanner) {
            System.out.print("Ingrese el nombre del comprador: ");
            String nombre = scanner.next();
        
            System.out.print("Ingrese el email del comprador: ");
            String email = scanner.next();
        
            System.out.print("Ingrese la cantidad de boletos deseados: ");
            int cantidadBoletosDeseados = scanner.nextInt();
        
            System.out.print("Ingrese el presupuesto máximo: ");
            double presupuestoMaximo = scanner.nextDouble();
        
            return new Comprador(nombre, email, cantidadBoletosDeseados, presupuestoMaximo); // Retornar un objeto Comprador creado
        }

        // Método para realizar la compra de boletos
        private static void comprarBoletos(Scanner scanner, Comprador compradorActual, Localidad localidad1,
                Localidad localidad5, Localidad localidad10) {
            System.out.println("Seleccione una localidad:");
            System.out.println("1. " + localidad1.getNombre() + " ($" + localidad1.getPrecio() + ")");
            System.out.println("2. " + localidad5.getNombre() + " ($" + localidad5.getPrecio() + ")");
            System.out.println("3. " + localidad10.getNombre() + " ($" + localidad10.getPrecio() + ")");
            System.out.print("Ingrese el número de localidad: ");
            int numLocalidad = scanner.nextInt();
            Localidad localidadSeleccionada = null;

            // Utilizar un switch para seleccionar la localidad basada en la opción ingresada por el usuario
            switch (numLocalidad) {
                case 1:
                    localidadSeleccionada = localidad1;
                    break;
                case 2:
                    localidadSeleccionada = localidad5;
                    break;
                case 3:
                    localidadSeleccionada = localidad10;
                    break;
                default:
                    System.out.println("Opción inválida."); // Manejar opciones no válidas
                    return;
            }

            System.out.print("Ingrese la cantidad de boletos a comprar: ");
            int cantidadBoletos = scanner.nextInt();

            // Verificar si la cantidad de boletos es válida
            if (cantidadBoletos <= 0) {
                System.out.println("Cantidad inválida.");
                return;
            }

            compradorActual.comprarBoletos(localidadSeleccionada, cantidadBoletos); // Realizar la compra de boletos
        }

        // Método para consultar la disponibilidad total de boletos
        private static void consultarDisponibilidadTotal(Localidad localidad1, Localidad localidad5,
                Localidad localidad10) {
            System.out.println("Disponibilidad total:");
            System.out.println(localidad1.getNombre() + ": " + localidad1.getEspaciosDisponibles() + " boletos disponibles");
            System.out.println(localidad5.getNombre() + ": " + localidad5.getEspaciosDisponibles() + " boletos disponibles");
            System.out.println(localidad10.getNombre() + ": " + localidad10.getEspaciosDisponibles() + " boletos disponibles");
        }

        // Método para consultar la disponibilidad de boletos en una localidad específica
        private static void consultarDisponibilidadIndividual(Scanner scanner, Localidad localidad1,
                Localidad localidad5, Localidad localidad10) {
            System.out.println("Seleccione una localidad:");
            System.out.println("1. " + localidad1.getNombre());
            System.out.println("2. " + localidad5.getNombre());
            System.out.println("3. " + localidad10.getNombre());
            System.out.print("Ingrese su opción: ");
            int opcionLocalidad = scanner.nextInt();

            // Utilizar un switch para mostrar la disponibilidad de boletos en la localidad seleccionada
            switch (opcionLocalidad) {
                case 1:
                    System.out.println(localidad1.getNombre() + ": " + localidad1.getEspaciosDisponibles() + " boletos disponibles");
                    break;
                case 2:
                    System.out.println(localidad5.getNombre() + ": " + localidad5.getEspaciosDisponibles() + " boletos disponibles");
                    break;
                case 3:
                    System.out.println(localidad10.getNombre() + ": " + localidad10.getEspaciosDisponibles() + " boletos disponibles");
                    break;
                default:
                    System.out.println("Opción inválida");
                    return;
            }
        }

        // Método para generar un reporte de caja
        private static void generarReporteCaja(Localidad localidad1, Localidad localidad5, Localidad localidad10) {
            // Calcular el total recaudado sumando los ingresos de todas las localidades
            double totalRecaudado = (localidad1.getPrecio() * localidad1.getBoletosVendidos()) +
                                (localidad5.getPrecio() * localidad5.getBoletosVendidos()) +
                                (localidad10.getPrecio() * localidad10.getBoletosVendidos());

            // Mostrar el reporte de caja con los ingresos por localidad y el total recaudado
            System.out.println("Reporte de caja:");
            System.out.println(localidad10.getNombre() + ": $" + (localidad10.getPrecio() * localidad10.getBoletosVendidos()));
            System.out.println("Total recaudado: $" + totalRecaudado);
        }
    }
