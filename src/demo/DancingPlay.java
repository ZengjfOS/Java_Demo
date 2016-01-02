/**
 *  设计声明：
 *         1、本次设计是模仿QQ炫舞类游戏，当图标到红色的检测区域时，按下正确的方向键，
 *                 将会得到good nice，运行一下您就懂了；
 *         2、本设计没有在配色、视觉效果上作改善，主要是因为个人对那些方面不懂，所以以这
 *                 种简陋的形式呈现给大家，望大家海涵；
 *         3、如果有朋友要写本程序，请注意按键监听的聚焦面板一定要能够获得聚焦，要不然，
 *                 按键会失效，如本程序中的dancingPlay.setFocusable(true);很重要。
 *         
 *   注意事项：
 *         1、本程序为java程序，同时感谢您花费宝贵的时间来阅读本文档；
 *         2、请注意包名为：dance，文件名为：DancingPlay，请注意检查，
 *                以防一些不必要的麻烦；
 *         3、在完成确认2中的内容后，本程序可以直接运行，因为本软件说明都在注释中；
 *         4、本设计是继以前的一些设计写的，所以在面已说明的东西，不再详述，同时由于个人能力、阅历等
 *                 原因，有些细节可能并没有详细阐述，或者有疏漏，请谅解。  
 *   设计思路：
 *          1、 每一个方向图标采用了仿俄罗斯方块（tetis）的工厂方法产生，所以每个方向块都是一个对象，
 *               是由类DanceDirection创建的；
 *          2、 用一个队列（queue）保存屏幕上的DanceDirection；
 *          3、 用一个线程完成DanceDirection在queue的添加，删除，以及queue中的DanceDirection的下落；
 *          4、 添加按键监听事件，用于判断处于检测区域是否有DanceDirection以及是否正确。
 *                                                
 */
