package demo;

import java.util.Arrays;
import java.util.Scanner;
/**
*
*
* <p>
* <h4>一、软件声明：</h4><br><ol>
*         <li>该软件是为了解决同学们在南昌达内第一阶段学习期间知识运用问题而开发的小Demo，同时感谢您花费宝贵的时间来阅读本文档；<br>
*         <li>如果您打算模仿这个小Demo，那么请阅读完这个这整段注释；<br>
*         <li>如果您只是路过，那么您可以也可以猫一眼 ^_^，不过我相信她不会让您失望的；<br>
*         <li>该软件已被我命名为EmpManage_V1版，采用了数组的结构存储数据，另一版本EmpManage_V2版，采用了集合存储数据  ^_^；<br>
*         <li>该小Demo被我命名为：EmpManage_V1，模仿了其他的的管理系统，另外加上了一些个人想法，希望她能给您带来些乐趣<br>
* </ol></p>
*
* @author 曾剑锋<br>
* @date 2014-1-16<br>
*/
public class EmpManage_V1 {
	static String[] names = {"zeng","jian","feng"};
	static int[] scores = {50,60,70};
	static int index;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\n" +
				"\t\t员工管理系统操作说明：\n" +
				"\t\t\t键入：1：\t添加员工信息；\n" +
				"\t\t\t键入：2：\t修改员工信息；\n" +
				"\t\t\t键入：3：\t查询员工信息；\n" +
				"\t\t\t键入：4：\t删除员工信息；\n" +
				"\t\t\t键入：5：\t查询所有员工信息；\n" +
				"\t\t\t键入：6：\t删除所有员工信息；\n" +
				"\t\t\t键入：7：\t\t退出员工管理平台；\n" +
				"－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		while (true) {
			System.out.print("请选择操作－－>");
			int input = scanner.nextInt();
			if (input <= 7) {
				if (1 == input) {
					insert(scanner);
				} else if (2 == input) {
					update(scanner);
				} else if (3 == input) {
					query(scanner);
				} else if (4 == input) {
					//delete(scanner);
				} else if (5 == input) {
					queryall();
				} else if (6 == input) {
					deleteall(scanner);
				} else if (7 == input) {
					if (quit(scanner)) break;
				} 
			}else {
				System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
				System.out.println("\t\t\t\t《提示》：请输入正确的命令");
				System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
			}
			
		}
		
	}
	private static boolean quit(Scanner scanner) {
		String input;
		System.out.print("确认退出(Y)－－>");
		input = scanner.next().trim();
		if (input.toLowerCase().equals("y")) {
			System.out.println("\t\t\t\t《提示》：程序已退出");
			System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
			return true;
		}else {
			System.out.println("\t\t\t\t《提示》：欢迎继续使用");
			System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
			return false;
		}
		
	}
	private static void deleteall(Scanner scanner) {
		String input;
		System.out.print("确认删除全部(Y)－－>");
		input = scanner.next().trim();
		if (input.toLowerCase().equals("y")) {
			names = new String[0];
			scores = new int[0];
			System.out.println("\t\t\t\t《提示》：数据已全部清空");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	private static void delete(Scanner scanner) {
		String input;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(input)) {
				index = i;
				for (int j = i; j < names.length-1; j++) {
					names[j] = names[j+1];
					scores[j] = scores[j+1];
				}
				names = Arrays.copyOf(names, names.length-1);
				scores = Arrays.copyOf(scores, scores.length-1);
			}
		}
		if (index == -1) {
			System.out.println("\t\t\t\t《提示》：该生不存在");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	
	private static void query(Scanner scanner) {
		String input;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(input)) {
				index = i;
				System.out.println("\t\t\t查询结果："+names[i]+"--->"+scores[i]);
			}
		}
		if (index == -1){
			System.out.println("\t\t\t\t《提示》：该生不存在");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	

	private static void queryall() {
		System.out.println("\t\t\t－－－－－－－－查询列表－－－------");
		System.out.println("\t\t\t|  id\t|  name\t|  score  |");
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			int score = scores[i];
			System.out.println("\t\t\t－－－－－－－－－－－－－－－------");
			String number = new Integer(i+1).toString();
			if (number.length()<2) {
				number = "0"+number;
			}
			System.out.println("\t\t\t|  "+number+"\t|  "+name+"\t|  " +score+"\t  |");
		}
		System.out.println("\t\t\t－－－－－－－－－－－－－－－------");
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	
	private static void update(Scanner scanner) {
		String input;
		int score = 0;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(input)) {
				index = i;
				System.out.print("请输入分数－－>");
				while ((score = scanner.nextInt()) > 100 && score < 0) {
					System.out.println("\t\t\t\t《提示》：100分制");
					System.out.print("请输入分数－－>");
				}
				scores[i] = score; 
			}
		}
		if (index == -1) {
			System.out.println("\t\t\t\t《提示》：该生不存在");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	private static void insert(Scanner scanner) {
		String input;
		int score = 0;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		names = Arrays.copyOf(names, names.length+1);
		names[names.length-1] = input;
		System.out.print("请输入分数－－>");
		while ((score = scanner.nextInt()) > 100 && score < 0) {
			System.out.println("\t\t\t\t《提示》：100分制");
			System.out.print("请输入分数－－>");
		}
		scores = Arrays.copyOf(scores, scores.length+1);
		scores[scores.length-1] = score;
		System.out.println("\t\t\t\t《提示》：信息录入完毕");
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
}
