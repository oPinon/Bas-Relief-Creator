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
		
		triPlot.fillTopTri(10.2, 293.4, 12.4, 245.3, 283.1);
		
		triPlot.fillBotTri(12.4, 246.2, 248, 14.3, 45.3);*/
		
		Triangle tri = new Triangle(300*Math.random(),300*Math.random(),300*Math.random(),300*Math.random(),300*Math.random(),300*Math.random());
		triPlot.fillTriangle(tri);
		
		g.dispose();
		ImageIO.write(img, "png", new java.io.File("test.png"));
	}
}
