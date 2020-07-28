import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.*;

public class A1063338_GUI extends JFrame implements ActionListener{
    ////TODO: GUI ////
    private static final int WIDTH =700;
    private static final int HEIGHT =700;

    private static int pos1w=0,pos1h=0,pos2w=0,pos2h=0,pos3w=0,pos3h=0,
			  pos4w=0,pos4h=0;
    private float movew=2;
    private long sleept,times;
    public static int who_dice,imgw,imgh,distance,runtotouch1=0,runtotouch2=0,runtotouch3=0,runtotouch4=0,flag1=0,recond;
    int roundnum=1,threadtimes;
    JLabel TurnCHR;
    JLabel ROUND;
    JButton Dicebtn,loadbtn,savebtn ;
    JLabel[] png_text = new JLabel[25];
    JLabel[] money_text = new JLabel[5];
    JLabel display_dicenum_text;
    A1063338_Checkpoint6 chkpt3 = new A1063338_Checkpoint6();
    BufferedImage img1,img2,img3,img4,sourceImg;
    boolean timeout=true,openland=false;
    

    
    public A1063338_GUI (){   	    	
    super();
    chkpt3.Load_to_Character();
    loadlocation();
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    setResizable(false);
	if(chkpt3.c[0].status==0&&chkpt3.c[1].status==0&&chkpt3.c[2].status==0&&chkpt3.c[3].status==0) {
		chkpt3.c[0].status+=1;
		chkpt3.c[1].status+=1;
		chkpt3.c[2].status+=1;
		chkpt3.c[3].status+=1;
		roundnum++;
	}   
	who_dice=Integer.parseInt(chkpt3.randt.get(1));
	if(chkpt3.c[who_dice-1].status==0) {
		who_dice++;
		}
	roundnum=Integer.parseInt( chkpt3.randt.get(0));
    setBackground(Color.WHITE);
    setLayout(new BorderLayout());
    JPanel toppanel = new JPanel();
    toppanel.setLayout(new FlowLayout());
    savebtn = new JButton("Save");
    savebtn.addActionListener(this);
    toppanel.add(savebtn);
    loadbtn = new JButton("Load");
    loadbtn.addActionListener(this);
    toppanel.add(loadbtn);
    JPanel character_data = new JPanel();
    character_data.setLayout(new GridLayout(2,4));
    character_data.setPreferredSize(new Dimension(520, 40));
    JLabel character1 = new JLabel("Character1");
    character1.setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(character1);
    JLabel character2 = new JLabel("Character2");
    character2.setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(character2);
    JLabel character3 = new JLabel("Character3");
    character3.setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(character3);
    JLabel character4 = new JLabel("Character4");
    character4.setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(character4);
    money_text[1] = new JLabel("     "+Integer.toString(chkpt3.c[0].money));
    money_text[1].setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(money_text[1]);
    money_text[2] = new JLabel("     "+Integer.toString(chkpt3.c[1].money));
    money_text[2].setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(money_text[2]);
    money_text[3] = new JLabel("     "+Integer.toString(chkpt3.c[2].money));
    money_text[3].setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(money_text[3]);
    money_text[4] = new JLabel("     "+Integer.toString(chkpt3.c[3].money));
    money_text[4].setFont(new Font("verdana", Font.BOLD, 15));
    character_data.add(money_text[4]);
    toppanel.add(character_data);

    add(toppanel,BorderLayout.NORTH);

    JPanel map = new JPanel();
    map.setLayout(new BorderLayout());

    JPanel n_img = new JPanel();
    n_img.setLayout(new FlowLayout());
    n_img.add(new JLabel(new ImageIcon("10.png")));
    JLabel png11 = new JLabel(new ImageIcon("11.png"));
    png11.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[11] = new JLabel("");
    png_text[11].setFont(new Font("verdana", Font.PLAIN,80));
    png11.add(png_text[11]);
    n_img.add(png11);
    JLabel png12 = new JLabel(new ImageIcon("12.png"));
    png12.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[12] = new JLabel("");
    png_text[12].setFont(new Font("verdana", Font.PLAIN,80));
    png12.add(png_text[12]);
    n_img.add(png12);
    JLabel png13 = new JLabel(new ImageIcon("13.png"));
    png13.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[13] = new JLabel("");
    png_text[13].setFont(new Font("verdana", Font.PLAIN,80));
    png13.add(png_text[13]);
    n_img.add(png13);
    JLabel png14 = new JLabel(new ImageIcon("14.png"));
    png14.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[14] = new JLabel("");
    png_text[14].setFont(new Font("verdana", Font.PLAIN,80));
    png14.add(png_text[14]);
    n_img.add(png14);
    n_img.add(new JLabel(new ImageIcon("15.png")));
    map.add(n_img,BorderLayout.NORTH);
  
    JPanel w_img = new JPanel();
    w_img.setBorder( new EmptyBorder( 0, 20, 0, 0 ) );
    w_img.setLayout(new GridLayout(4,1,0,0));
    JLabel png9 = new JLabel(new ImageIcon("9.png"));
    png9.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[9] = new JLabel("");
    png_text[9].setFont(new Font("verdana", Font.PLAIN,80));
    png9.add(png_text[9]);
    w_img.add(png9);
    JLabel png8 = new JLabel(new ImageIcon("8.png"));
    png8.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[8] = new JLabel("");
    png_text[8].setFont(new Font("verdana", Font.PLAIN,80));
    png8.add(png_text[8]);
    w_img.add(png8);
    JLabel png7 = new JLabel(new ImageIcon("7.png"));
    png7.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[7] = new JLabel("");
    png_text[7].setFont(new Font("verdana", Font.PLAIN,80));
    png7.add(png_text[7]);
    w_img.add(png7);
    JLabel png6 = new JLabel(new ImageIcon("6.png"));
    png6.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[6] = new JLabel("");
    png_text[6].setFont(new Font("verdana", Font.PLAIN,80));
    png6.add(png_text[6]);
    w_img.add(png6);
    map.add(w_img,BorderLayout.WEST);

    JPanel c_img = new JPanel();   
    c_img.setLayout(new BorderLayout());
    JPanel title_img = new JPanel();
    title_img.setLayout(new BorderLayout());
    title_img.add(new JLabel(new ImageIcon("title.png")),BorderLayout.WEST);
    c_img.add(title_img,BorderLayout.NORTH);



    JPanel display_dicenum_img = new JPanel();
    display_dicenum_img.setLayout(new GridLayout(2,1,0,-20));
    
    JLabel display_dicenum = new JLabel(new ImageIcon("display_dicenum.png"));
    display_dicenum.setLayout(new FlowLayout(FlowLayout.CENTER));
    display_dicenum.setBorder( new EmptyBorder(40, 0, 0, 0 ) );

    display_dicenum_text = new JLabel("0");
    display_dicenum_text.setFont(new Font("verdana", Font.BOLD, 50));
    display_dicenum.add(display_dicenum_text);

    display_dicenum_img.setBorder( new EmptyBorder(25, 0, 0, 35 ) );
    display_dicenum_img.add(display_dicenum);   
    
    ROUND = new JLabel("ROUND "+Integer.toString(roundnum));
    ROUND.setFont(new Font("verdana", Font.BOLD, 20));
    display_dicenum_img.add(ROUND);
    c_img.add(display_dicenum_img,BorderLayout.EAST);

    JPanel Turn_label = new JPanel();
    JLabel Turn = new JLabel("    Turn ");
    Turn.setFont(new Font("verdana", Font.BOLD, 30));  
    TurnCHR = new JLabel("Character "+Integer.toString(who_dice));
    TurnCHR.setFont(new Font("verdana", Font.BOLD, 30));  
    Turn_label.setLayout(new FlowLayout());
    Turn_label.add(Turn);
    Turn_label.add(TurnCHR);
    c_img.add(Turn_label,BorderLayout.SOUTH);

    map.add(c_img,BorderLayout.CENTER);

    JPanel Dice_img = new JPanel();
    Dice_img.setLayout(new BorderLayout());
    Dicebtn = new JButton(new ImageIcon("Dice.png"));  //
    Dicebtn.setBorder(null);
    Dicebtn.setContentAreaFilled(false);
    Dicebtn.addActionListener(this);
    Dice_img.add(Dicebtn,BorderLayout.NORTH);
    c_img.add(Dice_img,BorderLayout.WEST);
    JPanel e_img = new JPanel();
    e_img.setLayout(new GridLayout(4,1,0,0));
    e_img.setBorder( new EmptyBorder( 0, 0, 0, 20 ) );
    JLabel png16 = new JLabel(new ImageIcon("16.png"));
    png16.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[16] = new JLabel("");
    png_text[16].setFont(new Font("verdana", Font.PLAIN,80));
    png16.add(png_text[16]);
    e_img.add(png16);
    JLabel png17 = new JLabel(new ImageIcon("17.png"));
    png17.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[17] = new JLabel("");
    png_text[17].setFont(new Font("verdana", Font.PLAIN,80));
    png17.add(png_text[17]);
    e_img.add(png17);
    JLabel png18 = new JLabel(new ImageIcon("18.png"));
    png18.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[18] = new JLabel("");
    png_text[18].setFont(new Font("verdana", Font.PLAIN,80));
    png18.add(png_text[18]);
    e_img.add(png18);
    JLabel png19 = new JLabel(new ImageIcon("19.png"));
    png19.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[19] = new JLabel("");
    png_text[19].setFont(new Font("verdana", Font.PLAIN,80));
    png19.add(png_text[19]);
    e_img.add(png19);
    map.add(e_img,BorderLayout.EAST);

    JPanel s_img = new JPanel();
    s_img.setLayout(new FlowLayout());
    s_img.add(new JLabel(new ImageIcon("5.png")));
    JLabel png4 = new JLabel(new ImageIcon("4.png"));
    png4.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[4] = new JLabel("");
    png_text[4].setFont(new Font("verdana", Font.PLAIN,80));
    png4.add(png_text[4]);
    s_img.add(png4);
    JLabel png3 = new JLabel(new ImageIcon("3.png"));
    png3.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[3] = new JLabel("");
    png_text[3].setFont(new Font("verdana", Font.PLAIN,80));
    png3.add(png_text[3]);
    s_img.add(png3);
    JLabel png2 = new JLabel(new ImageIcon("2.png"));
    png2.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[2] = new JLabel("");
    png_text[2].setFont(new Font("verdana", Font.PLAIN,80));
    png2.add(png_text[2]);
    s_img.add(png2);
    JLabel png1 = new JLabel(new ImageIcon("1.png"));
    png1.setLayout(new FlowLayout(FlowLayout.CENTER));
    png_text[1] = new JLabel("");
    png_text[1].setFont(new Font("verdana", Font.PLAIN,80));
    png1.add(png_text[1]);
    s_img.add(png1);
    s_img.add(new JLabel(new ImageIcon("0.png")));
    map.add(s_img,BorderLayout.SOUTH);
    
    add(map,BorderLayout.CENTER);

    JPanel exit_panel = new JPanel();
    exit_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    JButton exitbtn = new JButton("Exit");
    exitbtn.addActionListener(this);
    exit_panel.add(exitbtn);
    add(exit_panel,BorderLayout.SOUTH);

	new java.util.Timer().schedule( 
	        new java.util.TimerTask() {
	            public void run() {
	            	repaint();
	            }
	        }, 
	        500 
	);
	new java.util.Timer().schedule( 
	        new java.util.TimerTask() {
	            public void run() {
	                resetlabel();
	                loadlabel();
	            	repaint();
	            }
	        }, 
	        600 
	);


}
    public void update() 
    { 
    	
    	if(chkpt3.ppllo==0) {
    		int dif;
    	if(5>runtotouch1+chkpt3.minuslocation) {
    		distance=pos1w-(570-chkpt3.minuslocation*100);
        	if(570-chkpt3.minuslocation*100<pos1w ) {
        	pos1w-=movew;        	
        	}
        	else {
        		repaint();
        	}
//        	else {
//            	if(pos1w-chkpt3.minuslocation*100<pos1w ) {
//                	pos1w-=movew; 
//            	}
//        	}	
    	}else if(5==runtotouch1+chkpt3.minuslocation) {
    		distance=pos1w-90;
        	if(90<pos1w ) {
        	pos1w-=movew;
        	}
    	}else if(10>runtotouch1+chkpt3.minuslocation) {
    		dif=runtotouch1+chkpt3.minuslocation-5;
    		distance=(pos1w-90)+(pos1h-(620-dif*90));
        	if(90<pos1w ) {
        	pos1w-=movew;
        	}	
        	else if(620-dif*90<pos1h ) {
        	pos1h-=movew;
        	}
        	else {
        		repaint();
        	}
//        	else {
//            	if(pos1h-dif*90<pos1h ) {
//            		pos1h-=movew;
//            	}
//        	}
    	}else if(10==runtotouch1+chkpt3.minuslocation) {
    		distance=pos1h-120;
    		if(chkpt3.minuslocation==6) {
    			if(90<pos1w ) {
    	        	pos1w-=movew;
    	        	}else if(140<pos1h) {
    	        		pos1h-=movew;
    	        	}
    		}
    		else {
        	if(140<pos1h) {
        	pos1h-=movew;
        	}}
    	}else if(15>runtotouch1+chkpt3.minuslocation) {
    		dif=runtotouch1+chkpt3.minuslocation-10;
    		distance=(pos1h-140)+((70+dif*110)-pos1w);
        	if(140<pos1h) {
        	pos1h-=movew;
        	}
        	else if(70+dif*110>pos1w ) {
        	pos1w+=movew;
        	}
        	else {
        		repaint();
        	}

    	}else if(15==runtotouch1+chkpt3.minuslocation) {
    		distance=570-pos1w;
    		if(chkpt3.minuslocation==6) {
    			if(140<pos1w ) {
    	        	pos1h-=movew;
    	        	}else if(570>pos1w) {
    	        		pos1w+=movew;
    	        	}
    		}
    		else {
        	if(570>pos1w) {
        	pos1w+=movew;
        	}}//
    	}else if(20>runtotouch1+chkpt3.minuslocation) {
    		dif=runtotouch1+chkpt3.minuslocation-15;
    		distance=(570-pos1w)+((120+dif*90)-pos1h);
        	if(570>pos1w) {
        	pos1w+=movew;
        	}
        	else if(120+dif*90>pos1h ) {
        	pos1h+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(20==runtotouch1+chkpt3.minuslocation) {
    		distance=620-pos1h;
    		if(chkpt3.minuslocation==6) {
    			if(570>pos1w ) {
    	        	pos1w+=movew;
    	        	}else if(620<pos1h) {
    	        		pos1h+=movew;
    	        	}
    		}
    		else {
        	if(620>pos1h) {
        	pos1h+=movew;
        	}} //
    	}else if(25>runtotouch1+chkpt3.minuslocation) {
    		dif=runtotouch1+chkpt3.minuslocation-20;
    		distance=(620-pos1h)+pos1w-(570-dif*100);
        	if(620>pos1h) {
        	pos1h+=movew;
        	}
            else if(570-dif*100<pos1w ) {
            pos1w-=movew;        	
            }
        	else {
        		repaint();
        	}
//            else {
//            	if(pos1w-dif*100<pos1w) {
//            		pos1w-=movew;
//            	}
//        	}	
    	}else if(25==runtotouch1+chkpt3.minuslocation) {
    		distance=pos1w-90;
    		if(chkpt3.minuslocation==6) {
    			if(620>pos1h ) {
    				pos1h+=movew;
    	        	}else if(90<pos1w ) {
    	        		pos1w-=movew;
    	        	}
    		}
    		else {
        	if(90<pos1w ) {
        	pos1w-=movew;
        	}}
    	}else if(30>runtotouch1+chkpt3.minuslocation) {
    		dif=runtotouch1+chkpt3.minuslocation-25;
    		distance=(pos1w-70)+(pos1h-(620-dif*90));
        	if(90<pos1w ) {
        	pos1w-=movew;
        	}	
        	else if(620-dif*90<pos1h ) {
        	pos1h-=movew;
        	}
        	else {
        		repaint();
        	}
//        	else {
//            	if(pos1h-dif*90<pos1h ) {
//            		pos1h-=movew;
//            	}
//        	}
    	}else if(30==runtotouch1+chkpt3.minuslocation) {
    		distance=pos1h-140;
    		if(chkpt3.minuslocation==6) {
    			if(90<pos1w ) {
    	        	pos1w-=movew;
    	        	}else if(140<pos1h) {
    	        		pos1h-=movew;
    	        	}
    		}
    		else {
        	if(140<pos1h) {
        	pos1h-=movew;
        	}}
    	}
    	}
    	if(chkpt3.ppllo==1) {
    		int dif;
    	if(5>runtotouch2+chkpt3.minuslocation) {
    		distance=pos2w-(590-chkpt3.minuslocation*100);
        	if(590-chkpt3.minuslocation*100<pos2w ) {
        	pos2w-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(5==runtotouch2+chkpt3.minuslocation) {
    		distance=pos2w-70;
        	if(70<pos2w ) {
        	pos2w-=movew;
        	}
    	}else if(10>runtotouch2+chkpt3.minuslocation) {
    		dif=runtotouch2+chkpt3.minuslocation-5;
    		distance=(pos2w-70)+(pos2h-(620-dif*90));
        	if(70<pos2w ) {
        	pos2w-=movew;
        	}	
        	else if(620-dif*90<pos2h ) {
        	pos2h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(10==runtotouch2+chkpt3.minuslocation) {
    		distance=pos2h-140;    	
    		if(chkpt3.minuslocation==6) {
    			if(70<pos2w ) {
    	        	pos2w-=movew;
    	        	}else if(140<pos2h) {
    	        		pos2h-=movew;
    	        	}
    		}
    		else {
        	if(140<pos2h) {
        	pos2h-=movew;
        	}}//
    	}else if(15>runtotouch2+chkpt3.minuslocation) {
    		dif=runtotouch2+chkpt3.minuslocation-10;
    		distance=(pos2h-140)+((70+dif*100)-pos2w);
        	if(140<pos2h) {
        	pos2h-=movew;
        	}
        	else if(70+dif*110>pos2w ) {
        	pos2w+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(15==runtotouch2+chkpt3.minuslocation) {
    		distance=590-pos2w;
    		if(chkpt3.minuslocation==6) {
    			if(140<pos2h ) {
    				pos2h-=movew;
    	        	}else if(590>pos2w) {
    	        		pos2w+=movew;
    	        	}
    		}
    		else {
        	if(590>pos2w) {
        	pos2w+=movew;
        	}}//
    	}else if(20>runtotouch2+chkpt3.minuslocation) {
    		dif=runtotouch2+chkpt3.minuslocation-15;
    		distance=(590-pos2w)+((140+dif*90)-pos2h);
        	if(590>pos2w) {
        	pos2w+=movew;
        	}
        	else if(140+dif*90>pos2h ) {
        	pos2h+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(20==runtotouch2+chkpt3.minuslocation) {
    		distance=620-pos2h;
    		if(chkpt3.minuslocation==6) {
    			if(590>pos2w) {
    				pos2w+=movew;
    	        	}else if(620>pos2h) {
    	        		pos2h+=movew;
    	        	}
    		}
    		else {
        	if(620>pos2h) {
        	pos2h+=movew;
        	}}//
    	}else if(25>runtotouch2+chkpt3.minuslocation) {
    		dif=runtotouch2+chkpt3.minuslocation-20;
    		distance=(620-pos2h)+pos2w-(590-dif*100);
        	if(620>pos2h) {
        	pos2h+=movew;
        	}
            else if(590-dif*100<pos2w ) {
            pos2w-=movew;        	
            }
        	else {
        		repaint();
        	}
    	}else if(25==runtotouch2+chkpt3.minuslocation) {
    		distance=pos2w-70;
       		if(chkpt3.minuslocation==6) {
    			if(620>pos2h) {
    				pos2h+=movew;
    	        	}else if(70<pos2w) {
    	        		pos2w-=movew;
    	        	}
    		}
    		else {
        	if(70<pos2w ) {
        	pos2w-=movew;
        	}}
    	}else if(30>runtotouch2+chkpt3.minuslocation) {
    		dif=runtotouch2+chkpt3.minuslocation-25;
    		distance=(pos2w-70)+(pos2h-(620-dif*90));
        	if(70<pos2w ) {
        	pos2w-=movew;
        	}	
        	else if(620-dif*90<pos2h ) {
        	pos2h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(30==runtotouch2+chkpt3.minuslocation) {
    		distance=pos2h-140;    	
    		if(chkpt3.minuslocation==6) {
    			if(70<pos2w ) {
    	        	pos2w-=movew;
    	        	}else if(140<pos2h) {
    	        		pos2h-=movew;
    	        	}
    		}
    		else {
        	if(140<pos2h) {
        	pos2h-=movew;
        	}}//
    	}
    	}
    	if(chkpt3.ppllo==2) {
    		int dif;
    	if(5>runtotouch3+chkpt3.minuslocation) {
    		distance=pos3w-(570-chkpt3.minuslocation*100);
        	if(570-chkpt3.minuslocation*100<pos3w ) {
        	pos3w-=movew;
        	}	
        	else {
        		repaint();
        	}
    	}else if(5==runtotouch3+chkpt3.minuslocation) {
    		distance=pos3w-90;
        	if(90<pos3w ) {
        	pos3w-=movew;
        	}
    	}else if(10>runtotouch3+chkpt3.minuslocation) {
    		dif=runtotouch3+chkpt3.minuslocation-5;
    		distance=(pos3w-90)+(pos3h-(640-dif*100));
        	if(90<pos3w ) {
        	pos3w-=movew;
        	}	
        	else if(640-dif*100<pos3h ) {
        	pos3h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(10==runtotouch3+chkpt3.minuslocation) {
    		distance=pos3h-115;
    		if(chkpt3.minuslocation==6) {
    			if(90<pos3w ) {
    				pos3w-=movew;
    	        	}else if(115<pos3h) {
    	        		pos3h-=movew;
    	        	}
    		}
    		else {
        	if(115<pos3h) {
        	pos3h-=movew;
        	}}//
    	}else if(15>runtotouch3+chkpt3.minuslocation) {
    		dif=runtotouch3+chkpt3.minuslocation-10;
    		distance=(pos3h-115)+((90+dif*100)-pos3w);
        	if(115<pos3h) {
        	pos3h-=movew;
        	}
        	else if(90+dif*100>pos3w ) {
        	pos3w+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(15==runtotouch3+chkpt3.minuslocation) {
    		distance=570-pos3w;
    		if(chkpt3.minuslocation==6) {
    			if(115<pos3h ) {
    				pos3h-=movew;
    	        	}else if(570>pos3w) {
    	        		pos3w+=movew;
    	        	}
    		}
    		else {
        	if(570>pos3w) {
        	pos3w+=movew;
        	}}//
    	}else if(20>runtotouch3+chkpt3.minuslocation) {
    		dif=runtotouch3+chkpt3.minuslocation-15;
    		distance=(570-pos3w)+((115+dif*100)-pos3h);
        	if(570>pos3w) {
        	pos3w+=movew;
        	}
        	else if(115+dif*100>pos3h ) {
        	pos3h+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(20==runtotouch3+chkpt3.minuslocation) {
    		distance=640-pos3h;
    		if(chkpt3.minuslocation==6) {
    			if(590>pos3w ) {
    				pos3w+=movew;
    	        	}else if(640>pos3h) {
    	        		pos3h+=movew;
    	        	}
    		}
    		else {
        	if(640>pos3h) {
        	pos3h+=movew;
        	}}//
    	}else if(25>runtotouch3+chkpt3.minuslocation) {
    		dif=runtotouch3+chkpt3.minuslocation-20;
    		distance=(640-pos3h)+pos3w-(570-dif*100);
        	if(640>pos3h) {
        	pos3h+=movew;
        	}
            else if(570-dif*100<pos3w ) {
            pos3w-=movew;        	
            }	
        	else {
        		repaint();
        	}
    	}else if(25==runtotouch3+chkpt3.minuslocation) {
    		distance=pos3w-90;
    		if(chkpt3.minuslocation==6) {
    			if(640>pos3h ) {
    				pos3h+=movew;
    	        	}else if(90<pos3w) {
    	        		pos3w-=movew;
    	        	}
    		}
    		else {
        	if(90<pos3w ) {
        	pos3w-=movew;
        	}}
    	}else if(30>runtotouch3+chkpt3.minuslocation) {
    		dif=runtotouch3+chkpt3.minuslocation-25;
    		distance=(pos3w-90)+(pos3h-(640-dif*100));
        	if(90<pos3w ) {
        	pos3w-=movew;
        	}	
        	else if(640-dif*100<pos3h ) {
        	pos3h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(30==runtotouch3+chkpt3.minuslocation) {
    		distance=pos3h-115;
    		if(chkpt3.minuslocation==6) {
    			if(90<pos3w ) {
    				pos3w-=movew;
    	        	}else if(115<pos3h) {
    	        		pos3h-=movew;
    	        	}
    		}
    		else {
        	if(115<pos3h) {
        	pos3h-=movew;
        	}}//
    	}
    	}
    	if(chkpt3.ppllo==3) {
    		int dif;
    	if(5>runtotouch4+chkpt3.minuslocation) {
    		distance=pos4w-(590-chkpt3.minuslocation*100);
        	if(590-chkpt3.minuslocation*100<pos4w ) {
        	pos4w-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(5==runtotouch4+chkpt3.minuslocation) {
    		distance=pos4w-70;
        	if(70<pos4w ) {
        	pos4w-=movew;
        	}
    	}else if(10>runtotouch4+chkpt3.minuslocation) {
    		dif=runtotouch4+chkpt3.minuslocation-5;
    		distance=(pos4w-70)+(pos4h-(640-dif*100));
        	if(70<pos4w ) {
        	pos4w-=movew;
        	}	
        	else if(640-dif*100<pos4h ) {
        	pos4h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(10==runtotouch4+chkpt3.minuslocation) {
    		distance=pos4h-115;
    		if(chkpt3.minuslocation==6) {
    			if(70<pos4w ) {
    				pos4w-=movew;
    	        	}else if(115<pos4h) {
    	        		pos4h-=movew;
    	        	}
    		}
    		else {
        	if(115<pos4h) {
        	pos4h-=movew;
        	}}//
    	}else if(15>runtotouch4+chkpt3.minuslocation) {
    		dif=runtotouch4+chkpt3.minuslocation-10;
    		distance=(pos4h-115)+((70+dif*100)-pos4w);
        	if(115<pos4h) {
        	pos4h-=movew;
        	}
        	else if(70+dif*100>pos4w ) {
        	pos4w+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(15==runtotouch4+chkpt3.minuslocation) {
    		distance=590-pos4w;
    		if(chkpt3.minuslocation==6) {
    			if(115<pos4h ) {
    				pos4h-=movew;
    	        	}else if(590>pos4w) {
    	        		pos4w+=movew;
    	        	}
    		}
    		else {
        	if(590>pos4w) {
        	pos4w+=movew;
        	}}
    	}else if(20>runtotouch4+chkpt3.minuslocation) {
    		dif=runtotouch4+chkpt3.minuslocation-15;
    		distance=(590-pos4w)+((115+dif*105)-pos4h);
        	if(590>pos4w) {
        	pos4w+=movew;
        	}
        	else if(115+dif*105>pos4h ) {
        	pos4h+=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(20==runtotouch4+chkpt3.minuslocation) {
    		distance=640-pos4h;
    		if(chkpt3.minuslocation==6) {
    			if(590>pos4w ) {
    				pos4w+=movew;
    	        	}else if(640>pos4h) {
    	        		pos4h+=movew;
    	        	}
    		}
    		else {
        	if(640>pos4h) {
        	pos4h+=movew;
        	}}
    	}else if(25>runtotouch4+chkpt3.minuslocation) {
    		dif=runtotouch4+chkpt3.minuslocation-20;
    		distance=(640-pos4h)+pos4w-(590-dif*100);
        	if(640>pos4h) {
        	pos4h+=movew;
        	}
            else if(590-dif*100<pos4w ) {
            pos4w-=movew;        	
            }	
        	else {
        		repaint();
        	}
    	}else if(25==runtotouch4+chkpt3.minuslocation) {
    		distance=pos4w-70;
    		if(chkpt3.minuslocation==6) {
    			if(640>pos4h) {
    				pos4h+=movew;
    	        	}else if(70<pos4w) {
    	        		pos4w-=movew;
    	        	}
    		}
    		else {
        	if(70<pos4w ) {
        	pos4w-=movew;
        	}}
    	}else if(30>runtotouch4+chkpt3.minuslocation) {
    		dif=runtotouch4+chkpt3.minuslocation-25;
    		distance=(pos4w-70)+(pos4h-(640-dif*100));
        	if(70<pos4w ) {
        	pos4w-=movew;
        	}	
        	else if(640-dif*100<pos4h ) {
        	pos4h-=movew;
        	}
        	else {
        		repaint();
        	}
    	}else if(30==runtotouch4+chkpt3.minuslocation) {
    		distance=pos4h-115;
    		if(chkpt3.minuslocation==6) {
    			if(70<pos4w ) {
    				pos4w-=movew;
    	        	}else if(115<pos4h) {
    	        		pos4h-=movew;
    	        	}
    		}
    		else {
        	if(115<pos4h) {
        	pos4h-=movew;
        	}}//
    	}
    	}
    }

    public void loadlocation() {
    	if(pos1w==0&&pos1h==0) {
    		if(pos1w==0) {
    			pos1w=570;
    		}
    	if(chkpt3.c[0].location<6 ) {
    		pos1h=620;
    		if(chkpt3.c[0].location==1) {pos1w=460;}
    		if(chkpt3.c[0].location==2) {pos1w=370;}
    		if(chkpt3.c[0].location==3) {pos1w=270;}
    		if(chkpt3.c[0].location==4) {pos1w=170;}
    	}
    	if(4<chkpt3.c[0].location&&chkpt3.c[0].location<11) {
    		pos1w=90;
    		if(chkpt3.c[0].location==6) {pos1h=535;} //450
    		if(chkpt3.c[0].location==7) {pos1h=440;}
    		if(chkpt3.c[0].location==8) {pos1h=345;}
    		if(chkpt3.c[0].location==9) {pos1h=250;}
    	}
    	if(9<chkpt3.c[0].location&&chkpt3.c[0].location<16) {
    		pos1h=140;
    		if(chkpt3.c[0].location==11) {pos1w=170;}
    		if(chkpt3.c[0].location==12) {pos1w=270;}
    		if(chkpt3.c[0].location==13) {pos1w=370;}
    		if(chkpt3.c[0].location==14) {pos1w=460;}
    	}
    	if(14<chkpt3.c[0].location) {
    		pos1w=570;
    		if(chkpt3.c[0].location==16) {pos1h=250;}  //450
    		if(chkpt3.c[0].location==17) {pos1h=345;}
    		if(chkpt3.c[0].location==18) {pos1h=440;}
    		if(chkpt3.c[0].location==19) {pos1h=535;}
    	}
    	}
    	//
    	if(pos2w==0&&pos2h==0) {
    		if(pos2w==0) {
    			pos2w=590;
    		}
    	if(chkpt3.c[1].location<6) {
    		pos2h=620;
    		if(chkpt3.c[1].location==1) {pos2w=485;}
    		if(chkpt3.c[1].location==2) {pos2w=395;}
    		if(chkpt3.c[1].location==3) {pos2w=295;}
    		if(chkpt3.c[1].location==4) {pos2w=195;}
    	}
    	if(4<chkpt3.c[1].location&&chkpt3.c[1].location<11) {
    		pos2w=70;
    		if(chkpt3.c[1].location==6) {pos2h=535;}  //450
    		if(chkpt3.c[1].location==7) {pos2h=440;}
    		if(chkpt3.c[1].location==8) {pos2h=345;}
    		if(chkpt3.c[1].location==9) {pos2h=250;}
    	}
    	if(9<chkpt3.c[1].location&&chkpt3.c[1].location<16) {
    		pos2h=140;
    		if(chkpt3.c[1].location==11) {pos2w=195;}
    		if(chkpt3.c[1].location==12) {pos2w=295;}
    		if(chkpt3.c[1].location==13) {pos2w=395;}
    		if(chkpt3.c[1].location==14) {pos2w=485;}
    	}
    	if(14<chkpt3.c[1].location) {
    		pos2w=590;
    		if(chkpt3.c[1].location==16) {pos2h=250;}
    		if(chkpt3.c[1].location==17) {pos2h=345;}
    		if(chkpt3.c[1].location==18) {pos2h=440;}
    		if(chkpt3.c[1].location==19) {pos2h=535;}
    	}
    	}
    	//
    	if(pos3w==0&&pos3h==0) {
    		if(pos3w==0) {
    			pos3w=570;
    		}
    	if(chkpt3.c[2].location<6) {
    		pos3h=640;
    		if(chkpt3.c[2].location==1) {pos3w=460;}
    		if(chkpt3.c[2].location==2) {pos3w=370;}
    		if(chkpt3.c[2].location==3) {pos3w=270;}
    		if(chkpt3.c[2].location==4) {pos3w=170;}
    	}
    	if(4<chkpt3.c[2].location&&chkpt3.c[2].location<11) {
    		pos3w=90;
    		if(chkpt3.c[2].location==6) {pos3h=515;} //435
    		if(chkpt3.c[2].location==7) {pos3h=420;}
    		if(chkpt3.c[2].location==8) {pos3h=325;}
    		if(chkpt3.c[2].location==9) {pos3h=230;}
    	}
    	if(9<chkpt3.c[2].location&&chkpt3.c[0].location<16) {
    		pos3h=115;
    		if(chkpt3.c[2].location==11) {pos3w=170;}
    		if(chkpt3.c[2].location==12) {pos3w=270;}
    		if(chkpt3.c[2].location==13) {pos3w=370;}
    		if(chkpt3.c[2].location==14) {pos3w=460;}
    	}
    	if(14<chkpt3.c[2].location) {
    		pos3w=570;
    		if(chkpt3.c[2].location==16) {pos3h=230;}
    		if(chkpt3.c[2].location==17) {pos3h=325;}
    		if(chkpt3.c[2].location==18) {pos3h=420;}
    		if(chkpt3.c[2].location==19) {pos3h=515;}
    	}
    	}
    	//
    	if(pos4w==0&&pos4h==0) {
    		if(pos4w==0) {
    			pos4w=590;
    		}
    	if(chkpt3.c[3].location<6) {
    		pos4h=640;
    		if(chkpt3.c[3].location==1) {pos4w=485;}
    		if(chkpt3.c[3].location==2) {pos4w=395;}
    		if(chkpt3.c[3].location==3) {pos4w=295;}
    		if(chkpt3.c[3].location==4) {pos4w=195;}
    	}
    	if(4<chkpt3.c[3].location&&chkpt3.c[2].location<11) {
    		pos4w=70;
    		if(chkpt3.c[3].location==6) {pos4h=515;}
    		if(chkpt3.c[3].location==7) {pos4h=420;}
    		if(chkpt3.c[3].location==8) {pos4h=325;}
    		if(chkpt3.c[3].location==9) {pos4h=230;}
    	}
    	if(9<chkpt3.c[3].location&&chkpt3.c[0].location<16) {
    		pos4h=115;
    		if(chkpt3.c[3].location==11) {pos4w=195;}
    		if(chkpt3.c[3].location==12) {pos4w=295;}
    		if(chkpt3.c[3].location==13) {pos4w=395;}
    		if(chkpt3.c[3].location==14) {pos4w=485;}
    	}
    	if(14<chkpt3.c[3].location) {
    		pos4w=590;
    		if(chkpt3.c[3].location==16) {pos4h=230;}
    		if(chkpt3.c[3].location==17) {pos4h=325;}
    		if(chkpt3.c[3].location==18) {pos4h=420;}
    		if(chkpt3.c[3].location==19) {pos4h=515;}
    	}
    	}
    	//
    }
    public void loadlabel() {
    	if(chkpt3.land[0].owner!=0) {png_text[chkpt3.land[0].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[0].owner));}
    	if(chkpt3.land[1].owner!=0) {png_text[chkpt3.land[1].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[1].owner));}
    	if(chkpt3.land[2].owner!=0) {png_text[chkpt3.land[2].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[2].owner));}
    	if(chkpt3.land[3].owner!=0) {png_text[chkpt3.land[3].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[3].owner));}
    	if(chkpt3.land[4].owner!=0) {png_text[chkpt3.land[4].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[4].owner));}
    	if(chkpt3.land[5].owner!=0) {png_text[chkpt3.land[5].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[5].owner));}
    	if(chkpt3.land[6].owner!=0) {png_text[chkpt3.land[6].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[6].owner));}
    	if(chkpt3.land[7].owner!=0) {png_text[chkpt3.land[7].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[7].owner));}
    	if(chkpt3.land[8].owner!=0) {png_text[chkpt3.land[8].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[8].owner));}
    	if(chkpt3.land[9].owner!=0) {png_text[chkpt3.land[9].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[9].owner));}
    	if(chkpt3.land[10].owner!=0) {png_text[chkpt3.land[10].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[10].owner));}
    	if(chkpt3.land[11].owner!=0) {png_text[chkpt3.land[11].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[11].owner));}
    	if(chkpt3.land[12].owner!=0) {png_text[chkpt3.land[12].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[12].owner));}
    	if(chkpt3.land[13].owner!=0) {png_text[chkpt3.land[13].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[13].owner));}
    	if(chkpt3.land[14].owner!=0) {png_text[chkpt3.land[14].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[14].owner));}
    	if(chkpt3.land[15].owner!=0) {png_text[chkpt3.land[15].PLACE_NUMBER].setText(Integer.toString(chkpt3.land[15].owner));}


    }
    public void resetlabel() {
    	png_text[19].setText("");
    	png_text[1].setText("");
    	png_text[2].setText("");
    	png_text[3].setText("");
    	png_text[4].setText("");
    	png_text[16].setText("");
    	png_text[6].setText("");
    	png_text[7].setText("");
    	png_text[8].setText("");
    	png_text[9].setText("");
    	png_text[17].setText("");
    	png_text[11].setText("");
    	png_text[12].setText("");
    	png_text[13].setText("");
    	png_text[14].setText("");
    	png_text[18].setText("");
        png_text[1].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[2].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[3].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[4].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[6].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[7].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[8].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[9].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[11].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[12].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[13].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[14].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[16].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[17].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[18].setFont(new Font("verdana", Font.PLAIN,80));
        png_text[19].setFont(new Font("verdana", Font.PLAIN,80));
    }
    public void getsleeptime(int dicenumber) {
    	update();
    	times=(long)(distance/movew);
    	if(dicenumber<=3) {
        	new java.util.Timer().schedule( 
        	        new java.util.TimerTask() {
        	            public void run() {
        	            	loadbtn.setEnabled(true);
        	            	savebtn.setEnabled(true);
        	            	Dicebtn.setEnabled(true);
        	            	timeout=true; 
        	            	LandController();
        	            	imgmove();
        	            	updatemoney();
        	            }
        	        }, 
        	        2100 
        	);
    		sleept= (long)(2000*movew/distance);
    	}else {
        	new java.util.Timer().schedule( 
        	        new java.util.TimerTask() {
        	            public void run() {
        	            	loadbtn.setEnabled(true);
        	            	savebtn.setEnabled(true);
        	            	Dicebtn.setEnabled(true);
        	            	timeout=true; 
        	            	LandController();
        	            	imgmove();
        	            	updatemoney();
        	            }
        	        }, 
        	        3100   	        
        	);
    		sleept= (long)(3000*movew/distance);	
    	}   	
    }
    public void Landbeopen() {
    	if(chkpt3.c[chkpt3.ppllo].location!=5&&chkpt3.c[chkpt3.ppllo].location!=10&&chkpt3.c[chkpt3.ppllo].location!=15&&chkpt3.c[chkpt3.ppllo].location!=20&&
 	    	   chkpt3.c[chkpt3.ppllo].location!=0&&chkpt3.c[chkpt3.ppllo].location!=25&&chkpt3.c[chkpt3.ppllo].location!=30) {
    		recond=chkpt3.c[chkpt3.ppllo].location;
        	if(recond>0 &&recond<5) {
        		recond--;
        	}
        	else if(recond>5&&recond<10) {
        		recond-=2;
        	}
        	else if(recond>10&&recond<15) {
        		recond-=3;
        	}
        	else if(recond>15&&recond<20) {
        		recond-=4;
        	}
        	else if(recond>20&&recond<25) {
        		recond-=5;
        	}   
    		openland=true;
    	}
    }

    public void LandController() {
    	passbystart();
    	if(openland) {

		if(chkpt3.land[recond].owner==0 && chkpt3.c[chkpt3.ppllo].money>chkpt3.land[recond].LAND_PRICE) {
        	int result=JOptionPane.showConfirmDialog(A1063338_GUI.this,"Do you want to buy"+'\n'+"$"+chkpt3.land[recond].LAND_PRICE,"Whether to buy !!",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        	if(result==JOptionPane.YES_OPTION) {
        		chkpt3.land[recond].owner=chkpt3.ppllo+1;
        		chkpt3.c[chkpt3.ppllo].money-=chkpt3.land[recond].LAND_PRICE;
        		png_text[chkpt3.c[chkpt3.ppllo].location].setText(Integer.toString(chkpt3.ppllo+1));
        	    money_text[chkpt3.ppllo+1].setText("     "+Integer.toString(chkpt3.c[chkpt3.ppllo].money));
        	    repaint();
        	}
		}else if(chkpt3.land[recond].owner!=0 && chkpt3.land[recond].owner!=chkpt3.ppllo+1) {
    		JOptionPane.showMessageDialog(A1063338_GUI.this,"This place is owned by Character"+chkpt3.land[recond].owner+", Character"+(chkpt3.ppllo+1)+" needs to pay a toll of $"+chkpt3.land[recond].TOLLS,"Pay tolls !!",JOptionPane.PLAIN_MESSAGE);
    		chkpt3.c[chkpt3.ppllo].money-=chkpt3.land[recond].TOLLS;
    	    money_text[chkpt3.ppllo+1].setText("     "+Integer.toString(chkpt3.c[chkpt3.ppllo].money));
    		chkpt3.c[chkpt3.land[recond].owner-1].money+=chkpt3.land[recond].TOLLS;
    	    money_text[chkpt3.land[recond].owner].setText("     "+Integer.toString(chkpt3.c[chkpt3.land[recond].owner-1].money));
    	    
		}
    	}
    	openland=false;
    	
    }
    
    public void paint(Graphics g)
    {
    	super.paint(g);
      try
      {
    	img1=ImageIO.read(new File(chkpt3.c[0].IMAGE_FILENAME));
    	img2=ImageIO.read(new File(chkpt3.c[1].IMAGE_FILENAME));
    	img3=ImageIO.read(new File(chkpt3.c[2].IMAGE_FILENAME));
    	img4=ImageIO.read(new File(chkpt3.c[3].IMAGE_FILENAME));
      }catch(Exception e){}
        g.drawImage(img1,(int)pos1w,(int)pos1h,null);
        g.drawImage(img2,(int)pos2w,(int)pos2h,null);
        g.drawImage(img3,(int)pos3w,(int)pos3h,null);
        g.drawImage(img4,(int)pos4w,(int)pos4h,null);
      }
    public void imgmove() {
    	if(who_dice==5) {
    		who_dice=1;
			if(chkpt3.c[0].status==0&&chkpt3.c[1].status==0&&chkpt3.c[2].status==0&&chkpt3.c[3].status==0) {
				chkpt3.c[0].status+=1;
				chkpt3.c[1].status+=1;
				chkpt3.c[2].status+=1;
				chkpt3.c[3].status+=1;				
			}
			if(chkpt3.c[who_dice-1].status==0) {
			who_dice++;
			}
			roundnum++;
			ROUND.setText("ROUND "+Integer.toString(roundnum));
			chkpt3.randt.set(0,Integer.toString(who_dice));
			TurnCHR.setText("Character "+(who_dice));		
    	}else {
		if(chkpt3.c[who_dice-1].status==0) {
		who_dice++;
    	TurnCHR.setText("Character "+(who_dice));		
		}else {
		TurnCHR.setText("Character "+(who_dice));
		}
    	}
    }
    
//    pos1w=WIDTH-125,pos1h=HEIGHT-130,pos2w=WIDTH-100,pos2h=HEIGHT-130,pos3w=WIDTH-125,pos3h=HEIGHT-105,
//			  pos4w=WIDTH-100,pos4h=HEIGHT-105;
    public void actionPerformed(ActionEvent e){

        String actionCommand = e.getActionCommand();
        if(actionCommand.equals("Save")) {
        	try {
        		chkpt3.Load("Character.txt","Land.txt");
        		FileWriter fw =new FileWriter("./Character.txt",false);
        		BufferedWriter bw = new BufferedWriter(fw);
        		bw.write("Round: "+Integer.toString(roundnum)+",Turn: "+Integer.toString(who_dice)+"\r\n");
        		bw.close();
        		FileWriter fw2 =new FileWriter("./Land.txt",false);
        		BufferedWriter bw2 = new BufferedWriter(fw2);
        		bw2.write("LOCATION_NUMBER, owner"+"\r\n");
        		bw2.close();
        		chkpt3.Save("Character.txt","Land.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else if (actionCommand.equals("Load")) {
        	dispose();
        	resetlabel();
        	pos1w=0;pos1h=0;pos2w=0;pos2h=0;pos3w=0;pos3h=0;
      			  pos4w=0;pos4h=0;
            chkpt3.Load_to_Character();
            try {
				chkpt3.specialLoad();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            loadlocation();
            A1063338_GUI reopen = new A1063338_GUI();
            reopen.pack();
            reopen.repaint();
            reopen.setVisible(true);
        }else if(e.getSource()==Dicebtn) {
        loadbtn.setEnabled(false);
        savebtn.setEnabled(false);
        Dicebtn.setEnabled(false);
    	distance=0;
    	runtotouch1=chkpt3.c[0].location;
    	runtotouch2=chkpt3.c[1].location;
    	runtotouch3=chkpt3.c[2].location;
    	runtotouch4=chkpt3.c[3].location;

    	chkpt3.Random();   	
    	who_dice++;	
    	
    	
    	getsleeptime(chkpt3.minuslocation);
    	passbystart();
    	timeout=false; 
    	Landbeopen();
    	Thread imgmove = new Thread() {    		    		
    		public void run() {      			
                while (true) {
                	update();
                	repaint(); 

                   try {
                      Thread.sleep(sleept); 
                   } catch (Exception e) {
                       System.err.print("Unexcepted error");
                    }
               	if(timeout) {
            		break;            		
            	}    	
                }
                
             }       		
    	};
    	imgmove.start();
    	passbystart();
    	display_dicenum_text.setText(Integer.toString(chkpt3.minuslocation));
        }

        else if	(actionCommand.equals("Exit")) {
            System.exit(0);      	
        }
    }
    public void passbystart() {
		if(chkpt3.c[0].location>19) {
			chkpt3.c[0].location-=20;
			chkpt3.c[0].money+=2000;
		}
		if(chkpt3.c[1].location>19) {
			chkpt3.c[1].location-=20;
			chkpt3.c[1].money+=2000;

		}
		if(chkpt3.c[2].location>19) {
			chkpt3.c[2].location-=20;
			chkpt3.c[2].money+=2000;

		}
		if(chkpt3.c[3].location>19) {
			chkpt3.c[3].location-=20;
			chkpt3.c[3].money+=2000;

		}
    }
    public void updatemoney() {
    	money_text[1].setText("     "+Integer.toString(chkpt3.c[0].money));
		money_text[2].setText("     "+Integer.toString(chkpt3.c[1].money));
		money_text[3].setText("     "+Integer.toString(chkpt3.c[2].money));
    	money_text[4].setText("     "+Integer.toString(chkpt3.c[3].money));
    }

}