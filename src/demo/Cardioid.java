package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cardioid extends JPanel{
	/**
	 * <ol>
	 * <li>centerX：代表x轴中心点坐标
	 * <li>centerY：代表y轴中心店坐标
	 * </ol>
	 * 整个窗口的长、宽等于2*centerX、2*centerY，这么做的目的是保证数据的统一性修改
	 * 当然你也可以考虑使用长、宽来表示，然后对他们进行取一半得到中心店坐标。
	 * 
	 */
	static int centerX = 600/2;
	static int centerY = 600/2;
	/**
	 * angle：主要用于角度计算，下面程序中的for循环里的变量，360一个周期
	 */
	int angle;
	/**
	 * <ol>
	 * <li>a:窗口中有2个心形图，a是其中一个的幅值
	 * <li>b:窗口中有2个心形图，b是其中一个的幅值
	 * </ol>
	 * 2个心形图，2个幅值，但初始值是不一样的。
	 */
	int a = 0;
	int b = 50;
	/**
	 * 构造函数调用start()函数。<br>
	 * 作用：开启一个线程，主要用于调整a、b的值，并刷新界面。
	 */
	public Cardioid() {
		start();
	}
	/**
	 * 重写paint()<br>
	 * 程序流程：<ol>
	 * 		<li>调用父类paint方法、并设置背景颜色为黑色
	 * 		<li>用for循环画两个发散的心形图
	 * </ol>
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.setBackground(Color.black);
		graphics.setColor(Color.red);
		/*
		 *  这里是难点，主要是因为需要对坐标进行定位，下面是获取x、y的坐标公式，你可以在网上查到
		 * 		x=a*(2*sin(t)-sin(2*t))
		 *		y=a*(2*cos(t)-cos(2*t))
		 *	这里的x、y和网上的公式对调了，主要是因为需要进行y=x对称，网上的图是横着的，这个图是正着的。
		 *  sin()函数传入的是弧度制，所以需要通过angle*Math.PI/180，将角度值换成幅度值
		 *  其中的500主要是用于坐标调整的，没有理由，是我自己试出来的，我也没有去深究为什么，因为功能完成了。
		 */
		for (angle = 0; angle < 360; angle++) {
			graphics.drawLine(
					centerY+(int)(a*(2*Math.sin(angle*Math.PI/180)-Math.sin(2*angle*Math.PI/180))), 
					500 -(centerX+(int)(a*(2*Math.cos(angle*Math.PI/180)-Math.cos(2*angle*Math.PI/180)))), 
					centerY+(int)((a+3)*(2*Math.sin((angle)*Math.PI/180)-Math.sin(2*(angle)*Math.PI/180))),
					500 -(centerX+(int)((a+3)*(2*Math.cos((angle)*Math.PI/180)-Math.cos(2*(angle)*Math.PI/180)))));
		}
		for (angle = 0; angle < 360; angle++) {
			graphics.drawLine(
					centerY+(int)(b*(2*Math.sin(angle*Math.PI/180)-Math.sin(2*angle*Math.PI/180))), 
					500 -(centerX+(int)(b*(2*Math.cos(angle*Math.PI/180)-Math.cos(2*angle*Math.PI/180)))), 
					centerY+(int)((b+3)*(2*Math.sin((angle)*Math.PI/180)-Math.sin(2*(angle)*Math.PI/180))),
					500 -(centerX+(int)((b+3)*(2*Math.cos((angle)*Math.PI/180)-Math.cos(2*(angle)*Math.PI/180)))));
		}
	}
	/**
	 * 创建一个匿名线程，线程主要完成以下事情：<ol>
	 * <li>改变a、b的值，相当于改变心形线的幅值；
	 * <li>延时20ms；
	 * <li>刷新界面repaint();
	 * </ol>
	 */
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						if (a++ >100) {
							a = 0;
						}
						if (b++ >100) {
							b = 0;
						}
						Thread.sleep(20);
						repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	/**
	 * 主函数完成以下内容：<ol>
	 * <li>初始化jframe窗口；
	 * <li>创建cardioid，并将cardioid填充到jFrame中；
	 * <li>设置jFrame可见。
	 * </ol>
	 */
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setTitle("Cardioid");
		jFrame.setSize(centerX*2, centerY*2);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		
		Cardioid cardioid = new Cardioid();
		jFrame.add(cardioid);
		jFrame.setVisible(true);
	}
}
