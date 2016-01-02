
/**
 * 设计声明：
 *         1、虽然岸本是日本人，而我个人作为其模仿者，依然想表示个人对其的敬意，希望作品不会玷污写轮眼的名声。
 *         2、由于本次设计包含过多数学计算，所以强烈建议您通过《注意事项》中的5查看图是如何设计的，先不要看源码，
 *             然后自己试图去设计，而您仅需要知道：计算机计算开方、三角函数、反三角等会造成误差，仅此而已。
 *         3、由于我本人在《动态中国太极图——Java原创》中改了一个注释错误，导致《动态中国太极图——Java原创》
 *             不能再次通过系统的审批，不知何故，所以导致大家可能看不到《动态中国太极图——Java原创》里面详细
 *             的注释，深表遗憾。同时也对我自己没能认真做好本质工作表示深深的反思。
 *         4、如果您对这个很好奇，那么请现查阅本人发表的《动态中国太极图——Java原创》(不知道还能不能通过审批)、
 *             《动态眨眼效果——Java原创》，因为那两个是这个设计的引导＋铺垫。
 *         
 *         
 * 注意事项：
 *         1、本程序为java程序，同时感谢您花费宝贵的时间来阅读本文档；
 *         2、请注意包名为：practice，文件名为：SharinganJFrame(Sharingan是写轮眼的英文)，注意检查，
 *                 以防一些不必要的麻烦；
 *         3、在完成确认2中的内容后，本程序可以直接运行，因为本软件说明都在注释中；
 *         4、由于本设计是继《动态中国太极图——Java原创》、《动态眨眼效果——Java原创》后编写，所以在那里
 *                 面已说明的东西，不再详述，同时由于个人能力、阅历等原因，有些细节可能并没有详细阐述，或者
 *                 有疏漏，请谅解。                        
 *         5、您可以注释paint()方法中的一些代码，因为效果很直观，也许这样您理解得更快，建议如下：
 *             5.1 先看paint()方法第1条语句效果，把paint()方法中的其它语句注释掉；
 *             5.2 再看paint()方法第1、2条语句效果，把paint()方法中的其它语句注释掉；
 *             5.3 再看paint()方法第1、2、3条语句效果，把paint()方法中的其它语句注释掉；
 *             5.4 以上面的方法类推，直到paint()方法中没有语句被注释，相信等您看完，您就理解了。
 *
 * 设计目标：在一个JFrame中绘制一个动态的写轮眼(动漫《火影》中的特殊眼睛)。
 *
 * 设计说明：   
 *      相信很多人不喜欢看那些很长文字的说明，所以本人只简单介绍一下个人想法及遇到的一些问题：
 *          1、设计的源头来自我和同桌(程梦真)无意中提到写轮眼，所以打算把它用Java绘出来，而且要是动态的；
 *          2、目前的版本的写轮眼和最初设计的写轮眼有很大差别，很多单词的定义已经没有了当初的味道。
 *            3、绘制这个动态的写轮眼的时候遇到最严重的的问题是计算误差，就是苍老师说的精确但不准确，所以
 *                angleErr是用来做误差补偿的，当然这个还是不是很准确，没有更深入的去探究，由于本来就是娱
 *                乐，所以没打算去深究，如果你感兴趣，想去琢磨，请注意一下angleErr所出现的地方，本人因为
 *                这个误差，重画了进3遍才找出根本原因。
 *             
 *    2014-1-13 星期一 晴 8度 微风 南昌
 *
 */