package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DancingPlay extends JPanel{
    /**
     * 时间计数器，主要用于在一定间隔时间里添加一个DanceDirection
     */
    private long timeCount = 0;
    /**
     * 检测框的长度,在软件中就是那个红色的框的长度
     */
    private int square = DanceDirection.FRAME_LENGTH+6;
    /**
     * 当按键和方向标在红色框的检测范围内,且按键正确时,会被幅值为true
     */
    private boolean niceFlag = false;
    /**
     * 用于对good nice显示时间进行计数
     */
    private int dispointNiceString = 10;
    /**
     * 使用LinkedList保存当前有的图标
     */
    private Queue<DanceDirection> queue = new LinkedList<DanceDirection> ();
    /**
     * 1. 绘制queue中的方向图标
     * 2. 绘制红色的检测区域
     * 3. 绘制good nice
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawDerction(graphics);
        drawSquare(graphics);
        drawNice(graphics);
    }
    /**
     * 使用迭代器,从queue中提取方向键,并调用对应的绘图功能,将图绘制在面板上
     * @param graphics
     */
    private void drawDerction(Graphics graphics) {
            Iterator<DanceDirection> iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next().drawImage(graphics);
        }
    }
    /**
     * 绘制good nice
     *     1. 先改变颜色
     *     2. 将字体字号改成合适的
     * @param graphics
     */
    private void drawNice(Graphics graphics) {
        if (niceFlag) {
            graphics.setColor(Color.red);
            Font font = graphics.getFont();
            Font font2 = new Font(font.getName(), font.getStyle(), font.getSize()+100);
            graphics.setFont(font2);
            graphics.drawString("good", 1024/2-square-280, 600);
            graphics.drawString("nice", 1024/2+square, 600);
        }
    }
    /**
     * 绘制红色的检测区域
     * @param graphics
     */
    private void drawSquare(Graphics graphics) {
        graphics.setColor(Color.red);
        for (int i = 0; i < 6; i+=2) {
            graphics.drawRoundRect(1024/2-(square+i)/2, 500-i/2, square+i, square+i, 5, 5);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        DancingPlay dancingPlay = new DancingPlay();
        jFrame.setSize(1024, 768);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //将jFrame居中
        jFrame.setLocation(    (Toolkit.getDefaultToolkit().getScreenSize().width-1024)/2,
                            (Toolkit.getDefaultToolkit().getScreenSize().height-768)/2);
        jFrame.add(dancingPlay);
        dancingPlay.setFocusable(true);
        dancingPlay.run();
        jFrame.setVisible(true);
    }
    /**
     * 1. 按键按下时,是否有方向图标在检测范围内,
     * 2. 如果有,在检查图标的方向与按键的方向是否一致,
     * 3. 如果一致,niceFlag赋为true
     * @param keyNumber
     */
    public void checkKey(int keyNumber){
        Iterator<DanceDirection> iterator = queue.iterator();
        while (iterator.hasNext()) {
            DanceDirection danceDirection = iterator.next();
            if (Math.abs(danceDirection.positionY - 500) <= 30
                    && danceDirection.direction == keyNumber) {
                niceFlag = true;
            }
        }
    }
    /**
     * 1.用于添加键盘监听事件
     * 2.启动一个线程维护动态效果
     */
    public void run(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    checkKey(3);
                    break;
                case KeyEvent.VK_RIGHT:
                    checkKey(1);
                    break;
                case KeyEvent.VK_LEFT:
                    checkKey(2);
                    break;
                case KeyEvent.VK_DOWN:
                    checkKey(4);
                    break;
                }
                repaint();
            }
        });
        Thread thread = new Thread(){
            public void run(){
                while (true) {
                    try {
                        //1. 完成对queue中每个DanceDirection的positionY加如相应的下移值
                        //2. 将不再屏幕范围内的DanceDirection移除
                        Iterator<DanceDirection> iterator = queue.iterator();
                        while (iterator.hasNext()) {
                            DanceDirection danceDirection = iterator.next();
                            if (danceDirection.positionY >= 768) {
                                iterator.remove();
                            }
                            danceDirection.positionY += 6;
                        }
                        //检查计算good nice的显示时间
                        if (niceFlag) {
                            dispointNiceString++;
                            if (dispointNiceString >= 10) {
                                niceFlag = false;
                                dispointNiceString = 0;
                            }
                        }
                        //1. 每20*25秒添加一次图标
                        //2. 添加的方式为随即添加,目前的概率为2/3
                        if(timeCount++%25 == 0){
                            if (Math.random()*4 > 1) {
                                queue.offer(DanceDirection.getInstance());
                            }
                        }
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        };
        thread.start();
    }
}
class DanceDirection {
    /**
     * 图标框的边长
     */
    public static final int FRAME_LENGTH     = 50;
    /**
     * 方向键右的常量
     */
    public static final int RIGHT = 1;
    /**
     * 方向键左的常量
     */
    public static final int LEFT     = 2;
    /**
     * 方向键上的常量
     */
    public static final int UP     = 3;
    /**
     * 方向键下的常量
     */
    public static final int DOWN     = 4;
    /**
     * 每个DanceDirection的初始X,Y坐标
     */
    public int positionX = 1024/2;
    public int positionY = 0-FRAME_LENGTH;
    /**
     * 方向值
     */
    protected int direction;
    /**
     * 私有构造函数
     */
    private DanceDirection(){
    }
    /**
     * 静态的工厂方法
     * @return
     */
    public static DanceDirection getInstance(){
        return new Dance();
    }
    /**
     * 为工厂构造方法提供的静态类,为了得到随即的方向图标
     * @author soft1
     *
     */
    static class Dance extends DanceDirection{
        public Dance() {
            direction = (int)(Math.random()*4+1);
        }
    }
    /**
     * 方向图标绘制函数
     * @param graphics
     */
    public void drawImage(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRoundRect(    positionX-FRAME_LENGTH/2,
                                positionY,
                                FRAME_LENGTH,
                                FRAME_LENGTH,
                                FRAME_LENGTH/5,
                                FRAME_LENGTH/5);
        switch (direction) {
        case 1:
            rightDirection(graphics);
            break;
        case 2:
            leftDirection(graphics);
            break;
        case 3:
            upDirection(graphics);
            break;
        case 4:
            downDirection(graphics);
            break;
        }
    }
    /**
     * 方向向左
     * @param graphics
     */
    private void leftDirection(Graphics graphics) {
        graphics.setColor(Color.yellow);
        for (int i = 0; i < 3; i++) {
            int[] arrayX = {positionX-FRAME_LENGTH/2+2+15*i,
                            positionX-FRAME_LENGTH/2+2+15+15*i,
                            positionX-FRAME_LENGTH/2+2+15+15*i};
            int[] arrayY = {positionY+FRAME_LENGTH/2,
                            positionY+FRAME_LENGTH/2-20,
                            positionY+FRAME_LENGTH/2+20};
            graphics.fillPolygon(arrayX, arrayY, arrayX.length);
        }
    }
    /**
     * 方向向下
     * @param graphics
     */
    private void downDirection(Graphics graphics) {
        graphics.setColor(Color.green);
        for (int i = 0; i < 3; i++) {
            int[] arrayX = {positionX,
                            positionX+20,
                            positionX-20};
            int[] arrayY = {positionY+FRAME_LENGTH-2-15*i,
                            positionY+FRAME_LENGTH-2-15-15*i,
                            positionY+FRAME_LENGTH-2-15-15*i};
            graphics.fillPolygon(arrayX, arrayY, arrayX.length);
        }
    }
    /**
     * 方向向右
     * @param graphics
     */
    private void rightDirection(Graphics graphics) {
        graphics.setColor(Color.magenta);
        for (int i = 0; i < 3; i++) {
            int[] arrayX = {positionX+FRAME_LENGTH/2-2-15*i,
                            positionX+FRAME_LENGTH/2-2-15-15*i,
                            positionX+FRAME_LENGTH/2-2-15-15*i};
            int[] arrayY = {positionY+FRAME_LENGTH/2,
                            positionY+FRAME_LENGTH/2+20,
                            positionY+FRAME_LENGTH/2-20};
            graphics.fillPolygon(arrayX, arrayY, arrayX.length);
        }
    }
    /**
     * 方向向上
     * @param graphics
     */
    private void upDirection(Graphics graphics) {
        graphics.setColor(Color.pink);
        for (int i = 0; i < 3; i++) {
            int[] arrayX = {positionX,
                            positionX+20,
                            positionX-20};
            int[] arrayY = {positionY+15*i,
                            positionY+2+15+15*i,
                            positionY+2+15+15*i};
            graphics.fillPolygon(arrayX, arrayY, arrayX.length);
        }
    }
    @Override
    public String toString() {
        return "direciton:"+direction+"\tpositionX:"+positionX+"\tpositionY:"+positionY;
    }
}

