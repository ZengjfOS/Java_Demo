/**
 * 注意事项：
 *         1、本程序为java程序，同时感谢您花费宝贵的时间来阅读本文档；
 *         2、请注意包名为：practice，文件名为：EyesJFrame，注意检查，以防一些不必要的麻烦；
 *         3、在完成确认2中的内容后，本程序可以直接运行，因为本软件说明都在注释中；
 *         4、由于本例子是继《动态中国太极图——Java原创》后编写，所以在那里面已说明的东西，不再详述，
 *                 同时由于个人能力、阅历等原因，有些细节可能并没有详细阐述，或者有疏漏，请谅解。        
 *         5、您可以注释paint()方法中的一些代码，因为效果很直观，也许这样您理解得更快，建议如下：
 *             5.1 先看paint()方法第1条语句效果，把paint()方法中的其它语句注释掉；
 *             5.2 再看paint()方法第1、2条语句效果，把paint()方法中的其它语句注释掉；
 *             5.3 再看paint()方法第1、2、3条语句效果，把paint()方法中的其它语句注释掉；
 *             5.4 以上面的方法类推，直到paint()方法中没有语句被注释，相信等您看完，您就理解了。
 *
 * 设计目标：在一个JFrame中绘制一个眨眼效果。
 * 设计说明：   
 *             在本人想实现眨眼效果的时候，Java提供的绘图工具有点无奈。本设计灵感主要来自于无线电信
 *         号调制，如果你想去了解无线电调制怎么回事，那就找下度娘或者谷哥吧，您也许没必要了解无线电调
 *         制，因为在绘图的时候体现的只不过的绘制直线填充而已，并没有其他的高深内容。
 *             程序的使用了sin()函数的半个周期来完成。本人知道您不喜欢看很多文字描述，很多时候文字
 *         总是无力的，所以请参照《注意事项》中的第5步去调试吧，相信摆在眼前的调试效果会告诉你是怎么
 *         完成的。   ^_^
 *
 *    2014-1-13 星期一 晴 8度 微风 南昌
 *
 */
package demo;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EyesJPanel extends JPanel{
    //设置角度值，同时也就眼睛的横坐标长度
    public static int angle = 300;
    //因为眨眼采用的sin()函数组成，所以其自然有幅值这一个属性。
    int amplitude = 40;
    //创建一个用于保存幅值的变化的变量，采用amplitude的缩写ampl，便于认识 ^_^
    int ampl = 0;
    //判断是否到了幅值的临界值，由线程维护
    boolean flag = true;
    //定义中心点坐标，个人喜好
    static int centerX = 600/2;
    static int centerY = 300/2;
    //创建统一的颜色背景，如果你有兴趣调试的话，也许你就会知道为什么要这么做了。
    Color color = Color.red;
    //灰眼球的半径
    int blackBallSemi = 25;
   
   
    public EyesJPanel() {
        //这里面的内容在本人的《动态中国太极图——Java原创》中有说明，不再做
        //详细介绍，当然您也可以参考一些书籍
        
        startRun();
    }
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.setBackground(Color.red);
        //以centerX,centerY为中心，在眼睛所在的地方绘制一个白色的背景底色，
        //长度为angle，宽为amplitude*2
        graphics.setColor(Color.white);
        graphics.fillRect(centerX-angle/2, centerY-amplitude, angle, amplitude*2);
        //以centerX,centerY为中心，绘制一个灰色的眼球
        //半径为blackBallSemi*2
        graphics.setColor(Color.darkGray);
        graphics.fillOval(    centerX-blackBallSemi,
                            centerY-blackBallSemi,
                            blackBallSemi*2,
                            blackBallSemi*2);
        //以centerX,centerY为中心，绘制一个白色的瞳孔
        //半径为blackBallSemi/2
        graphics.setColor(Color.white);
        graphics.fillOval(    centerX-blackBallSemi/2/2,
                            centerY-blackBallSemi/2/2,
                            blackBallSemi/2,
                            blackBallSemi/2);
        //使用和窗口一样的背景色将眼睛外框颜色去掉，这里使用sin()函数来完成，灵感来收音机自信号调制
        //在这里体现的方法其实就是画直线，把不需要的地方都画成与背景色相同的颜色，这里是中国红
        graphics.setColor(color);
        for (int i = 0; i < angle; i++) {
            graphics.drawLine(    centerX-angle/2+i,
                                centerY-amplitude,
                                centerX-angle/2+i,
                                centerY-(int)(Math.sin(Math.PI*i/angle)*ampl));
            graphics.drawLine(    centerX-angle/2+i,
                                centerY+amplitude,
                                centerX-angle/2+i,
                                centerY+(int)(Math.sin(Math.PI*i/angle)*ampl));
        }
    }
    /**
     * 用线程维护眼睛的眨眼效果，线程结构如下：
     *         1、该线程使用while(true)维护动态效果
     *         2、ampl用于表示当前的眨眼效果的幅值
     *         3、ampl的值限定在于0到amplitude之间，如果您对这个算法不理解，
     *             请像苍老师那样绘图模型图，但本人觉得您一定能秒杀这种算法  ^_^
     */
    public void startRun() {
        new Thread(){
            public void run() {
                while(true){
                    if (flag) {
                        ampl++;
                        if (ampl >= amplitude) {
                            flag = false;
                        }
                    }else {
                        ampl--;
                        if (ampl <= 0) {
                            flag = true;
                        }
                    }
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            };
        }.start();
    }
    public static void main(String[] args) {
    	JFrame jFrame = new JFrame();
    	jFrame.setTitle("Eyes");
    	jFrame.setSize(centerX*2, centerY*2);
    	jFrame.setLocationRelativeTo(null);
        JPanel jPanel = new EyesJPanel();
        jFrame.add(jPanel);
        
        jFrame.setVisible(true);
    }
}
