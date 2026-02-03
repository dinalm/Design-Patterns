public class TemperatureAlert implements WeatherObserver {
    private String alertName;
    private static final double HOT_THRESHOLD = 30.0;
    private static final double COLD_THRESHOLD = 0.0;

    public TemperatureAlert(String alertName) {
        this.alertName = alertName;
    }

    @Override
    public void update(double temperature) {
        String message = String.format("[%s] Temperature: %.1f°C",
                alertName, temperature);

        if (temperature > HOT_THRESHOLD) {
            message += " - HOT WARNING!";
        } else if (temperature < COLD_THRESHOLD) {
            message += " - COLD WARNING!";
        } else {
            message += " - Normal conditions";
        }

        System.out.println(message);
    }
}
