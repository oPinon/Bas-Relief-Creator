package mesh;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class MeshImport {

	private ArrayList<Triangle> mesh;
	private File source;
	private TreeMap<Integer,Vertex> vertices;

	public MeshImport(String file) {

		source = new File(file);
		mesh = new ArrayList<Triangle>();
		vertices = new TreeMap<Integer,Vertex>();
		try {
			Import();
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File couldn't be imported");
		}
	}

	private void Import() throws java.io.FileNotFoundException{

		boolean searchingVertex = false;
		String line = "";
		
		Scanner scan = new Scanner(source);
		
		while (!searchingVertex) {
			line = scan.next();
			if(line.equals("v")) {searchingVertex = true;}
		}
		
		int i = 0;
		while (searchingVertex) {
			
			i++;
			double x, y ,z;
			line = scan.next();
			x = Double.parseDouble(line);
			line = scan.next();
			y = Double.parseDouble(line);
			line = scan.next();
			z = Double.parseDouble(line);
			line = scan.next();
			vertices.put(i, new Vertex(x,y,z));
			if(!line.equals("v")) {searchingVertex = false;}
		}
		
		while(!line.equals("f")) {
			line = scan.next();
		}
		
		while(scan.hasNext()) {
			line = scan.next();
			ArrayList<Vertex> face = new ArrayList<Vertex>();
			while(!line.equals("f")&&scan.hasNext()){
				face.add(vertices.get(Integer.parseInt(line)));
				line = scan.next();
			}
			if(face.size()>2) mesh.add(new Triangle(100+100*face.get(0).getX(),200+100*face.get(0).getY(),100+100*face.get(1).getX(),200+100*face.get(1).getY(),100+100*face.get(2).getX(),200+100*face.get(2).getY(),10*Math.abs(face.get(0).getZ()),10*Math.abs(face.get(1).getZ()),10*Math.abs(face.get(2).getZ())));
			else System.out.println("End");
		}
		scan.close();
		
	}
	
	public ArrayList<Triangle> getMesh() {
		return mesh;
	}
}
