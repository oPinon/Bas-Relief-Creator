package tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import mesh.*;

public class TrianglePlotTest extends JPanel implements ActionListener{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		TrianglePlotTest panel = new TrianglePlotTest();
		frame.add(panel);
		frame.setSize(TrianglePlotTest.width, TrianglePlotTest.width);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	final private double x0,y0,v0,x1,y1,v1,x2,y2,v2;
	final static int width=1000;
	private double theta;
	private Timer timer;
	private TrianglePlot plotter;
	
	public TrianglePlotTest() {
		
		this.x0 = width*(1-2*Math.random())/3;
		this.y0 = width*(1-2*Math.random())/3;
		this.x1 = width*(1-2*Math.random())/3;
		this.y1 = width*(1-2*Math.random())/3;
		this.x2 = width*(1-2*Math.random())/3;
		this.y2 = width*(1-2*Math.random())/3;
		this.v0 = 255*Math.random();
		this.v1 = 255*Math.random();
		this.v2 = 255*Math.random();
		this.theta=0;
		
		this.setSize(width, width);
		this.timer = new Timer(16,this);
		this.plotter = new TrianglePlot() {
			public void plot(int x, int y, double value) {
				this.g.setColor(new Color((int)value,(int)value,(int)value));
				this.g.fillRect(x, y, 1, 1);
			}
		};
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, width);
		
		theta+=0.01;
		
		this.plotter.setGraphics(g);
		double X0 = width/2.0+this.x0*Math.cos(theta)-this.y0*Math.sin(theta);
		double Y0 = width/2.0+this.y0*Math.cos(theta)+this.x0*Math.sin(theta);
		double X1 = width/2.0+this.x1*Math.cos(theta)-this.y1*Math.sin(theta);
		double Y1 = width/2.0+this.y1*Math.cos(theta)+this.x1*Math.sin(theta);
		double X2 = width/2.0+this.x2*Math.cos(theta)-this.y2*Math.sin(theta);
		double Y2 = width/2.0+this.y2*Math.cos(theta)+this.x2*Math.sin(theta);
		/*g.setColor(Color.white); // plots the vertices of the triangle
		g.fillOval((int)X0-1,(int)Y0-1,2,2);
		g.fillOval((int)X1-1,(int)Y1-1,2,2);
		g.fillOval((int)X2-1,(int)Y2-1,2,2);*/
		Triangle tri = new Triangle(X0,Y0,X1,Y1,X2,Y2,this.v0,this.v1,this.v2);
		this.plotter.fillTriangle(tri);
		g.dispose();
	}

	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}

}
