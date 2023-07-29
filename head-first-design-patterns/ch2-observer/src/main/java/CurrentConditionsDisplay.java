public class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;

	public CurrentConditionsDisplay(WeatherData weatherData) {
		this.temperature = weatherData.getTemperature();
		this.humidity = weatherData.getHumidity();
		this.pressure = weatherData.getPressure();
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;

		display();
	}

	public void display() {
		System.out.println("현재 상태: 온도 " + temperature + "F, 습도: " + humidity + "%");
	}
}
