package mesh;

import java.awt.image.BufferedImage;

public class ZBuffer {
	private double[] buffer;
	private int width, height;

	public ZBuffer(int width, int height) {
		this.width=width;
		this.height=height;
		buffer = new double[width*height];
		for(int i=0; i<buffer.length; i++) { buffer[i]=Double.NEGATIVE_INFINITY; }
	}

	public double get(int x, int y) {
		return buffer[x+y*width];
	}

	public void set(int x, int y, double value) { buffer[x+y*width] = value; }

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public BufferedImage getImage() {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(int i=0;i<buffer.length;i++) {
			if(buffer[i]>Double.NEGATIVE_INFINITY) {
				if(buffer[i]>max) { max = buffer[i]; }
				if(buffer[i]<min) { min = buffer[i]; }
			}
		}
		System.out.println("min = "+min+" ; max = "+max);
		Image img = new Image(width,height);
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(get(x,y)>Double.NEGATIVE_INFINITY) {
					int value = (int) (128+(255.0/(max-min))*(get(x,y)-(max+min)/2.0));
					img.setCol(x, y, new RGB(value,value,value));
				}
			}
		}
		return img.getImage();
	}

}
