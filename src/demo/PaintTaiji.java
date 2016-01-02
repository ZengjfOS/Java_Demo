package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintTaiji extends JPanel{
	private static final long serialVersionUID = 1L;
	//所有的数据都是依照
	static int  centerX = 600/2;
	static int centerY = centerX;
	ArrayList<Taiji> taijis = new ArrayList<Taiji>();
	
	
	public PaintTaiji() {
		startRun();
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				taijis.add(new Taiji(mouseX, mouseY));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.setBackground(Color.red);
		for (Taiji taiji : taijis) {
			taiji.paint(graphics);
		}
	}
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
    	//设置JFrame标题
    	jFrame.setTitle("TaiJi");
        //设置JFrame的大小
    	jFrame.setSize(centerX*2, centerY*2);
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	PaintTaiji taiji = new PaintTaiji();
        jFrame.add(taiji);

        //将窗口设置为可见
        jFrame.setVisible(true);
	}
	public void startRun() {
        new Thread(){
            public void run() {
                while(true){
                   try{
                	   	for (Taiji taiji : taijis) {
                		   taiji.angle += taiji.angleStep;
                	   	}
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            };
        }.start();
    }
}
class Taiji{
	int centerX;
	int centerY;
    int extendCircleSemi = (int)(Math.random()*200)+1;
    int innerCircleSemi = extendCircleSemi/2;
    //旋转角度，值由线程维护，大约每20ms自加1
    int angle = 0;
    int angleStep = (int)(Math.random()*6)+1;
    public Taiji(int x, int y) {
        centerX = x;
        centerY = y;
    }
    
    public void paint(Graphics graphics ) {
        graphics.setColor(    Color.white);
        graphics.fillArc(    centerX-extendCircleSemi,
                            centerY-extendCircleSemi,
                            extendCircleSemi*2,
                            extendCircleSemi*2, 0+angle, 180);
        graphics.setColor(    Color.black);
        graphics.fillArc(    centerX-extendCircleSemi,
                            centerY-extendCircleSemi,
                            extendCircleSemi*2,
                            extendCircleSemi*2,
                            180+angle, 180);
        graphics.fillArc(    centerX+(int)(Math.cos(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi,
                            centerY-(int)(Math.sin(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi,
                            innerCircleSemi*2,
                            innerCircleSemi*2,
                            0,
                            360);
        graphics.setColor(    Color.white);
        graphics.fillArc(    centerX+(int)(Math.cos(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi/2,
                            centerY-(int)(Math.sin(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi/2,
                            innerCircleSemi,
                            innerCircleSemi,
                            0,
                            360);
        graphics.setColor(    Color.white);
        graphics.fillArc(    centerX-(int)(Math.cos(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi,
                            centerY+(int)(Math.sin(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi,
                            innerCircleSemi*2,
                            innerCircleSemi*2,
                            0,
                            360);
        graphics.setColor(    Color.black);
        graphics.fillArc(    centerX-(int)(Math.cos(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi/2,
                            centerY+(int)(Math.sin(Math.PI*angle/180)*innerCircleSemi)-innerCircleSemi/2,
                            innerCircleSemi,
                            innerCircleSemi,
                            0,
                            360);
    }
}