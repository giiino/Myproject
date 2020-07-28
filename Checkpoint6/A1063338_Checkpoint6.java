import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.regex.*;
import java.sql.*;
public class A1063338_Checkpoint6{
	
	public static String strall;
	public static String[] str = new String[100];
	public static String[] str2 = new String[100];
	public static ArrayList<String> randt =  new ArrayList<String>();
	public static ArrayList<String> myList = new ArrayList<String>();
	public static ArrayList<String> locationList = new ArrayList<String>();
	public static ArrayList<String> dbList = new ArrayList<String>();
	public static int check_random = 0;
	public static int objnum = 0;
	public static int objnum2 = 0;
	public static int minuslocation;
	public static int ppllo;
	public static Character[] c ;
	public static Land[] land ;
	static A1063338_GUI demo;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String protocol = "jdbc:mysql:";
	
	public static void main(String[] args) throws IOException{
		
		//// TODO: Announce your GUI object to make the GUI ////
		//// TODO: This time we won't give you a function to realize our demands (Please look for demands on the document).
		//// TODO: So, you can make it in anyway whatever you like.
			theinterface doit= new theinterface();
			doit.setVisible(true);

	}
	public static void Load(String filename, String filename2) throws IOException {
		//// TODO: You should load the variables from the file. ////
		myList.clear();
		randt.clear();
		FileReader fr =new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String strall2 = br.readLine();
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(strall2);
		String result = m.replaceAll("");
		for (int i = 0; i < result.length(); i++) {
			randt.add(result.substring(i, i+1));
			}
		while ((strall = br.readLine()) != null) {
		str = strall.split(",");
	
		myList.add(str[0]);
		myList.add(str[1]);
		myList.add(str[2]);
		myList.add(str[3]);
		myList.add(str[4]);	
		objnum++;
	}		
		br.close();
		
		locationList.clear();
		FileReader fr2 =new FileReader(filename2);
		BufferedReader br2 = new BufferedReader(fr2);
		String strall3 = br2.readLine();
		while ((strall3 = br2.readLine()) != null) {
		str2 = strall3.split(",");
	
		locationList.add(str2[0]);
		locationList.add(str2[1]);
		objnum2++;
	}	
		
		br2.close();
	}

	public static void Save(String filename, String filename2) throws IOException {
		//// TODO: You should save the changed variables into original data (filename). ////
		FileWriter fw =new FileWriter(filename,true);
		demo.passbystart();
		for(int i=0;i<4;i++)  {
			fw.write(Integer.parseInt(Integer.toString(c[i].location))+",");  //location
			fw.write(Integer.toString(c[i].CHARACTER_NUMBER)+",");
			fw.write(Integer.toString(c[i].money)+",");
			fw.write(Integer.toString(c[i].status)+",");	//status
			fw.write((c[i].IMAGE_FILENAME)+'\n');

   }
   			fw.close();
   		
   		FileWriter fw2 =new FileWriter(filename2,true);
		for(int J=0;J<16;J++)  {
			fw2.write(Integer.parseInt(Integer.toString(land[J].PLACE_NUMBER))+","+Integer.parseInt(Integer.toString(land[J].owner))+'\n');  

   }
   			fw2.close();
	}
	