package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SharinganJPanel extends JPanel {
    //设置中心坐标，个人喜欢一种心点作为物体的坐标，物理里面成为质点
    static int centerX = 600/2;
    static int centerY = 600/2;
    //外圆半径，写轮眼转动所依赖的红线
    double extendCircleSemi = 200;
    //写轮眼的旋转部分有一个固定的大小的圆，刚开始设计的时候是定义为白色的，后来改为了红色，好看 ^_^
    double whiteOfSharingan = extendCircleSemi/6;
    //在上面的红色的圆旁边有一个随着旋转角度变大而变大的圆，其半径为miniCircleSemi
    double miniCircleSemi = 0;
    //这里用到了绘制太极时的思路，这个圆和上面的两个圆(whiteOfSharingan和miniCircleSemi)外切
    double sharinganSemi = 0;
    //旋转度角范围的变量，您可以通过观察旋转的循环角度是120*2
    int angleCircle = 0;
    //小圆(miniCircleSemi)和白圆(whiteOfSharingan)在外圆(extendCircleSemi)上的角度
    double angleOfWhiteMini = 0;
    //写轮眼沟玉圆(sharinganSemi)和白圆(whiteOfSharingan)在外圆(extendCircleSemi)的角度
    double angleOfwhiteShar = 0;
    //用于判断圆是否到达极限位置，还句话说就是是否到达了正确的角度，进行下一次循环
    boolean flag = true;
    //沟玉球心到弦(whiteOfSharingan圆中心和miniCircleSemi圆中心所连成的线)中心距离；
    double distansOfWhiteShar =0;
    //沟玉球心到中心点的距离，这一步主要是为了计算坐标
    double distansOfSharSemi = 0;
    //在绘图过程中，由于计算机计算开方、三角函数、反三角等造成了误差，这是一个补偿值，但要注意真正补偿的
    //误差为这个误差的angleErr*2/3,因为这个值是本人通过理想设置临界状态得到的值。
    double angleErr = 0.02500260489936114;
    //沟玉球(whiteOfSharingan)中的黑球半径，这个值是动态的
    int blackOfWhiteSemi = 0;
    //中心眼睛所占的长度
    int eyeLength = 300;
    //眼睛幅值
    int amplitude = 50;
    
    public SharinganJPanel() {
        
        startRun();
    }
    
    @Override
    public void paint(Graphics graphics) {
        System.out.println();
    	super.paint(graphics);
        this.setBackground(Color.black);
        //绘制一个眼睛作为背景
        graphics.setColor(Color.red);
        for (int i = 0; i < eyeLength; i++) {
            graphics.drawLine(    centerX-eyeLength/2+i,
                                centerY-(int)(Math.sin(Math.PI*i/eyeLength)*amplitude),
                                centerX-eyeLength/2+i,
                                centerY+(int)(Math.sin(Math.PI*i/eyeLength)*amplitude));
        }
        //在眼睛的中心绘制一个黑色的圆
        graphics.setColor(Color.black);
        graphics.fillOval(    centerX-90/2,
                            centerY-90/2,
                            90,
                            90);
        //在眼睛的中心绘制一个白色的圆
        graphics.setColor(Color.white);
        graphics.fillOval(    centerX-60/2/2/2,
                            centerY-60/2/2/2,
                            60/2/2,
                            60/2/2);
        //用于绘制3个不同角度、在不断变化、不同位置的太极图，图是有顺序的
        for (int i = 0; i < 3; i++) {
            //绘制写轮眼球(sharinganSemi)
            graphics.setColor(Color.red);
            graphics.fillArc(   (int)(centerX-sharinganSemi+Math.cos(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini/2+angleOfwhiteShar+angleErr*2/3)*distansOfSharSemi),
                                (int)(centerY-sharinganSemi-Math.sin(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini/2+angleOfwhiteShar+angleErr*2/3)*distansOfSharSemi),
                                (int)(sharinganSemi*2),
                                (int)(sharinganSemi*2),angleCircle+i*120,180);
            //绘制写轮眼固定圆(whiteOfSharingan)
            graphics.setColor(Color.red);
            graphics.fillOval(  (int)(centerX-whiteOfSharingan+Math.cos(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(centerY-whiteOfSharingan-Math.sin(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(whiteOfSharingan*2),
                                (int)(whiteOfSharingan*2));
            //绘制写轮眼黑球(miniCircleSemi)
            graphics.setColor(Color.black);
            graphics.fillOval(  (int)(centerX-miniCircleSemi+Math.cos(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini)*extendCircleSemi),
                                (int)(centerY-miniCircleSemi-Math.sin(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini)*extendCircleSemi),
                                (int)(miniCircleSemi*2),
                                (int)(miniCircleSemi*2));
            //绘制写轮眼固定球内黑球(blackOfWhiteSemi)
            graphics.setColor(Color.black);
            graphics.fillOval(  (int)(centerX-(blackOfWhiteSemi+0.0)/120*whiteOfSharingan/2+Math.cos(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(centerY-(blackOfWhiteSemi+0.0)/120*whiteOfSharingan/2-Math.sin(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)((blackOfWhiteSemi+0.0)/120*whiteOfSharingan),
                                (int)((blackOfWhiteSemi+0.0)/120*whiteOfSharingan));
        }
        //绘制外圆
            graphics.setColor(Color.red);
            graphics.drawOval(  (int)(centerX-extendCircleSemi),
                                (int)(centerY-extendCircleSemi),
                                (int)(extendCircleSemi)*2,
                                (int)(extendCircleSemi)*2);
    }
    public void startRun() {
        new Thread(){
            public void run() {
                while(true){
                    if (flag) {
                        angleCircle += 2 ;
                        //沟玉白球中的黑球半径
                        blackOfWhiteSemi = angleCircle;
                        //System.out.println(angleCircle);
                        //根据目前写轮眼沟玉球转过角度来确定mini小球目前的对应的半径
                        miniCircleSemi = (angleCircle+0.0)/120*whiteOfSharingan;
                        //System.out.println(miniCircleSemi);
                        //沟玉球半径
                        sharinganSemi = miniCircleSemi+whiteOfSharingan;
                        //System.out.println(sharinganSemi);
                        //由于白球和mini小球都是在外圆上，所以通过弦对应的角度来求的小圆落后于白球的角度
                        angleOfWhiteMini = Math.asin(sharinganSemi/2/extendCircleSemi)*2;
                        //System.out.println(angleOfWhiteMini);
                        //沟玉球心到白球中心距离
                        distansOfWhiteShar = (whiteOfSharingan-miniCircleSemi)/2;
                        //沟玉球心到中心点的距离
                        distansOfSharSemi = Math.sqrt(  extendCircleSemi*extendCircleSemi
                                                        -((whiteOfSharingan+miniCircleSemi)/2)*((whiteOfSharingan+miniCircleSemi)/2)
                                                        +((whiteOfSharingan-miniCircleSemi)/2)*((whiteOfSharingan-miniCircleSemi)/2));
                        //沟玉球心和白求在中心圆上所成的角度
                        //通过这里可以可以求出由于计算机计算产生的计算误差为（0.02500260489936114）
                    //System.out.println(distansOfSharSemi);
                        angleOfwhiteShar = Math.asin(distansOfWhiteShar/2/distansOfSharSemi);
                        //System.out.println(angleOfwhiteShar);
                        if (angleCircle == 120) {
                            flag = false;
                        }
                    }else {
                        angleCircle += 2;
                        //沟玉白球中的黑球半径
                        blackOfWhiteSemi = 240-angleCircle;
                        //根据目前写轮眼沟玉球转过角度来确定mini小球目前的对应的半径
                        miniCircleSemi = (240.0-angleCircle)/120*whiteOfSharingan;
                        //miniCircleSemi = (angleCircle+0.0)/120*whiteOfSharingan;
                        //沟玉球半径
                        sharinganSemi = miniCircleSemi+whiteOfSharingan;
                        //由于白球和mini小球都是在外圆上，所以通过弦对应的角度来求的小圆落后于白球的角度
                        angleOfWhiteMini = Math.asin(sharinganSemi/2/extendCircleSemi)*2;
                        //沟玉球心到白球中心距离
                        distansOfWhiteShar = (whiteOfSharingan-miniCircleSemi)/2;
                        //沟玉球心到中心点的距离
                        distansOfSharSemi = Math.sqrt(  extendCircleSemi*extendCircleSemi
                                                        -((whiteOfSharingan+miniCircleSemi)/2)*((whiteOfSharingan+miniCircleSemi)/2)
                                                        +((whiteOfSharingan-miniCircleSemi)/2)*((whiteOfSharingan-miniCircleSemi)/2));
                        //沟玉球心和白求在中心圆上所成的角度
                        angleOfwhiteShar = Math.asin(distansOfWhiteShar/2/distansOfSharSemi);
                        if (angleCircle == 240) {
                            angleCircle = 0;
                            flag = true;
                        }
                    }
                    try {
                        Thread.sleep(20);
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
        jFrame.setTitle("Sharingan");
        jFrame.setSize(centerX*2, centerY*2);
        jFrame.getContentPane().setBackground(Color.black);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jPanel = new SharinganJPanel();
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}



