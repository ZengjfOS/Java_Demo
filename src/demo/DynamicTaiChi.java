package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicTaiChi extends JPanel{
	private static final long serialVersionUID = 1L;
	/*
	 * 	      所有的数据都是按比例依照centerX进行改变的,所以
	 * 如果想要更改面板大小的话,可以直接改centerX的值.
	 */
	static int centerX = 600/2;
	static int centerY = centerX;
	static int cicle = centerX;
	static int angle = 0;
	
	public DynamicTaiChi() {
		start();
	}
	private void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						angle++;
						Thread.sleep(10);
						repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.setBackground(Color.red);
		graphics.setColor(Color.white);
		graphics.fillArc(centerX-cicle/2, centerY-cicle/2, cicle, cicle, angle, 180);
		graphics.setColor(Color.black);
		graphics.fillArc(centerX-cicle/2, centerY-cicle/2, cicle, cicle, 180+angle, 180);
		graphics.fillArc(centerX+(int)(cicle/2/2*(Math.cos(angle*Math.PI/180)))-cicle/2/2, centerY-(int)(cicle/2/2*(Math.sin(angle*Math.PI/180)))-cicle/2/2, cicle/2, cicle/2, 0, 360);
		graphics.setColor(Color.white);
		graphics.fillArc(centerX+(int)(cicle/2/2*(Math.cos((angle+180)*Math.PI/180)))-cicle/2/2, centerY-(int)(cicle/2/2*(Math.sin((angle+180)*Math.PI/180)))-cicle/2/2, cicle/2, cicle/2, 0, 360);
		graphics.setColor(Color.black);
		graphics.fillArc(centerX+(int)(cicle/2/2*(Math.cos((angle+180)*Math.PI/180)))-cicle/2/2/2, centerY-(int)(cicle/2/2*(Math.sin((angle+180)*Math.PI/180)))-cicle/2/2/2, cicle/2/2, cicle/2/2, 0, 360);
		graphics.setColor(Color.white);
		graphics.fillArc(centerX+(int)(cicle/2/2*(Math.cos(angle*Math.PI/180)))-cicle/2/2/2, centerY-(int)(cicle/2/2*(Math.sin(angle*Math.PI/180)))-cicle/2/2/2, cicle/2/2, cicle/2/2, 0, 360);
	}
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setTitle("DynamicTaiChi");
		jFrame.setSize(centerX*2, centerY*2);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		
		DynamicTaiChi jPanel = new DynamicTaiChi();
		jFrame.add(jPanel);
		
		jFrame.setVisible(true);
	}
}
