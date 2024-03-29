package matchstickMan;

import java.awt.Graphics2D;

public class Man {
	public Head head;
	/** 左脚 */
	private Matchstick leftFoot;
	/** 左小腿 */
	private Matchstick leftCrus;
	/** 左大腿 */
	private Matchstick leftThigh;
	/** 躯干 */
	private Matchstick trunk;
	/** 右大腿 */
	private Matchstick rightThigh;
	/** 右小腿 */
	private Matchstick rightCrus;
	/** 右脚 */
	private Matchstick rightFoot;
	/** 左胳膊 */
	private Matchstick leftArm;
	/** 左前臂 */
	private Matchstick leftForearm;
	/** 右胳膊 */
	private Matchstick rightArm;
	/** 右前臂 */
	private Matchstick rightForearm;
	/** 用于方便对Matchstick赋值 */
	public Matchstick[] matchsticks;
	/** 设置默认角度 */
	private int[] angleOfManDefault = { 0, 90, 45, 90 + 18, -45, -90, 0, -135,
			-180, -45, 0 };
	/** 设置默认身体各个部位的长度 */
	private int[] lengthsOfDefault = { 20, 50, 60, 80, 60, 50, 20, 50, 50, 50, 50 };
	/**
	 * 动作改变的走向,向头的方向
	 */
	public static final int FOOT_HEAD = 0;
	/**
	 * 动作改变的走向,向脚的方向
	 */
	public static final int HEAD_FOOT = 1;

	/**
	 * 创建人的各个补位相关的对象
	 */
	public Man(int leftFootX, int leftFootY) {
		leftFoot = new Matchstick(leftFootX, leftFootY, lengthsOfDefault, angleOfManDefault, Matchstick.LEFT_FOOT);
		leftCrus = new Matchstick(leftFoot, lengthsOfDefault, angleOfManDefault, Matchstick.LEFT_CRUS);
		leftThigh = new Matchstick(leftCrus, lengthsOfDefault, angleOfManDefault, Matchstick.LEFT_THIGH);
		trunk = new Matchstick(leftThigh, lengthsOfDefault, angleOfManDefault, Matchstick.TRUNK);
		rightThigh = new Matchstick(trunk,lengthsOfDefault, angleOfManDefault, Matchstick.RIGHT_THIGH);
		rightCrus = new Matchstick(rightThigh, lengthsOfDefault, angleOfManDefault, Matchstick.RIGHT_CRUS);
		rightFoot = new Matchstick(rightCrus, lengthsOfDefault, angleOfManDefault, Matchstick.RIGHT_FOOT);
		leftArm = new Matchstick(trunk, lengthsOfDefault, angleOfManDefault, Matchstick.LEFT_ARM);
		leftForearm = new Matchstick(leftArm, lengthsOfDefault, angleOfManDefault, Matchstick.LEFT_FOREARM);
		rightArm = new Matchstick(trunk, lengthsOfDefault, angleOfManDefault, Matchstick.RIGHT_ARM);
		rightForearm = new Matchstick(rightArm, lengthsOfDefault, angleOfManDefault, Matchstick.RIGHT_FOREARM);
		matchsticks = new Matchstick[] { leftFoot, leftCrus, leftThigh, trunk,
				rightThigh, rightCrus, rightFoot, leftArm, leftForearm,
				rightArm, rightForearm };
		head = new Head(trunk, 30);
	}

