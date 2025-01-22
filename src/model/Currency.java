//Practicando con Java: Challenge Conversor de Monedas

package model;
/**
 * Clase que representa una moneda, incluyendo su código y tasa de cambio.
 */
public class Currency {
    // Código de la moneda (por ejemplo, "USD" para dólar)
    private String code;
    // Tasa de cambio de la moneda con respecto a una moneda base (por ejemplo, 1.0 para USD)
    private double exchangeRate;

    /**
     * Constructor para inicializar un objeto Currency con su código y tasa de cambio.
     *
     * @param code         Código de la moneda (por ejemplo, "USD").
     * @param exchangeRate Tasa de cambio de la moneda en relación con una moneda base.
     */
    public Currency(String code, double exchangeRate) {
        this.code = code;
        this.exchangeRate = exchangeRate;
    }
    /**
     * Obtiene el código de la moneda.
     *
     * @return Código de la moneda como una cadena (por ejemplo, "USD").
     */
    public String getCode() {
        return code;
    }
    /**
     * Obtiene la tasa de cambio de la moneda.
     *
     * @return Tasa de cambio de la moneda como un valor double.
     */
    public double getExchangeRate() {
        return exchangeRate;
    }
}