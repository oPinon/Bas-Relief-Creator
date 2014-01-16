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
		// TODO Auto-generated method stub
		System.out.println(Double.NEGATIVE_INFINITY);
		MeshImport importer = new MeshImport("untitled.obj");
		long t0 = System.currentTimeMillis();
		ArrayList<Triangle> triangles = importer.getRescaledMesh(1000, 1000);
		TrianglePlot plotter = new TrianglePlot(new ZBuffer(1000,1000));
		for(Triangle t : triangles) {
			plotter.fillTriangle(t);
		}
		System.out.println("equalizing...");
		BufferedImage result = plotter.getZBuffer().getEqualized(30);
		System.out.println("rendered in "+(System.currentTimeMillis()-t0)+" ms");
		ImageIO.write(result, "png", new File("render.png"));
	}

}
