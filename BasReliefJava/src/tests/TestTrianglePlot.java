package tests;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mesh.*;

public class TestTrianglePlot {

	public static void main(String[] args) throws IOException {
		
		testPlot();
	}

	static void testSort() {
		Triangle tri = new Triangle(Math.random(),Math.random(),Math.random(),Math.random(),Math.random(),Math.random());
		System.out.println("Not sorted :\n"+tri.print());
		tri.sortY();
		System.out.println("Sorted :\n"+tri.print());
	}
	
	static void testPlot() throws IOException {
		BufferedImage img = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		
		TrianglePlot triPlot = new TrianglePlot(g);
		
		/*triPlot.fillLine(100, 43.1, 84.2, 4.3, 201.2);
		triPlot.fillLine(200, 234.91, 94.75, 0.2, 254.1);
		
		double x3=300*Math.random(), x1=300*Math.random(), y1=100*Math.random(), x2=300*Math.random(), y2=y1+200*Math.random();
		triPlot.fillTopTri(x3,x1,y1,x2,y2);
		
		x3=300*Math.random(); x2=300*Math.random(); x1=300*Math.random(); y1=100*Math.random(); y2=y1+200*Math.random();
		triPlot.fillBotTri(x3,x2,y2,y1,x1);*/
		
		Triangle tri = new Triangle(200*Math.random(),200*Math.random(),200*Math.random(),200*Math.random(),200*Math.random(),200*Math.random());
		//Triangle tri = new Triangle(100,150,154,150,23.4,180);
		triPlot.fillTriangle(tri);
		
		g.dispose();
		ImageIO.write(img, "png", new java.io.File("test.png"));
	}
}
