
package demo;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RGBOfCharMaps{
    /** 声明一个文件选择器引用 */
    static JFileChooser jFileChooser = null;
    /** 用于保存您选择的单个或者多个文件路径集合， 初始化为null */
    static File filePath = null;
    /** 保存图片的宽、高 */
    static int imageWidth = 0;
    static int imageHeight = 0;
    /** 图像缓冲引用 */
    static BufferedImage bufferedImage = null;
    /**
     * main()函数,完成任务如下：<br><ol>
     *         <li>对文件选择器进行初始化；<br>
     *         <li>保存转换好的文件；<br>
     *         <li>如果出现异常，给出提示信息。<br></ol>
     */
    public static void main(String[] args) {
        try {
            filesSelectInit();
            System.out.println(1);
            if (getImageFile()) {
            	fileSave();  
            	System.out.println(imageHeight);
            	System.out.println(imageWidth);
			};
              
        } catch (Exception e) {
            //System.out.println("请选择后缀为png/PNG/jpeg/jpe/JPEG的文件");
        	System.out.println(e);
        }
    }


    private static boolean getImageFile() throws IOException {
    	if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    		filePath = jFileChooser.getSelectedFile();
    		if (fileSuffixCheck(filePath)) {
    			bufferedImage = ImageIO.read(filePath);
    			imageWidth = bufferedImage.getWidth();
    			imageHeight = bufferedImage.getHeight();
				return true;
			}
		}
    	return false;
	}


	/**
     * 文件后缀检查函数，完成任务如下：<br>
     * &nbsp &nbsp &nbsp &nbsp
     *         采用正则表达式对文件进行匹配。<br>
     */
    private static boolean fileSuffixCheck(File filePath) {
        //使用正则表达式来防止选择目前不支持的文件，目前只支持png/PNG/jpeg/jpe/JPEG格式图片
        Pattern pattern = Pattern.compile(".+[.][pPJj][nNpP][eEgGpP][gG]?");
        Matcher matcher = pattern.matcher(filePath.getName());
        if (matcher.matches() == false) {
            return false;
        }
        return true;
    }
    /**
     * 文件保存函数，完成任务如下：<br><ol>
     *         <li>设置一个文件保存的路径，这个路径可以自己修改；<br>
     *         <li>创建文件路径下的文件缓冲区；<br>
     *         <li>将charMaps中的字符写好文件中，忽略在上、下、左、右边界之外的部分，只将边界内的字符输出；<br>
     *         <li>每写完一行字符，进行换行；<br>
     *         <li>最后关闭文件缓冲区，如果不关闭，文件缓冲区内的字符可能不会写到文件中，请注意;<br>
     *         <li>提示完成以及提示文件路径。<br><ol>
     */
    private static void fileSave() {
        File[] saveFilePath = new File[3];
        saveFilePath[0] = new File("/home/soft1/B.txt");
        saveFilePath[1] = new File("/home/soft1/G.txt");
        saveFilePath[2] = new File("/home/soft1/R.txt");
        try {
        	SaveRGB(saveFilePath);    
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

	private static void SaveRGB(File[] saveFilePath) throws IOException {
		String[] RGB = {"Blue","Green","Red"};
		StringBuilder stringBuilder = new StringBuilder(); 
		for (int i = 0; i < saveFilePath.length; i++) {
    		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(saveFilePath[i]));
    		singleColorSave(stringBuilder,bufferedOutputStream,i);
            System.out.println("CharMaps已完成颜色"+RGB[i]+"工作，请到"+saveFilePath[i].getPath()+"中查看结果 ^_^\n");
		}		
	}
	private static void singleColorSave(StringBuilder stringBuilder,
			BufferedOutputStream bufferedOutputStream, int i) throws IOException {
		stringBuilder.append('{');
		for (int row = 0; row < imageHeight; row++) {
			stringBuilder.append('{');
			for (int col = 0; col < imageWidth; col++) {
				int rgb = bufferedImage.getRGB(col, row);
				int singleColor = ((rgb >> (8*i))&0xff);
				stringBuilder.append(singleColor);
				stringBuilder.append(',');
			}
			stringBuilder.append('}');
			if (row == imageHeight-1) {
				stringBuilder.append('}');
			}else {
    			stringBuilder.append(',');
			}
    		byte[] byteWrite = (byte[])stringBuilder.toString().getBytes();
    		bufferedOutputStream.write(byteWrite, 0, stringBuilder.length());
    		bufferedOutputStream.write('\n');
    		bufferedOutputStream.flush();
    		stringBuilder.delete(0, stringBuilder.length());
		}
		bufferedOutputStream.close();
	}


	/**
     * 文件选择对话框初始化函数，Init是初始化的英文单词缩写，完成任务如下：<br><ol>
     *         <li>提示欢迎使用CharMaps；<br>
     *         <li>创建文件选择对话框；<br>
     *         <li>创建文件选择过滤器；<br>
     *         <li>将文件选择过滤器添加进入文件对话框，还句话说是：使文件选择过滤器有效；<br>
     *         <li>将文件选择对话框设置为可以多选；<br>
     *         <li>提示完成初始化。<br></ol>
     */
    private static void filesSelectInit() {
        System.out.println("\n\t欢迎使用RGBOfCharMaps");
        jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "png","PNG","JPG","jpe","JPE");
        jFileChooser.setFileFilter(filter);
        System.out.println("1、完成文件选择初始化");
    }
}

