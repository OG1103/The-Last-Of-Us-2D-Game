package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import engine.Game;
import model.characters.Hero;

public class selectHero extends JFrame implements ActionListener {
	Game game;
	public static String name="";
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	JButton b7;
	JButton b8;
	JLabel l1= new JLabel("Please Choose");
	JLabel label;
	JPanel panel;
	JLabel cover;
	ImageIcon img;
	Music sound1=new Music();
	String sound_track;
	selectHero(){
		sound_track="selectplayersound.wav";
		sound1.setFile(sound_track);
		sound1.play();
		
		label=new JLabel("Select HERO");
		label.setFont(new Font("MV BOLI",Font.BOLD,15));
		label.setBounds(200, 0, 200, 50);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		Border border= BorderFactory.createLineBorder(Color.cyan,3);
		label.setBorder(border);
		
		panel = new JPanel();
		panel.setBounds(50, 100,500 , 400);
		panel.setBackground(Color.red);
		panel.setLayout(new GridLayout(4,2));
		
		Border border1 = BorderFactory.createLineBorder(Color.GREEN);
		Border border2 = BorderFactory.createLineBorder(Color.RED);
		Border border3 = BorderFactory.createLineBorder(Color.CYAN);
		 b1= new JButton("FIGHTER: Joel Miller");
		 b1.setBackground(Color.black);
		 b1.setForeground(Color.RED);
		 b1.setBorder(border2);
		 b1.setFocusable(false);
		 
		b2= new JButton("MEDIC: Ellie Williams");
		 b2.setBackground(Color.black);
		 b2.setForeground(Color.GREEN);
		 b2.setBorder(border1);
		 b2.setFocusable(false);
		 
		 b3= new JButton("EXPLORER: Tess");
		 b3.setBackground(Color.black);
		 b3.setForeground(Color.CYAN);
		 b3.setBorder(border3);
		 b3.setFocusable(false);
		 
		 b4= new JButton("EXPLORER: Riley Abel");
		 b4.setBackground(Color.black);
		 b4.setForeground(Color.CYAN);
		 b4.setBorder(border3);
		 b4.setFocusable(false);
		 
		 b5= new JButton("EXPLORER: Tommy Miller");
		 b5.setBackground(Color.black);
		 b5.setForeground(Color.CYAN);
		 b5.setBorder(border3);
		 b5.setFocusable(false);
		 
		 b6= new JButton("MEDIC: Bill");
		 b6.setBackground(Color.black);
		 b6.setForeground(Color.GREEN);
		 b6.setBorder(border1);
		 b6.setFocusable(false);
		 
		 b7= new JButton("FIGHTER: David");
		 b7.setBackground(Color.black);
		 b7.setForeground(Color.RED);
		 b7.setBorder(border2);
		 b7.setFocusable(false);
		 
		 b8= new JButton("MEDIC: Henry Burell");
		 b8.setBackground(Color.black);
		 b8.setForeground(Color.GREEN);
		 b8.setBorder(border1);
		 b8.setFocusable(false);
		//JLabel l1= new JLabel("Please Choose");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		
		img= new ImageIcon("selectinghero.jpg");
		cover=new JLabel(img);
		cover.setSize(600,600);
		cover.setOpaque(true);
		
		
		
		this.setTitle(" Last of Us");
		this.setSize(600,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		this.add(label);
		this.add(panel);
		this.add(cover);
		
		
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			name="Joel Miller";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b2) {
			name="Ellie Williams";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b3) {
			name="Tess";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b4) {
			name="Riley Abel";
			this.dispose();
			new TheGame();
		}if(e.getSource()==b5) {
			name="Tommy Miller";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b6) {
			name="Bill";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b7) {
			name="David";
			this.dispose();
			new TheGame();
		}
		if(e.getSource()==b8) {
			name="Henry Burell";
			this.dispose();
			new TheGame();
		}
		
		
	}

	public String getName() {
		return name;
	}

	
	

}
