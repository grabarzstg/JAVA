package hermit;

public class Power {
	private double number;
	private double degree;

	
	public Power() {
	}
	
	public Power(double number, double degree) {
		super();
		this.number = number;
		this.degree = degree;
	}	
	
	public void setNumber(double number) {
		this.number = number;
	}
	public double getNumber() {
		return number;
	}
	public void setDegree(double degree) {
		this.degree = degree;
	}
	public double getDegree() {
		return degree;
	}
}
