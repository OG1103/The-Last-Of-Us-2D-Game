package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.border.Border;
import javax.swing.*;
import engine.Game;

public class HeroTypes extends JFrame implements ActionListener{
	
	JPanel Medic;
	JPanel Fighter;
	JPanel Explorer;
	JLabel label;
	JLabel background;
	JLabel medicIcon;
	JLabel fighterIcon;
	JLabel explorerIcon;
	ImageIcon m;
	ImageIcon e;
	ImageIcon f;
	JButton next;
	HeroTypes(){
		m=new ImageIcon("medic.png");
		e=new ImageIcon("explorer.png");
		f=new ImageIcon("fighter.png");
		medicIcon = new JLabel(m);
		fighterIcon = new JLabel(f);
		explorerIcon= new JLabel(e);
		
		Medic = new JPanel();
		Fighter= new JPanel();
		Explorer= new JPanel();
		next= new JButton("Next");
		label=new JLabel("TYPES OF HEROS");
		label.setFont(new Font("MV BOLI",Font.BOLD,15));
		label.setBounds(200, 0, 200, 50);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		Border border= BorderFactory.createLineBorder(Color.cyan,3);
		label.setBorder(border);
		
		Medic.setBounds(40, 120,150 , 250);
		Medic.setBackground(Color.green);
		Fighter.setBounds(215, 120,150 , 250);
		Fighter.setBackground(Color.red);
		Explorer.setBounds(400, 120,150 , 250);
		Explorer.setBackground(Color.CYAN);		
		
		next.addActionListener(this);
		next.setBounds(250,450,100,75);
		next.setFont(new Font("MV BOLI",Font.BOLD,15));
		next.setFocusable(false);
		next.setBackground(Color.black);
		next.setForeground(Color.green);
		Border border1= BorderFactory.createLineBorder(Color.green,3);
		next.setBorder(border1);
		
		
		JLabel medic = new JLabel("MEDIC  ");
		JLabel m1= new JLabel("  Ellie Willer:");
		JLabel m2= new JLabel("HP:110 DMG=15 ACT=6");
		JLabel m3= new JLabel("BILL:");
		JLabel m4= new JLabel("HP:100 DMG=10 ACT=7");
		JLabel m5= new JLabel("Henry Bure:");
		JLabel m6= new JLabel("HP:105 DMG=15 ACT=6");
		JLabel m7= new JLabel("Special Action:");
		JLabel m8= new JLabel("Can Heal any Hero");
		medic.setForeground(Color.white);
		Medic.add(medic);
		Medic.add(m1);
		Medic.add(m2);
		Medic.add(m3);
		Medic.add(m4);
		Medic.add(m5);
		Medic.add(m6);
		Medic.add(m7);
		Medic.add(m8);
		Medic.add(medicIcon);
		
		JLabel fighter= new JLabel("FIGHTER");
		fighter.setForeground(Color.black);
		Fighter.add(fighter);
		JLabel f1= new JLabel("  Joel Miller:");
		JLabel f2= new JLabel("HP:140 DMG=30 ACT=5");
		JLabel f3= new JLabel("David:");
		JLabel f4= new JLabel("HP:150 DMG=35 ACT=4");
		JLabel f5= new JLabel("Special Action:");
		JLabel f6= new JLabel("Attack with no Actions");
		
		Fighter.add(f1);
		Fighter.add(f2);
		Fighter.add(f3);
		Fighter.add(f4);
		Fighter.add(f5);
		Fighter.add(f6);
		Fighter.add(fighterIcon);
		
		JLabel explorer = new JLabel("EXPLORER");
		explorer.setForeground(Color.white);
		Explorer.add(explorer);
		JLabel e1= new JLabel("  TESS:");
		JLabel e2= new JLabel("HP:80 DMG=20 ACT=6");
		JLabel e3= new JLabel("Riley Abel:");
		JLabel e4= new JLabel("HP:90 DMG=25 ACT=5");
		JLabel e5= new JLabel("Tommy Miller:");
		JLabel e6= new JLabel("HP:95 DMG=25 ACT=5");
		JLabel e7= new JLabel("Special Action:");
		JLabel e8= new JLabel("Can see the whole Map");
		
		Explorer.add(e1);
		Explorer.add(e2);
		Explorer.add(e3);
		Explorer.add(e4);
		Explorer.add(e5);
		Explorer.add(e6);
		Explorer.add(e7);
		Explorer.add(e8);
		Explorer.add(explorerIcon);

		
		ImageIcon img = new ImageIcon("types3.jpg");
		background= new JLabel(img);
		background.setSize(600,600);
		this.setTitle("Last of Us");
		this.setSize(600,600);
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(Medic);
		this.add(Fighter);
		this.add(Explorer);
		this.add(next);
		this.add(label);
		this.add(background);
		ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
		this.setIconImage(frameIcon.getImage());
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next) {
			this.dispose();
			new selectHero();
			
		}
		
	}

}
