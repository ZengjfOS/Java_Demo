package matchstickMan;
/**
 * <h4>AngleOfMan类说明:</h4><br><ol>
 * 		<li>以下所有的角度都是在笛卡尔坐标系下的坐标;
 * 		<li>屏幕坐标系和笛卡尔坐标系是关于X轴对称的;
 *</ol>
 */
public class AngleOfMan {
	
	/** 左脚与X正半轴所成的角度 */
	private double leftFootAngle;
	/** 左小腿与X正半轴所成的角度 */
	private double leftCrusAngle;
	/** 左大腿与X正半轴所成的角度 */
	private double leftThighAngle;
	/** 躯干与X正半轴所成的角度 */
	private double trunkAngle;
	/** 右大腿与X正半轴所成的角度 */
	private double rightThighAngle;
	/** 右小腿与X正半轴所成的角度 */
	private double rightCrusAngle;
	/** 右脚与X正半轴所成的角度 */
	private double rightFootAngle;
	/** 左胳膊与X正半轴所成的角度 */
	private double leftArmAngle;
	/** 左前臂与X正半轴所成的角度 */
	private double leftForearmAngle;
	/** 右胳膊与X正半轴所成的角度 */
	private double rightArmAngle;
	/** 右前臂与X正半轴所成的角度 */
	private double rightForearmAngle;
	public double[] angles;
	public AngleOfMan() {
	}
	/**
	 * 构造函数接受一个double[]数组对内部属性进行初始化,同时将其报存在angles中
	 * @param angles
	 */
	public AngleOfMan(double[] angles) {
		this.leftFootAngle = angles[0];
		this.leftCrusAngle = angles[1];
		this.leftThighAngle = angles[2];
		this.trunkAngle = angles[3];
		this.rightThighAngle = angles[4];
		this.rightCrusAngle = angles[5];
		this.rightFootAngle = angles[6];
		this.leftArmAngle = angles[7];
		this.leftForearmAngle = angles[8];
		this.rightArmAngle = angles[9];
		this.rightForearmAngle = angles[10];
		this.angles = new double[]{leftFootAngle,leftCrusAngle,leftThighAngle,
				trunkAngle,rightThighAngle,rightCrusAngle,rightFootAngle,
				leftArmAngle,leftForearmAngle,rightArmAngle,rightForearmAngle};
	}
	/**
	 * 构造函数接受一个int[]数组对内部属性进行初始化,同时将其报存在angles中
	 * @param angles
	 */
	public AngleOfMan(int[] angles) {
		this.leftFootAngle = (double)angles[0];
		this.leftCrusAngle = (double)angles[1];
		this.leftThighAngle = (double)angles[2];
		this.trunkAngle = (double)angles[3];
		this.rightThighAngle = (double)angles[4];
		this.rightCrusAngle = (double)angles[5];
		this.rightFootAngle = (double)angles[6];
		this.leftArmAngle = (double)angles[7];
		this.leftForearmAngle = (double)angles[8];
		this.rightArmAngle = (double)angles[9];
		this.rightForearmAngle = (double)angles[10];
		this.angles = new double[]{leftFootAngle,leftCrusAngle,leftThighAngle,
				trunkAngle,rightThighAngle,rightCrusAngle,rightFootAngle,
				leftArmAngle,leftForearmAngle,rightArmAngle,rightForearmAngle};
	}
	/**
	 * 构造函数接受一个string对内部属性进行初始化,同时将其报存在angles中
	 * @param angles
	 */
	public AngleOfMan(String string) {
		string.replace("[", "");
		string.replace("]", "");
		String[] strings = string.split(", ");
		this.leftFootAngle = Double.parseDouble(strings[0]);
		this.leftCrusAngle = Double.parseDouble(strings[1]);
		this.leftThighAngle = Double.parseDouble(strings[2]);
		this.trunkAngle = Double.parseDouble(strings[3]);
		this.rightThighAngle = Double.parseDouble(strings[4]);
		this.rightCrusAngle = Double.parseDouble(strings[5]);
		this.rightFootAngle = Double.parseDouble(strings[6]);
		this.leftArmAngle = Double.parseDouble(strings[7]);
		this.leftForearmAngle = Double.parseDouble(strings[8]);
		this.rightArmAngle = Double.parseDouble(strings[9]);
		this.rightForearmAngle = Double.parseDouble(strings[10]);
		this.angles = new double[]{leftFootAngle,leftCrusAngle,leftThighAngle,
				trunkAngle,rightThighAngle,rightCrusAngle,rightFootAngle,
				leftArmAngle,leftForearmAngle,rightArmAngle,rightForearmAngle};
	}
	@Override
	public String toString() {
		return "["+
					leftFootAngle+", "+leftCrusAngle+", "+leftThighAngle+", "+
					trunkAngle+", "+
					rightThighAngle+", "+rightCrusAngle+","+rightFootAngle+	
					", "+leftArmAngle+", "+leftForearmAngle+", "+
					rightArmAngle+", "+rightForearmAngle+
				"]";
	}
}
