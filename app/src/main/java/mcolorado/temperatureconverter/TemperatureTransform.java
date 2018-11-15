package mcolorado.temperatureconverter;

/**
 * Temperature conversion calculator class
 */
public class TemperatureTransform {

    /**
     * Constants that identify the text for each unit
     */
    public static final String CELSIUS_UNIT = "C";
    public static final String KELVIN_UNIT = "K";
    public static final String FARENHEIT_UNIT = "F";

    /**
     * Converts from farenheit to celsius
     *
     * @param degreesInF a Double representing the temperature in Farenheit
     * @return A Double representing the calculated value in Celsius
     */
    public double farenheitToCelsius(double degreesInF) {
        return (degreesInF - 32) * 5 / 9;
    }

    /**
     * Converts from celsius to farenheit
     *
     * @param degreesInC a Double representing the temperature in celsius
     * @return A Double representing the calculated value in farenheit
     */
    public double celsiusToFarehnheit(double degreesInC) {
        return degreesInC * 9 / 5 + 32;
    }

    /**
     * Converts from kelvin to farenheit
     *
     * @param degreesInK a Double representing the temperature in kelvin
     * @return A Double representing the calculated value in farenheit
     */
    public double kelvinToFarenheit(double degreesInK) {
        return degreesInK * 9 / 5 - 459.67;
    }

    /**
     * Converts from farenheit to kelvin
     *
     * @param degreesInF a Double representing the temperature in farenheit
     * @return A Double representing the calculated value in kelvin
     */
    public double farenheitToKelvin(double degreesInF) {
        return (degreesInF + 459.67) * 5 / 9;
    }

    /**
     * Converts from kelvin to celsius
     *
     * @param degreesInK a Double representing the temperature in kelvin
     * @return A Double representing the calculated value in celsius
     */
    public double kelvinToCelsius(double degreesInK) {
        return degreesInK - 273.15;
    }

    /**
     * Convert from celsius to kelvin
     *
     * @param degreesInC a Double representing the temperature in celsius
     * @return A Double representing the calculated value in kelvin
     */
    public double celsiusToKelvin(double degreesInC) {
        return degreesInC + 273.15;
    }
}
