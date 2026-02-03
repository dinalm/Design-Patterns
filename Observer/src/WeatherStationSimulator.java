public class WeatherStationSimulator {
    public static void main(String[] args) {
        System.out.println("=== Weather Station Simulator ===\n");

        // Create weather station
        WeatherStation weatherStation = new WeatherStation();

        // Create observers
        WeatherObserver display1 = new TemperatureDisplay("Display-1");
        WeatherObserver display2 = new TemperatureDisplay("Display-2");
        WeatherObserver alert = new TemperatureAlert("Alert System");
        WeatherObserver stats = new StatisticsDisplay("Statistics");

        // Register observers
        weatherStation.registerObserver(display1);
        weatherStation.registerObserver(display2);
        weatherStation.registerObserver(alert);
        weatherStation.registerObserver(stats);

        System.out.println();

        // Start weather station thread
        Thread weatherThread = new Thread(weatherStation);
        weatherThread.start();

        try {
            // Let simulation run for 15 seconds
            System.out.println("--- Running simulation for 15 seconds ---\n");
            Thread.sleep(15000);

            // Remove one observer
            System.out.println("\n--- Removing Display-2 observer ---\n");
            weatherStation.removeObserver(display2);

            // Continue simulation for another 10 seconds
            System.out.println("--- Continuing simulation for 10 more seconds ---\n");
            Thread.sleep(10000);

            // Stop the weather station
            weatherStation.stop();
            weatherThread.join(2000);

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("\n=== Simulation Complete ===");
    }
}
