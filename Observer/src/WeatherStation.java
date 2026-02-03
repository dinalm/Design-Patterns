import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation implements Runnable {
    private List<WeatherObserver> observers;
    private double currentTemperature;
    private static final double MIN_TEMPERATURE = -30.0;
    private static final double MAX_TEMPERATURE = 45.0;
    private static final int MIN_UPDATE_INTERVAL = 1000;
    private static final int MAX_UPDATE_INTERVAL = 5000;
    private Random random;
    private volatile boolean running;

    public WeatherStation() {
        this.observers = new ArrayList<>();
        this.random = new Random();
        // Set initial random temperature within range
        this.currentTemperature = MIN_TEMPERATURE +
                (MAX_TEMPERATURE - MIN_TEMPERATURE) * random.nextDouble();
        this.running = true;

        System.out.printf("Weather Station initialized with temperature: %.1f°C%n",
                currentTemperature);
    }

    // Register an observer
    public void registerObserver(WeatherObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println(observer.getClass().getSimpleName() + " registered.");
        }
    }

    // Remove an observer
    public void removeObserver(WeatherObserver observer) {
        if (observers.remove(observer)) {
            System.out.println(observer.getClass().getSimpleName() + " removed.");
        }
    }

    // Notify all observers about temperature change
    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(currentTemperature);
        }
    }

    // Update temperature
    private void updateTemperature() {
        double change = random.nextBoolean() ? 1.0 : -1.0;
        double newTemperature = currentTemperature + change;

        if (newTemperature >= MIN_TEMPERATURE && newTemperature <= MAX_TEMPERATURE) {
            currentTemperature = newTemperature;
        } else {
            currentTemperature += -change;
        }
    }

    // Stop the weather station thread
    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        System.out.println("Weather Station thread started...\n");

        while (running) {
            try {
                int sleepTime = MIN_UPDATE_INTERVAL +
                        random.nextInt(MAX_UPDATE_INTERVAL - MIN_UPDATE_INTERVAL);
                Thread.sleep(sleepTime);
                updateTemperature();
                notifyObservers();

            } catch (InterruptedException e) {
                System.out.println("Weather Station thread interrupted.");
                break;
            }
        }

        System.out.println("\nWeather Station thread stopped.");
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }
}
