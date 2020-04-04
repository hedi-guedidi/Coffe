package io.github.oliviercailloux.samples.coffee;

/**
 * <p>
 * A <a href=
 * "https://www.startpage.com/sp/search?query=drip+coffee+maker&cat=pics">drip
 * coffee maker</a>. It uses a specific brand of coffee, which makes it able to
 * produce coffee of any strength from 0 to 10. It takes a constant time of 2
 * minutes to produce coffee (of strength higher than zero). Unrealistically, we
 * suppose here that it produces only one coffee at a time.
 * </p>
 * <p>
 * The energy required for producing a coffee (of strength higher than zero) is
 * an estimated 83 <a href="https://en.wikipedia.org/wiki/Watt_hour">watt
 * hours</a>.
 * </p>
 */
public class DripCoffeeMaker implements CoffeeMachine {
	private int producedCoffee;
	private double lastCoffeeStrength;
	
	public static DripCoffeeMaker newInstance(){
		return new DripCoffeeMaker();
	}
	private DripCoffeeMaker(int producedCoffee,double lastCoffeeStrength){
		this.producedCoffee=producedCoffee;
		this.lastCoffeeStrength=lastCoffeeStrength;
	}
	
	private DripCoffeeMaker() {
		this(0,0.0);
	}
	
	@Override
	public double getMaxStrength() {
		return 10.0;
	}

	@Override
	public int getTimeForCoffee(double strength) {
		if(strength >getMaxStrength() || strength<0) {
			throw new IllegalArgumentException();
		}
		if(strength == 0) {
			return 0;
		}
		return 120;
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
	public double getEnergySpent() throws IllegalStateException {
		if(producedCoffee==0) {
			throw new IllegalStateException();
		}
		if(lastCoffeeStrength ==0) {
			return 0;
		}
		return 83.0;
	}

}
