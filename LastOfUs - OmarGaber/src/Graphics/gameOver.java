package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

public class gameOver extends JFrame implements ActionListener{
	
	JLabel gameover;
	ImageIcon img;
	JButton play;
	JButton leave;
	Music sound=new Music();
	String sound_track;
	gameOver(){
		
		sound_track="gameoversound.wav";
		sound.setFile(sound_track);
		sound.play();
		gameover= new JLabel();
		img=new ImageIcon("gameover.jpg");
		gameover.setIcon(img);
		gameover.setSize(600,600);
		gameover.setOpaque(true);
		
		Border border1 = BorderFactory.createLineBorder(Color.GREEN);
		Border border2 = BorderFactory.createLineBorder(Color.RED);
		play = new JButton("PLAY!!");
		play.setBounds(450,450, 100, 100);
		play.setFocusable(false);
		play.setBackground(Color.BLACK);
		play.setForeground(Color.GREEN);
		play.setFont(new Font("Serif",Font.BOLD,15));
		play.setBorder(border1);
		play.addActionListener(this);
		leave= new JButton("EXIT !!");
		leave.setBounds(50,450, 100, 100);
		leave.setFocusable(false);
		leave.setBackground(Color.black);
		leave.setForeground(Color.RED);
        leave.setBorder(border2);
		leave.addActionListener(this);
		leave.setFont(new Font("Serif",Font.BOLD,15));
		
		
		
		
		this.setTitle("Game Over");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(play);
		this.add(leave);
		this.add(gameover);
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==play) {
			sound.setFile(null);
			try {
				sound.stop();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			new select();
			
		}
		if(e.getSource()==leave) {
			this.dispose();			
		}
		
	}

}
