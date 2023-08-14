import java.util.Scanner;

class Localidad {
    private String nombre;
    private double precio;
    private int espaciosDisponibles;
    private int boletosVendidos;

    public Localidad(String nombre, double precio, int espaciosDisponibles) {
        this.nombre = nombre;
        this.precio = precio;
        this.espaciosDisponibles = espaciosDisponibles;
        this.boletosVendidos = 0;
    }

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

    public boolean validarEspacio() {
        return espaciosDisponibles > 0;
    }

    public boolean validarDisponibilidad(int cantidad) {
        return espaciosDisponibles >= cantidad;
    }

    public boolean validarPrecio(double presupuesto) {
        return precio <= presupuesto;
    }

    public void venderBoletos(int cantidad) {
        espaciosDisponibles -= cantidad;
        boletosVendidos += cantidad;
    }
}

class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletosDeseados;
    private double presupuestoMaximo;
    private int boletosComprados;

    public Comprador(String nombre, String email, int cantidadBoletosDeseados, double presupuestoMaximo) {
        this.nombre = nombre;
        this.email = email;
        this.cantidadBoletosDeseados = cantidadBoletosDeseados;
        this.presupuestoMaximo = presupuestoMaximo;
        this.boletosComprados = 0;
    }

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

    public void comprarBoletos(Localidad localidad, int cantidad) {
        if (localidad.validarEspacio() && localidad.validarDisponibilidad(cantidad) && localidad.validarPrecio(presupuestoMaximo)) {
            localidad.venderBoletos(cantidad);
            boletosComprados += cantidad;
            System.out.println("Compra exitosa de " + cantidad + " boletos en la localidad " + localidad.getNombre());
        } else {
            System.out.println("No se pudo completar la compra.");
        }
    }
}

public class DriverProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Localidad localidad1 = new Localidad("Localidad 1", 100, 20);
        Localidad localidad5 = new Localidad("Localidad 5", 500, 20);
        Localidad localidad10 = new Localidad("Localidad 10", 1000, 20);

        Comprador compradorActual = null;

        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    compradorActual = crearComprador(scanner);
                    break;
                case 2:
                    if (compradorActual == null) {
                        System.out.println("Primero registre un comprador.");
                        break;
                    }
                    comprarBoletos(scanner, compradorActual, localidad1, localidad5, localidad10);
                    break;
                case 3:
                    consultarDisponibilidadTotal(localidad1, localidad5, localidad10);
                    break;
                case 4:
                    if (compradorActual == null) {
                        System.out.println("Primero registre un comprador.");
                        break;
                    }
                    consultarDisponibilidadIndividual(scanner, localidad1, localidad5, localidad10);
                    break;
                case 5:
                    generarReporteCaja(localidad1, localidad5, localidad10);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static void generarReporteCaja(Localidad localidad1, Localidad localidad5, Localidad localidad10) {
    }

    private static void consultarDisponibilidadIndividual(Scanner scanner, Localidad localidad1, Localidad localidad5,
            Localidad localidad10) {
    }

    private static void consultarDisponibilidadTotal(Localidad localidad1, Localidad localidad5,
            Localidad localidad10) {
    }

    private static void comprarBoletos(Scanner scanner, Comprador compradorActual, Localidad localidad1,
            Localidad localidad5, Localidad localidad10) {
    }

    private static Comprador crearComprador(Scanner scanner) {
        return null;
    }

    private static void mostrarMenu() {
    }
}