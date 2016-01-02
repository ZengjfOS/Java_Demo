package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TaiJiBaGua extends JPanel{
	//设置太极的状态：RUNNING 为运行状态（旋转）；STOP 为暂停状态（静止）
	private static final int RUNNING = 1;
	private static final int STOP = 0;
	//设置初始状态为暂停状态
	private static int state = STOP;
	//通过s的计数可以来判定state的状态值
	private static int s = 0;
	//初始太极中心坐标位置和太极直径
	private static int centerX = 300;
	private static int centerY = 300;
	private static int circle = 300;
	//靠近内层文字以外对应的圆直径
	private static int circleS = 375;
	private static int circleE = 450;
	//最外层文字中心对应的圆半径
	private static int circleE_R = 243;
	//中层文字中心对应的圆半径
	private static int circleS_R = 205;
	//内层文字中心对应的圆半径
	private static int circle_R = 168;
	//设置一个旋转角度参数
	private int angel = 0; 
	//左上太极（位置与直径）
	private static int centerX1 = 75;
	private static int centerY1 = 75;
	private static int circle1 = 100;
	//右上太极（位置与直径）
	private static int centerX2 = 525;
	private static int centerY2 = 75;
	private static int circle2 = 100;
	//左下太极（位置与直径）
	private static int centerX3 = 75;
	private static int centerY3 = 525;
	private static int circle3 = 100;
	//右下太极（位置与直径）
	private static int centerX4 = 525;
	private static int centerY4 = 525;
	private static int circle4= 100;
	//构造方法
	public TaiJiBaGua() {
		//创造一个驱动线程通过累加angel角度来实现太极的旋转
		new Thread(){
			@Override
			public void run() {
				while (true) {
					try {
						if(state==RUNNING){
						  angel++;
						  repaint();
						}
						Thread.sleep(30);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	/**
	 *     画太极：通过传参的方式画任意位置、任意直径大小的太极<br><ol>
	 *     		<li>第一个参数(Graphics)： 引入画笔<br>
	 *     	   <li>第二个参数（centerX): 太极中心的X坐标<br>
	 *     		<li>第三个参数（centerY）:太极中心的Y坐标<br>
	 *     		<li>第四个参数（circle）：太极的直径<br>
	 *     </ol><br>
	 *    画法（运用drawArc方法画圆）：<br><ol>
	 *    			<li>先画太极的最外层两个黑白大半圆，组成一个大圆；<br>
	 *				<li>再在太极黑白分隔线上分别以大圆的两个半径为直径画一黑一白圆贴上去；<br>
	 *				<li>最后以上一步得到的两个圆的圆心为圆心画直径缩小一半的小圆贴上去（注意<br>
	 *				颜色：与上一步得到圆的颜色相反）； <br>
	 *				<li>在方法参数的坐标合适中添加angel值使得随着angel的递增、太极旋转，通过计算<br>
	 *				太极旋转angel角度是上一位置与下一个位置的通用关系，来设置旋转的参数坐标，使得可以旋转。
	 *		 </ol>
	 *
	 */
	public void drawTaiji(Graphics g,int centerX,int centerY,int circle){
		g.setColor(Color.white);
		g.fillArc(centerX-circle/2, centerY-circle/2, circle, circle, 0+angel, 180);
		g.setColor(Color.black);
		g.fillArc(centerX-circle/2, centerY-circle/2, circle, circle, 180+angel, 180);
		g.setColor(Color.black);
		g.fillArc((int)(centerX-circle/4-(circle/4)*Math.cos(angel*Math.PI/180)), (int)(centerY-circle/2+circle/4+(circle/4)*Math.sin(angel*Math.PI/180)), circle/2, circle/2, 0+angel, 360);
		g.setColor(Color.white);
		g.fillArc((int)(centerX-circle/4+(circle/4)*Math.cos(angel*Math.PI/180)),(int)(centerY-circle/2+circle/4-(circle/4)*Math.sin(angel*Math.PI/180)), circle/2, circle/2, 0+angel, 360);
		g.setColor(Color.white);
		g.fillArc((int)(centerX-circle/4+circle/8-(circle/4)*Math.cos(angel*Math.PI/180)), (int)(centerY-circle/2+circle/4+circle/8+(circle/4)*Math.sin(angel*Math.PI/180)), circle/4, circle/4, 0+angel, 360);
		g.setColor(Color.black);
		g.fillArc((int)(centerX-circle/4+circle/8+(circle/4)*Math.cos(angel*Math.PI/180)),(int)(centerY-circle/2+circle/4+circle/8-(circle/4)*Math.sin(angel*Math.PI/180)), circle/4, circle/4, 0+angel, 360);
	}
	/**
	 * <p>
	 *  1. 绘制20个八卦字体和对应处于面板上的位置x，y；<br><ol>
	 *      <li>最外层的四个字：“乾”，“坤”，“阴”，“阳”；<br>
	 *      <li>中层文字：“乾”，“坤”，“坎”，“离”，“震”，“巽”，“艮”，“兑”；<br>
	 *      <li>内层文字：“天”，“地”，“水”，“火”，“雷”，“风”，“山”，“泽”；<br>
	 *     </ul>
	 *        随着太极图的旋转，为展示出效果，让中层文字沿太极图转向相反的方向进行旋转：<br>
	 *   应为java中drawString方法中的坐标不是以文字中心为标准的，而是指文字左上角位<br>
	 *   置，使用对同层文字中心与太极中心的距离为半径，以太极中心为圆心模拟一个圆；利用<br>
	 *   数学三角函数知识，便可以计算当前位置与旋转angel角度是文字中心的位置，在根据相<br>
	 *   关文字大小进行（x-size/2），（y-size/2）平移，其中size为文字大小。<br>
	 *  
	 *  </ol></p>
	 * 
	 *  2. 设置右侧多句文字句排布：<br><ol>
	 *   		<li>计算各文字应该写在那些位置：坐标<br>
	 *   	   <li>利用角度自增的线程，通过angel值来让这些值在Jpanel画板上向下移动<br>
	 *   		<li>调节文字移动速度，让角度除以10.0来改变Y值<br>
	 *     </ol>
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawString("太极神图", 280, 100);
		//中心大八卦图
		/**
		 * 绘制旋转的八卦字体及颜色*/
		Font font = new Font(Font.SERIF,Font.BOLD,30);
		g.setFont(font);
		/**
		 * 给画笔设置成黄色*/
		g.setColor(Color.yellow);
		
		g.drawString("乾", (int)(285-circleE_R*Math.sin(angel*Math.PI/180)), (int)(70+circleE_R*(1-Math.cos(angel*Math.PI/180))));
		g.drawString("坤", (int)(285+circleE_R*Math.sin(angel*Math.PI/180)), (int)(555-circleE_R*(1-Math.cos(angel*Math.PI/180))));
		g.drawString("阴", (int)(40+circleE_R*(1-Math.cos(angel*Math.PI/180))), (int)(310+circleE_R*Math.sin(angel*Math.PI/180)));
		g.drawString("阳", (int)(530-circleE_R*(1-Math.cos(angel*Math.PI/180))), (int)(310-circleE_R*Math.sin(angel*Math.PI/180)));
		g.drawString("乾", (int)(285-circleS_R*Math.sin(-angel*Math.PI/180)), (int)(110+circleS_R*(1-Math.cos(-angel*Math.PI/180))));
		g.drawString("天", (int)(285-circle_R*Math.sin(angel*Math.PI/180)), (int)(145+circle_R*(1-Math.cos(angel*Math.PI/180))));
		g.drawString("坤", (int)(285+circleS_R*Math.sin(-angel*Math.PI/180)), (int)(520-circleS_R*(1-Math.cos(-angel*Math.PI/180))));
		g.drawString("地", (int)(285+circle_R*Math.sin(angel*Math.PI/180)), (int)(480-circle_R*(1-Math.cos(angel*Math.PI/180))));
		g.drawString("坎", (int)(80+circleS_R*(1-Math.cos(-angel*Math.PI/180))), (int)(310+circleS_R*Math.sin(-angel*Math.PI/180)));
		g.drawString("水", (int)(118+circle_R*(1-Math.cos(angel*Math.PI/180))), (int)(310+circle_R*Math.sin(angel*Math.PI/180)));
		g.drawString("离", (int)(490-circleS_R*(1-Math.cos(-angel*Math.PI/180))), (int)(310-circleS_R*Math.sin(-angel*Math.PI/180)));
		g.drawString("火", (int)(450-circle_R*(1-Math.cos(angel*Math.PI/180))), (int)(310-circle_R*Math.sin(angel*Math.PI/180)));
		g.drawString("震", (int)(140-(circleS_R*Math.cos(135*Math.PI/180)-circleS_R*Math.cos((135-angel)*Math.PI/180))), (int)(165+(circleS_R*Math.sin(135*Math.PI/180)-circleS_R*Math.sin((135-angel)*Math.PI/180))));
		g.drawString("雷", (int)(170-(circle_R*Math.cos(135*Math.PI/180)-circle_R*Math.cos((135+angel)*Math.PI/180))), (int)(195+(circle_R*Math.sin(135*Math.PI/180)-circle_R*Math.sin((135+angel)*Math.PI/180))));
		g.drawString("巽", (int)(430-(circleS_R*Math.cos(45*Math.PI/180)-circleS_R*Math.cos((45-angel)*Math.PI/180))), (int)(170+(circleS_R*Math.sin(45*Math.PI/180)-circleS_R*Math.sin((45-angel)*Math.PI/180))));
		g.drawString("风", (int)(400-(circle_R*Math.cos(45*Math.PI/180)-circle_R*Math.cos((45+angel)*Math.PI/180))), (int)(190+(circle_R*Math.sin(45*Math.PI/180)-circle_R*Math.sin((45+angel)*Math.PI/180))));
		g.drawString("艮", (int)(140-(circleS_R*Math.cos(225*Math.PI/180)-circleS_R*Math.cos((225-angel)*Math.PI/180))), (int)(455+(circleS_R*Math.sin(225*Math.PI/180)-circleS_R*Math.sin((225-angel)*Math.PI/180))));
		g.drawString("山", (int)(165-(circle_R*Math.cos(225*Math.PI/180)-circle_R*Math.cos((225+angel)*Math.PI/180))), (int)(425+(circle_R*Math.sin(225*Math.PI/180)-circle_R*Math.sin((225+angel)*Math.PI/180))));
		g.drawString("兑", (int)(430-(circleS_R*Math.cos(315*Math.PI/180)-circleS_R*Math.cos((315-angel)*Math.PI/180))), (int)(460+(circleS_R*Math.sin(315*Math.PI/180)-circleS_R*Math.sin((315-angel)*Math.PI/180))));
		g.drawString("泽", (int)(405-(circle_R*Math.cos(315*Math.PI/180)-circle_R*Math.cos((315+angel)*Math.PI/180))), (int)(435+(circle_R*Math.sin(315*Math.PI/180)-circle_R*Math.sin((315+angel)*Math.PI/180))));
		g.setColor(Color.blue);
		g.drawArc(centerX-circleE/2, centerY-circleE/2, circleE, circleE, 0, 360);
		g.setColor(Color.white);
		g.drawArc(centerX-circleS/2, centerY-circleS/2, circleS, circleS, 0, 360);
		drawTaiji(g, centerX, centerY, circle);
	    //中心大八卦图
		//四个角落的小太极画法如下：
		//左上角八卦图
		drawTaiji(g, centerX1, centerY1,(int)(circle1/2+circle1/2*Math.cos(angel/100.0)));
		//左上角八卦图
		angel = -angel;
		//右上角八卦图
		drawTaiji(g, centerX2, centerY2, (int)(circle2/2+circle2/2*Math.cos(-angel/100.0)));
		//右上角八卦图
		//左下角八卦图
		drawTaiji(g, centerX3, centerY3,(int)(circle3/2+circle3/2*Math.cos(-angel/100.0)));
		//左下角八卦图
		angel = -angel;
		//右下角八卦图
		drawTaiji(g, centerX4, centerY4,(int)(circle4/2+circle4/2*Math.cos(angel/100.0)));
		//右下角八卦图
		//设置右边文字样式：文字字体、颜色、大小
		font = new Font(Font.SERIF,Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.white);
		/**
		 *    设置右侧多句文字句排布：<br><ol>
		 *     */
		g.drawString("太极生两仪", 600, (int)((50+100*angel/20.0)%600));
		g.drawString("两仪生四象", 600, (int)((150+100*angel/20.0)%600));
		g.drawString("四象生八卦", 600, (int)((250+100*angel/20.0)%600));
		g.drawString("八卦生六十四卦", 600, (int)((350+100*angel/20.0)%600));
		g.drawString("一生二", 600, (int)((450+100*angel/20.0)%600));
		g.drawString("二生三  三生万物", 600, (int)((550+100*angel/20.0)%600));
	}
	public static void main(String[] args) {
		//定义一个JFrame窗口
		JFrame jFrame = new JFrame();
		//给窗口设置一个主题：太极
		jFrame.setTitle("太极");
		//设置窗口的大小
		jFrame.setSize(centerX*2+250,centerY*2);
		//给窗口加一个关闭按钮
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		//窗口居中
		jFrame.setLocationRelativeTo(null);
		//创建太极面板：taiji
		TaiJiBaGua taiJi = new TaiJiBaGua();
		//设置面板颜色：红色
		taiJi.setBackground(Color.red);
		//把面板添加到窗口中
		jFrame.add(taiJi);
		//创建一个控制按钮jButton
		JButton jButton = new JButton("卜卦");
		//新建一个jButton按钮监听器，用于监听点击事件
		//用于控制太极图的状态
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//s记录总共的点击按钮次数，通过判断s是奇数还是偶数来
				//确定当前状态，从而来改变状态
				if(s%2==0){   
				   state = RUNNING;
				}else {
					state = STOP;
				}
				s++;
			}
		});
		//把按钮添加到太极面板上
		taiJi.add(jButton);
		//设置窗体可见，自动调用paint方法
	   jFrame.setVisible(true);		
	}
}
