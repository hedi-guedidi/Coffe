package io.github.oliviercailloux.samples.coffee;

/**
 * A specific espresso machine, that produces coffee of strength up to 20, whose
 * power is 2000 watts, and that produces a coffee of strength <em>s</em> in
 * <em>140 + 2 * s</em> seconds.
 *
 */
public class MyEspressoMachine implements EspressoMachine {
	int producedCoffee;
	double lastCoffeeStrength;
	
	public static MyEspressoMachine newInstance(){
		return new MyEspressoMachine();
	}
	private MyEspressoMachine(int producedCoffee,double lastCoffeeStrength){
		this.producedCoffee=producedCoffee;
		this.lastCoffeeStrength=lastCoffeeStrength;
	}
	
	private MyEspressoMachine() {
		this(0,0.0);
	}
	
	@Override
	public double getMaxStrength() {
		return 20.0;
	}

	@Override
	public int getTimeForCoffee(double strength) {
		if(strength >getMaxStrength() || strength<0) {
			throw new IllegalArgumentException();
		}
		if(strength==0) {
			return 0;
		}
		return (int)(140+2*strength);
	}

	@Override
	public void produceCoffee(double strength) {
		if(strength >getMaxStrength() || strength<0) {
			throw new IllegalArgumentException();
		}
		producedCoffee++;
		lastCoffeeStrength=strength;
		
	}

	@Override
	public int getNumberOfCoffeesProduced() {
		return producedCoffee;
	}

	@Override
	public double getPower() {
		return 2000.0;
	}

	@Override
	public double getEnergySpent() throws IllegalStateException {
		if(producedCoffee==0) {
			throw new IllegalStateException();
		}
		if(lastCoffeeStrength ==0) {
			return 0;
		}
		return (getPower()*((double)getTimeForCoffee(lastCoffeeStrength)/(double)(3600)))+15.0;
	}

}
