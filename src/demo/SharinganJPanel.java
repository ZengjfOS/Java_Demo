
/**
 * ���������
 *         1����Ȼ�������ձ��ˣ����Ҹ�����Ϊ��ģ���ߣ���Ȼ���ʾ���˶���ľ��⣬ϣ����Ʒ��������д���۵�������
 *         2�����ڱ�����ư���������ѧ���㣬����ǿ�ҽ�����ͨ����ע������е�5�鿴ͼ�������Ƶģ��Ȳ�Ҫ��Դ�룬
 *             Ȼ���Լ���ͼȥ��ƣ���������Ҫ֪������������㿪�������Ǻ����������ǵȻ���������˶��ѡ�
 *         3�������ұ����ڡ���̬�й�̫��ͼ����Javaԭ�����и���һ��ע�ʹ��󣬵��¡���̬�й�̫��ͼ����Javaԭ����
 *             �����ٴ�ͨ��ϵͳ����������֪�ιʣ����Ե��´�ҿ��ܿ���������̬�й�̫��ͼ����Javaԭ����������ϸ
 *             ��ע�ͣ�����ź���ͬʱҲ�����Լ�û���������ñ��ʹ�����ʾ����ķ�˼��
 *         4�������������ܺ��棬��ô���ֲ��ı��˷���ġ���̬�й�̫��ͼ����Javaԭ����(��֪�����ܲ���ͨ������)��
 *             ����̬գ��Ч������Javaԭ��������Ϊ�������������Ƶ��������̵档
 *         
 *         
 * ע�����
 *         1��������Ϊjava����ͬʱ��л�����ѱ����ʱ�����Ķ����ĵ���
 *         2����ע�����Ϊ��practice���ļ���Ϊ��SharinganJFrame(Sharingan��д���۵�Ӣ��)��ע���飬
 *                 �Է�һЩ����Ҫ���鷳��
 *         3�������ȷ��2�е����ݺ󣬱��������ֱ�����У���Ϊ�����˵������ע���У�
 *         4�����ڱ�����Ǽ̡���̬�й�̫��ͼ����Javaԭ����������̬գ��Ч������Javaԭ�������д������������
 *                 ����˵���Ķ���������������ͬʱ���ڸ���������������ԭ����Щϸ�ڿ��ܲ�û����ϸ����������
 *                 ����©�����½⡣                        
 *         5��������ע��paint()�����е�һЩ���룬��ΪЧ����ֱ�ۣ�Ҳ�����������ø��죬�������£�
 *             5.1 �ȿ�paint()������1�����Ч������paint()�����е��������ע�͵���
 *             5.2 �ٿ�paint()������1��2�����Ч������paint()�����е��������ע�͵���
 *             5.3 �ٿ�paint()������1��2��3�����Ч������paint()�����е��������ע�͵���
 *             5.4 ������ķ������ƣ�ֱ��paint()������û����䱻ע�ͣ����ŵ������꣬��������ˡ�
 *
 * ���Ŀ�꣺��һ��JFrame�л���һ����̬��д����(��������Ӱ���е������۾�)��
 *
 * ���˵����   
 *      ���źܶ��˲�ϲ������Щ�ܳ����ֵ�˵�������Ա���ֻ�򵥽���һ�¸����뷨��������һЩ���⣺
 *          1����Ƶ�Դͷ�����Һ�ͬ��(������)�������ᵽд���ۣ����Դ��������Java�����������Ҫ�Ƕ�̬�ģ�
 *          2��Ŀǰ�İ汾��д���ۺ������Ƶ�д�����кܴ��𣬺ܶ൥�ʵĶ����Ѿ�û���˵�����ζ����
 *            3�����������̬��д���۵�ʱ�����������صĵ������Ǽ��������ǲ���ʦ˵�ľ�ȷ����׼ȷ������
 *                angleErr�������������ģ���Ȼ������ǲ��Ǻ�׼ȷ��û�и������ȥ̽�������ڱ���������
 *                �֣�����û����ȥ�����������Ȥ����ȥ��ĥ����ע��һ��angleErr�����ֵĵط���������Ϊ
 *                ������ػ��˽�3����ҳ�����ԭ��
 *             
 *    2014-1-13 ����һ �� 8�� ΢�� �ϲ�
 *
 */
