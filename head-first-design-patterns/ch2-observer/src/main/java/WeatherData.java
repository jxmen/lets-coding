import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
	private final List<Observer> observers;

	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyAllToObservers() {
		this.observers.forEach(o -> o.update(temperature, humidity, temperature));
	}

	@Override
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	@Override
	public float getHumidity() {
		return this.humidity;
	}

	@Override
	public float getTemperature() {
		return this.temperature;
	}

	@Override
	public float getPressure() {
		return this.pressure;
	}

	private void measurementsChanged() {
		notifyAllToObservers();
	}

}
