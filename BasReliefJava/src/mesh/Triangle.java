package mesh;

public class Triangle {

	public double x1,x2,x3;
	public double y1,y2,y3;
	public double z1,z2,z3;

	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, double v1, double v2, double v3) {
		this.x1=x1; this.x2=x2; this.x3=x3;
		this.y1=y1; this.y2=y2; this.y3=y3;
		this.z1=v1; this.z2=v2; this.z3=v3;
	}

	/*
	 * Sort the 3 vertices by their Y value. The result is : y1<y2<y3
	 */
	public void sortY() {
		final double X1, X2, X3;
		final double Y1, Y2, Y3;
		final double V1, V2, V3;
		if(y1>y3 && y1>y2) {
			X3=x1; Y3=y1; V3=z1;
			if(y3>y2) {
				X2=x3; Y2=y3; V2=z3;
				X1=x2; Y1=y2; V1=z2;
			}
			else {
				X1=x3; Y1=y3; V1=z3;
				X2=x2; Y2=y2; V2=z2;
			}
		}
		else if(y3>y1 && y3>y2) {
			X3=x3; Y3=y3; V3=z3;
			if(y1>y2) {
				X2=x1; Y2=y1; V2=z1;
				X1=x2; Y1=y2; V1=z2;
			}
			else {
				X2=x2; Y2=y2; V2=z2;
				X1=x1; Y1=y1; V1=z1;
			}
		}
		else {
			X3=x2; Y3=y2; V3=z2;
			if(y1>y3) {
				X2=x1; Y2=y1;V2=z1;
				X1=x3; Y1=y3; V1=z3;
			}
			else {
				X2=x3; Y2=y3; V2=z3;
				X1=x1; Y1=y1; V1=z1;
			}
		}
		x1=X1; y1=Y1; z1=V1;
		x2=X2; y2=Y2; z2=V2;
		x3=X3; y3=Y3; z3=V3;
	}
	
	public String print() {
		String toReturn = "Triangle :\n";
		toReturn+="vertex1 = ("+x1+","+y1+") -> +"+z1+"\n";
		toReturn+="vertex2 = ("+x2+","+y2+") -> +"+z2+"\n";
		toReturn+="vertex3 = ("+x3+","+y3+") -> +"+z3+"\n";
		return toReturn;
	}
}