package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SharinganJPanel extends JPanel {
    //�����������꣬����ϲ��һ���ĵ���Ϊ��������꣬���������Ϊ�ʵ�
    static int centerX = 600/2;
    static int centerY = 600/2;
    //��Բ�뾶��д����ת���������ĺ���
    double extendCircleSemi = 200;
    //д���۵���ת������һ���̶��Ĵ�С��Բ���տ�ʼ��Ƶ�ʱ���Ƕ���Ϊ��ɫ�ģ�������Ϊ�˺�ɫ���ÿ� ^_^
    double whiteOfSharingan = extendCircleSemi/6;
    //������ĺ�ɫ��Բ�Ա���һ��������ת�Ƕȱ�������Բ����뾶ΪminiCircleSemi
    double miniCircleSemi = 0;
    //�����õ��˻���̫��ʱ��˼·�����Բ�����������Բ(whiteOfSharingan��miniCircleSemi)����
    double sharinganSemi = 0;
    //��ת�ȽǷ�Χ�ı�����������ͨ���۲���ת��ѭ���Ƕ���120*2
    int angleCircle = 0;
    //СԲ(miniCircleSemi)�Ͱ�Բ(whiteOfSharingan)����Բ(extendCircleSemi)�ϵĽǶ�
    double angleOfWhiteMini = 0;
    //д���۹���Բ(sharinganSemi)�Ͱ�Բ(whiteOfSharingan)����Բ(extendCircleSemi)�ĽǶ�
    double angleOfwhiteShar = 0;
    //�����ж�Բ�Ƿ񵽴Ｋ��λ�ã����仰˵�����Ƿ񵽴�����ȷ�ĽǶȣ�������һ��ѭ��
    boolean flag = true;
    //�������ĵ���(whiteOfSharinganԲ���ĺ�miniCircleSemiԲ���������ɵ���)���ľ��룻
    double distansOfWhiteShar =0;
    //�������ĵ����ĵ�ľ��룬��һ����Ҫ��Ϊ�˼�������
    double distansOfSharSemi = 0;
    //�ڻ�ͼ�����У����ڼ�������㿪�������Ǻ����������ǵ������������һ������ֵ����Ҫע������������
    //���Ϊ�������angleErr*2/3,��Ϊ���ֵ�Ǳ���ͨ�����������ٽ�״̬�õ���ֵ��
    double angleErr = 0.02500260489936114;
    //������(whiteOfSharingan)�еĺ���뾶�����ֵ�Ƕ�̬��
    int blackOfWhiteSemi = 0;
    //�����۾���ռ�ĳ���
    int eyeLength = 300;
    //�۾���ֵ
    int amplitude = 50;
    
    public SharinganJPanel() {
        
        startRun();
    }
    
    @Override
    public void paint(Graphics graphics) {
        System.out.println();
    	super.paint(graphics);
        this.setBackground(Color.black);
        //����һ���۾���Ϊ����
        graphics.setColor(Color.red);
        for (int i = 0; i < eyeLength; i++) {
            graphics.drawLine(    centerX-eyeLength/2+i,
                                centerY-(int)(Math.sin(Math.PI*i/eyeLength)*amplitude),
                                centerX-eyeLength/2+i,
                                centerY+(int)(Math.sin(Math.PI*i/eyeLength)*amplitude));
        }
        //���۾������Ļ���һ����ɫ��Բ
        graphics.setColor(Color.black);
        graphics.fillOval(    centerX-90/2,
                            centerY-90/2,
                            90,
                            90);
        //���۾������Ļ���һ����ɫ��Բ
        graphics.setColor(Color.white);
        graphics.fillOval(    centerX-60/2/2/2,
                            centerY-60/2/2/2,
                            60/2/2,
                            60/2/2);
        //���ڻ���3����ͬ�Ƕȡ��ڲ��ϱ仯����ͬλ�õ�̫��ͼ��ͼ����˳���
        for (int i = 0; i < 3; i++) {
            //����д������(sharinganSemi)
            graphics.setColor(Color.red);
            graphics.fillArc(   (int)(centerX-sharinganSemi+Math.cos(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini/2+angleOfwhiteShar+angleErr*2/3)*distansOfSharSemi),
                                (int)(centerY-sharinganSemi-Math.sin(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini/2+angleOfwhiteShar+angleErr*2/3)*distansOfSharSemi),
                                (int)(sharinganSemi*2),
                                (int)(sharinganSemi*2),angleCircle+i*120,180);
            //����д���۹̶�Բ(whiteOfSharingan)
            graphics.setColor(Color.red);
            graphics.fillOval(  (int)(centerX-whiteOfSharingan+Math.cos(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(centerY-whiteOfSharingan-Math.sin(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(whiteOfSharingan*2),
                                (int)(whiteOfSharingan*2));
            //����д���ۺ���(miniCircleSemi)
            graphics.setColor(Color.black);
            graphics.fillOval(  (int)(centerX-miniCircleSemi+Math.cos(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini)*extendCircleSemi),
                                (int)(centerY-miniCircleSemi-Math.sin(Math.PI*(angleCircle+90+(i*120))/180-angleOfWhiteMini)*extendCircleSemi),
                                (int)(miniCircleSemi*2),
                                (int)(miniCircleSemi*2));
            //����д���۹̶����ں���(blackOfWhiteSemi)
            graphics.setColor(Color.black);
            graphics.fillOval(  (int)(centerX-(blackOfWhiteSemi+0.0)/120*whiteOfSharingan/2+Math.cos(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)(centerY-(blackOfWhiteSemi+0.0)/120*whiteOfSharingan/2-Math.sin(Math.PI*(angleCircle+90+(i*120))/180)*extendCircleSemi),
                                (int)((blackOfWhiteSemi+0.0)/120*whiteOfSharingan),
                                (int)((blackOfWhiteSemi+0.0)/120*whiteOfSharingan));
        }
        //������Բ
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
                        //��������еĺ���뾶
                        blackOfWhiteSemi = angleCircle;
                        //System.out.println(angleCircle);
                        //����Ŀǰд���۹�����ת���Ƕ���ȷ��miniС��Ŀǰ�Ķ�Ӧ�İ뾶
                        miniCircleSemi = (angleCircle+0.0)/120*whiteOfSharingan;
                        //System.out.println(miniCircleSemi);
                        //������뾶
                        sharinganSemi = miniCircleSemi+whiteOfSharingan;
                        //System.out.println(sharinganSemi);
                        //���ڰ����miniС��������Բ�ϣ�����ͨ���Ҷ�Ӧ�ĽǶ������СԲ����ڰ���ĽǶ�
                        angleOfWhiteMini = Math.asin(sharinganSemi/2/extendCircleSemi)*2;
                        //System.out.println(angleOfWhiteMini);
                        //�������ĵ��������ľ���
                        distansOfWhiteShar = (whiteOfSharingan-miniCircleSemi)/2;
                        //�������ĵ����ĵ�ľ���
                        distansOfSharSemi = Math.sqrt(  extendCircleSemi*extendCircleSemi
                                                        -((whiteOfSharingan+miniCircleSemi)/2)*((whiteOfSharingan+miniCircleSemi)/2)
                                                        +((whiteOfSharingan-miniCircleSemi)/2)*((whiteOfSharingan-miniCircleSemi)/2));
                        //�������ĺͰ���������Բ�����ɵĽǶ�
                        //ͨ��������Կ���������ڼ������������ļ������Ϊ��0.02500260489936114��
                    //System.out.println(distansOfSharSemi);
                        angleOfwhiteShar = Math.asin(distansOfWhiteShar/2/distansOfSharSemi);
                        //System.out.println(angleOfwhiteShar);
                        if (angleCircle == 120) {
                            flag = false;
                        }
                    }else {
                        angleCircle += 2;
                        //��������еĺ���뾶
                        blackOfWhiteSemi = 240-angleCircle;
                        //����Ŀǰд���۹�����ת���Ƕ���ȷ��miniС��Ŀǰ�Ķ�Ӧ�İ뾶
                        miniCircleSemi = (240.0-angleCircle)/120*whiteOfSharingan;
                        //miniCircleSemi = (angleCircle+0.0)/120*whiteOfSharingan;
                        //������뾶
                        sharinganSemi = miniCircleSemi+whiteOfSharingan;
                        //���ڰ����miniС��������Բ�ϣ�����ͨ���Ҷ�Ӧ�ĽǶ������СԲ����ڰ���ĽǶ�
                        angleOfWhiteMini = Math.asin(sharinganSemi/2/extendCircleSemi)*2;
                        //�������ĵ��������ľ���
                        distansOfWhiteShar = (whiteOfSharingan-miniCircleSemi)/2;
                        //�������ĵ����ĵ�ľ���
                        distansOfSharSemi = Math.sqrt(  extendCircleSemi*extendCircleSemi
                                                        -((whiteOfSharingan+miniCircleSemi)/2)*((whiteOfSharingan+miniCircleSemi)/2)
                                                        +((whiteOfSharingan-miniCircleSemi)/2)*((whiteOfSharingan-miniCircleSemi)/2));
                        //�������ĺͰ���������Բ�����ɵĽǶ�
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



