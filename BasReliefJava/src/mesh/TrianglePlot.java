package mesh;

import java.awt.Graphics;

public class TrianglePlot {

	private ZBuffer buffer;

	public TrianglePlot(ZBuffer buffer) {
		this.buffer=buffer;;
	}

	public void plot(int x, int y, double value) {
		if(value>buffer.get(x, y)) { buffer.set(x, y, value); }
	}
	
	public ZBuffer getZBuffer() { return buffer; }

	/*
	 * plots a scanline from x1 with value v1, to x2 with value 2. (value is what we plot)
	 */
	public void fillLine(double y, double x1,double x2, double v1, double v2) {
		if(x2<x1) {
			double xTemp = x2; x2=x1; x1=xTemp;
			double vTemp = v2; v2=v1; v1=vTemp;
		}
		if(x2==x1) { plot((int)x1,(int)y,Math.max(v1, v2)); }
		else {
			double dV = (v2-v1)/(x2-x1);
			double v = v1;
			for(int x=(int)x1; x<(int)x2+1;x++) {
				plot(x,(int)y,v);
				v+=dV;
			}
		}
	}

	/*
	 * requires that yB != yC == yA
	 */
	public void fillTopTri(double x2, double x1, double y1, double x3, double y3, double v1, double v2, double v3) {
		double dBC = (x3-x2)/(y3-y1); double xCB = x2;
		double dvBC = (v3-v2)/(y3-y1); double vCB = v2;
		double dAB = (x3-x1)/(y3-y1); double xCA = x1;
		double dvAB = (v3-v1)/(y3-y1); double vCA = v1;
		for(double y= y1; y<y3;y++) {
			fillLine(y,xCB,xCA,vCB,vCA);
			xCB+=dBC; xCA+=dAB;
			vCB+=dvBC; vCA+=dvAB;
		}
	}

	/*
	 * requires that yA != yC ==yB
	 */
	public void fillBotTri(double x2, double x3, double y3, double y1, double x1, double v1, double v2, double v3) {
		double dCA = (x2-x1)/(y3-y1); double xCA = x2;
		double dvCA = (v2-v1)/(y3-y1); double vCA = v2;
		double dBA = (x1-x3)/(y1-y3); double xBA = x3;
		double dvBA = (v1-v3)/(y1-y3); double vBA = v3;
		for(double y=y3; y>y1;y--) {
			fillLine(y,xCA,xBA,vCA,vBA);
			xCA-=dCA; xBA-=dBA;
			vCA-=dvCA; vBA-=dvBA;
		}
	}

	public void fillTriangle(Triangle tri) {
		tri.sortY();
		double x1=tri.x1, x2=tri.x2, x3=tri.x3;
		double y1=tri.y1, y2=tri.y2, y3=tri.y3;
		double v1=tri.z1, v2=tri.z2, v3=tri.z3;
		if(Math.abs(y3-y2)<=1) {
			if(Math.abs(y3-y1)<=1) { fillLine(y1,Math.min(x1, Math.min(x2, x3)),Math.max(x1, Math.max(x2, x3)),42,42); }
			else { fillTopTri(x2,x1,y1,x3,y3,v1,v2,v3); }
		}
		else if(Math.abs(y2-y1)<=1) { fillBotTri(x2,x3,y3,y1,x1,v1,v2,v3);}
		else {
			double x4 = x1 + ((x3-x1)*(y2-y1))/(y3-y1);
			double v4 = v1 + ((v3-v1)*(y2-y1))/(y3-y1);
			fillBotTri(x2,x4,y2,y1,x1,v1,v2,v4);
			fillTopTri(x2,x4,y2,x3,y3,v4,v2,v3);
		}
	}
}
