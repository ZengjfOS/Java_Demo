package demo;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
*
* <p>
* <h4>一、软件声明：</h4><br>
* <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
*           本软件是仿AutoCAD部分功能软件，最初设计之初的想法是：实现各种图形的绘制(直线、多段线、圆、写轮眼)。但是
*       当实现到了目前的状态的时候发现后面的程序开发只不过是对当前程序的复制、粘贴，而且最终绘制出来的图形看上去并不是
*       很精细、优雅，就如同当您在使用本软件的时候会发现，绘制出来的直线是那种很让人纠结的线，不够平滑的感觉，至少她让
*       我本人感觉不是太舒服的感觉。
* </p>
* <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
*             同时如果在软件中添加过多的东西，会让人觉的更难以阅读理解，不适合用来沟通交流，尤其是像这种只能通过文字沟通
*         的方式，所以本人不打算对此软件进行进一步的扩展。
* </p>
* <h4>二、软件结构如下：</h4><br><ol>
*             <li>所有类的继承关系如下：<br>
*             <ul><li>绘图类继承关系：<br>
*                     |--Shape<br>
*                     |--|--SingleLine<br>
*                     |--|--MultiLine<br>
*                 <li>按钮类继承关系(括号内为添加的监听事件)：<br>
*                     |--JButton<br>
*                     |--|--PaintJButton<br>
*                     |--|--|--SingleJButton(MouseAdapter)<br>
*                     |--|--|--MultiJButton(MouseAdapter)<br>
*                 <li>窗口类继承关系：<br>
*                     |--JFrame<br>
*                     |--|--PaintJFrame<br>
*                 <li>绘图面板继承关系(PaintJFrame的内部类，括号内为添加的监听事件):<br>
*                     |--JPanel<br>
*                     |--PaintJPanel(MouseAdapter,MouseMotionAdapter)<br>
*             </ul>
*             <li>绘图类与按钮来的逻辑关系如下：
*             <ul><li>SingleLine和SingleJButton是对应的;
*                 <li>MultiLine和MultJButton是对应的;
*                 <li>当按下SingleJButton时，就能够在面板上绘制SingleLine；
*                 <li>当按下MultJButton时，就能够在面板上绘制MultiLine；
*             </ul>
*             <li>GUI图形界面容器、组件的包含关系：<br>
*             <ul>
*                 |--PaintJFrame(窗口容器，位于main函数中，BorderLayout布局)<br>
*                 |--|--buttonJPanel(JPanel容器，全局变量，FlowLayout布局)<br>
*                 |--|--|--singleLineJButton(绘直线按钮，paintJButtons数组中下标为0的位置)<br>
*                 |--|--|--multiLineJButton(绘多段线按钮，paintJButtons数组中下标为1的位置)<br>
*                 |--|--paintJPanel(JPanel画板，全局变量)<br>
*             </ul>    
* </ol>    
*/
public class PaintJFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    /**
     * &nbsp &nbsp &nbsp &nbsp
     *      主要用于保存所画图的类型,如:直线、多样线等内容，可以认为是一个容器，包含所有的图形的基本信息，
     * 每次当前绘制的图形都保存在shapes的最后一个位置上，主要是为了便于查找。
     */
    Shape[] shapes = {};
    /**
     * 定义一个绘图面板，主要用于绘图
     */
    PaintJPanel paintJPanel    = new PaintJPanel();
    /**
     * 定义一个按钮面板,主要用于放置按钮
     */
    JPanel buttonJPanel = new JPanel(new FlowLayout());
    /**
     * 主要用于保存动态的X轴坐标，通过鼠标移动监听器来获取
     */
    int trendsX = 0;
    /**
     * 主要用于保存动态的Y轴坐标，通过鼠标移动监听器来获取
     */
    int trendsY = 0;
    /**
     * 主要用于保存点击时的X轴坐标，通过鼠标移动监听器来获取
     */
    int clickX = 0;
    /**
     * 主要用于保存点击时的Y轴坐标，通过鼠标移动监听器来获取
     */
    int clickY = 0;
    /**
     * 用于放置各种按钮，主要用于凸现当前绘图时的按钮,方便查找,修改
     */
    PaintJButton[] paintJButtons = new PaintJButton[2];
    /**
     * <h4>窗口构造函数功能如下:</h4><br><ol>
     *         <li>setButtonJPanel();设置按钮面板,主要是添加一些需要的按钮在其中;
     *         <li>setJFrame();设置窗口属性,主要是窗口的一些基本属性设置;
     *         <li>paintJPanel.startRun();启动paintJPanel内部线程，主要用于对画板中图形的计算以及重绘
     * </ol>
     */
    public PaintJFrame() {
        setButtonJPanel();
        setJFrame();
        paintJPanel.startRun();
    }
    /**
     * <h4>设置按钮面板函数功能如下:</h4><br><ol>
     *         <li>创建一个绘制直线的按钮,名字为:直线;
     *         <li>创建一个绘制多段线的按钮,名字为:多段线;
     *         <li>将直线按钮加入paintJButtons[0]中,主要是因为直线按钮对应PaintJButton.SINGLE_LINE；
     *             将多段线按钮加入paintJButtons[1]中,主要是因为直线按钮对应PaintJButton.MULTI_LINE
     *         <li>将两个按钮添加进入buttonJPanel。
     * <ol>
     */
    private void setButtonJPanel() {
        PaintJButton singleLineJButton = new SingleJButton("直线");
        PaintJButton multiLineJButton = new MultiJButton("多段线");
        paintJButtons[PaintJButton.SINGLE_LINE] = singleLineJButton;
        paintJButtons[PaintJButton.MULTI_LINE] = multiLineJButton;
        buttonJPanel.add(singleLineJButton);
        buttonJPanel.add(multiLineJButton);
    }
    /**
     * <h4>窗体设置函数功能如下:</h4><br><ol>
     *         <li>设置窗体布局格式;
     *         <li>设置窗体标题;
     *         <li>设置窗体大小;
     *         <li>设置窗体关闭模式;
     *         <li>设置窗体位置;
     *         <li>将paintJPanel,buttonJPanel加入窗体相应位置
     *         <li>将窗体设置为可见;
     * </ol>
     */
    private void setJFrame() {
        this.setLayout(new BorderLayout());
        this.setTitle("Painting");
        this.setSize(1024, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(    (int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-1024)/2),
                            (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-600)/2));
        this.add(BorderLayout.CENTER,paintJPanel);
        this.add(BorderLayout.NORTH,buttonJPanel);
        this.setVisible(true);
    }
    /**
     * <h4>PaintJPanel类设计思路:</h4><br><ol>
     *         这是一个绘图面板,只与图形显示和鼠标点击、移动相关;<br><br>
     *         <li>在图形显示方面:</li>
     *         <ul><li>重写paint()方法;
     *             <li>添加线程来动态维护图形显示。
     *         </ul>
     *         <li>需要在这个类中添加事件监听事件:
     *         <ul><li>鼠标点击，本类使用了MouseAdapter;
     *             <li>鼠标移动事件，本类使用了MouseMotionAdapter。
     *         </ul>
     * </ol>
     */
    class PaintJPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        /**
         * <h4>PaintJPanel构造方法功能如下：</h4><br><ol>
         *         <li>为PaintJPanel添加MouseAdapter事件；
         *         <li>为PaintJPanel添加MouseMotionAdapter事件。
         * </ol>
         */
        public PaintJPanel() {    
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickX = e.getX();
                    clickY = e.getY();
                    dealWithClick(e);
                }
            });
            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    trendsX = e.getX();
                    trendsY = e.getY();
                    setDynamicPoint();
                }
            });
        }
        /**
         * <h4>设置动态点函数功能如下：</h4><br><ol>
         *         <li>如果当前绘制的是直线
         *         <ul><li>判断是否当前是绘制直线的第二个点，因为，第一个点不需要动态效果；
         *             <li>由于当前所绘制的图形信息保存在shapes最后一个位置上，所以快速找到那个对象；
         *             <li>如果是，则设置直线的第二个点。</ul>
         *         <li>如果当前绘制的是多段线
         *         <ul><li>判断是否当前是绘制多段线非第一个点，因为，第一个点不需要动态效果；
         *             <li>由于当前所绘制的图形信息保存在shapes最后一个位置上，所以快速找到那个对象；
         *             <li>如果是，则设置将动态点加入多段线的pointX、pointY数组的末尾。</ul>
         * </ol>        
         */
        private void setDynamicPoint() {
            if (PaintJButton.currentButtonFlag == PaintJButton.SINGLE_LINE) {
                if (SingleLine.clickCount == 1) {
                    SingleLine singleLine = (SingleLine)shapes[shapes.length-1];
                    singleLine.setSecondPoint(trendsX, trendsY);
                }
            }if (PaintJButton.currentButtonFlag == PaintJButton.MULTI_LINE) {
                if (MultiLine.clickCount != 0) {
                    MultiLine multiLine = (MultiLine)shapes[shapes.length-1];
                    multiLine.pointX[MultiLine.clickCount] = trendsX;
                    multiLine.pointY[MultiLine.clickCount] = trendsY;
                }
            }
        }
        /**
         * <h4>处理点击函数功能如下:</h4><br><ol>
         *         <li>如果是鼠标左击事件,进入左击函数处理左击相关事务;
         *         <li>如果是鼠标中键事件,直接处理;
         *         <li>如果是鼠标右击事件,进入右击函数处理右击相关事务。
         * </ol>
         */
        private void dealWithClick(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                leftClick();
            }else if (e.getButton() == MouseEvent.BUTTON2) {
                System.out.println("您点了鼠标中间( ^_^ )");
            }else if (e.getButton() == MouseEvent.BUTTON3) {
                rightClick();
            }        
        }
        /**
         * <h4>右击函数</h4><br><ol>
         *         右击函数是用于结束当前绘图状态,相当于鼠标右键结束本次绘图环境：<br><br>
         *         <li>如果当前绘制的是直线:
         *         <ul><li>右击处理近针对非第一次点击,如果没有开始绘图,没必要结束本次绘图;
         *             <li>因为是直线,取消绘图,相当于直接抛弃当前直线就行了;
         *             <li>同时需要将直线的全局变量clickCount置0,为下一次绘图作准备。</ul>
         *         <li>如果当前绘图的是多段线:
         *         <ul><li>右击处理近针对非第一次点击,如果没有开始绘图,没必要结束本次绘图;
         *             <li>因为是多段线,取消绘图,相当于直接抛弃最后一个点就行了;
         *             <li>同时需要将多段线的全局变量clickCount置0,为下一次绘图作准备。</ul>
         * </ol>
         */
        private void rightClick() {
            if (PaintJButton.currentButtonFlag == PaintJButton.SINGLE_LINE) {
                if (SingleLine.clickCount != 0) {
                    shapes = Arrays.copyOf(shapes, shapes.length-1);
                }
                SingleLine.clickCount = 0;
            }else if (PaintJButton.currentButtonFlag == PaintJButton.MULTI_LINE) {
                if (MultiLine.clickCount != 0) {
                    MultiLine multiLine = (MultiLine)shapes[shapes.length-1];
                    multiLine.setXYLengthUpDown(-1);
                }    
                MultiLine.clickCount = 0;    
            }
        }
        /**
         * <h4>左击函数功能如下:</h4><br><ol>
         *         左击函数,主要用于给当前选定的图形添加坐标点相关信息:<br><br>
         *
         *         <li>如果当前绘制的图形是直线,进入直线左击函数处理相关的事务;
         *         <li>如果当前绘制的图形是多段线,进入多段线左击函数处理相关的事务。
         * </ol>
         */
        private void leftClick() {
            if (PaintJButton.currentButtonFlag == PaintJButton.SINGLE_LINE) {
                leftClickForSingleLine();
            }else if (PaintJButton.currentButtonFlag == PaintJButton.MULTI_LINE) {
                leftClickForMutiLine();
            }
        }
        /**
         * <h4>多段线左击函数功能如下:</h4><br><ol>
         *         <li>如果是第一次点击:
         *         <ul><li>shapes长度+1;
         *             <li>新建多段线对象;
         *             <li>将新建的对象放入shapes最后;
         *             <li>将当前鼠标点击的坐标放入多段线第一个坐标值中
         *             <li>点击次数+1;</ul>
         *         <li>如果不是第一次点击:
         *         <ul><li>点击次数+1;
         *             <li>从shapes最后一个位置取出多段线对象;
         *             <li>将当前鼠标点击的坐标放入多段线对应坐标值中</ul>
         *     </ol>
         */
        private void leftClickForMutiLine() {
            if (MultiLine.clickCount == 0) {
                shapes = Arrays.copyOf(shapes, shapes.length+1);
                MultiLine multiLine = new MultiLine();
                shapes[shapes.length-1] = multiLine;
                multiLine.setFirstPoint(clickX, clickY);
                MultiLine.clickCount++;
            }else {
                MultiLine.clickCount++;
                MultiLine multiLine = (MultiLine)shapes[shapes.length-1];
                multiLine.setOtherPoint(clickX, clickY);
            }
        }
        /**
         * <h4>直线左击函数功能如下:</h4>
         *         <li>如果是第一次点击:
         *         <ul><li>shapes长度+1;
         *             <li>新建直线对象;
         *             <li>将新建的对象放入shapes最后;
         *             <li>将当前鼠标点击的坐标放入多段线第一个坐标值中
         *             <li>将当前鼠标点击的坐标放入多段线第二个坐标值中,消除原点坐标影响
         *             <li>点击次数+1;</ul>
         *         <li>如果不是第一次点击:
         *         <ul><li>从shapes最后一个位置取出直线对象;
         *             <li>将当前鼠标点击的坐标放入直线对应坐标值中
         *             <li>点击次数清零;</ul>
         *     </ol>
         */
        private void leftClickForSingleLine() {
            if (SingleLine.clickCount == 0) {
                shapes = Arrays.copyOf(shapes, shapes.length+1);
                SingleLine singleLine = new SingleLine();
                shapes[shapes.length-1] = singleLine;
                singleLine.setFirstPoint(clickX, clickY);
                singleLine.setSecondPoint(clickX, clickY);
                SingleLine.clickCount++;
            }else {
                SingleLine singleLine = (SingleLine)shapes[shapes.length-1];
                singleLine.setSecondPoint(clickX, clickY);
                SingleLine.clickCount = 0;
            }
        }
        /**
         * <h4>重写paint方法</h4><br><ol>
         *         <li>调用父类paint方法;
         *         <li>设置背景色:黑色;
         *         <li>将shapes数组中的图形绘出来。
         * </ol>
         */
        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            this.setBackground(Color.black);
            for (int i = 0; i < shapes.length; i++) {
                shapes[i].show(graphics, Color.white);
            }
        }
        /**
         * <h4>startRun方法功能如下:</h4><br><ol>
         *         创建一个线程用于维护PaintJPanel所需的一些动态效果:<br><br>
         *             <li>用于改变当前选择绘制的图形按钮，高亮突出显示，容易辨别，只有当两个标志不相同时
         *                 才改变颜色，相同表示同一个按钮多次点击，主要是为了解决对同一个按钮多次点击的问题;
         *             <li>50ms将图形刷新一次。
         * </ol>
         */
        public void startRun() {
            new Thread(){
                public void run() {    
                    while (true) {
                        if (PaintJButton.preButtonFlag != PaintJButton.currentButtonFlag) {
                            paintJButtons[PaintJButton.preButtonFlag].setBackground(Color.white);
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        repaint();
                    }
                };
            }.start();
        }
    }
    public static void main(String[] args) {
        new PaintJFrame();
    }
}

