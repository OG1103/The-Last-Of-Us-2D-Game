package Graphics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

public class Start extends JFrame{
	Music se = new Music();
	String sound_track;
	public Start(){
		sound_track="welcomesound.wav";
		se.setFile(sound_track);
		se.play();
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());		
		ImageIcon frameImage= new ImageIcon("Last of us cover.jpg");
		this.getContentPane().setBackground(Color.green);
		JLabel label = new JLabel();
		label.setBounds(0,0,600,600);
		label.setIcon(frameImage);
		label.setText("WELCOME TO THE LAST OF US");
		label.setFont(new Font("MV BOLI",Font.ITALIC,20));
		label.setForeground(Color.RED);		
		label.setOpaque(true);
		this.setTitle(" Last of Us");
		this.setSize(600,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(label);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int Answer= JOptionPane.showConfirmDialog(null,"ARE YOU ABOVE 10 YEARS OLD???","woah",JOptionPane.YES_NO_OPTION);
			if(Answer==0) {
				this.dispose();
				se.setFile("loadingsound.wav");
				se.play();
				new progress();
			}
			else {
				se.setFile("holdonsound.wav");
				se.play();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.dispose();
				//dispose frame
			}
	
		
		
		
		
			
		
		
		
		
	}
	
	
	

}
