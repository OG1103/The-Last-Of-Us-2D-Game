package Graphics;

import java.awt.Color;
import Graphics.select;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class win extends JFrame implements ActionListener{
	JButton play;
	JButton leave;
	JLabel win;
	ImageIcon img;
	Music sound=new Music();
	String sound_track;
	win(){
		
		sound_track="winsound.wav";
		sound.setFile(sound_track);
		sound.play();
		win= new JLabel();
		img=new ImageIcon("win.jpg");
		win.setIcon(img);
		win.setSize(600,600);
		//win.setOpaque(true);
		Border border1 = BorderFactory.createLineBorder(Color.GREEN);
		Border border2 = BorderFactory.createLineBorder(Color.RED);
		
		play = new JButton("PLAY!!");
		play.setBounds(450,450, 100, 100);
		play.setFocusable(false);
		play.setBackground(Color.black);
		play.setForeground(Color.green);
		play.setFont(new Font("Serif",Font.BOLD,15));
		play.addActionListener(this);
		play.setBorder(border1);
		
		
		leave= new JButton("EXIT!!");
		leave.setBounds(50,450, 100, 100);
		leave.setFocusable(false);
		leave.setBackground(Color.BLACK);
		leave.setForeground(Color.RED);
		leave.addActionListener(this);
		leave.setFont(new Font("Serif",Font.BOLD,15));
		leave.setBorder(border2);
		
		this.setTitle("Game Over");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(play);
		this.add(leave);
		this.add(win);
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==play) {
			this.dispose();
			new select();
			
		}
		if(e.getSource()==leave) {
			this.dispose();			
		}
		
	}

}
