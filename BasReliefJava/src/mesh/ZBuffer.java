package mesh;

public class ZBuffer {
	private double[] buffer;
	private int width, height;
	
	public ZBuffer(int width, int height) {
		this.width=width;
		this.height=height;
		buffer = new double[width*height];
		for(int i=0; i<buffer.length; i++) { buffer[i]=Double.MIN_VALUE; }
	}
	
	public double get(int x, int y) {
		return buffer[x+y*width];
	}
	
	public void set(int x, int y, double value) { buffer[x+y*width] = value; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
}
