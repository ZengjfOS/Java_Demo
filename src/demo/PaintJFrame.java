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
* <h4>һ�����������</h4><br>
* <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
*           ������Ƿ�AutoCAD���ֹ��������������֮�����뷨�ǣ�ʵ�ָ���ͼ�εĻ���(ֱ�ߡ�����ߡ�Բ��д����)������
*       ��ʵ�ֵ���Ŀǰ��״̬��ʱ���ֺ���ĳ��򿪷�ֻ�����ǶԵ�ǰ����ĸ��ơ�ճ�����������ջ��Ƴ�����ͼ�ο���ȥ������
*       �ܾ�ϸ�����ţ�����ͬ������ʹ�ñ������ʱ��ᷢ�֣����Ƴ�����ֱ�������ֺ����˾�����ߣ�����ƽ���ĸо�����������
*       �ұ��˸о�����̫����ĸо���
* </p>
* <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
*             ͬʱ������������ӹ���Ķ����������˾��ĸ������Ķ���⣬���ʺ�������ͨ������������������ֻ��ͨ�����ֹ�ͨ
*         �ķ�ʽ�����Ա��˲�����Դ�������н�һ������չ��
* </p>
* <h4>��������ṹ���£�</h4><br><ol>
*             <li>������ļ̳й�ϵ���£�<br>
*             <ul><li>��ͼ��̳й�ϵ��<br>
*                     |--Shape<br>
*                     |--|--SingleLine<br>
*                     |--|--MultiLine<br>
*                 <li>��ť��̳й�ϵ(������Ϊ��ӵļ����¼�)��<br>
*                     |--JButton<br>
*                     |--|--PaintJButton<br>
*                     |--|--|--SingleJButton(MouseAdapter)<br>
*                     |--|--|--MultiJButton(MouseAdapter)<br>
*                 <li>������̳й�ϵ��<br>
*                     |--JFrame<br>
*                     |--|--PaintJFrame<br>
*                 <li>��ͼ���̳й�ϵ(PaintJFrame���ڲ��࣬������Ϊ��ӵļ����¼�):<br>
*                     |--JPanel<br>
*                     |--PaintJPanel(MouseAdapter,MouseMotionAdapter)<br>
*             </ul>
*             <li>��ͼ���밴ť�����߼���ϵ���£�
*             <ul><li>SingleLine��SingleJButton�Ƕ�Ӧ��;
*                 <li>MultiLine��MultJButton�Ƕ�Ӧ��;
*                 <li>������SingleJButtonʱ�����ܹ�������ϻ���SingleLine��
*                 <li>������MultJButtonʱ�����ܹ�������ϻ���MultiLine��
*             </ul>
*             <li>GUIͼ�ν�������������İ�����ϵ��<br>
*             <ul>
*                 |--PaintJFrame(����������λ��main�����У�BorderLayout����)<br>
*                 |--|--buttonJPanel(JPanel������ȫ�ֱ�����FlowLayout����)<br>
*                 |--|--|--singleLineJButton(��ֱ�߰�ť��paintJButtons�������±�Ϊ0��λ��)<br>
*                 |--|--|--multiLineJButton(�����߰�ť��paintJButtons�������±�Ϊ1��λ��)<br>
*                 |--|--paintJPanel(JPanel���壬ȫ�ֱ���)<br>
*             </ul>    
* </ol>    
*/
public class PaintJFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    /**
     * &nbsp &nbsp &nbsp &nbsp
     *      ��Ҫ���ڱ�������ͼ������,��:ֱ�ߡ������ߵ����ݣ�������Ϊ��һ���������������е�ͼ�εĻ�����Ϣ��
     * ÿ�ε�ǰ���Ƶ�ͼ�ζ�������shapes�����һ��λ���ϣ���Ҫ��Ϊ�˱��ڲ��ҡ�
     */
    Shape[] shapes = {};
    /**
     * ����һ����ͼ��壬��Ҫ���ڻ�ͼ
     */
    PaintJPanel paintJPanel    = new PaintJPanel();
    /**
     * ����һ����ť���,��Ҫ���ڷ��ð�ť
     */
    JPanel buttonJPanel = new JPanel(new FlowLayout());
    /**
     * ��Ҫ���ڱ��涯̬��X�����꣬ͨ������ƶ�����������ȡ
     */
    int trendsX = 0;
    /**
     * ��Ҫ���ڱ��涯̬��Y�����꣬ͨ������ƶ�����������ȡ
     */
    int trendsY = 0;
    /**
     * ��Ҫ���ڱ�����ʱ��X�����꣬ͨ������ƶ�����������ȡ
     */
    int clickX = 0;
    /**
     * ��Ҫ���ڱ�����ʱ��Y�����꣬ͨ������ƶ�����������ȡ
     */
    int clickY = 0;
    /**
     * ���ڷ��ø��ְ�ť����Ҫ����͹�ֵ�ǰ��ͼʱ�İ�ť,�������,�޸�
     */
    PaintJButton[] paintJButtons = new PaintJButton[2];
    /**
     * <h4>���ڹ��캯����������:</h4><br><ol>
     *         <li>setButtonJPanel();���ð�ť���,��Ҫ�����һЩ��Ҫ�İ�ť������;
     *         <li>setJFrame();���ô�������,��Ҫ�Ǵ��ڵ�һЩ������������;
     *         <li>paintJPanel.startRun();����paintJPanel�ڲ��̣߳���Ҫ���ڶԻ�����ͼ�εļ����Լ��ػ�
     * </ol>
     */
    public PaintJFrame() {
        setButtonJPanel();
        setJFrame();
        paintJPanel.startRun();
    }
    /**
     * <h4>���ð�ť��庯����������:</h4><br><ol>
     *         <li>����һ������ֱ�ߵİ�ť,����Ϊ:ֱ��;
     *         <li>����һ�����ƶ���ߵİ�ť,����Ϊ:�����;
     *         <li>��ֱ�߰�ť����paintJButtons[0]��,��Ҫ����Ϊֱ�߰�ť��ӦPaintJButton.SINGLE_LINE��
     *             ������߰�ť����paintJButtons[1]��,��Ҫ����Ϊֱ�߰�ť��ӦPaintJButton.MULTI_LINE
     *         <li>��������ť��ӽ���buttonJPanel��
     * <ol>
     */
    private void setButtonJPanel() {
        PaintJButton singleLineJButton = new SingleJButton("ֱ��");
        PaintJButton multiLineJButton = new MultiJButton("�����");
        paintJButtons[PaintJButton.SINGLE_LINE] = singleLineJButton;
        paintJButtons[PaintJButton.MULTI_LINE] = multiLineJButton;
        buttonJPanel.add(singleLineJButton);
        buttonJPanel.add(multiLineJButton);
    }
    /**
     * <h4>�������ú�����������:</h4><br><ol>
     *         <li>���ô��岼�ָ�ʽ;
     *         <li>���ô������;
     *         <li>���ô����С;
     *         <li>���ô���ر�ģʽ;
     *         <li>���ô���λ��;
     *         <li>��paintJPanel,buttonJPanel���봰����Ӧλ��
     *         <li>����������Ϊ�ɼ�;
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
     * <h4>PaintJPanel�����˼·:</h4><br><ol>
     *         ����һ����ͼ���,ֻ��ͼ����ʾ����������ƶ����;<br><br>
     *         <li>��ͼ����ʾ����:</li>
     *         <ul><li>��дpaint()����;
     *             <li>����߳�����̬ά��ͼ����ʾ��
     *         </ul>
     *         <li>��Ҫ�������������¼������¼�:
     *         <ul><li>�����������ʹ����MouseAdapter;
     *             <li>����ƶ��¼�������ʹ����MouseMotionAdapter��
     *         </ul>
     * </ol>
     */
    class PaintJPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        /**
         * <h4>PaintJPanel���췽���������£�</h4><br><ol>
         *         <li>ΪPaintJPanel���MouseAdapter�¼���
         *         <li>ΪPaintJPanel���MouseMotionAdapter�¼���
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
         * <h4>���ö�̬�㺯���������£�</h4><br><ol>
         *         <li>�����ǰ���Ƶ���ֱ��
         *         <ul><li>�ж��Ƿ�ǰ�ǻ���ֱ�ߵĵڶ����㣬��Ϊ����һ���㲻��Ҫ��̬Ч����
         *             <li>���ڵ�ǰ�����Ƶ�ͼ����Ϣ������shapes���һ��λ���ϣ����Կ����ҵ��Ǹ�����
         *             <li>����ǣ�������ֱ�ߵĵڶ����㡣</ul>
         *         <li>�����ǰ���Ƶ��Ƕ����
         *         <ul><li>�ж��Ƿ�ǰ�ǻ��ƶ���߷ǵ�һ���㣬��Ϊ����һ���㲻��Ҫ��̬Ч����
         *             <li>���ڵ�ǰ�����Ƶ�ͼ����Ϣ������shapes���һ��λ���ϣ����Կ����ҵ��Ǹ�����
         *             <li>����ǣ������ý���̬��������ߵ�pointX��pointY�����ĩβ��</ul>
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
         * <h4>������������������:</h4><br><ol>
         *         <li>������������¼�,�������������������������;
         *         <li>���������м��¼�,ֱ�Ӵ���;
         *         <li>���������һ��¼�,�����һ����������һ��������
         * </ol>
         */
        private void dealWithClick(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                leftClick();
            }else if (e.getButton() == MouseEvent.BUTTON2) {
                System.out.println("����������м�( ^_^ )");
            }else if (e.getButton() == MouseEvent.BUTTON3) {
                rightClick();
            }        
        }
        /**
         * <h4>�һ�����</h4><br><ol>
         *         �һ����������ڽ�����ǰ��ͼ״̬,�൱������Ҽ��������λ�ͼ������<br><br>
         *         <li>�����ǰ���Ƶ���ֱ��:
         *         <ul><li>�һ��������Էǵ�һ�ε��,���û�п�ʼ��ͼ,û��Ҫ�������λ�ͼ;
         *             <li>��Ϊ��ֱ��,ȡ����ͼ,�൱��ֱ��������ǰֱ�߾�����;
         *             <li>ͬʱ��Ҫ��ֱ�ߵ�ȫ�ֱ���clickCount��0,Ϊ��һ�λ�ͼ��׼����</ul>
         *         <li>�����ǰ��ͼ���Ƕ����:
         *         <ul><li>�һ��������Էǵ�һ�ε��,���û�п�ʼ��ͼ,û��Ҫ�������λ�ͼ;
         *             <li>��Ϊ�Ƕ����,ȡ����ͼ,�൱��ֱ���������һ���������;
         *             <li>ͬʱ��Ҫ������ߵ�ȫ�ֱ���clickCount��0,Ϊ��һ�λ�ͼ��׼����</ul>
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
         * <h4>���������������:</h4><br><ol>
         *         �������,��Ҫ���ڸ���ǰѡ����ͼ���������������Ϣ:<br><br>
         *
         *         <li>�����ǰ���Ƶ�ͼ����ֱ��,����ֱ���������������ص�����;
         *         <li>�����ǰ���Ƶ�ͼ���Ƕ����,���������������������ص�����
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
         * <h4>��������������������:</h4><br><ol>
         *         <li>����ǵ�һ�ε��:
         *         <ul><li>shapes����+1;
         *             <li>�½�����߶���;
         *             <li>���½��Ķ������shapes���;
         *             <li>����ǰ�����������������ߵ�һ������ֵ��
         *             <li>�������+1;</ul>
         *         <li>������ǵ�һ�ε��:
         *         <ul><li>�������+1;
         *             <li>��shapes���һ��λ��ȡ������߶���;
         *             <li>����ǰ�����������������߶�Ӧ����ֵ��</ul>
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
         * <h4>ֱ�����������������:</h4>
         *         <li>����ǵ�һ�ε��:
         *         <ul><li>shapes����+1;
         *             <li>�½�ֱ�߶���;
         *             <li>���½��Ķ������shapes���;
         *             <li>����ǰ�����������������ߵ�һ������ֵ��
         *             <li>����ǰ�����������������ߵڶ�������ֵ��,����ԭ������Ӱ��
         *             <li>�������+1;</ul>
         *         <li>������ǵ�һ�ε��:
         *         <ul><li>��shapes���һ��λ��ȡ��ֱ�߶���;
         *             <li>����ǰ��������������ֱ�߶�Ӧ����ֵ��
         *             <li>�����������;</ul>
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
         * <h4>��дpaint����</h4><br><ol>
         *         <li>���ø���paint����;
         *         <li>���ñ���ɫ:��ɫ;
         *         <li>��shapes�����е�ͼ�λ������
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
         * <h4>startRun������������:</h4><br><ol>
         *         ����һ���߳�����ά��PaintJPanel�����һЩ��̬Ч��:<br><br>
         *             <li>���ڸı䵱ǰѡ����Ƶ�ͼ�ΰ�ť������ͻ����ʾ�����ױ��ֻ�е�������־����ͬʱ
         *                 �Ÿı���ɫ����ͬ��ʾͬһ����ť��ε������Ҫ��Ϊ�˽����ͬһ����ť��ε��������;
         *             <li>50ms��ͼ��ˢ��һ�Ρ�
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
 * <h4>һ���������˼·����:</h4><br><ol>
 *         <li>������Ҫ���ڱ������ͼ�εĻ�����״��Ϣ;
 *         <li>�����Ŀǰ������,��ֱ�ߡ������,�ṩ����X,Y��������Ϣ��һЩ��������.
 * <ol>
 */
