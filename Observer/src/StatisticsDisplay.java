public class StatisticsDisplay implements WeatherObserver {
    private String displayName;
    private double maxTemp = Double.MIN_VALUE;
    private double minTemp = Double.MAX_VALUE;
    private int readingCount = 0;

    public StatisticsDisplay(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void update(double temperature) {
        readingCount++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        if (temperature < minTemp) {
            minTemp = temperature;
        }

        System.out.printf("[%s] Stats - Current: %.1f°C | Min: %.1f°C | Max: %.1f°C | Readings: %d%n",
                displayName, temperature, minTemp, maxTemp, readingCount);
    }
}
