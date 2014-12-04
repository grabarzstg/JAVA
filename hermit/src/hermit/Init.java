package hermit;

public class Init {
	
	private double x;
	private double y;
	private double z;
	
	public Init() {
	}
	
	public Init(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}	
	
	public void setX(double x) {
		this.x = x;
	}
	public double getX() {
		return x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getZ() {
		return z;
	}
}
