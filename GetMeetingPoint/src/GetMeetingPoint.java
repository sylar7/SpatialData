class GetMeetingPoint{
	public static void main(String args[]){
		
	}
	
	public static void Solve(int x1, int y1, int x2, int y2){
		
	}
	public static float getMin(float d1, float d2){
		return (d1 <= d2) ? d1 : d2;
	}
	
	public static boolean isInMBR(int x, int y, MBR mbr){
		return (x >= mbr.lower_x && x <= mbr.upper_x && y >= mbr.lower_y && y <= mbr.upper_y);
	}
	
	public static class MBR{
	private int id;
	private float lower_x;
	private float lower_y;
	private float upper_x;
	private float upper_y;
	
	public MBR (int id, float lower_x, float lower_y, float upper_x, float upper_y){
		this.id = id;
		this.lower_x = lower_x;
		this.lower_y = lower_y;
		this.upper_x = upper_x;
		this.upper_y = upper_y;
	}
	
	public int getId(){return this.id;}
	public float getLowerX(){return this.lower_x;}
	public float getLowerY(){return this.lower_y;}
	public float getUpperX(){return this.upper_x;}
	public float getUpperY(){return this.upper_y;}
	
	public void showMBR(){
		System.out.print(this.getId()+"\t");
		System.out.print(this.getLowerX()+"\t");
		System.out.print(this.getLowerY()+"\t");
		System.out.print(this.getUpperX()+"\t");
		System.out.print(this.getUpperY()+"\n");
	}
}

}