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

	public BufferedImage getEqualized(int filterSize) {

		Image toReturn = new Image(this.width,this.height);

		int n = (2*filterSize+1)*(2*filterSize+1);

		for(int x = filterSize; x<this.width-filterSize;x++){
			for(int y = filterSize; y<this.height-filterSize;y++) {

				int result = 0;
				double value = get(x,y);

				for(int i=-filterSize;i<=filterSize;i++){
					for(int j=-filterSize;j<=filterSize;j++){
						if(get(x+i, y+j)<value) { result++; }
					}
				}
				toReturn.setCol(x, y, new RGB((result*255)/n,(result*255)/n,(result*255)/n));
			}
		}
		return toReturn.getImage();
	}

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
