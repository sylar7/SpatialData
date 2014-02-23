import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

class GetMeetingPoint{
	int id1;
	int id2;
	static float distance1;
	static float distance2;
	static float mindistance;
	private static int GROUP_NUMBER = 9; 
	private static MBR[] MBRs = new MBR[GROUP_NUMBER];
	
	public static void main(String args[]){
		readMBRs();
		for (int i = 0; i < GROUP_NUMBER; i++){
			MBRs[i].showMBR();
		}
	}
	public static void readMBRs(){
		String filepath = "src/group_mbrs.txt";
		File f = new File(filepath);
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp = null;
			int i = 0;
			while((temp = br.readLine()) != null){
		        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
		        int n = 0;
		        int id = 0;
				float x1 = 0;
				float x2 = 0;
				float y1 = 0; 
				float y2 = 0;
		        while(token.hasMoreElements()){
		        	if(n == 0){
		        		id = Integer.parseInt(token.nextToken());
		        	}else if(n == 1){
		        		x1 = Float.parseFloat(token.nextToken());
		        	}else if(n == 2){
		        		y1 = Float.parseFloat(token.nextToken());
		        	}else if(n == 3){
		        		x2 = Float.parseFloat(token.nextToken());
		        	}else if(n == 4){
		        		y2 = Float.parseFloat(token.nextToken());
		        	}    
		        	n++;
		        }
		        MBRs[i] = new MBR(id, x1, y1, x2, y2);
		        i++;
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void Solve(int x1, int y1, int x2, int y2){
		
	}
//	return the smaller figure
	public static float getMin(float d1, float d2){
		return (d1 <= d2) ? d1 : d2;
	}
	
//	check if a position is inside a MBR
	public static boolean isInMBR(int x, int y, MBR mbr){
		return (x >= mbr.lower_x && x <= mbr.upper_x && y >= mbr.lower_y && y <= mbr.upper_y);
	}
	
	public static void getmin(int x, int y){
		for (int i = 0; i < GROUP_NUMBER; i++){
			
		}
	}
	
//	Define class MBR
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