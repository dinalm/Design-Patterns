public class TemperatureDisplay implements WeatherObserver {
    private String displayName;

    public TemperatureDisplay(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void update(double temperature) {
        System.out.printf("[%s] Current temperature: %.1f°C%n",
                displayName, temperature);
    }
}
