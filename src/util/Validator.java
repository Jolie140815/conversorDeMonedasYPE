//Practicando con Java: Challenge Conversor de Monedas

package util;

import model.Currency;
import java.util.List;

public class Validator {
    private List<Currency> currencies;  // Lista que contiene las monedas disponibles.

    // Constructor que recibe la lista de monedas y la inicializa.
    public Validator(List<Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * Este metodo verifica si un código de moneda es válido.
     *
     * @param currencyCode El código de la moneda que queremos validar.
     * @return True si el código es válido, false si no lo es.
     */
    public boolean isValidCurrencyCode(String currencyCode) {
        // Recorro todas las monedas en la lista para verificar si alguna coincide con el código ingresado.
        for (Currency currency : currencies) {
            // Si encuentro una moneda cuyo código coincida (ignorando mayúsculas y minúsculas), retorno true.
            if (currency.getCode().equalsIgnoreCase(currencyCode)) {
                return true;
            }
        }
        // Si no se encuentra ninguna moneda con ese código, retorno false.
        return false;
    }

    /**
     * Este metodo valida si una cantidad es válida.
     *
     * @param amount La cantidad que queremos validar.
     * @return True si la cantidad es mayor que 0, false si es 0 o negativa.
     */
    public boolean isValidAmount(double amount) {
        // La cantidad debe ser mayor a 0 para ser válida.
        return amount > 0;
    }

    /**
     * Este metodo imprime todos los códigos de moneda válidos disponibles.
     *
     * Imprime en la consola los códigos de las monedas que tenemos en la lista.
     */
    public void printValidCurrencyCodes() {
        System.out.println("Códigos de moneda válidos:");
        // Recorro todas las monedas y muestro su código.
        for (Currency currency : currencies) {
            System.out.println("- " + currency.getCode() + " (" + currency.getCode() + ")");
        }
    }
}