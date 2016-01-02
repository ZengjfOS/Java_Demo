package matchstickMan;

import java.awt.Graphics2D;

public class Matchstick { 
	/** 用于标识所创建的火柴是那个类型 */
	public final int typeId;
	/** 左脚,主要用于查表 */
	public static final int LEFT_FOOT = 0;
	/** 左小腿,主要用于查表 */
	public static final int LEFT_CRUS = 1;
	/** 左大腿,主要用于查表 */
	public static final int LEFT_THIGH = 2;
	/** 躯干,主要用于查表 */
	public static final int TRUNK = 3;
	/** 右大腿,主要用于查表 */
	public static final int RIGHT_THIGH = 4;
	/** 右小腿,主要用于查表 */
	public static final int RIGHT_CRUS = 5;
	/** 右脚,主要用于查表 */
	public static final int RIGHT_FOOT = 6;
	/** 左胳膊,主要用于查表 */
	public static final int LEFT_ARM = 7;
	/** 左前臂,主要用于查表 */
	public static final int LEFT_FOREARM = 8;
	/** 右胳膊,主要用于查表 */
	public static final int RIGHT_ARM = 9;
	/** 右前臂,主要用于查表 */
	public static final int RIGHT_FOREARM = 10;
	/** 头 */
	public static final int HEAD = 11;
	
	/** 用于表示设置第一个点 */
	public static final int FOOT_HEAD = 0;
	/** 用于表示设置第二个点 */
	public static final int HEAD_FOOT = 1;
	