/**
 * <h4>一、本类设计思路如下:</h4><br><ol>
 *         <li>本类主要用于保存各种图形的基本形状信息;
 *         <li>仅针对目前的需求,如直线、多段线,提供保存X,Y轴坐标信息，一些基本方法.
 * <ol>
 */
abstract class Shape {
    /** x轴数列,默认长度为1 */
    int[] pointX = { 0 };
    /** Y轴数列,默认长度为1 */
    int[] pointY = { 0 };
    public Shape() {
    }
    public Shape(int pointX,int pointY) {
        this.pointX[0] = pointX;
        this.pointY[0] = pointY;
    }
    /** 抽象方法,因为每个图形都应该有自己的绘图方法 */
    public abstract void show(Graphics graphics,Color color);
    @Override
    public String toString() {
        return "pointX:"+Arrays.toString(pointX)+"\npointY:"+Arrays.toString(pointY);
    }
}

/**
 * <h4>本类设计思路如下:</h4><br><ol>
 *         <li>继承自Shape类；
 *        <li>一条直线有两个坐标点；
 *        <li>实现show方法；
 *        <li>static int clickcount = 0;用一个静态变量确定鼠标点击的次数，方便绘图时
 *                确定当前所处的绘图状态。
 * </ol>
 */
class SingleLine extends Shape{
    /** 主要用于解决在绘制单条线的时候，当前鼠标点击是第一次点击，还是第二次点击 */
    public static int clickCount = 0;
    public SingleLine() {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+1);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+1);
    }
    /**
     * 设置直线第一个点坐标
     */
    public void setFirstPoint(int pointX_0, int pointY_0) {
        pointX[0] = pointX_0;
        pointY[0] = pointY_0;
    }
    /**
     * 设置直线第二个点坐标
     */
    public void setSecondPoint(int pointX_1,int pointY_1) {
        pointX[1] = pointX_1;
        pointY[1] = pointY_1;
    }
    @Override
    public void show(Graphics graphics, Color color) {
        graphics.setColor(color);
        graphics.drawLine(pointX[0], pointY[0], pointX[1], pointY[1]);
    }
    @Override
    public String toString() {
        return "pointX"+Arrays.toString(pointX)+"\n"+"pointY"+Arrays.toString(pointY);
    }
}

