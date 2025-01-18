//Practicando con Java: Challenge Conversor de Monedas

package menu;
import service.CurrencyService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Esta es la clase principal del programa. Aquí es donde todo empieza.
 * El objetivo principal es ofrecerte un conversor de monedas interactivo,
 * que te permita convertir valores entre varias monedas disponibles.
 */

public class MainMenu {

    // Mapa de monedas del menú a los códigos de la API
    private static final Map<String, String> currencyMap = new HashMap<>();

    static {
        currencyMap.put("Dólar", "USD");
        currencyMap.put("Peso argentino", "ARS");
        currencyMap.put("Real brasileño", "BRL");
        currencyMap.put("Peso colombiano", "COP");
    }

    public static void main(String[] args) {
        // Primero, necesitamos un escáner para recibir la entrada del usuario.
        Scanner scanner = new Scanner(System.in);
        // También creo una instancia de 'CurrencyService'. Este servicio es el que maneja la lógica de conversión de monedas.
        CurrencyService currencyService = new CurrencyService();

        // Te doy la bienvenida al programa y muestro un menú con las opciones disponibles.
        System.out.println("Bienvenido al conversor de monedas =]");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Salir");
        System.out.print("Elija una opción válida: ");

        // Aquí espero a que ingreses un número del menú.
        int option = scanner.nextInt();
        scanner.nextLine(); // Este metodo solo limpia el salto de línea sobrante para evitar errores.

        // Según la opción que elijas, voy a ejecutar una de estas acciones.
        switch (option) {
            case 1:
                handleCurrencyConversion(currencyService, "USD", "ARS", 1);
                break;
            case 2:
                handleCurrencyConversion(currencyService, "ARS", "USD", 1);
                break;
            case 3:
                handleCurrencyConversion(currencyService, "USD", "BRL", 1);
                break;
            case 4:
                handleCurrencyConversion(currencyService, "BRL", "USD", 1);
                break;
            case 5:
                handleCurrencyConversion(currencyService, "USD", "COP", 1);
                break;
            case 6:
                handleCurrencyConversion(currencyService, "COP", "USD", 1);
                break;
            // Si decides salir, te despido con un mensaje y termino el programa.
            case 7:
                System.out.println("Saliendo...");
                System.exit(0);
                break;

            default:
                // Si eliges algo que no está en el menú, te lo digo para que intentes de nuevo.
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }
    /**
     * Este metodo maneja la lógica de la conversión de monedas.
     * Cuando eliges una opción, este metodo se encarga de preguntarte el monto
     * y calcular el resultado de la conversión.
     *
     * @param currencyService El servicio que realiza la conversión de monedas.
     * @param fromCurrency    El código de la moneda de origen (por ejemplo, "USD").
     * @param toCurrency      El código de la moneda de destino (por ejemplo, "ARS").
     * @param amount          El monto a convertir. Aunque aquí empieza en 1, luego se actualiza.
     */
    private static void handleCurrencyConversion(CurrencyService currencyService, String fromCurrency, String toCurrency, double amount) {
        // Te pregunto cuánto dinero quieres convertir.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a convertir: ");
        amount = scanner.nextDouble();
        //Antes de hacer nada, verifico que las monedas sean válidas y que el monto sea positivo.
        if (currencyService.isValidCurrency(fromCurrency) && currencyService.isValidCurrency(toCurrency) && amount > 0) {
            // Sitodo esta bien, realizo la conversión usando el servicio.
            double result = currencyService.convertCurrency(fromCurrency, toCurrency, amount);
            // Finalmente, imprimo el resultado de la conversión.
            System.out.println("Resultado: " + result + " " + toCurrency);
        } else {
            // Si algo está mal, te lo informo. Tal vez el código de moneda no existe o el monto es negativo.
            System.out.println("Error: Código de moneda no válido o cantidad negativa.");
        }
    }
}