	/** 第一个点X轴坐标 */
	public int firstPositionX;
	/** 第一个点Y轴坐标 */
	public int firstPositionY;
	/** 第二个点X轴坐标 */
	public int secondPositionX;
	/** 第二个点Y轴坐标 */
	public int secondPositionY;
	/** 长度 */
	public int length;
	/** 在屏幕坐标系中的角度 */
	public double angle;
	/**
	 * 第一个点的(x, y),直线的长度,直线与x轴所成的角度,该对象属于那种类型
	 * @param firstPositionX
	 * @param firstPositionY
	 * @param length
	 * @param angle
	 * @param typeId
	 */
	public Matchstick(	int firstPositionX, int firstPositionY,
						int length, double angle, int typeId) {
		this.firstPositionX = firstPositionX;
		this.firstPositionY = firstPositionY;
		this.length = length;
		this.angle = -angle;
		this.typeId = typeId;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	/**
	 * 这个Matchstick从传入的Matchstick的第而个点开始,长度从LengthOfMan提取,
	 * 角度从AngleOfMan提取,设置新创建的对象的typeId类型
	 * @param matchstick
	 * @param lengthOfMan
	 * @param angleOfMan
	 * @param typeId
	 */
	public Matchstick(	Matchstick matchstick,
			int[] lengthOfMan, double[] angleOfMan, int typeId) {
		if (typeId == Matchstick.RIGHT_THIGH) {
			this.firstPositionX = matchstick.firstPositionX;
			this.firstPositionY = matchstick.firstPositionY;
		}else if (typeId == Matchstick.LEFT_ARM || typeId == Matchstick.RIGHT_ARM) {
			this.firstPositionX = matchstick.secondPositionX;
			this.firstPositionY = matchstick.secondPositionY;
		}else {
			this.firstPositionX = matchstick.secondPositionX;
			this.firstPositionY = matchstick.secondPositionY;
		}
		this.length = lengthOfMan[typeId];
		this.angle = -angleOfMan[typeId];
		this.typeId = typeId;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	public Matchstick(	Matchstick matchstick,
			int[] lengthOfMan, int[] angleOfMan, int typeId) {
		if (typeId == Matchstick.RIGHT_THIGH) {
			this.firstPositionX = matchstick.firstPositionX;
			this.firstPositionY = matchstick.firstPositionY;
		}else if (typeId == Matchstick.LEFT_ARM || typeId == Matchstick.RIGHT_ARM) {
			this.firstPositionX = matchstick.secondPositionX;
			this.firstPositionY = matchstick.secondPositionY;
		}else {
			this.firstPositionX = matchstick.secondPositionX;
			this.firstPositionY = matchstick.secondPositionY;
		}
		this.length = lengthOfMan[typeId];
		this.angle = -angleOfMan[typeId];
		this.typeId = typeId;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	public Matchstick(	int firstPositionX, int firstPositionY,
			int[] lengthOfMan, double[] angleOfMan, int typeId) {
		this.firstPositionX = firstPositionX;
		this.firstPositionY = firstPositionY;
		this.length = lengthOfMan[typeId];
		this.angle = -angleOfMan[typeId];
		this.typeId = typeId;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	public Matchstick(	int  firstPositionX, int firstPositionY,
			int[] lengthOfMan, int[] angleOfMan, int typeId) {
		this.firstPositionX = firstPositionX;
		this.firstPositionY = firstPositionY;
		this.length = lengthOfMan[typeId];
		this.angle = -angleOfMan[typeId];
		this.typeId = typeId;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	public Matchstick(	int firstPositionX, int firstPositionY,
						int secondPositionX, int secondPositionY, int typeId) {
		this.firstPositionX = firstPositionX;
		this.firstPositionY = firstPositionY;
		this.secondPositionX = secondPositionX;
		this.secondPositionY = secondPositionY;
		this.length = MatchstickLength(firstPositionX, firstPositionY, 
			secondPositionX, secondPositionY);
		this.angle = MatchstickAngleOfDecare(firstPositionX, firstPositionY, 
			secondPositionX, secondPositionY);
		this.typeId = typeId;
	}	
	/** 求取length在X轴上的映射长度 */
	public int MatchstickCosX(int length, double angle) {
		return (int)(length*Math.cos(Math.PI*angle/180));
	}
	/** 求取length在Y轴上的映射长度 */
	public int MatchstickSinX(int length, double angle) {
		return (int)(length*Math.sin(Math.PI*angle/180));
	}
	/** 求取两个点之间的长度 */
	public int MatchstickLength(int firstPositionX, int firstPositionY,
								int secondPositionX, int secondPositionY) {
		return (int)Math.sqrt(
				(secondPositionX-firstPositionX)*
				(secondPositionX-firstPositionX)+
				(secondPositionY-firstPositionY)*
				(secondPositionY-firstPositionY));
	}
	/** 求取两个点在屏幕坐标系上的角度 */
	public static double MatchstickAngle(	int firstPositionX, int firstPositionY,
									int secondPositionX, int secondPositionY) {
		return Math.atan((secondPositionY-firstPositionY)/
				(secondPositionX-firstPositionX))*180/Math.PI;
	}
	/** 求取两个点在屏幕坐标系上的角度 */
	public static double MatchstickAngleOfDecare(	int firstPositionX, int firstPositionY,
											int secondPositionX, int secondPositionY) {
		return -Math.atan((secondPositionY-firstPositionY)/
				(secondPositionX-firstPositionX))*180/Math.PI;
	}
	/**
	 * 改变角度
	 */
	public void changedAngle(int direction, double angle){
		this.angle = -angle;
		if (direction == FOOT_HEAD) {
			this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
			this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
		}else if (direction == HEAD_FOOT) {
			this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
			this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
		} 
	}
	public void changedAngle(int direction){
		if (direction == FOOT_HEAD) {
			this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
			this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
		}else if (direction == HEAD_FOOT) {
			this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
			this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
		} 
	}
	public void changedAngle(int direction, Object matchstick){
		if (direction == FOOT_HEAD) {
//			if (matchstick instanceof Matchstick) {
//				this.firstPositionX = ((Matchstick)matchstick).secondPositionX;
//				this.firstPositionY = ((Matchstick)matchstick).secondPositionY;
//				this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
//				this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
//			}else if (matchstick instanceof double[] || matchstick instanceof int[]) {
//				if (matchstick instanceof double[]) {
//					this.angle = -((double[])matchstick)[this.typeId];
//				}else if (matchstick instanceof int[]){
//					this.angle = -(double)((int[])matchstick)[this.typeId];
//				}
//				this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
//				this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);	
//			}else if (matchstick == null) {
//				this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
//				this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
//			}
			if (matchstick instanceof Matchstick) {
				if (this.typeId == Matchstick.RIGHT_THIGH) {
					this.firstPositionX = ((Matchstick)matchstick).firstPositionX;
					this.firstPositionY = ((Matchstick)matchstick).firstPositionY;
				}else {
					this.firstPositionX = ((Matchstick)matchstick).secondPositionX;
					this.firstPositionY = ((Matchstick)matchstick).secondPositionY;
				}	
			}else if (matchstick instanceof double[]) {
				this.angle = -((double[])matchstick)[this.typeId];	
			}else if (matchstick instanceof int[]){
				this.angle = -(double)((int[])matchstick)[this.typeId];	
			}
			this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
			this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);		
		}else if (direction == HEAD_FOOT) {
			if (matchstick instanceof Matchstick) {
				this.secondPositionX = ((Matchstick)matchstick).secondPositionX;
				this.secondPositionY = ((Matchstick)matchstick).secondPositionY;
//				this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
//				this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
			}else if (matchstick instanceof Head) {
				this.secondPositionX = ((Head)matchstick).touchPositionX;
				this.secondPositionY = ((Head)matchstick).touchPositionY;
//				this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
//				this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
			}else if (matchstick instanceof double[] || matchstick instanceof int[]) {
				if (matchstick instanceof double[]) {
					this.angle = -((double[])matchstick)[this.typeId];
				}else if (matchstick instanceof int[]) {
					this.angle = -(double)((int[])matchstick)[this.typeId];
				}
			}
			this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
			this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
		} 
	}
	public void changedAngle(int direction, Object angleOfMan, Object matchstick){
		if (angleOfMan instanceof double[]) {
			this.angle = -((double[])angleOfMan)[this.typeId];
		}else if (angleOfMan instanceof int[]) {
			this.angle = -((int[])angleOfMan)[this.typeId];
		}
		if (direction == FOOT_HEAD) {
			if (matchstick instanceof Matchstick) {
				Matchstick matchstick2 = ((Matchstick)matchstick);
				if (typeId == Matchstick.RIGHT_THIGH && matchstick2.typeId == Matchstick.TRUNK) {
					this.firstPositionX = matchstick2.firstPositionX;
					this.firstPositionY = matchstick2.firstPositionY;
				}else {
					this.firstPositionX = matchstick2.secondPositionX;
					this.firstPositionY = matchstick2.secondPositionY;
				}
				this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
				this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
			}
			
		}else if (direction == HEAD_FOOT) {
			if (matchstick instanceof Matchstick) {
				Matchstick matchstick2 = ((Matchstick)matchstick);
				if (typeId == Matchstick.RIGHT_THIGH && matchstick2.typeId == Matchstick.TRUNK) {
					this.firstPositionX = matchstick2.firstPositionX;
					this.firstPositionY = matchstick2.firstPositionY;
				}else {
					this.secondPositionX = matchstick2.firstPositionX;
					this.secondPositionY = matchstick2.firstPositionY;
				}
//				this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
//				this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
			} else if (matchstick instanceof Head) {
				this.secondPositionX = ((Head)matchstick).touchPositionX;
				this.secondPositionY = ((Head)matchstick).touchPositionY;
	
			}
			this.firstPositionX = secondPositionX+MatchstickCosX(length, this.angle+180);
			this.firstPositionY = secondPositionY+MatchstickSinX(length, this.angle+180);
		} 	
	}
	/**
	 * 改变位置
	 */
	public void moveRelative(int positonX, int positionY){
		this.firstPositionX += positonX;
		this.firstPositionY += positionY;
		this.secondPositionX += positonX;
		this.secondPositionY += positionY;
	}
	public void moveTo(int positonX, int positionY){
		this.firstPositionX = positonX;
		this.firstPositionY = positionY;
		this.secondPositionX = firstPositionX+MatchstickCosX(length, this.angle);
		this.secondPositionY = firstPositionY+MatchstickSinX(length, this.angle);
	}
	/**
	 * 显示Matchstic
	 * @param graphics2d
	 */
	public void showMatchstick(Graphics2D graphics2d) {
		graphics2d.drawLine(firstPositionX, firstPositionY, secondPositionX, secondPositionY);
	}
	@Override
	public String toString() {
		return 	"\n\n\tfirstPositionX:"+firstPositionX+
				"\n\tfirstPositionY:"+firstPositionY+
				"\n\tsecondPositionX:"+secondPositionX+
				"\n\tsecondPositionY:"+secondPositionY+
				"\n\tLength:"+length+
				"\n\tAngle:"+angle+
				"\n\ttypeId:"+typeId;
	}
}
