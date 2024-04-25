package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import Graphics.selectHero;

import javax.swing.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.*;

public class TheGame extends JFrame implements ActionListener{
	JPanel frame;
	JPanel functions;
	JPanel buttonMap;
	JButton attack;
	JButton cure;
	JButton special;
	JButton[][] map;
	Game game;
	Hero h;
	JProgressBar HP;
	JLabel heroName;
	JButton endturn;
	JButton up;
	JButton down;
	JButton left;
	JButton right;
	ImageIcon fighterPic;
	ImageIcon expPic;
	ImageIcon medicPic;
	ImageIcon zombiePic;
	ImageIcon collect;
	Music sound=new Music();
	String sound_track;
	Music pickup=new Music();
	String pick;
	JLabel Info;
	
	TheGame(){
		sound_track="spwansound.wav";
		pick="pickupsound.wav";
		pickup.setFile(pick);
		sound.setFile(sound_track);
		sound.play();
		
	frame = new JPanel();
	frame.setBounds(0, 0, 1000,700 );
	frame.setBackground(Color.blue);
	frame.setOpaque(true);
	frame.setLayout(new GridLayout(15,15));
	JLabel hp= new JLabel("  HP!!");
	hp.setSize(50,50);
	hp.setFont(new Font("MV BOLI",Font.BOLD,15));
	hp.setForeground(Color.WHITE);

	
	 //ImageIcon right1 = new ImageIcon("right.png");
	 
	functions = new JPanel();
	functions.setBounds(0, 700,1000, 100);
	functions.setBackground(Color.black);
	functions.setOpaque(true);
	attack= new JButton("Attack!");
	attack.addActionListener(this);
	cure= new JButton("Cure!!");
	cure.addActionListener(this);
	special= new JButton("USE SPECIAL!!");
	special.addActionListener(this);
	endturn= new JButton("END TURN!");
	endturn.addActionListener(this);
	left= new JButton("Left");
	right= new JButton("Right");
	up= new JButton("UP");
	down= new JButton("Down");
	left.setFont(new Font("MV BOLI",Font.BOLD,15));
	right.setFont(new Font("MV BOLI",Font.BOLD,15));
	up.setFont(new Font("MV BOLI",Font.BOLD,15));
	down.setFont(new Font("MV BOLI",Font.BOLD,15));
	left.addActionListener(this);
	right.addActionListener(this);
	up.addActionListener(this);
	down.addActionListener(this);
	
	 fighterPic= new ImageIcon("fighter.png");
	 medicPic= new ImageIcon("medic.png");
	 expPic= new ImageIcon("explorer.png");
	 zombiePic=new ImageIcon("zombie.png");
	 collect= new ImageIcon("vaccine.png");
	
	game = new Game();
	try {
		Game.loadHeroes("Heroes.csv");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	map= new JButton[15][15];
	for(int i=0;i<15;i++) {
		for(int j=0;j<15;j++) {
			map[i][j]=new JButton("x");
			map[i][j].setSize(100, 150);
			map[i][j].setHorizontalTextPosition(JButton.CENTER);
			map[i][j].addActionListener(this);
			map[i][j].setBackground(Color.red);
			map[i][j].setFocusable(false);
			frame.add(map[i][j]);
		}
	}
	
		
	for(int i=0;i<game.availableHeroes.size();i++) {
		try {
			if(selectHero.name.equals(game.availableHeroes.get(i).getName())) {
				h=game.availableHeroes.get(i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	game.startGame(h);
	heroName= new JLabel(h.getName()+"   ");
	Info= new JLabel("AttackDmg:"+h.getAttackDmg()+"Actions:"+h.getActionsAvailable()+"supplies: "+h.getSupplyInventory().size()+" vaccines"+h.getVaccineInventory().size());
	Info.setFont(new Font("MV BOLI",Font.BOLD,15));
	heroName.setFont(new Font("MV BOLI",Font.BOLD,30));
	HP= new JProgressBar();
	HP.setSize(75,10);
	HP.setMaximum(h.getMaxHp());
	HP.setValue(h.getMaxHp());
	HP.setStringPainted(true);
	HP.setForeground(Color.green);
	HP.setFont(new Font("MV BOLI",Font.BOLD,10));
	HP.setBackground(Color.black);
	
	updateMap();
	
	
	
	this.setFocusable(true);
	
	this.setTitle(" Last of Us");
	this.setSize(1000,820);
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setLayout(null);
	this.setLocationRelativeTo(null);
	ImageIcon frameIcon = new ImageIcon("Frame Icon.jpg");
	this.setIconImage(frameIcon.getImage());
	functions.add(left);
	functions.add(right);
	functions.add(up);
	functions.add(down);
	functions.add(heroName);
	functions.add(endturn);
	functions.add(attack);
	functions.add(cure);
	functions.add(special);
	functions.add(HP);
	functions.add(hp);
	functions.add(Info);
	this.add(frame);
	this.add(functions);
	
	JOptionPane.showMessageDialog(null,"RULE 1:ONLY A MEDIC CAN SELECT A HERO AS HIS TARGET", "RULE 1", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog(null,"RULE 2:WHEN CLICKING ON A HERO YOU CAN PLAY WITH IT OR VIEW ITS CHARACTERSITICS", "RULE 2", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog(null,"RULE 3:WHEN ENDING A TURN ALL HERO'S ACTIONS ARE RESET, BUT A NEW ZOMBIE IS SPAWNED", "RULE 3", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog(null,"RULE 4: TO ATTACK YOU NEED TO SET A TARGET BY CLICKING ON THE ZOMBIE", "RULE 4", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog(null,"RULE 5: CURING A ZOMBIE, REPLACES THE ZOMBIE WITH A HERO", "RULE 1", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog(null,"LETS BEGIN", "LETS BEGIN", JOptionPane.INFORMATION_MESSAGE);
	
	
	
	
	
	
	
	
	
	
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(e.getSource()==map[i][j]) {
					if(game.map[i][j].isVisible()) {
					if (game.map[i][j] instanceof CharacterCell) {
						CharacterCell c = (CharacterCell)game.map[i][j];
						if(c.getCharacter() instanceof Hero) {
							Hero h1= (Hero)c.getCharacter(); 
							if(h1 instanceof Fighter) {
								JOptionPane.showMessageDialog(null, "TYPE:Fighter, "+"Name:"+h1.getName()+", Attack Dmg:"+h1.getAttackDmg()+" ,HP:"+h1.getCurrentHp()+ ",Actions available: "+h1.getActionsAvailable()+ ", Supplies:"+h1.getSupplyInventory().size()+ ", Vaccines:"+h1.getVaccineInventory().size(), "INFO", JOptionPane.INFORMATION_MESSAGE);
								if(h instanceof Medic) {
									int Answer= JOptionPane.showConfirmDialog(null,"Do you want to select Target?","Target",JOptionPane.YES_NO_OPTION);
									if(Answer==0) {
										JOptionPane.showMessageDialog(null, "Target Selected", "Target", JOptionPane.INFORMATION_MESSAGE);
										h.setTarget(h1);;
										updateMap();
									}
										else {
											if(!h1.getName().equals(h.getName())) {
												int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
												if(Answer1==0) {
													JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
													h=h1;
													updateMap();
													sound.setFile(null);
													sound.setFile("changeplayer.wav");
													sound.play();
												
												}
											}
										}
											
										}
								else if(!h1.getName().equals(h.getName())) {
								int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
								if(Answer1==0) {
									JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
									h=h1;
									updateMap();
									sound.setFile(null);
									sound.setFile("changeplayer.wav");
									sound.play();
								
								}
							}
							}
				
							if(h1 instanceof Medic) {
								JOptionPane.showMessageDialog(null, "TYPE:Medic, "+"Name:"+h1.getName()+", Attack Dmg:"+h1.getAttackDmg()+" ,HP:"+h1.getCurrentHp()+ ",Actions available: "+h1.getActionsAvailable()+ ", Supplies:"+h1.getSupplyInventory().size()+ ", Vaccines:"+h1.getVaccineInventory().size(), "INFO", JOptionPane.INFORMATION_MESSAGE);
								if(h instanceof Medic) {
									int Answer= JOptionPane.showConfirmDialog(null,"Do you want to select Target?","Target",JOptionPane.YES_NO_OPTION);
									if(Answer==0) {
										JOptionPane.showMessageDialog(null, "Target Selected", "Target", JOptionPane.INFORMATION_MESSAGE);
										h.setTarget(h1);;
										updateMap();
									}
										else {
											if(!h1.getName().equals(h.getName())) {
												int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
												if(Answer1==0) {
													JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
													h=h1;
													updateMap();
													sound.setFile(null);
													sound.setFile("changeplayer.wav");
													sound.play();
												
												}
											}
										}
											
										}   
								else if(!h1.getName().equals(h.getName())) {
								int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
								if(Answer1==0) {
									JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
									h=h1;
									updateMap();
									sound.setFile(null);
									sound.setFile("changeplayer.wav");
									sound.play();
										
								}
							}
									
							}
								
							
							if(h1 instanceof Explorer) {
								
								JOptionPane.showMessageDialog(null, "TYPE:Explorer, "+"Name:"+h1.getName()+", Attack Dmg:"+h1.getAttackDmg()+" ,HP:"+h1.getCurrentHp()+ ",Actions available: "+h1.getActionsAvailable()+ ", Supplies:"+h1.getSupplyInventory().size()+ ", Vaccines:"+h1.getVaccineInventory().size(), "INFO", JOptionPane.INFORMATION_MESSAGE);
								
								if(h instanceof Medic) {
									int Answer= JOptionPane.showConfirmDialog(null,"Do you want to select Target?","Target",JOptionPane.YES_NO_OPTION);
									if(Answer==0) {
										JOptionPane.showMessageDialog(null, "Target Selected", "Target", JOptionPane.INFORMATION_MESSAGE);
										h.setTarget(h1);;
										updateMap();
									}
										else {
											if(!h1.getName().equals(h.getName())) {
												int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
												if(Answer1==0) {
													JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
													h=h1;
													updateMap();
													sound.setFile(null);
													sound.setFile("changeplayer.wav");
													sound.play();
												
												}
											}
										}
											
										}
								
								else if(!h.getName().equals(h1.getName())) {
								int Answer1= JOptionPane.showConfirmDialog(null,"Do you want to play with this Hero???","SELECT",JOptionPane.YES_NO_OPTION);
								if(Answer1==0) {
									JOptionPane.showMessageDialog(null, "PLAYER CHANGED", "player", JOptionPane.INFORMATION_MESSAGE);
									h=h1;
									updateMap();
									sound.setFile(null);
									sound.setFile("changeplayer.wav");
									sound.play();
									
								}
								}
							}
							
					}
						else if(c.getCharacter() instanceof Zombie) {
							JOptionPane.showMessageDialog(null, "TYPE:Zombie, "+"Name:"+c.getCharacter().getName()+" ,HP:"+c.getCharacter().getCurrentHp(), "INFO", JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(null, "Target Selected", "Target", JOptionPane.INFORMATION_MESSAGE);
							h.setTarget(c.getCharacter());
							
							updateMap();
						}
					
				}
				
			}
			}
		}
		}
		
		if(e.getSource()==attack) {
			try {
				h.attack();
				updateMap();
				sound.setFile(null);
				sound.setFile("attacksound.wav");
				sound.play();
				JOptionPane.showMessageDialog(null, "Target Attacked", "ATTACK", JOptionPane.INFORMATION_MESSAGE);
				if(h.getTarget().getCurrentHp()==0) {
					sound.setFile(null);
					sound.setFile("targetdeadsound.wav");
					sound.play();
				}
				h.setTarget(null);
				
			} catch (NotEnoughActionsException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if(e.getSource()==cure) {
			try {
				h.cure();
				sound.setFile(null);
				sound.setFile(sound_track);
				sound.play();
				updateMap();
				JOptionPane.showMessageDialog(null, "Hero Spwaned", "Cure", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==special) {
			try {
				h.useSpecial();
				JOptionPane.showMessageDialog(null, "SPECIAL ACTION ACTIVATED", "Special Action", JOptionPane.INFORMATION_MESSAGE);
				updateMap();
				if(h instanceof Medic) {
					sound.setFile(null);
					sound.setFile("medspecialsound.wav");
					sound.play();
				}
				else if(h instanceof Fighter) {
					sound.setFile(null);
					sound.setFile("fighspecialsound.wav");
					sound.play();
					
				}
				else if(h instanceof Explorer) {
					sound.setFile(null);
					sound.setFile("expspecialsound.wav");
					sound.play();
					
				}
			} catch (NoAvailableResourcesException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==endturn) {
			try {
				game.endTurn();
				JOptionPane.showMessageDialog(null,"Turn has Ended, Restarting a new one.", "End Turn", JOptionPane.INFORMATION_MESSAGE);
				updateMap();
				
			} catch (NotEnoughActionsException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==left) {
			try {
				int hp1= h.getCurrentHp();
				int s1= h.getSupplyInventory().size();
				int v1=h.getVaccineInventory().size();
				h.move(Direction.LEFT);
				sound.setFile(null);
				sound.setFile("movesound.wav");
				sound.play();
				int hp2=h.getCurrentHp();
				int s2= h.getSupplyInventory().size();
				int v2=h.getVaccineInventory().size();
				updateMap();
				if(s2==s1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Supply", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(v2==v1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Vaccine", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(hp1 != hp2) {
					sound.setFile(null);
					sound.setFile("trapsound.wav");
					sound.play();
					JOptionPane.showMessageDialog(null,"You walked into a TrapCell", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				
			} catch (MovementException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==right) {
			try {
				
				int hp1= h.getCurrentHp();
				int s1= h.getSupplyInventory().size();
				int v1=h.getVaccineInventory().size();
				h.move(Direction.RIGHT);
				sound.setFile(null);
				sound.setFile("movesound.wav");
				sound.play();
				int hp2=h.getCurrentHp();
				int s2= h.getSupplyInventory().size();
				int v2=h.getVaccineInventory().size();
				updateMap();
				if(hp1 != hp2) {
					sound.setFile(null);
					sound.setFile("trapsound.wav");
					sound.play();
					JOptionPane.showMessageDialog(null,"You walked into a TrapCell", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(s2==s1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Supply", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(v2==v1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Vaccine", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==up) {
			try {
				
				int hp1= h.getCurrentHp();
				int s1= h.getSupplyInventory().size();
				int v1=h.getVaccineInventory().size();
				h.move(Direction.DOWN);
				sound.setFile(null);
				sound.setFile("movesound.wav");
				sound.play();
				int hp2=h.getCurrentHp();
				int s2= h.getSupplyInventory().size();
				int v2=h.getVaccineInventory().size();
				updateMap();
				if(hp1 != hp2) {
					sound.setFile(null);
					sound.setFile("trapsound.wav");
					sound.play();
					JOptionPane.showMessageDialog(null,"You walked into a TrapCell", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(s2==s1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Supply", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(v2==v1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Vaccine", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==down) {
			try {
				int hp1= h.getCurrentHp();
				int s1= h.getSupplyInventory().size();
				int v1=h.getVaccineInventory().size();
				h.move(Direction.UP);
				sound.setFile(null);
				sound.setFile("movesound.wav");
				sound.play();
				int s2= h.getSupplyInventory().size();
				int v2=h.getVaccineInventory().size();
				int hp2=h.getCurrentHp();
				updateMap();
				if(hp1 != hp2) {
					sound.setFile(null);
					sound.setFile("trapsound.wav");
					sound.play();
					JOptionPane.showMessageDialog(null,"You walked into a TrapCell", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(s2==s1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Supply", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(v2==v1+1) {
					pickup.setFile(null);
					pickup.setFile(pick);
					pickup.play();
					JOptionPane.showMessageDialog(null,"You Collected a Vaccine", "WOAHH!!", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (MovementException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(game.checkWin()) {
			this.dispose();
			new win();
		}
		else if(game.checkGameOver()) {
			this.dispose();
			new gameOver();
		}
		
	
	}
	
	public void updateMap() {

		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				map[i][j].setText("");
				map[i][j].setIcon(null);
				if(game.map[i][j].isVisible()==false) {
					map[i][j].setText("x");
					map[i][j].setHorizontalTextPosition(JButton.CENTER);
					map[i][j].setBackground(Color.red);
					
				}
				else if(game.map[i][j] instanceof CharacterCell) {
					CharacterCell c= (CharacterCell)game.map[i][j];
					if(c.getCharacter() instanceof Hero) {
						Hero h= (Hero)c.getCharacter();
						if(h instanceof Fighter) {
							map[i][j].setIcon(fighterPic); 
							//set image
							map[i][j].setBackground(Color.yellow);
						}
						else if(h instanceof Medic) {
							map[i][j].setIcon(medicPic); 
							//set image
							map[i][j].setBackground(Color.yellow);
						}
						else if(h instanceof Explorer) {
							map[i][j].setIcon(expPic); 
							//set image
							map[i][j].setBackground(Color.yellow);
						}
						
					}
					else if(c.getCharacter() instanceof Zombie) {
						map[i][j].setIcon(zombiePic);
						map[i][j].setBackground(Color.yellow);
						//set image
					}
					else {
						map[i][j].setBackground(Color.yellow);
					}
						
				}
				else if(game.map[i][j] instanceof CollectibleCell) {
					map[i][j].setIcon(collect);
					map[i][j].setBackground(Color.yellow);
					
				}
				else if(game.map[i][j] instanceof TrapCell){
					map[i][j].setBackground(Color.yellow);
					
				}
			}
		}
		HP.setMaximum(h.getMaxHp());
		HP.setValue(h.getCurrentHp());
		heroName.setText(h.getName()+"  ");
		Info.setText(" AttackDmg:"+h.getAttackDmg()+"  Actions: "+h.getActionsAvailable()+"  supplies: "+h.getSupplyInventory().size()+"  vaccines: "+h.getVaccineInventory().size());
		
		if(h instanceof Fighter) {
			heroName.setForeground(Color.red);
			Info.setForeground(Color.RED);
		}
		else if(h instanceof Medic) {
			heroName.setForeground(Color.green);
			Info.setForeground(Color.green);
		}
		else if(h instanceof Explorer) {
			heroName.setForeground(Color.CYAN);
			Info.setForeground(Color.cyan);
		}
		
		this.setFocusable(true);
		
	}
}
