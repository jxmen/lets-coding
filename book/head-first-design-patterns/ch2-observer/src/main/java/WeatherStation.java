public class WeatherStation {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();

		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		currentDisplay.display();

		weatherData.registerObserver(currentDisplay);
		weatherData.setMeasurements(80, 65, 30.4f);
		currentDisplay.display();
	}
}
