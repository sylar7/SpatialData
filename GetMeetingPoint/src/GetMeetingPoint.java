import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class GetMeetingPoint{
	private static int GROUP_NUMBER = 9; 
	private static MBR[] MBRs = new MBR[GROUP_NUMBER];
	private static int bestResId;
	private static int bestDistance;
	private static float mindistancex = 1000;
	private static float mindistancey = 1000;
	private static float mindistance = 2000;
	
	public static class MBRchoice{
		private MBR mbr;
		private float distance;
		private int x;
		private int y;
		
		public MBRchoice(MBR mbr, int x, int y){
			this.mbr = mbr;
			this.x = x;
			this.y = y;
			this.distance = distanceCal();
		}
		
		public MBR getMBR(){
			return this.mbr;
		}
		
		public float distanceCal(){
			float d1 = (float)Math.sqrt((x - mbr.lower_x) * (x - mbr.lower_x) + (y - mbr.lower_y) * (y - mbr.lower_y));
			float d2 = (float)Math.sqrt((x - mbr.lower_x) * (x - mbr.lower_x) + (y - mbr.upper_y) * (y - mbr.upper_y));
			float d3 = (float)Math.sqrt((x - mbr.upper_x) * (x - mbr.upper_x) + (y - mbr.lower_y) * (y - mbr.lower_y));
			float d4 = (float)Math.sqrt((x - mbr.upper_x) * (x - mbr.upper_x) + (y - mbr.upper_y) * (y - mbr.upper_y));
			float d5 = (d1 < d2) ? d1 : d2;
			float d6 = (d3 < d4) ? d3 : d4;
			return (d5 < d6) ? d5 : d6;
		}
		public float getDistance(){
			return this.distance;
		}
	}
	
	private static Comparator<MBRchoice> mbrComp = new Comparator<MBRchoice>() {
		@Override
		public int compare(MBRchoice o1, MBRchoice o2) {
			// TODO Auto-generated method stub
			if (o1.getDistance() - o2.getDistance() < 0){
				return -1;
			}else{
				return 1;
			}
		}
	};
	
	private static Queue<MBRchoice> mbrchoicex = new PriorityQueue<MBRchoice>(20, mbrComp);
	private static Queue<MBRchoice> mbrchoicey = new PriorityQueue<MBRchoice>(20, mbrComp);
	
	public static void init(){
		while(!choicex.isEmpty()){
			choicex.poll();
		}
		while(!choicey.isEmpty()){
			choicey.poll();
		}
		while(mbrchoicex.isEmpty()){
			mbrchoicex.poll();
		}
		while(mbrchoicey.isEmpty()){
			mbrchoicey.poll();
		}
		bestResId = 0;
		bestDistance = 0;
		mindistancex = 1000;
		mindistancey = 1000;
		mindistance = 2000;
	}
	
	public static void getMBRchoicex(int x, int y){
		for (int i = 0; i < GROUP_NUMBER; i++){
			MBRchoice mbrc = new MBRchoice(MBRs[i], x, y);
			mbrchoicex.add(mbrc);
		}
	}
	public static void getMBRchoicey(int x, int y){
		for (int i = 0; i < GROUP_NUMBER; i++){
			MBRchoice mbrc = new MBRchoice(MBRs[i], x, y);
			mbrchoicey.add(mbrc);
		}
	}
	
	private static Comparator<Choice> comp = new Comparator<Choice>(){
		public int compare(Choice o1, Choice o2) {
			// TODO Auto-generated method stub
			if(o1.getDistance() - o2.getDistance() < 0){
				return -1;
			}else{
				return 1;
			}
		}
	};
	
	public static class Choice{
		private Restaurant r;
		private float distance;
		private int x;
		private int y;
		
		public Choice(Restaurant r, int x, int y){
			this.r = r;
			this.x = x;
			this.y = y;
			this.distance = (float) Math.sqrt((x - r.getRestx()) * (x - r.getRestx()) + (y - r.getResty()) * (y - r.getResty())); 
		}
		public float getDistance(){
			return distance;
		}
		public int getId(){
			return r.getId();
		}
		public int getx(){
			return x;
		}
		public int gety(){
			return y;
		}
		public Restaurant getRestaurant(){
			return r;
		}
	}
	
	public static class Restaurant{
		private int id;
		private float rx;
		private float ry;
		
		public Restaurant(int id, float rx, float ry){
			this.id = id;
			this.rx = rx;
			this.ry = ry;

		}
		public float getRestx(){
			return this.rx;
		}
		public float getResty(){
			return this.ry;
		}
		public int getId(){
			return this.id;
		}
	}

	private static Queue<Choice> choicex =  new PriorityQueue<Choice>(20, comp);
	private static Queue<Choice> choicey =  new PriorityQueue<Choice>(20, comp);
	
	public static void findChoicex(int x, int y){
		while(!mbrchoicex.isEmpty() && mbrchoicex.peek().getDistance() < mindistancex){
			int mbrnumber = mbrchoicex.poll().getMBR().getId();
			String filepath =  "restaurant_data/restaurants_group_";
			String filename = filepath + Integer.toString(mbrnumber) + ".txt";
			File f = new File(filename);
			try{
				BufferedReader br = new BufferedReader(new FileReader(f));
				String temp = null;
				while((temp = br.readLine()) != null){
			        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
			        int n = 0;
			        int id = 0;
					float MBRx = 0;
					float MBRy = 0;
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
			        Choice choice = new Choice(new Restaurant(id, MBRx, MBRy), x, y);
			        if (choice.getDistance() < mindistancex){
			        	choicex.add(choice);
			        	mindistancex = choice.getDistance();
			        }
				}
				br.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	public static void findChoicey(int x, int y){
		while(!mbrchoicey.isEmpty() && mbrchoicey.peek().getDistance() < mindistancey){
			int mbrnumber = mbrchoicey.poll().getMBR().getId();
			String filepath =  "restaurant_data/restaurants_group_";
			String filename = filepath + Integer.toString(mbrnumber) + ".txt";
			File f = new File(filename);
			try{
				BufferedReader br = new BufferedReader(new FileReader(f));
				String temp = null;
				while((temp = br.readLine()) != null){
			        StringTokenizer token = new StringTokenizer(temp.substring(0, temp.length() - 1)); 
			        int n = 0;
			        int id = 0;
					float MBRx = 0;
					float MBRy = 0;
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
			        Choice choice = new Choice(new Restaurant(id, MBRx, MBRy), x, y);
			        if (choice.getDistance() < mindistancey){
			        	choicey.add(choice);
			        	mindistancey = choice.getDistance();
			        }
				}
				br.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]){
		readMBRs();
		for (int i = 0; i < GROUP_NUMBER; i++){
			MBRs[i].showMBR();
		}
		getMBRchoicex(720, 120);
		getMBRchoicey(215, 820);
		
		findChoicex(720, 120);
		findChoicey(215, 820);
	
		findBestChoice();
		System.out.println(bestResId + "\t" + bestDistance);
		System.out.println("============================");
//		while(!mbrchoicex.isEmpty()){
//			System.out.println(mbrchoicex.peek().getMBR().id + "\t" + mbrchoicex.poll().getDistance());
//		}
//		getMBRchoicex(720, 120);
//		findChoicex1(720, 120);
		
//		findChoicex1(720, 120);
//		findChoicey1(215, 820);
////		
//		findBestChoice1();
//		System.out.println("============================");
//		while(!choicex.isEmpty()){
//			System.out.println(choicex.peek().getRestaurant().getId() + "\t" + choicex.poll().getDistance());
//		}
//		
//		System.out.println("============================");
		System.out.println("============================");
		
		init();
		getMBRchoicex(320, 120);
		getMBRchoicey(450, 680);
		
		findChoicex(320, 120);
		findChoicey(450, 680);
	
		findBestChoice();
		System.out.println(bestResId + "\t" + bestDistance);
		System.out.println("============================");
//		findChoicex(320, 120);
//		findChoicey(450, 680);
////		
//		findBestChoice();
//		System.out.println(bestResId + "\t" + bestDistance);
//		System.out.println("============================");
	}
	
//
//	public static void findBestChoice(){
//		float mindistance = 2000;
//		Restaurant r = null;
//		while(!choicex.isEmpty() && !choicey.isEmpty() && choicex.peek().getDistance() < mindistance && choicey.peek().getDistance() < mindistance){
//			if(choicex.peek().getId() == choicey.peek().getId() && choicex.peek().getDistance() < mindistance){
//				r = choicex.peek().getRestaurant();
//				mindistance = choicex.peek().getDistance();
//				break;
//			}
//			else {
//				if(getDistance(choicex.peek().getRestaurant(), choicey.peek().getx(), choicey.peek().gety()) <= getDistance(choicey.peek().getRestaurant(), choicex.peek().getx(), choicex.peek().gety())){
//					r = choicex.peek().getRestaurant();
//					mindistance = getDistance(choicex.peek().getRestaurant(), choicey.peek().getx(), choicey.peek().gety());
//					choicey.poll();
//				}else{
//					r = choicey.peek().getRestaurant();
//					mindistance = getDistance(choicey.peek().getRestaurant(), choicex.peek().getx(), choicex.peek().gety());
//					choicex.poll();
//				}	
//			}
//		}
//		bestResId = r.getId();
//		bestDistance = (int) mindistance;
//	}
	
	public static void findBestChoice(){
		Restaurant r = null;
		while(!choicex.isEmpty() && !choicey.isEmpty() && choicex.peek().getDistance() < mindistance && choicey.peek().getDistance() < mindistance){
			if(choicex.peek().getId() == choicey.peek().getId() && choicex.peek().getDistance() < mindistance){
				r = choicex.peek().getRestaurant();
				mindistance = choicex.peek().getDistance();
				break;
			}
			else {
				if(getDistance(choicex.peek().getRestaurant(), choicey.peek().getx(), choicey.peek().gety()) <= getDistance(choicey.peek().getRestaurant(), choicex.peek().getx(), choicex.peek().gety())){
					r = choicex.peek().getRestaurant();
					mindistance = getDistance(choicex.peek().getRestaurant(), choicey.peek().getx(), choicey.peek().gety());
					choicey.poll();
				}else{
					r = choicey.peek().getRestaurant();
					mindistance = getDistance(choicey.peek().getRestaurant(), choicex.peek().getx(), choicex.peek().gety());
					choicex.poll();
				}	
			}
		}
		bestResId = r.getId();
		bestDistance = (int) mindistance;
	}

	

	public static float getDistance(Restaurant r, int x, int y){
		return (float)Math.sqrt((x-r.getRestx()) * (x-r.getRestx()) + (y-r.getResty()) * (y-r.getResty()));
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


//	return the smaller figure
	public static float getMin(float d1, float d2){
		return (d1 <= d2) ? d1 : d2;
	}
//	
	public static float getMax(float d1, float d2){
		return (d1 >= d2) ? d1 : d2;
	}
	
//	check if a position is inside a MBR
	public static boolean isInMBR(int x, int y, MBR mbr){
		return (x >= mbr.lower_x && x <= mbr.upper_x && y >= mbr.lower_y && y <= mbr.upper_y);
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