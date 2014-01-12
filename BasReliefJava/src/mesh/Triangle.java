package mesh;

public class Triangle {

	public double x1,x2,x3;
	public double y1,y2,y3;

	public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
		this.x1=x1; this.x2=x2; this.x3=x3;
		this.y1=y1; this.y2=y2; this.y3=y3;
	}

	/*
	 * Sort the 3 vertices by their Y value. The result is : y1<y2<y3
	 */
	public void sortY() {
		final double xA, xB, xC;
		final double yA, yB, yC;
		if(y1>y3 && y1>y2) {
			xC=x1; yC=y1;
			if(y3>y2) {
				xB=x3; yB=y3;
				xA=x2; yA=y2;
			}
			else {
				xA=x3; yA=y3;
				xB=x2; yB=y2;
			}
		}
		else if(y3>y1 && y3>y2) {
			xC=x3; yC=y3;
			if(y3>y2) {
				xB=x1; yB=y1;
				xA=x2; yA=y2;
			}
			else {
				xB=x2; yB=y2;
				xA=x3; yA=y3;
			}
		}
		else {
			xC=x2; yC=y2;
			if(y1>y3) {
				xB=x1; yB=y1;
				xA=x3; yA=y3;
			}
			else {
				xA=x1; yA=y1;
				xB=x3; yB=y3;
			}
		}
		x1=xA; y1=yA;
		x2=xB; y2=yB;
		x3=xC; y3=yC;
	}
	
	public String print() {
		String toReturn = "Triangle :\n";
		toReturn+="vertex1 = ("+x1+","+y1+")\n";
		toReturn+="vertex2 = ("+x2+","+y2+")\n";
		toReturn+="vertex3 = ("+x3+","+y3+")\n";
		return toReturn;
	}
}
