package mcolorado.temperatureconverter;

public class TemperatureTransform {

    public static final String CELSIUS_UNIT = "C";
    public static final String KELVIN_UNIT = "K";
    public static final String FARENHEIT_UNIT = "F";

    public double farenheitToCelsius(double degreesInF) {
        return (degreesInF - 32) * 5 / 9;
    }

    public double celsiusToFarehnheit(double degreesInC) {
        return degreesInC * 9 / 5 + 32;
    }

    public double kelvinToFarenheit(double degreesInK) {
        return degreesInK * 9 / 5 - 459.67;
    }

    public double farenheitToKelvin(double degreesInF) {
        return (degreesInF + 459.67) * 5 / 9;
    }

    public double kelvinToCelsius(double degreesInK) {
        return degreesInK - 273.15;
    }

    public double celsiusToKelvin(double degreesInC) {
        return degreesInC + 273.15;
    }


}
