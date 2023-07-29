public interface Subject {
	void registerObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyAllToObservers();

	void setMeasurements(float temperature, float humidity, float pressure);

	float getHumidity();

	float getTemperature();

	float getPressure();
}