/**
 * <h4>本类设计思路如下:</h4><br><ol>
 *         <li>继承自Shape类；
 *        <li>一条多段线有多个坐标点；
 *        <li>实现show方法；
 *        <li>static int clickcount = 0;用一个静态变量确定鼠标点击的次数，方便绘图时
 *            确定当前所处的绘图状态。
 *</ol>
 */
class MultiLine extends Shape{
    /** 主要用于解决在绘制单条线的时候，当前鼠标点击是第一次点击，还是第几次点击 */
    public static int clickCount = 0;
    public MultiLine() {
    }
    /** 在创建MultiLine以后,加入第一个点的时候,pointX,pointY长度加+1 */
    public void setFirstPoint(int pointX_0, int pointY_0) {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+1);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+1);
        pointX[0] = pointX_0;
        pointY[0] = pointY_0;
        pointX[1] = pointX_0;
        pointY[1] = pointY_0;
    }
    /**
     * 每次多段线上多出一个点时,数组长度+1,并赋予相应的值
     */
    public void setOtherPoint(int pointX_1,int pointY_1) {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+1);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+1);
        pointX[pointX.length-2] = pointX_1;
        pointY[pointY.length-2] = pointY_1;
        pointX[pointX.length-1] = pointX_1;
        pointY[pointY.length-1] = pointY_1;
    }
    /**
     * 增加或者截取pointX,pointY长度
     */
    public void setXYLengthUpDown(int x) {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+x);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+x);
    }
    @Override
    public void show(Graphics graphics, Color color) {
        graphics.setColor(color);
        for (int i = 0; i < pointX.length-1; i++) {
            graphics.drawLine(pointX[i], pointY[i], pointX[i+1], pointY[i+1]);
        }
    }
    @Override
    public String toString() {
        return "pointX"+Arrays.toString(pointX)+"\n"+"pointY"+Arrays.toString(pointY);
    }
}

