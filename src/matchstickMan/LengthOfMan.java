package matchstickMan;
/** 用于保存火柴人身体各个部位的长度 */
public class LengthOfMan {
	
	/** 左脚与X正半轴所成的角度 */
	private int leftFootLength;
	/** 左小腿与X正半轴所成的角度 */
	private int leftCrusLength;
	/** 左大腿与X正半轴所成的角度 */
	private int leftThighLength;
	/** 躯干与X正半轴所成的角度 */
	private int trunkLength;
	/** 右大腿与X正半轴所成的角度 */
	private int rightThighLength;
	/** 右小腿与X正半轴所成的角度 */
	private int rightCrusLength;
	/** 右脚与X正半轴所成的角度 */
	private int rightFootLength;
	/** 左胳膊与X正半轴所成的角度 */
	private int leftArmLength;
	/** 左前臂与X正半轴所成的角度 */
	private int leftForearmLength;
	/** 右胳膊与X正半轴所成的角度 */
	private int rightArmLength;
	/** 右前臂与X正半轴所成的角度 */
	private int rightForearmLength;
	public int[] lengths;
	public LengthOfMan() {
	}
	/**
	 * 构造函数接受一个int[]数组对内部属性进行初始化,同时将其报存在lengths中
	 * @param lengths
	 */
	public LengthOfMan(int[] lengths) {
		this.leftFootLength = lengths[0];
		this.leftCrusLength = lengths[1];
		this.leftThighLength = lengths[2];
		this.trunkLength = lengths[3];
		this.rightThighLength = lengths[4];
		this.rightCrusLength = lengths[5];
		this.rightFootLength = lengths[6];
		this.leftArmLength = lengths[7];
		this.leftForearmLength = lengths[8];
		this.rightArmLength = lengths[9];
		this.rightForearmLength = lengths[10];
		this.lengths = new int[]{leftFootLength,leftCrusLength,leftThighLength,
				trunkLength,rightThighLength,rightCrusLength,rightFootLength,
				leftArmLength,leftForearmLength,rightArmLength,rightForearmLength};
	}
	/**
	 * 构造函数接受一个double[]数组对内部属性进行初始化,同时将其报存在lengths中
	 * @param lengths
	 */
	public LengthOfMan(double[] lengths) {
		this.leftFootLength = (int)lengths[0];
		this.leftCrusLength = (int)lengths[1];
		this.leftThighLength = (int)lengths[2];
		this.trunkLength = (int)lengths[3];
		this.rightThighLength = (int)lengths[4];
		this.rightCrusLength = (int)lengths[5];
		this.rightFootLength = (int)lengths[6];
		this.leftArmLength = (int)lengths[7];
		this.leftForearmLength = (int)lengths[8];
		this.rightArmLength = (int)lengths[9];
		this.rightForearmLength = (int)lengths[10];
		this.lengths = new int[]{leftFootLength,leftCrusLength,leftThighLength,
				trunkLength,rightThighLength,rightCrusLength,rightFootLength,
				leftArmLength,leftForearmLength,rightArmLength,rightForearmLength};
	}
	/**
	 * 构造函数接受一个string对内部属性进行初始化,同时将其报存在lengths中
	 * @param lengths
	 */
	public LengthOfMan(String string) {
		string.replace("[", "");
		string.replace("]", "");
		String[] strings = string.split(", ");
		this.leftFootLength = Integer.parseInt(strings[0]);
		this.leftCrusLength = Integer.parseInt(strings[1]);
		this.leftThighLength = Integer.parseInt(strings[2]);
		this.trunkLength = Integer.parseInt(strings[3]);
		this.rightThighLength = Integer.parseInt(strings[4]);
		this.rightCrusLength = Integer.parseInt(strings[5]);
		this.rightFootLength = Integer.parseInt(strings[6]);
		this.leftArmLength = Integer.parseInt(strings[7]);
		this.leftForearmLength = Integer.parseInt(strings[8]);
		this.rightArmLength = Integer.parseInt(strings[9]);
		this.rightForearmLength = Integer.parseInt(strings[10]);
		this.lengths = new int[]{leftFootLength,leftCrusLength,leftThighLength,
				trunkLength,rightThighLength,rightCrusLength,rightFootLength,
				leftArmLength,leftForearmLength,rightArmLength,rightForearmLength};
	}
	@Override
	public String toString() {
		return "["+
					leftFootLength+", "+leftCrusLength+", "+leftThighLength+", "+
					trunkLength+", "+
					rightThighLength+", "+rightCrusLength+","+rightFootLength+	
					", "+leftArmLength+", "+leftForearmLength+", "+
					rightArmLength+", "+rightForearmLength+
				"]";
	}
}
