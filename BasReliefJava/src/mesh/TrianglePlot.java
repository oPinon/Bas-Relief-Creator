package mesh;

import java.awt.Graphics;

public class TrianglePlot {

	private Graphics g;

	public TrianglePlot(Graphics g) {
		this.g=g;
	}

	public void plot(int x, int y, double value) {
		g.setColor(new java.awt.Color(255,255,255,100));
		g.drawRect(x, y, 1, 1);
	}

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
	public void fillTopTri(double xC, double xA, double yA, double xB, double yB) {
		double dBC = (xB-xC)/(yB-yA); double xCB = xC;
		double dAB = (xB-xA)/(yB-yA); double xCA = xA;
		for(double y= yA; y<yB;y++) {
			fillLine(y,xCB,xCA,42,42);
			xCB+=dBC; xCA+=dAB;
		}
	}

	/*
	 * requires that yA != yC ==yB
	 */
	public void fillBotTri(double xC, double xB, double yB, double yA, double xA) {
		double dCA = (xC-xA)/(yB-yA); double xCA = xC;
		double dBA = (xA-xB)/(yA-yB); double xBA = xB;
		for(double y=yB; y>yA;y--) {
			fillLine(y,xCA,xBA,42,42);
			xCA-=dCA; xBA-=dBA;
		}
	}

	public void fillTriangle(Triangle tri) {
		tri.sortY();
		double x1=tri.x1, x2=tri.x2, x3=tri.x3;
		double y1=tri.y1, y2=tri.y2, y3=tri.y3;
		if(y1==y3) {
			if(y3==y2) { fillLine(y1,Math.min(x1, Math.min(x2, x3)),Math.max(x1, Math.max(x2, x3)),42,42); }
			else { fillTopTri(x3,x1,y1,x2,y2); }
		}
		else if(y3==y2) { fillBotTri(x3,x2,y2,y1,x1); }
		else {
			double x4 = x1 + ((x2-x1)*(y3-y1))/(y2-y1);
			fillBotTri(x4,x3,y3,y1,x1);
			fillTopTri(x3,x1,y1,x4,y3);
		}
	}
}
