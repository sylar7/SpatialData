import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class GetMeetingPoint{
	static int id1;
	static int id2;
	static float distance1;
	static float distance2;
	static float mindistancex = 1000;
	static float mindistancey = 1000;
	private static int GROUP_NUMBER = 9; 
	private static MBR[] MBRs = new MBR[GROUP_NUMBER];
	private static Comparator<Restaurantx> compx = new Comparator<Restaurantx>() {
		
		@Override
		public int compare(Restaurantx o1, Restaurantx o2) {
			// TODO Auto-generated method stub
			if (o1.getDistance() - o2.getDistance() <= 0){
				return 1;
			}else {
				return 0;
			}
		}
	}; 
	private static Comparator<Restauranty> compy = new Comparator<Restauranty>() {
		
		@Override
		public int compare(Restauranty o1, Restauranty o2) {
			// TODO Auto-generated method stub
			if (o1.getDistance() - o2.getDistance() <= 0){
				return 1;
			}else {
				return 0;
			}
		}
	}; 
	
	private static Queue<Restaurantx> restaurantxQueue = new PriorityQueue<Restaurantx>(20,compx);
	private static Queue<Restauranty> restaurantyQueue = new PriorityQueue<Restauranty>(20,compy);
	
	public static void main(String args[]){
		readMBRs();
		for (int i = 0; i < GROUP_NUMBER; i++){
			MBRs[i].showMBR();
		}
		findNearestNeighbour(720, 120);
		System.out.println(restaurantxQueue.peek().id + "\n" + restaurantxQueue.poll().getDistance());
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
			br.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

//	find kth nearest restaurant 
	public static void findNearestNeighbour(int x, int y){
		for (int i = 0; i < GROUP_NUMBER; i++){
			String filepath =  "restaurant_data/restaurants_group_";
			String filename = filepath + Integer.toString(i) + ".txt";
			File f = new File(filename);
			float distance;
			try{
				BufferedReader br = new BufferedReader(new FileReader(f));
				String temp = null;
				while((temp = br.readLine()) != null){
			        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
			        int n = 0;
			        int id = 0;
					float MBRx = 0;
					float MBRy = 0;
					Restaurantx rest;
			        while(token.hasMoreElements()){
			        	if(n == 0){
			        		id = Integer.parseInt(token.nextToken());
			        	}else if(n == 1){
			        		MBRx = Float.parseFloat(token.nextToken());
			        	}else if(n == 2){
			        		MBRy = Float.parseFloat(token.nextToken());
			        	}   
			        	n++;
			        }
			        rest = new Restaurantx(id, MBRx, MBRy, x, y);
			        if (rest.getDistance() < mindistancex){
			        	mindistancex = rest.getDistance();
			        	restaurantxQueue.add(rest);
			        }
				}
				br.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
//	return the smaller figure
	public static float getMin(float d1, float d2){
		return (d1 <= d2) ? d1 : d2;
	}
	
//	check if a position is inside a MBR
	public static boolean isInMBR(int x, int y, MBR mbr){
		return (x >= mbr.lower_x && x <= mbr.upper_x && y >= mbr.lower_y && y <= mbr.upper_y);
	}
	
//	public static void reachMBRgroup(int i, int x, int y){
//		String filepath =  "restaurant_data/restaurants_group_";
//		String filename = filepath + Integer.toString(i) + ".txt";
//		File f = new File(filename);
//		float distance;
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			String temp = null;
//			while((temp = br.readLine()) != null){
//		        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
//		        int n = 0;
//		        int id = 0;
//				float MBRx = 0;
//				float MBRy = 0;
//		        while(token.hasMoreElements()){
//		        	if(n == 0){
//		        		id = Integer.parseInt(token.nextToken());
//		        	}else if(n == 1){
//		        		MBRx = Float.parseFloat(token.nextToken());
//		        	}else if(n == 2){
//		        		MBRy = Float.parseFloat(token.nextToken());
//		        	}   
//		        	n++;
//		        }
//		        float value = ((x - MBRx) * (x - MBRx) + (y - MBRy) * (y - MBRy));  
//		        distance = (float) Math.sqrt(value);
//		        if (distance <= mindistance)
//		        	mindistance = distance;
//		        	id1 = id;
//			}
//			br.close();
//		}catch(IOException ioe){
//			ioe.printStackTrace();
//		}
//	}
	public static void getmin(int x, int y){
		for (int i = 0; i < GROUP_NUMBER; i++){
			if (isInMBR(x, y, MBRs[i])){
				
			}
		}
	}
//	Define class Restaurantx
	public static class Restaurantx{
		private int id;
		private float rx;
		private float ry;
		private float distance;
		private float px;
		private float py;
		
		public Restaurantx(int id, float rx, float ry, float px, float py){
			this.id = id;
			this.rx = rx;
			this.ry = ry;
			this.px = px;
			this.py = py;
			this.distance = (float)Math.sqrt((rx - px) * (rx - px) + (ry - py) * (ry - py));
		}
		
		public float getDistance(){
			return this.distance;
		}
	}

//	Define class Restauranty
	public static class Restauranty{
		private int id;
		private float rx;
		private float ry;
		private float distance;
		private float px;
		private float py;
		
		public Restauranty(int id, float rx, float ry, float px, float py){
			this.id = id;
			this.rx = rx;
			this.ry = ry;
			this.px = px;
			this.py = py;
			this.distance = (float)Math.sqrt((rx - px) * (rx - px) + (ry - py) * (ry - py));
		}
		
		public float getDistance(){
			return this.distance;
		}
	}
	
//	Define class Person
	public static class Person{
		private String id;
		private int x;
		private int y;
		
		public Person(String id, int x, int y){
			this.id = id;
			this.x = x;
			this.y = y;
		}
//		public getDistance(){
//			
//		}
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