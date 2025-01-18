//Practicando con Java: Challenge Conversor de Monedas

package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Esta clase se encarga de interactuar con la API de tasas de cambio.
 * Utilizo la API para obtener las tasas de cambio de la moneda base (USD) frente a otras monedas.
 */
public class CurrencyApiClient {

    // URL de la API que proporciona las tasas de cambio en tiempo real para USD
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/4fa596f274a0a80c131b0f12/latest/USD";

    /**
     * Este metodo solicita las tasas de cambio desde la API.
     *
     * @return Devuelve la respuesta de la API en formato JSON, que contiene las tasas de cambio.
     * @throws IOException Si hay un error al realizar la solicitud o recibir la respuesta.
     * @throws InterruptedException Si el hilo es interrumpido durante la operación.
     */
    public String getExchangeRates() throws IOException, InterruptedException {
        // Creo un cliente HTTP para hacer las solicitudes a la API.
        HttpClient client = HttpClient.newHttpClient();

        // Creo la solicitud HTTP para obtener los datos desde la URL de la API.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL)) // Defino la URL a la que se enviará la solicitud
                .build(); // Construyo la solicitud

        // Envío la solicitud y obtengo la respuesta.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Al recibir la respuesta, devuelvo el cuerpo (contenido) de la misma, que es el JSON con las tasas de cambio.
        return response.body();
    }
}