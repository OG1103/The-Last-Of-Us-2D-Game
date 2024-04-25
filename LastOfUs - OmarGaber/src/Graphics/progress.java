package Graphics;

import javax.swing.ImageIcon;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class progress extends JFrame {
	JProgressBar bar= new JProgressBar();
	progress(){
		this.setTitle("Welcome to the Last of Us");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());
		this.getContentPane().setBackground(Color.CYAN);		
		JLabel label = new JLabel("JUST A MOMENT!!!");
		label.setBounds(200, 0, 200, 50);
		label.setFont(new Font("MV BOLI",Font.BOLD,18));
	    label.setBackground(Color.GRAY);
		label.setForeground(Color.red);
		label.setOpaque(true);
		Border border = BorderFactory.createLineBorder(Color.black, 4);
		label.setBorder(border);
		
		bar.setValue(0);
		bar.setBounds(0,250,600,50);
		bar.setStringPainted(true);
		bar.setForeground(Color.RED);
		bar.setFont(new Font("MV BOLI",Font.BOLD,25));
		bar.setBackground(Color.black);
		this.add(bar);
		this.add(label);
		fill();
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new select();
		this.dispose();
		
	}
	public void fill() {
		int counter =0;
		while(counter<=100) {
			bar.setValue(counter);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter+=10;
		}
		bar.setString("WELCOME");
	}

}
