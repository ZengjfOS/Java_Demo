package demo;

import java.util.Arrays;
import java.util.Scanner;
/**
*
*
* <p>
* <h4>һ�����������</h4><br><ol>
*         <li>�������Ϊ�˽��ͬѧ�����ϲ����ڵ�һ�׶�ѧϰ�ڼ�֪ʶ���������������СDemo��ͬʱ��л�����ѱ����ʱ�����Ķ����ĵ���<br>
*         <li>���������ģ�����СDemo����ô���Ķ������������ע�ͣ�<br>
*         <li>�����ֻ��·������ô������Ҳ����èһ�� ^_^����������������������ʧ���ģ�<br>
*         <li>������ѱ�������ΪEmpManage_V1�棬����������Ľṹ�洢���ݣ���һ�汾EmpManage_V2�棬�����˼��ϴ洢����  ^_^��<br>
*         <li>��СDemo��������Ϊ��EmpManage_V1��ģ���������ĵĹ���ϵͳ�����������һЩ�����뷨��ϣ�����ܸ�������Щ��Ȥ<br>
* </ol></p>
*
* @author ������<br>
* @date 2014-1-16<br>
*/
public class EmpManage_V1 {
	static String[] names = {"zeng","jian","feng"};
	static int[] scores = {50,60,70};
	static int index;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������������������������������������������������������������������������������������������������\n" +
				"\t\tԱ������ϵͳ����˵����\n" +
				"\t\t\t���룺1��\t���Ա����Ϣ��\n" +
				"\t\t\t���룺2��\t�޸�Ա����Ϣ��\n" +
				"\t\t\t���룺3��\t��ѯԱ����Ϣ��\n" +
				"\t\t\t���룺4��\tɾ��Ա����Ϣ��\n" +
				"\t\t\t���룺5��\t��ѯ����Ա����Ϣ��\n" +
				"\t\t\t���룺6��\tɾ������Ա����Ϣ��\n" +
				"\t\t\t���룺7��\t\t�˳�Ա������ƽ̨��\n" +
				"������������������������������������������������������������������������������������������������");
		while (true) {
			System.out.print("��ѡ���������>");
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
				System.out.println("������������������������������������������������������������������������������������������������");
				System.out.println("\t\t\t\t����ʾ������������ȷ������");
				System.out.println("������������������������������������������������������������������������������������������������");
			}
			
		}
		
	}
	private static boolean quit(Scanner scanner) {
		String input;
		System.out.print("ȷ���˳�(Y)����>");
		input = scanner.next().trim();
		if (input.toLowerCase().equals("y")) {
			System.out.println("\t\t\t\t����ʾ�����������˳�");
			System.out.println("������������������������������������������������������������������������������������������������");
			return true;
		}else {
			System.out.println("\t\t\t\t����ʾ������ӭ����ʹ��");
			System.out.println("������������������������������������������������������������������������������������������������");
			return false;
		}
		
	}
	private static void deleteall(Scanner scanner) {
		String input;
		System.out.print("ȷ��ɾ��ȫ��(Y)����>");
		input = scanner.next().trim();
		if (input.toLowerCase().equals("y")) {
			names = new String[0];
			scores = new int[0];
			System.out.println("\t\t\t\t����ʾ����������ȫ�����");
		}
		System.out.println("������������������������������������������������������������������������������������������������");
	}
	private static void delete(Scanner scanner) {
		String input;
		System.out.print("��������������>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t����ʾ������������С��10");
			System.out.print("��������������>");
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
			System.out.println("\t\t\t\t����ʾ��������������");
		}
		System.out.println("������������������������������������������������������������������������������������������������");
	}
	
	private static void query(Scanner scanner) {
		String input;
		System.out.print("��������������>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t����ʾ������������С��10");
			System.out.print("��������������>");
		}
		index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(input)) {
				index = i;
				System.out.println("\t\t\t��ѯ�����"+names[i]+"--->"+scores[i]);
			}
		}
		if (index == -1){
			System.out.println("\t\t\t\t����ʾ��������������");
		}
		System.out.println("������������������������������������������������������������������������������������������������");
	}
	

	private static void queryall() {
		System.out.println("\t\t\t������������������ѯ�б�����------");
		System.out.println("\t\t\t|  id\t|  name\t|  score  |");
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			int score = scores[i];
			System.out.println("\t\t\t������������������������������------");
			String number = new Integer(i+1).toString();
			if (number.length()<2) {
				number = "0"+number;
			}
			System.out.println("\t\t\t|  "+number+"\t|  "+name+"\t|  " +score+"\t  |");
		}
		System.out.println("\t\t\t������������������������������------");
		System.out.println("������������������������������������������������������������������������������������������������");
	}
	
	private static void update(Scanner scanner) {
		String input;
		int score = 0;
		System.out.print("��������������>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t����ʾ������������С��10");
			System.out.print("��������������>");
		}
		index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(input)) {
				index = i;
				System.out.print("�������������>");
				while ((score = scanner.nextInt()) > 100 && score < 0) {
					System.out.println("\t\t\t\t����ʾ����100����");
					System.out.print("�������������>");
				}
				scores[i] = score; 
			}
		}
		if (index == -1) {
			System.out.println("\t\t\t\t����ʾ��������������");
		}
		System.out.println("������������������������������������������������������������������������������������������������");
	}
	private static void insert(Scanner scanner) {
		String input;
		int score = 0;
		System.out.print("��������������>");
		while ((input = scanner.next()).trim().length() > 10) {
			System.out.println("\t\t\t\t����ʾ������������С��10");
			System.out.print("��������������>");
		}
		names = Arrays.copyOf(names, names.length+1);
		names[names.length-1] = input;
		System.out.print("�������������>");
		while ((score = scanner.nextInt()) > 100 && score < 0) {
			System.out.println("\t\t\t\t����ʾ����100����");
			System.out.print("�������������>");
		}
		scores = Arrays.copyOf(scores, scores.length+1);
		scores[scores.length-1] = score;
		System.out.println("\t\t\t\t����ʾ������Ϣ¼�����");
		System.out.println("������������������������������������������������������������������������������������������������");
	}
}