	/**
	 * 改变matchsticks中的所有的Mastick角度
	 * 
	 * @param angleOfMan
	 */
	public void changedAngle(int direction, int firstChanged, Object angleOfMan) {
		if (firstChanged > 11 && firstChanged < 0) {
			firstChanged = 11;
		}
		if (direction == FOOT_HEAD) {
			for (int i = firstChanged; i < matchsticks.length + 1; i++) {
				if (i == Matchstick.LEFT_FOOT) {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD, angleOfMan);
				} else if (i == Matchstick.RIGHT_THIGH || i == Matchstick.LEFT_ARM || i == Matchstick.RIGHT_ARM) {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
				} else if (i == Matchstick.HEAD) {
					head.changedHead(Head.FOOT_HEAD,matchsticks[Matchstick.TRUNK]);
				} else {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD, angleOfMan, matchsticks[i - 1]);
				}
			}
		}else if (direction == HEAD_FOOT) {
			headToFootChangedAngle(firstChanged, angleOfMan);	
		}
	}	

	/**
	 * 修改了Matchsticks中某个Matchstick的角度
	 * 
	 * @param Changed
	 * @param angle
	 */
	public void changedAngle(int direction, int changed, double angle) {
		if (changed > 11 && changed < 0) {
			changed = 11;
		}else if (changed >= 0 && changed <11) {
			matchsticks[changed].angle = -angle;
		}
		if (direction == FOOT_HEAD) {
			for (int i = changed; i < matchsticks.length + 1; i++) {
				if (i == Matchstick.LEFT_FOOT) {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD);
//				} else if (i == Matchstick.RIGHT_THIGH) {
//					matchsticks[i].firstPositionX = matchsticks[Matchstick.TRUNK].firstPositionX;
//					matchsticks[i].firstPositionY = matchsticks[Matchstick.TRUNK].firstPositionY;
//					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD);
//				} else if (i == Matchstick.LEFT_ARM || i == Matchstick.RIGHT_ARM) {
//					matchsticks[i].firstPositionX = matchsticks[Matchstick.TRUNK].secondPositionX;
//					matchsticks[i].firstPositionY = matchsticks[Matchstick.TRUNK].secondPositionY;
//					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD);
//				} else if (i == matchsticks.length) {
//					head.changedHead(0,matchsticks[Matchstick.TRUNK]);
//				} else {
//					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD,
//							matchsticks[i - 1]);
//				}
				} else if (i == Matchstick.RIGHT_THIGH || i == Matchstick.LEFT_ARM || i == Matchstick.RIGHT_ARM) {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD, matchsticks[Matchstick.TRUNK]);
				} else if (i == Matchstick.HEAD) {
					head.changedHead(Head.FOOT_HEAD, matchsticks[Matchstick.TRUNK]);
				} else {
					matchsticks[i].changedAngle(Matchstick.FOOT_HEAD, matchsticks[i - 1]);
				}
			}
		}else if (direction == HEAD_FOOT) {
			headToFootChangedAngle(changed, null);
		}	
	}
	private void headToFootChangedAngle(int firstChanged, Object angleOfMan) {
		switch (firstChanged) {
		case 3:
		case 11:
			head.changedHead(HEAD_FOOT,angleOfMan, matchsticks[Matchstick.TRUNK]);
			matchsticks[Matchstick.TRUNK].changedAngle(HEAD_FOOT, angleOfMan, head);
			matchsticks[Matchstick.RIGHT_ARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
			matchsticks[Matchstick.RIGHT_FOREARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_ARM]);
			matchsticks[Matchstick.LEFT_ARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
			matchsticks[Matchstick.LEFT_FOREARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.LEFT_ARM]);
			matchsticks[Matchstick.RIGHT_THIGH].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
			matchsticks[Matchstick.RIGHT_CRUS].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_THIGH]);
			matchsticks[Matchstick.RIGHT_FOOT].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_CRUS]);
			matchsticks[Matchstick.LEFT_THIGH].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.TRUNK]);
			matchsticks[Matchstick.LEFT_CRUS].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.LEFT_THIGH]);
			matchsticks[Matchstick.LEFT_FOOT].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.LEFT_CRUS]);
			break;
		case 9:
			matchsticks[Matchstick.RIGHT_ARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
		case 10:
			matchsticks[Matchstick.RIGHT_FOREARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_ARM]);
			break;
		case 7:
			matchsticks[Matchstick.LEFT_ARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
		case 8:
			matchsticks[Matchstick.LEFT_FOREARM].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.LEFT_ARM]);
			break;
		case 4:
			matchsticks[Matchstick.RIGHT_THIGH].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.TRUNK]);
		case 5:
			matchsticks[Matchstick.RIGHT_CRUS].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_THIGH]);
		case 6:
			matchsticks[Matchstick.RIGHT_FOOT].changedAngle(FOOT_HEAD, angleOfMan, matchsticks[Matchstick.RIGHT_CRUS]);
			break;
		case 2:
			matchsticks[Matchstick.LEFT_THIGH].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.TRUNK]);
		case 1:
			matchsticks[Matchstick.LEFT_CRUS].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.LEFT_THIGH]);
		case 0:
			matchsticks[Matchstick.LEFT_FOOT].changedAngle(HEAD_FOOT, angleOfMan, matchsticks[Matchstick.LEFT_CRUS]);
			break;
		}	
	}

	/**
	 * 显示人物
	 */
	public void showMan(Graphics2D graphics2d) {
		for (int i = 0; i < matchsticks.length; i++) {
			matchsticks[i].showMatchstick(graphics2d);
		}
		head.showHead(graphics2d);
	}

	@Override
	public String toString() {
		return "\nleftFoot:" + leftFoot + "\nleftCrus:" + leftCrus +"\nleftThigh:" + leftThigh +
				"\ntrunk:" + trunk +
				"\nrightThigh:" + rightThigh+"\nrightCrus:" + rightCrus+"\nrightFoot:" + rightFoot+
				"\nleftArm:" + leftArm + "\nleftForearm:" + leftForearm +
				"\nrightArm:"+ rightArm +  "\nrightForearm:"+ rightForearm + 
				"\nhead:" + head;
	}
}
