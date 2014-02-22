import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

class GetMBRs{
	
	private static int GROUP_NUMBER = 9; 
	private static MBR[] MBRs = new MBR[GROUP_NUMBER];

	public static void main(String args[]){
		for (int i = 0; i < GROUP_NUMBER; i++){
			searchMBRs(i);
			MBRs[i].showMBR();
		}
		writeMBRs();
	}
	public static void writeMBRs(){
		String filePath = "src/group_mbrs.txt";
		File f = new File(filePath);
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
			for(int i = 0; i < GROUP_NUMBER; i++){
				bw.write(Integer.toString(MBRs[i].getId())+"\t");
				bw.write(Float.toString(MBRs[i].getLowerX())+"\t");
				bw.write(Float.toString(MBRs[i].getLowerY())+"\t");
				bw.write(Float.toString(MBRs[i].getUpperX())+"\t");
				bw.write(Float.toString(MBRs[i].getUpperY())+"\n");
			}
			bw.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void searchMBRs(int i){
		float minx = 1000;
		float miny = 1000;
		float maxx = 0;
		float maxy = 0;
		try{
			File f = null;
			String filePath = "src/restaurant_data/restaurants_group_";
			f = new File(filePath + String.valueOf(i) + ".txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String temp = null;
			while((temp = br.readLine()) != null){
		        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
		        int n = 0;
		        int id = 0;
				float x = 0;
				float y = 0;
		        while(token.hasMoreElements()){
		        	if(n == 0){
		        		id = Integer.parseInt(token.nextToken());
		        	}else if(n == 1){
		        		x = Float.parseFloat(token.nextToken());
		        	}else if(n == 2){
		        		y = Float.parseFloat(token.nextToken());
		        	}   
		        	n++;
		        }
		        if(x <= minx){minx = x;}
		        if(x >= maxx){maxx = x;}
		        if(y <= miny){miny = y;}
		        if(y >= maxy){maxy = y;}
			}
			br.close();
			MBR mbr = new MBR(i, minx, miny, maxx, maxy);
			MBRs[i] = mbr;
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
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