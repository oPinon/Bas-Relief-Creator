package tests;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mesh.*;

public class TestImport {

	public static void main(String[] args) throws IOException {
		
		MeshImport importer = new MeshImport("suzan.obj");
		ArrayList<Triangle> triangles = importer.getMesh();
		System.out.println("number of triangles = "+triangles.size());
		
		BufferedImage img = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		
		TrianglePlot triPlot = new TrianglePlot(g);
		
		for(Triangle tri : triangles) { triPlot.fillTriangle(tri); }
		
		g.dispose();
		ImageIO.write(img, "png", new java.io.File("test.png"));
	}

}