/**
 * <h4>一、本类设计思路如下:</h4><br><ol>
 *         <li>通过选择不同的按钮来绘制不同的图形;
 *         <li>通过改变当前按键的背景色来区分当前选中的按钮和未被选中的按钮.
 * </ol>
 * <h4>二、解决方案如下:</h4><br><ol>
 *      <li>使用"静态"的数据来保存当前所选择的按钮(currentButtonFlag);
 *      <li>为了保证上一次的按钮能恢复到没有按下的状态的颜色,,所以又需要一个
 *              "静态"的数据来保存前一次所选择的按钮(preButtonFlag);
 *      <li>常量SINGLE_LINE表示直线,常量MULTI_LINE表示多段线.
 * </ol>
 */
class PaintJButton extends JButton{
    private static final long serialVersionUID = 1L;
    /** 用于保存上一次点击的按键的标志 */
    static int preButtonFlag = 0;
    /** 用于保存当前正在使用的按键标志 */
    static int currentButtonFlag = 0;
    /** 直线的标志 */
    public static final int SINGLE_LINE = 0;
    /**　多段线的标志　*/
    public static final int MULTI_LINE = 1;
    public PaintJButton() {
    }
    public PaintJButton(String string) {
        super(string);    
    }
}

/**
 * <h4>一、本类设计思路如下:</h4><br><ol>
 *         <li>本类继承自PaintJButton;
 *      <li>为本类添加按钮响应事件;
 *      <ul><li>preButtonFlag = currentButtonFlag;将当前的按键标志赋给前一次的按键标志;
 *          <li>currentButtonFlag = SINGLE_LINE;并将当前的按键标志改成多段线对应的按键值;
 *          <li>setBackground(Color.red);改变当前按键的背景色。
 *        </ul>
 *</ol>
 */