abstract class Shape {
    /** x������,Ĭ�ϳ���Ϊ1 */
    int[] pointX = { 0 };
    /** Y������,Ĭ�ϳ���Ϊ1 */
    int[] pointY = { 0 };
    public Shape() {
    }
    public Shape(int pointX,int pointY) {
        this.pointX[0] = pointX;
        this.pointY[0] = pointY;
    }
    /** ���󷽷�,��Ϊÿ��ͼ�ζ�Ӧ�����Լ��Ļ�ͼ���� */
    public abstract void show(Graphics graphics,Color color);
    @Override
    public String toString() {
        return "pointX:"+Arrays.toString(pointX)+"\npointY:"+Arrays.toString(pointY);
    }
}

/**
 * <h4>�������˼·����:</h4><br><ol>
 *         <li>�̳���Shape�ࣻ
 *        <li>һ��ֱ������������㣻
 *        <li>ʵ��show������
 *        <li>static int clickcount = 0;��һ����̬����ȷ��������Ĵ����������ͼʱ
 *                ȷ����ǰ�����Ļ�ͼ״̬��
 * </ol>
 */
class SingleLine extends Shape{
    /** ��Ҫ���ڽ���ڻ��Ƶ����ߵ�ʱ�򣬵�ǰ������ǵ�һ�ε�������ǵڶ��ε�� */
    public static int clickCount = 0;
    public SingleLine() {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+1);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+1);
    }
    /**
     * ����ֱ�ߵ�һ��������
     */
    public void setFirstPoint(int pointX_0, int pointY_0) {
        pointX[0] = pointX_0;
        pointY[0] = pointY_0;
    }
    /**
     * ����ֱ�ߵڶ���������
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
 * <h4>�������˼·����:</h4><br><ol>
 *         <li>�̳���Shape�ࣻ
 *        <li>һ��������ж������㣻
 *        <li>ʵ��show������
 *        <li>static int clickcount = 0;��һ����̬����ȷ��������Ĵ����������ͼʱ
 *            ȷ����ǰ�����Ļ�ͼ״̬��
 *</ol>
 */
