package tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mesh.*;

public class RenderTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		int width = 800; int height = 800;
		
		System.out.println(Double.NEGATIVE_INFINITY);
		MeshImport importer = new MeshImport("untitled.obj");
		long t0 = System.currentTimeMillis();
		ArrayList<Triangle> triangles = importer.getRescaledMesh(width, height);
		TrianglePlot plotter = new TrianglePlot(new ZBuffer(width,height));
		for(Triangle t : triangles) {
			plotter.fillTriangle(t);
		}
		System.out.println("equalizing...");
		BufferedImage result = plotter.getZBuffer().getEqualized(50);//.getImage(importer.getMaxZ());
		System.out.println("rendered in "+(System.currentTimeMillis()-t0)+" ms");
		ImageIO.write(result, "png", new File("render.png"));
	}

}
