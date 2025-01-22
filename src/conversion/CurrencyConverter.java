//Practicando con Java: Challenge Conversor de Monedas

package conversion;

import model.Currency;

import java.util.List;
/**
 * Clase responsable de convertir montos entre diferentes monedas
 * basándose en tasas de cambio.
 */
public class CurrencyConverter {
    /**
     * Convierte un monto de una moneda a otra.
     *
     * @param currencies Lista de objetos Currency disponibles con sus respectivas tasas de cambio.
     * @param fromCode   Código de la moneda desde la que se desea convertir (por ejemplo, "USD").
     * @param toCode     Código de la moneda a la que se desea convertir (por ejemplo, "EUR").
     * @param amount     Monto a convertir.
     * @return El monto convertido a la moneda destino.
     * @throws IllegalArgumentException Si uno de los códigos de moneda no es válido.
     */
    public double convert(List<Currency> currencies, String fromCode, String toCode, double amount) {
        // Busca la moneda origen por su código segpun la API sugerida por Alura Cursos
        Currency from = findCurrencyByCode(currencies, fromCode);
        // Busca la moneda destino por su código
        Currency to = findCurrencyByCode(currencies, toCode);

        // Verificamos que ambas monedas existan
        if (from == null || to == null) {
            throw new IllegalArgumentException("Código de moneda no válido");
        }

        // Conviertimos el monto a una moneda base (tasa de cambio 1.0) y luego a la moneda destino
        double amountInBase = amount / from.getExchangeRate();
        return amountInBase * to.getExchangeRate();
    }

    /**
     * Buscamios un objeto Currency en la lista por su código de moneda.
     *
     * @param currencies Lista de objetos Currency disponibles.
     * @param code       Código de la moneda que se busca (por ejemplo, "USD").
     * @return El objeto Currency correspondiente al código, o null si no se encuentra.
     */
    private Currency findCurrencyByCode(List<Currency> currencies, String code) {
        // Recorremos la lista para encontrar el objeto Currency con el código especificado
        for (Currency currency : currencies) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }
        return null; // Devuelve null si no se encuentra el código
    }
}