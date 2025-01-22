//Practicando con Java: Challenge Conversor de Monedas

package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class CurrencyService {

    // La URL de la API que se utiliza para obtener las tasas de cambio, en este caso, con la moneda base USD
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/13067274c45486a2fb471375/latest/USD";

    /**
     * Este metodo realiza la conversión de una moneda a otra.
     *
     * @param fromCurrency La moneda de origen.
     * @param toCurrency La moneda a la que se desea convertir.
     * @param amount La cantidad a convertir.
     * @return El monto convertido.
     */
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            // Primero, construyo la URL con la que me conectaré a la API.
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Leo la respuesta de la API en formato JSON.
            Reader reader = new InputStreamReader(connection.getInputStream());
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

            // Obtengo el objeto con las tasas de conversión.
            JsonObject conversionRates = response.getAsJsonObject("conversion_rates");

            // Verifico si las monedas solicitadas existen en la respuesta.
            if (!conversionRates.has(fromCurrency) || !conversionRates.has(toCurrency)) {
                throw new IllegalArgumentException("Moneda no válida");
            }

            // Obtengo las tasas de cambio de las dos monedas.
            double fromRate = conversionRates.get(fromCurrency).getAsDouble();
            double toRate = conversionRates.get(toCurrency).getAsDouble();

            // Realizo la conversión aplicando las tasas de cambio.
            return amount * (toRate / fromRate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;  // Si ocurre un error, devuelvo 0 como valor por defecto.
    }

    /**
     * Este metodo obtiene todas las monedas disponibles desde la API.
     *
     * @return Un conjunto de códigos de monedas disponibles.
     */
    public Set<String> fetchCurrencies() {
        Set<String> currencies = new HashSet<>();
        try {
            // Abro la conexión con la API.
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Leo la respuesta en formato JSON.
            Reader reader = new InputStreamReader(connection.getInputStream());
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

            // Obtengo las tasas de conversión de la respuesta.
            JsonObject conversionRates = response.getAsJsonObject("conversion_rates");

            // Agrego todos los códigos de moneda disponibles al conjunto.
            for (String currency : conversionRates.keySet()) {
                currencies.add(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencies;  // Devuelvo el conjunto de monedas disponibles.
    }

    /**
     * Este metodo verifica si un código de moneda es válido.
     *
     * @param currencyCode El código de la moneda que queremos validar.
     * @return True si la moneda es válida, false si no lo es.
     */
    public boolean isValidCurrency(String currencyCode) {
        // Obtengo las monedas disponibles.
        Set<String> availableCurrencies = fetchCurrencies();
        // Verifico si la moneda que se pasa como parámetro está en el conjunto de monedas disponibles.
        return availableCurrencies.contains(currencyCode);
    }
}

