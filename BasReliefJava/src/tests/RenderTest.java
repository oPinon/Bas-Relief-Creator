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
		ArrayList<Triangle> triangles = importer.getRescaledMesh(3000, 3000);
		TrianglePlot plotter = new TrianglePlot(new ZBuffer(3000,3000));
		for(Triangle t : triangles) {
			plotter.fillTriangle(t);
		}
		System.out.println("equalizing...");
		BufferedImage result = plotter.getZBuffer().getImage(importer.getMaxZ());
		System.out.println("rendered in "+(System.currentTimeMillis()-t0)+" ms");
		ImageIO.write(result, "png", new File("render.png"));
	}

}