class MultiLine extends Shape{
    /** ��Ҫ���ڽ���ڻ��Ƶ����ߵ�ʱ�򣬵�ǰ������ǵ�һ�ε�������ǵڼ��ε�� */
    public static int clickCount = 0;
    public MultiLine() {
    }
    /** �ڴ���MultiLine�Ժ�,�����һ�����ʱ��,pointX,pointY���ȼ�+1 */
    public void setFirstPoint(int pointX_0, int pointY_0) {
        this.pointX = Arrays.copyOf(this.pointX, this.pointX.length+1);
        this.pointY = Arrays.copyOf(this.pointY, this.pointY.length+1);
        pointX[0] = pointX_0;
        pointY[0] = pointY_0;
        pointX[1] = pointX_0;
        pointY[1] = pointY_0;
    }
    /**
     * ÿ�ζ�����϶��һ����ʱ,���鳤��+1,��������Ӧ��ֵ
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
     * ���ӻ��߽�ȡpointX,pointY����
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
 * <h4>һ���������˼·����:</h4><br><ol>
 *         <li>ͨ��ѡ��ͬ�İ�ť�����Ʋ�ͬ��ͼ��;
 *         <li>ͨ���ı䵱ǰ�����ı���ɫ�����ֵ�ǰѡ�еİ�ť��δ��ѡ�еİ�ť.
 * </ol>
 * <h4>���������������:</h4><br><ol>
 *      <li>ʹ��"��̬"�����������浱ǰ��ѡ��İ�ť(currentButtonFlag);
 *      <li>Ϊ�˱�֤��һ�εİ�ť�ָܻ���û�а��µ�״̬����ɫ,,��������Ҫһ��
 *              "��̬"������������ǰһ����ѡ��İ�ť(preButtonFlag);
 *      <li>����SINGLE_LINE��ʾֱ��,����MULTI_LINE��ʾ�����.
 * </ol>
 */
class PaintJButton extends JButton{
    private static final long serialVersionUID = 1L;
    /** ���ڱ�����һ�ε���İ����ı�־ */
    static int preButtonFlag = 0;
    /** ���ڱ��浱ǰ����ʹ�õİ�����־ */
    static int currentButtonFlag = 0;
    /** ֱ�ߵı�־ */
    public static final int SINGLE_LINE = 0;
    /**������ߵı�־��*/
    public static final int MULTI_LINE = 1;
    public PaintJButton() {
    }
    public PaintJButton(String string) {
        super(string);    
    }
}

/**
 * <h4>һ���������˼·����:</h4><br><ol>
 *         <li>����̳���PaintJButton;
 *      <li>Ϊ������Ӱ�ť��Ӧ�¼�;
 *      <ul><li>preButtonFlag = currentButtonFlag;����ǰ�İ�����־����ǰһ�εİ�����־;
 *          <li>currentButtonFlag = SINGLE_LINE;������ǰ�İ�����־�ĳɶ���߶�Ӧ�İ���ֵ;
 *          <li>setBackground(Color.red);�ı䵱ǰ�����ı���ɫ��
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
     * <h4>������������</h4><br><ol>
     *         �������������¼���
     *         <li>���curentButtonFlag����ĵ�ǰ�İ�ť��־�����Ե����һ����ťʱ��currentButtonFlag
     *                 �����ֵ�߳���ǰһ����ť��ֵ�������ֵӦ����preButtonFlag�����档
     *         <li>��currentButtonFlag���ó�SingleJButton��Ӧ��SINGLE_LINEֵ��
     *         <li>����ť��ɫ����Ϊ��ɫ��
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
 * <h4>һ���������˼·����:</h4><br><ol>
 *         <li>����̳���PaintJButton;
 *      <li>Ϊ������Ӱ�ť��Ӧ�¼�;
 *      <ul><li>preButtonFlag = currentButtonFlag;����ǰ�İ�����־����ǰһ�εİ�����־;
 *          <li>currentButtonFlag = MULTI_LINE;������ǰ�İ�����־�ĳɶ���߶�Ӧ�İ���ֵ;
 *          <li>setBackground(Color.red);�ı䵱ǰ�����ı���ɫ.
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
     * <h4>������������</h4><br><ol>
     *         �������������¼���
     *         <li>���curentButtonFlag����ĵ�ǰ�İ�ť��־�����Ե����һ����ťʱ��currentButtonFlag
     *                 �����ֵ�߳���ǰһ����ť��ֵ�������ֵӦ����preButtonFlag�����档
     *         <li>��currentButtonFlag���ó�MultiJButton��Ӧ��MULTI_LINEֵ��
     *         <li>����ť��ɫ����Ϊ��ɫ��
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


