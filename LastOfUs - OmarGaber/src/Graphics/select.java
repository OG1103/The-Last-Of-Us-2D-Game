package Graphics;

import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;

import javax.swing.*;

public class select extends JFrame implements ActionListener {
	JLabel background;
	JButton select;
	JButton exit;
	static Music se = new Music();
	String sound_track;
	
	select(){
	sound_track="zombiessound2.wav";
	se.setFile(sound_track);
	se.play();
	this.setLayout(null);
	this.setTitle("Welcome to the Last of Us");
	select= new JButton("START");
	select.setBounds(450,450, 100, 100);
	select.setFocusable(false);
	select.setBackground(Color.green);
	select.setFont(new Font("Serif",Font.BOLD,15));
	select.addActionListener(this);
	this.add(select);
	exit= new JButton("EXIT");
	exit.setBounds(50,450, 100, 100);
	exit.setFocusable(false);
	exit.setBackground(Color.red);
	exit.addActionListener(this);
	exit.setFont(new Font("Serif",Font.BOLD,15));
	this.add(exit);
	
	ImageIcon img = new ImageIcon("cover.jpg");
	background= new JLabel(img);
	background.setSize(600,600);
	this.add(background);
	this.setSize(600,600);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
	this.setIconImage(frameIcon.getImage());
	
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			this.dispose();
		}
		else if(e.getSource()==select) {
			this.dispose();
			new HeroTypes();
		}
		
	}
	public static void stop() {
		se.setFile(null);
		try {
			se.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
