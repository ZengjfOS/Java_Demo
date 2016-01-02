package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovingBall extends JPanel {

	private static final long serialVersionUID = 1L;
	static int centerX = 600/2;
	static int centerY = 600/2;
	final Ball ball = new Ball(centerX, centerY);
	boolean cicleflag = true;
	int cicleSemi = 0;
	double angle = 0;
	int ballCicle = 30;
	public MovingBall() {
		start();
	}
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.setBackground(Color.black);
		graphics.setColor(Color.BLUE);
		graphics.fillArc(centerX-cicleSemi/2, centerY-cicleSemi/2, cicleSemi, cicleSemi, 0, 360);
		ball.drawing(graphics);
		graphics.setColor(Color.white);
		graphics.drawArc(centerX-cicleSemi/2, centerY-cicleSemi/2, cicleSemi, cicleSemi, 0, 360);
		graphics.fillArc((int)(centerX+cicleSemi/2*Math.cos(angle*Math.PI/180)-ballCicle/2), (int)(centerY+cicleSemi/2*Math.sin(angle*Math.PI/180)-ballCicle/2), ballCicle, ballCicle, 0, 360);
		graphics.fillArc((int)(centerX+cicleSemi/2*Math.cos((angle+180)*Math.PI/180)-ballCicle/2), (int)(centerY+cicleSemi/2*Math.sin((angle+180)*Math.PI/180)-ballCicle/2), ballCicle, ballCicle, 0, 360);
	}
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						ballMove(ball);
						
						if (cicleflag == true) {
							cicleSemi += 2;
							if (cicleSemi > centerX) {
								cicleflag = false;
							}
						}else {
							cicleSemi -= 2;
							if (cicleSemi < 0) {
								cicleflag = true;
							}
						}
						angle ++;
						repaint();
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}

			public void ballMove(Ball ball) {
				ball.x += ball.dx;
				ball.y += ball.dy;
				if (ball.x < 50 || ball.x > centerX*2-50) {
					ball.dx = -ball.dx;
				}else if (ball.y < 50 || ball.y > centerY*2-50) {
					ball.dy = -ball.dy;
				}
			}
		}).start();
	} 
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setTitle("MovingBall");
		jFrame.setSize(centerX*2, centerY*2);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		
		MovingBall moveBall = new MovingBall();
		jFrame.add(moveBall);
		jFrame.setVisible(true);
	}
}

class Ball{
	static int step = 10;
	int x;
	int y;
	int dx;
	int dy;
	double angle;
	int cicle = 30;
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.angle = Math.random()*360;
		if (angle <= 180) {
			dx = (int) (step*Math.cos(angle*Math.PI/180));
			dy = (int) (step*Math.sin(angle*Math.PI/180));
		}else {
			dx = (int) (step*Math.cos(angle*Math.PI/180));
			dy = -(int) (step*Math.sin(angle*Math.PI/180));
		}
	}
	public Ball(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		if (angle <= 180) {
			dx = (int) (step*Math.cos(angle*Math.PI/180));
			dy = (int) (step*Math.sin(angle*Math.PI/180));
		}else {
			dx = (int) (step*Math.cos(angle*Math.PI/180));
			dy = -(int) (step*Math.sin(angle*Math.PI/180));
		};
	}
	public void drawing(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.fillArc(this.x-cicle/2, this.y-cicle/2, cicle, cicle, 0, 360);
	}
}