final class SingleJButton extends PaintJButton {

    private static final long serialVersionUID = 1L;
    public SingleJButton() {
        addMouseAdepter();
    }
    public SingleJButton(String string) {
        super(string);
        addMouseAdepter();
    }
    /**
     * <h4>添加鼠标适配器</h4><br><ol>
     *         用于添加鼠标点击事件：
     *         <li>如果curentButtonFlag保存的当前的按钮标志，所以当点击一个按钮时，currentButtonFlag
     *                 保存的值边成了前一个按钮的值，而这个值应该由preButtonFlag来保存。
     *         <li>将currentButtonFlag设置成SingleJButton对应的SINGLE_LINE值；
     *         <li>将按钮颜色设置为红色。
     * </ol>
     */
    private void addMouseAdepter() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                preButtonFlag = currentButtonFlag;
                currentButtonFlag = SINGLE_LINE;
                setBackground(Color.red);
            }
        });    
    }
}

/**
 * <h4>一、本类设计思路如下:</h4><br><ol>
 *         <li>本类继承自PaintJButton;
 *      <li>为本类添加按钮响应事件;
 *      <ul><li>preButtonFlag = currentButtonFlag;将当前的按键标志赋给前一次的按键标志;
 *          <li>currentButtonFlag = MULTI_LINE;并将当前的按键标志改成多段线对应的按键值;
 *          <li>setBackground(Color.red);改变当前按键的背景色.
 *      </ul>
 * </ol>
 */
final class MultiJButton extends PaintJButton{
    private static final long serialVersionUID = 1L;
    public MultiJButton() {
        addMouseAdapter();
    }
    public MultiJButton(String string) {
        super(string);
        addMouseAdapter();
    }
    /**
     * <h4>添加鼠标适配器</h4><br><ol>
     *         用于添加鼠标点击事件：
     *         <li>如果curentButtonFlag保存的当前的按钮标志，所以当点击一个按钮时，currentButtonFlag
     *                 保存的值边成了前一个按钮的值，而这个值应该由preButtonFlag来保存。
     *         <li>将currentButtonFlag设置成MultiJButton对应的MULTI_LINE值；
     *         <li>将按钮颜色设置为红色。
     * </ol>
     */
    private void addMouseAdapter() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                preButtonFlag = currentButtonFlag;
                currentButtonFlag = MULTI_LINE;
                setBackground(Color.red);
            }
        });
    }
}