	public static void Random() {
			//// TODO: while calling the Random function, Character.location should plus the random value, and Character.status should minus one.
			//// TODO: While Character.status more than zero(not include zero), Character can move(plus the random value).
		try {		
	        int prelocation=c[demo.who_dice-1].location;
			c[demo.who_dice-1].location+=get_Random();
			c[demo.who_dice-1].status-=1;
			int aftlocation=c[demo.who_dice-1].location;
			minuslocation=aftlocation-prelocation;
			ppllo=demo.who_dice-1;
			}catch(Exception e1) {
				System.out.print(e1.getMessage());
			}
		
	}
	public static void specialLoad()throws IOException {
		objnum2 = 0;
		locationList.clear();
		FileReader fr2 =new FileReader("Land.txt");
		BufferedReader br2 = new BufferedReader(fr2);
		String strall3 = br2.readLine();
		while ((strall3 = br2.readLine()) != null) {
		str2 = strall3.split(",");
	
		locationList.add(str2[0]);
		locationList.add(str2[1]);
		objnum2++;
		linkDatabase();
		land=new Land[objnum2]; 
		int k = 0,f=0;
		for(int i=0;i<objnum2;i++)  {
			do {					
				land[i] = new Land(
						Integer.parseInt(locationList.get(f++)), 
						Integer.parseInt(locationList.get(f++)),
						Integer.parseInt(dbList.get(k++)), 
						Integer.parseInt(dbList.get(k++))
						);	
				break;					 
			}while(k<dbList.size());
		}
		
	}
	}
    public static void Load_to_Character() {
	try {
		Load("Character.txt","Land.txt");
		c=new Character[objnum];

		int j = 0;
		for(int i=0;i<objnum;i++)  {
			do {					
				c[i] = new Character(
						Integer.parseInt(myList.get(j++)), 
						Integer.parseInt(myList.get(j++)),
						Integer.parseInt(myList.get(j++)), 
						Integer.parseInt(myList.get(j++)),
						(String)myList.get(j++)
						);	
				break;					 
			}while(j<myList.size());
		}
		linkDatabase();
		land=new Land[objnum2]; 
		int k = 0,f=0;
		for(int i=0;i<objnum2;i++)  {
			do {					
				land[i] = new Land(
						Integer.parseInt(locationList.get(f++)), 
						Integer.parseInt(locationList.get(f++)),
						Integer.parseInt(dbList.get(k++)), 
						Integer.parseInt(dbList.get(k++))
						);	
				break;					 
			}while(k<dbList.size());
		}
		
		}catch(Exception e1) {
			System.out.print(e1.getMessage());
		}	

    }
    public static int get_Random() {
    	return (int) (Math.random()*6+1);
    }
    public static void linkDatabase() {
    	  Connection conn = null;
    	  try {
    	   Class.forName(driver).newInstance();
    	   System.out.println("driver loading");
    	  }catch(Exception err) {
    	   System.err.println("failed");
    	   err.printStackTrace(System.err);
    	   System.exit(0);
    	  } 
    	  String url = "//localhost:3306/";
    	  String dbName = "CHECKPOINT";
    	  String username = "checkpoint";
    	  String password = "ckppwd";
    	  try {
    	   conn = DriverManager.getConnection(protocol + url + dbName+"?serverTimezone=UTC", username, password);
    	   Statement s = conn.createStatement();

    	   ResultSet rs  = null;
    	   rs =  s.executeQuery("SELECT l.PLACE_NUMBER,l.LAND_PRICE,l.TOLLS FROM LAND l;");
    	            while(rs.next()) {
    	    String lp = rs.getString("LAND_PRICE");
    	    String tolls = rs.getString("TOLLS");
    		dbList.add(lp);
    		dbList.add(tolls);
    	   }
    	   rs.close();
    	   conn.close();
    	  }catch(SQLException err) {
    	   System.err.println("sql error");
    	   err.printStackTrace();
    	   System.exit(0);
    	  }
    	 }
    private static class theinterface extends JFrame implements ActionListener{
    	JButton startbtn,sloadbtn,sexitbtn;
    	public  theinterface(){
    		setSize(200, 200);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setResizable(false);
    		
    		JPanel put3btn = new JPanel();
    		put3btn.setBorder( new EmptyBorder(10, 0, 0, 0 ) );
    		FlowLayout layout = new FlowLayout();
    		layout.setVgap(20);
    		put3btn.setLayout(layout);    		
    		startbtn = new JButton("Start");
    		startbtn.addActionListener(this);
    		startbtn.setPreferredSize(new Dimension(100,20));
    		put3btn.add(startbtn);
    	    sloadbtn = new JButton("Load");
    	    sloadbtn.addActionListener(this);
    	    sloadbtn.setPreferredSize(new Dimension(100,20));
    	    put3btn.add(sloadbtn);
    	    sexitbtn = new JButton("Exit");
    	    sexitbtn.addActionListener(this);
    	    sexitbtn.setPreferredSize(new Dimension(100,20));
    	    put3btn.add(sexitbtn);
    	    add(put3btn,BorderLayout.CENTER);
    	}
    	public void actionPerformed(ActionEvent e){
    		String actionCommand = e.getActionCommand();
            if(actionCommand.equals("Start")) {
            	dispose();
            	try {
    			File writename = new File("Character.txt"); 
    			writename.createNewFile(); 
    			BufferedWriter out = new BufferedWriter(new FileWriter(writename,false));
    			out.write("Round:1,Turn:1"+'\n'+"0,1,2000,1,Character_1.png"+'\n'+"0,2,2000,1,Character_2.png"+'\n'+"0,3,2000,1,Character_3.png"
    					+'\n'+"0,4,2000,1,Character_4.png");
				out.flush(); 
				out.close(); 
    			File writename2 = new File("Land.txt"); 
    			writename2.createNewFile(); 
    			BufferedWriter out2 = new BufferedWriter(new FileWriter(writename2,false));
    			out2.write("LOCATION_NUMBER, owner"+'\n'+"1,0"+'\n'+"2,0"+'\n'+"3,0"+'\n'+"4,0"+'\n'+"5,0"+'\n'+"6,0"+'\n'+"7,0"+'\n'+"8,0"+'\n'+"9,0"+'\n'
    					+"10,0"+'\n'+"11,0"+'\n'+"12,0"+'\n'+"13,0"+'\n'+"14,0"+'\n'+"15,0"+'\n'+"16,0"+'\n'+"17,0"+'\n'+"18,0"+'\n'+"19,0"+'\n');
				out2.flush(); 
				out2.close(); 
    			new java.util.Timer().schedule( 
    			        new java.util.TimerTask() {
    			            public void run() {
    			    			demo = new A1063338_GUI();	
    			    	    	demo.pack();
    			    	    	demo.repaint();
    			    	        demo.setVisible(true);
    			            }
    			        }, 
    			        600 
    			);

            	}catch(Exception a) {
            		
            	}
            }
            else if(actionCommand.equals("Load")) {
            	dispose();
            	try {
        			    			demo = new A1063338_GUI();	
        			    	    	demo.pack();
        			    	    	demo.repaint();
        			    	        demo.setVisible(true);

            	}catch(Exception s) {
            		errorinterface exceptionerror = new errorinterface();
            		exceptionerror.setVisible(true);
            	}
            	
            }
            else if(actionCommand.equals("Exit")) {
            	System.exit(0);
            }
    	}
    }
    private static class errorinterface extends JFrame implements ActionListener{
    	JButton confirmbtn;
    	
    	public errorinterface() {
    		setSize(150, 100);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setResizable(false);
    		JPanel placeforerror = new JPanel();
    		placeforerror.setLayout(new FlowLayout());
    		JLabel infoerror = new JLabel("Without record");
    		confirmbtn = new JButton("Confirm");
    		confirmbtn.setPreferredSize(new Dimension(100,20));
    		confirmbtn.addActionListener(this);
    		placeforerror.add(infoerror);
    		placeforerror.add(confirmbtn);
    		add(placeforerror,BorderLayout.CENTER);
    	}
    	public void actionPerformed(ActionEvent e){
    		String actionCommand = e.getActionCommand();
    		if(actionCommand.equals("Confirm")) {
    			 System.exit(0);
    		}
    	}
    }
}