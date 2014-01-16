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
			if(face.size()>2) {
				mesh.add(new Triangle(face.get(0).getX(),face.get(0).getY(),face.get(1).getX(),face.get(1).getY(),face.get(2).getX(),face.get(2).getY(),face.get(0).getZ(),face.get(1).getZ(),face.get(2).getZ()));
				//System.out.println(face.get(0).getZ()+" "+face.get(1).getZ()+" "+face.get(2).getZ());
			}
			else System.out.println("End");
		}
		scan.close();
		
	}
	
	public ArrayList<Triangle> getMesh() {
		return mesh;
	}
	
	public ArrayList<Triangle> getRescaledMesh(int width, int height) {
		double minX = Double.POSITIVE_INFINITY; double maxX = Double.NEGATIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY; double maxY = Double.NEGATIVE_INFINITY;
		for(Triangle t : mesh) {
			final double Xmin = Math.min(t.x1, Math.min(t.x2, t.x3));
			final double Xmax = Math.max(t.x1, Math.max(t.x2, t.x3));
			final double Ymin = Math.min(t.y1, Math.min(t.y2, t.y3));
			final double Ymax = Math.max(t.y1, Math.max(t.y2, t.y3));
			if(Xmin<minX){minX=Xmin;}
			if(Xmax>maxX){maxX=Xmax;}
			if(Ymin<minY){minY=Ymin;}
			if(Ymax>maxY){maxY=Ymax;}
		}
		double x0 = (minX+maxX)/2; double xRange = maxX-minX;
		double y0 = (minY+maxY)/2; double yRange = maxY-minY;
		double scale = 0.95*Math.min(width/xRange, height/yRange);
		ArrayList<Triangle> toReturn = new ArrayList<Triangle>();
		for(Triangle t : mesh) {
			double x1 = width/2.0+scale*(t.x1-x0);
			double x2 = width/2.0+scale*(t.x2-x0);
			double x3 = width/2.0+scale*(t.x3-x0);
			double y1 = height/2.0+scale*(t.y1-y0);
			double y2 = height/2.0+scale*(t.y2-y0);
			double y3 = height/2.0+scale*(t.y3-y0);	
			toReturn.add(new Triangle(x1,y1,x2,y2,x3,y3,t.z1,t.z2,t.z3));
		}
		return toReturn;
	}
}
