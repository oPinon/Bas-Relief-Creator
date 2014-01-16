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
		MeshImport importer = new MeshImport("suzan.obj");
		ArrayList<Triangle> triangles = importer.getRescaledMesh(1920, 1080);
		TrianglePlot plotter = new TrianglePlot(new ZBuffer(1920,1080));
		for(Triangle t : triangles) {
			plotter.fillTriangle(t);
		}
		BufferedImage result = plotter.getZBuffer().getImage();
		ImageIO.write(result, "png", new File("render.png"));
	}

}
