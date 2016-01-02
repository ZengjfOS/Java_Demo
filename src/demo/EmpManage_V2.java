package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpManage_V2 {
	static ArrayList<String> names = new ArrayList<String>();
	static ArrayList<String> scores = new ArrayList<String>();
	static int index;
	public static void init() {
		names.add("zeng");
		names.add("jian");
		names.add("feng");
		scores.add("10");
		scores.add("20");
		scores.add("30");
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\n" +
				"\t\t员工管理系统操作说明：\n" +
				"\t\t\t1、insert：\t添加员工信息；\n" +
				"\t\t\t2、update：\t修改员工信息；\n" +
				"\t\t\t3、query：\t查询员工信息；\n" +
				"\t\t\t4、delete：\t删除员工信息；\n" +
				"\t\t\t5、queryall：\t查询所有员工信息；\n" +
				"\t\t\t6、deleteall：\t删除所有员工信息；\n" +
				"\t\t\t7、quit：\t\t退出员工管理平台；\n" +
				"－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		init();
		while (true) {
			System.out.print("请选择操作－－>");
			String input = scanner.next().trim();
			if (input.matches("\\w{1,10}")) {
				if ("insert".equals(input)) {
					insert(scanner);
				} else if ("queryall".equals(input)) {
					queryall();
				} else if ("update".equals(input)) {
					update(scanner);
				} else if ("query".equals(input)) {
					query(scanner);
				} else if ("delete".equals(input)) {
					delete(scanner);
				} else if ("deleteall".equals(input)) {
					deleteall(scanner);
				} else if ("quit".equals(input)) {
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
			names.removeAll(names);
			scores.removeAll(scores);
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
		if ((index = names.indexOf(input)) != -1) {
			names.remove(index);
			scores.remove(index);
		}else {
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
		if ((index = names.indexOf(input)) != -1) {
			String score = scores.get(index);
			System.out.println("\t\t\t\t《查询结果》："+input +"－－>"+score);
		}else {
			System.out.println("\t\t\t\t《提示》：该生不存在");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	private static void update(Scanner scanner) {
		String input;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		if ((index = names.indexOf(input)) != -1) {
			System.out.print("请输入分数－－>");
			while (!(input = scanner.next()).trim().matches("(\\d){0,2}")) {
				System.out.println("\t\t\t\t《提示》：100分制");
				System.out.print("请输入分数－－>");
			}
			scores.remove(index);
			scores.add(index,input);
		}else {
			System.out.println("\t\t\t\t《提示》：该生不存在");
		}
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}
	private static void queryall() {
		System.out.println("\t\t\t－－－－－－－－查询列表－－－------");
		System.out.println("\t\t\t|  id\t|  name\t|  score  |");
		for (int i = 0; i < names.size(); i++) {
			String name = names.get(i);
			String score = scores.get(i);
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
	private static void insert(Scanner scanner) {
		String input;
		System.out.print("请输入姓名－－>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t《提示》：姓名长度小于10");
			System.out.print("请输入姓名－－>");
		}
		names.add(input);
		System.out.print("请输入分数－－>");
		while (!(input = scanner.next()).trim().matches("(\\d){0,2}")) {
			System.out.println("\t\t\t\t《提示》：100分制");
			System.out.print("请输入分数－－>");
		}
		scores.add(input);
		System.out.println("\t\t\t\t《提示》：信息录入完毕");
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
	}

}
