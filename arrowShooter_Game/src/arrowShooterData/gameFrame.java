package arrowShooterData;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class gameFrame{
	
	private JFrame mainFrame;
	private JPanel shootPanel;
	private JButton tryAgain;
	private JButton quitGame;
	private JButton playGameButton;
	private JButton shootBullet;
	private JButton shootGrenade;
	private JButton activateShield;
	private JButton store;
	private JButton[] colButtons;
	private JLabel[][] mapLabel;
	private JLabel scoreLabel;
	private JLabel coinLabel;
	private JLabel bulletLabel;
	private JLabel grenadeLabel;
	private JLabel shieldLabel;
	private JLabel healthBarLabel;
	private JLabel freezePowerLabel;
	private JLabel gameOverLabel;
	private JLabel redKillsLabel;
	private JLabel finalScoreLabel;
	private JLabel arrowShooterLogoLabel;
	private JLabel shooterMoveInstructions;
	private KeyListener moveMapDownListener;
	
	private boolean isMoveMapCurrent;
	private boolean isMoveMap;
	private boolean isStore;
	private boolean isQuitGame;
	private boolean isMoveInstructions;
	private boolean isAbilities;
	private boolean isFreeze;
	private boolean isCol;
	private boolean isShootBullet;
	private boolean isShootGrenade;
	private boolean isShield;
	private boolean isColButtons;
	private int bullets;
	private int upgradeBulletPrice;
	private int bulletPower;
	private int buyBulletPrice;
	private int grenades;
	private int upgradeGrenadePrice;
	private int grenadePower;
	private int buyGrenadePrice;
	private int shields;
	private int upgradeShieldPrice;
	private int shieldPower;
	private int buyShieldPrice;
	private int score;
	private int coins;
	private String[][] shootingMap;
	private String[] specialThings;
	private int updateMapNum;
	private int shieldCurrentPower;
	private int health;
	private int movePlayer;
	private int movePlayerX;
	private int eliminateRedCount;
	private int freezePower;
	private int bombKills;
	private int laserCol;
	private int redKills;

	public gameFrame() {
		
		int widthSize = 600;
		int heightSize = 500;
		
		ImageIcon arrowShooterImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shootCharacterImage.png");
		mainFrame = new JFrame("Arrow Shooter");
		mainFrame.setVisible(true);
		mainFrame.setSize(widthSize, heightSize);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setIconImage(arrowShooterImage.getImage());
		
		KeyListener moveCharacterListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
						
			}
		
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					if(movePlayer - 1 >= 0 && shootingMap[shootingMap.length-1][movePlayer-1].equals("*") == false && isShield == false) {
						movePlayer--;
						movePlayerX -= 20;
						shootingMap[shootingMap.length-1][movePlayer] = "^";
						shootingMap[shootingMap.length-1][movePlayer+1] = " ";
						if(isMoveInstructions == true) {
							isMoveInstructions = false;
							shooterMoveInstructions.hide();
							shootPanel.remove(shooterMoveInstructions);
						}
						updateMapLabel();
					}
				}
					
				else if(e.getKeyCode() == KeyEvent.VK_D) {
					if(movePlayer + 1 <= shootingMap[0].length-1 && shootingMap[shootingMap.length-1][movePlayer+1].equals(" ") && isShield == false) {
						movePlayer++;
						movePlayerX += 20;
						shootingMap[shootingMap.length-1][movePlayer] = "^";
						shootingMap[shootingMap.length-1][movePlayer-1] = " ";
						if(isMoveInstructions == true) {
							isMoveInstructions = false;
							shooterMoveInstructions.hide();
							shootPanel.remove(shooterMoveInstructions);
						}
						updateMapLabel();
					}
				}
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
					
			}};
			mainFrame.addKeyListener(moveCharacterListener);
		
			moveMapDownListener = new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
							
				}
			
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_S) {
						if(isStore == false && isMoveMap == true && isMoveMapCurrent == true) {
							updateTheMap();
							updateMapLabel();
						}
					}
				}
			
				@Override
				public void keyReleased(KeyEvent e) {
						
				}
			};
		
		isMoveInstructions = false;
		isMoveMap = false;
		
		shootPanel = new JPanel();
		shootPanel.setLayout(null);
		mainFrame.add(shootPanel);
		shootPanel.setBackground(Color.WHITE);
		
		ImageIcon arrowShooterLogoImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\ArrowShooterLogo.png");
		arrowShooterLogoLabel = new JLabel(arrowShooterLogoImage);
		shootPanel.add(arrowShooterLogoLabel);
		arrowShooterLogoLabel.setBounds(130, 10, 300, 300);
		
		shooterMoveInstructions = new JLabel("Press A and D To Move");
		shooterMoveInstructions.setBounds(270, 240, 150, 150);
		shooterMoveInstructions.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		playGameButton = new JButton("Start Game");
		shootPanel.add(playGameButton);
		playGameButton.setBounds(225, 220, 100, 30);
		playGameButton.setForeground(Color.WHITE);
		playGameButton.setBorder(null);
		playGameButton.setBackground(new Color(46, 92, 184));
		playGameButton.setFocusable(false);
		
		playGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameButton.hide();
				arrowShooterLogoLabel.hide();
				shootPanel.remove(arrowShooterLogoLabel);
				shootPanel.remove(playGameButton);
				shootPanel.add(shooterMoveInstructions);
				shooterMoveInstructions.show();
				isMoveInstructions = true;
				startGame();
			}
		});
		
		playGameButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				playGameButton.setBounds(224, 219, 102, 32);
				playGameButton.setBackground(new Color(31, 61, 122));
			}
			public void mouseExited(MouseEvent e) {
				playGameButton.setBounds(225, 220, 100, 30);
				playGameButton.setBackground(new Color(46, 92, 184));
			}
		});
	}
	
	public void startGame() {
		coins = 500;
		bullets = 15;
		upgradeBulletPrice = 50;
		bulletPower = 1;
		buyBulletPrice = 50;
		grenades = 0;
		upgradeGrenadePrice = 75;
		grenadePower = 1;
		buyGrenadePrice = 100;
		shields = 0;
		upgradeShieldPrice = 100;
		shieldPower = 2;
		shieldCurrentPower = shieldPower;
		buyShieldPrice = 125;
		score = 0;
		health = 100;
		movePlayer = 4;
		movePlayerX = 330;
		eliminateRedCount = 0;
		freezePower = 5;
		bombKills = 0;
		laserCol = -1;
		redKills = 0;
		
		isMoveMapCurrent = false;
		isStore = false;
		isQuitGame = true;
		isCol = false;
		isAbilities = false;
		isFreeze = false;
		isShootBullet = false;
		isShootGrenade = false;
		isShield = false;
		isColButtons = false;
		shootingMap = new String[10][9];
		mapLabel = new JLabel[shootingMap.length][shootingMap[0].length];
		specialThings = new String[6];
		
		for(int i = 0; i < specialThings.length; i++) {
			switch(i) {
			case 0:
				specialThings[i] = "G"; 																					// Grenade
				break;
			case 1:
				specialThings[i] = "C"; 																					// Coins
				break;
			case 2:
				specialThings[i] = "B"; 																					// Bullets
				break;
			case 3:
				specialThings[i] = "Q"; 																					// Bomb
				break;
			case 4:
				specialThings[i] = "S"; 																					// Shield
				break;
			case 5:
				specialThings[i] = "+";
				break;
			}
		}
		
		store = new JButton("Store");
		shootPanel.add(store);
		store.setBounds(90, 280, 75, 30);
		store.setForeground(Color.WHITE);
		store.setBackground(new Color(179, 119, 0));
		store.setBorder(null);
		store.setFocusable(false);
		
		shootBullet = new JButton("Use Bullet");
		shootPanel.add(shootBullet);
		shootBullet.setBounds(80, 160, 100, 30);
		shootBullet.setForeground(new Color(80, 80, 48));
		shootBullet.setBorder(null);
		shootBullet.setBackground(new Color(194, 194, 163));
		shootBullet.setFocusable(false);
		
		shootGrenade = new JButton("Use Grenade");
		shootPanel.add(shootGrenade);
		shootGrenade.setBounds(75, 200, 109, 30);
		shootGrenade.setEnabled(false);
		shootGrenade.setBorder(null);
		shootGrenade.setBackground(new Color(226, 226, 208));
		shootGrenade.setFocusable(false);
		
		activateShield = new JButton("Use Shield");
		shootPanel.add(activateShield);
		activateShield.setBounds(80, 240, 100, 30);
		activateShield.setEnabled(false);
		activateShield.setBorder(null);
		activateShield.setBackground(new Color(226, 226, 208));
		activateShield.setFocusable(false);
		
		tryAgain = new JButton("Try Again");
		tryAgain.setBounds(100, 255, 100, 30);
		tryAgain.setForeground(Color.WHITE);
		tryAgain.setBorder(null);
		tryAgain.setBackground(new Color(38, 38, 38));
		tryAgain.setFocusable(false);
		
		tryAgain.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				tryAgain.setBackground(Color.WHITE);
				tryAgain.setBorder(new LineBorder(new Color(38, 38, 38)));
				tryAgain.setForeground(new Color(38, 38, 38));
			}
			public void mouseExited(MouseEvent e) {
				tryAgain.setBorder(null);
				tryAgain.setBackground(new Color(38, 38, 38));
				tryAgain.setForeground(Color.WHITE);
			}
		});
		
		quitGame = new JButton("Quit Game");
		shootPanel.add(quitGame);
		quitGame.setBounds(5, 425, 85, 30);
		quitGame.setBorder(null);
		quitGame.setBackground(new Color(230, 0, 0));
		quitGame.setForeground(Color.WHITE);
		quitGame.setFocusable(false);
		
		quitGame.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(isQuitGame == true) {
					quitGame.setBackground(new Color(128, 0, 0));
				}
			}
			public void mouseExited(MouseEvent e) {
				if(isQuitGame == true) {
					quitGame.setBackground(new Color(230, 0, 0));
				}
			}
		});
		
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitGame.hide();
				if(health == 0) {
					tryAgain.hide();
					gameOverLabel.hide();
					redKillsLabel.hide();
					finalScoreLabel.hide();
					shootPanel.remove(gameOverLabel);
					shootPanel.remove(finalScoreLabel);
					shootPanel.remove(redKillsLabel);
					shootPanel.remove(tryAgain);
				}
				shootBullet.hide();
				shootGrenade.hide();
				activateShield.hide();
				store.hide();
				scoreLabel.hide();
				coinLabel.hide();
				bulletLabel.hide();
				grenadeLabel.hide();
				shieldLabel.hide();
				healthBarLabel.hide();
				if(isMoveInstructions == true) {
					isMoveInstructions = false;
					shooterMoveInstructions.hide();
					shootPanel.remove(shooterMoveInstructions);
				}
				if(isFreeze == true) {
					freezePowerLabel.hide();
					shootPanel.remove(freezePowerLabel);
				}
				shootPanel.remove(shootBullet);
				shootPanel.remove(shootGrenade);
				shootPanel.remove(activateShield);
				shootPanel.remove(store);
				shootPanel.remove(scoreLabel);
				shootPanel.remove(coinLabel);
				shootPanel.remove(bulletLabel);
				shootPanel.remove(grenadeLabel);
				shootPanel.remove(shieldLabel);
				shootPanel.remove(healthBarLabel);
				for(int i = 0; i < mapLabel.length; i++) {
					for(int j = 0; j < mapLabel[0].length; j++) {
						shootPanel.remove(mapLabel[i][j]);
					}
				}
				for(int i = 0; i < colButtons.length; i++) {
					colButtons[i].hide();
					shootPanel.remove(colButtons[i]);
				}
				shootPanel.removeKeyListener(moveMapDownListener);
				shootPanel.remove(quitGame);
				shootPanel.add(playGameButton);
				shootPanel.add(arrowShooterLogoLabel);
				arrowShooterLogoLabel.show();
				playGameButton.show();
			}
		});
		
		colButtons = new JButton[9];
		int colx = 252;
		
		for(int i = 0; i < colButtons.length; i++) {
			colButtons[i] = new JButton(i + 1 + "");
			shootPanel.add(colButtons[i]);
			colButtons[i].setBounds(colx, 370, 15, 25);
			colButtons[i].setBorder(null);
			colButtons[i].setEnabled(false);
			colButtons[i].setFocusable(false);
			colButtons[i].setBackground(new Color(245, 245, 240));
			colx += 20;
		}
		
		startingMap();
		
		int z = 150;
		for(int i = 0; i < shootingMap.length; i++) {
			int l = 250;
			for(int j = 0; j < shootingMap[0].length; j++) {
				if(shootingMap[i][j].equals("*")) {
					ImageIcon redGuys = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\smallRed.png");
					mapLabel[i][j] = new JLabel(redGuys);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("G")) {
					ImageIcon grenadeImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\grenadeImage.png");
					mapLabel[i][j] = new JLabel(grenadeImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("B")) {
					ImageIcon bulletImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\bulletImage.png");
					mapLabel[i][j] = new JLabel(bulletImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("S")) {
					ImageIcon shieldImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shieldImage.png");
					mapLabel[i][j] = new JLabel(shieldImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("C")) {
					ImageIcon coinImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\coinImage.png");
					mapLabel[i][j] = new JLabel(coinImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("Q")) {
					ImageIcon bombImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\bombImage.png");
					mapLabel[i][j] = new JLabel(bombImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("+")) {
					ImageIcon healImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\healImage.png");
					mapLabel[i][j] = new JLabel(healImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else if(shootingMap[i][j].equals("^")) {
					ImageIcon shootCharacImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shootCharacterImage.png");
					mapLabel[i][j] = new JLabel(shootCharacImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
				}
				else {
					mapLabel[i][j] = new JLabel(shootingMap[i][j]);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(l, z, 20, 20);
					mapLabel[i][j].setForeground(Color.blue);
					mapLabel[i][j].setFont(new Font("Open Sans", Font.PLAIN, 18));
				}
				l += 20;
				if(l >= 420) {z += 20;}
			}
		}
		
		scoreLabel = new JLabel();
		scoreLabel.setText("Score: " + score);
		shootPanel.add(scoreLabel);
		scoreLabel.setBounds(300, 60, 125, 125);
		scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		coinLabel = new JLabel();
		coinLabel.setText("Coins: " + coins);
		shootPanel.add(coinLabel);
		coinLabel.setBounds(450, 150, 100, 100);
		coinLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		bulletLabel = new JLabel();
		bulletLabel.setText("Bullets: " + bullets);
		shootPanel.add(bulletLabel);
		bulletLabel.setBounds(450, 175, 100, 100);
		bulletLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		grenadeLabel = new JLabel();
		grenadeLabel.setText("Grenades: " + grenades);
		shootPanel.add(grenadeLabel);
		grenadeLabel.setBounds(450, 200, 100, 100);
		grenadeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		shieldLabel = new JLabel();
		shieldLabel.setText("Shields: " + shields);
		shootPanel.add(shieldLabel);
		shieldLabel.setBounds(450, 225, 100, 100);
		shieldLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		healthBarLabel = new JLabel();
		healthBarLabel.setText("Health: " + health);
		shootPanel.add(healthBarLabel);
		healthBarLabel.setBounds(450, 125, 100, 100);
		healthBarLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		healthBarLabel.setForeground(new Color(0, 77, 26));
		
		shootBullet.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(isShootBullet == false && bullets > 0) {
					shootBullet.setBorder(new LineBorder(new Color(112, 112, 67)));
					shootBullet.setBounds(79, 159, 102, 32);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(isShootBullet == false && bullets > 0) {
					shootBullet.setBorder(null);
					shootBullet.setBounds(80, 160, 100, 30);
				}
			}
		});
		
		shootBullet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shootBullet.setBorder(new LineBorder(new Color(112, 112, 67)));
				shootBullet.setBackground(new Color(226, 226, 208));
				isShootBullet = true;
				isShootGrenade = false;
				isColButtons = true;
				shootBullet.setEnabled(false);
				if(grenades > 0 && isShootGrenade == false) {
					shootGrenade.setEnabled(true);
					shootGrenade.setBackground(new Color(194, 194, 163));
					shootGrenade.setBorder(null);
				}
				if(shields > 0 && isShield == false && shootingMap[shootingMap.length-2][movePlayer].equals(" ")) {
					activateShield.setEnabled(true);
					activateShield.setBackground(new Color(194, 194, 163));
					activateShield.setBorder(null);
				}
				colButtons("on");
				for(int i = 0; i < colButtons.length; i++) {
					colButtons[i].setBackground(new Color(184, 184, 148));
				}
			}
		});
		
		shootGrenade.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(isShootGrenade == false && grenades > 0) {
					shootGrenade.setBorder(new LineBorder(new Color(112, 112, 67)));
					shootGrenade.setBounds(74, 199, 111, 32);
					
				}
			}
			public void mouseExited(MouseEvent e) {
				if(isShootGrenade == false && grenades > 0) {
					shootGrenade.setBounds(75, 200, 109, 30);
					shootGrenade.setBorder(null);
				}
			}
		});
		
		shootGrenade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shootGrenade.setBorder(new LineBorder(new Color(112, 112, 67)));
				shootGrenade.setBackground(new Color(226, 226, 208));
				isShootBullet = false;
				isShootGrenade = true;
				isColButtons = true;
				shootGrenade.setEnabled(false);
				if(bullets > 0) {
					shootBullet.setEnabled(true);
					shootBullet.setBackground(new Color(194, 194, 163));
					shootBullet.setBorder(null);
				}
				if(shields > 0 && isShield == false && shootingMap[shootingMap.length-2][movePlayer].equals(" ")) {
					activateShield.setEnabled(true);
					activateShield.setBackground(new Color(194, 194, 163));
					shootBullet.setBorder(null);
				}
				colButtons("on");
				for(int i = 0; i < colButtons.length; i++) {
					colButtons[i].setBackground(new Color(184, 184, 148));
				}
			}
		});
		
		activateShield.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(isShield == false && shields > 0) {
					activateShield.setBorder(new LineBorder(new Color(112, 112, 67)));
					activateShield.setBounds(79, 239, 102, 32);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(isShield == false && shields > 0) {
					activateShield.setBorder(null);
					activateShield.setBounds(80, 240, 100, 30);
				}
			}
		});
		
		activateShield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateShield.setBorder(new LineBorder(new Color(112, 112, 67)));
				activateShield.setBackground(new Color(226, 226, 208));
				shields--;
				isShield = true;
				activateShield.setEnabled(false);
				shieldLabel.setText("Shields: " + shields);
				shieldCurrentPower = shieldPower;
				
				shootPanel.remove(mapLabel[mapLabel.length-1][movePlayer]);
				ImageIcon activatedShieldImage = new ImageIcon("C:\\Users\\Vipul\\Documents\\shieldActivatedIMG.png");
				JLabel activatedShieldLabel = new JLabel(activatedShieldImage);
				mapLabel[mapLabel.length-1][movePlayer] = activatedShieldLabel;
				shootPanel.add(mapLabel[mapLabel.length-1][movePlayer]);
				mapLabel[mapLabel.length-1][movePlayer].setBounds(movePlayerX, 330, 20, 20);
				
				if((isShootBullet == false && bullets > 0) || (isShootGrenade == false && grenades > 0)) {
					colButtons("on");
					isColButtons = true;
				}
			}
		});
		
		int j = 0;
		int colx2 = 252;
		int coly2 = 370;
		while(j < colButtons.length) {
			int k = j;
			int colx3 = colx2;
			int coly3 = coly2;
			colButtons[j].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if(isColButtons == true) {
						colButtons[k].setBorder(new LineBorder(new Color(115, 115, 115)));
						colButtons[k].setBounds(colx3-1, coly3-1, 16, 26);
					}
				}
				public void mouseExited(MouseEvent e) {
					if(isColButtons == true) {
						colButtons[k].setBorder(null);
						colButtons[k].setBounds(colx3, coly3, 15, 25);
					}
				}
			});
			colx2 += 20;
			j++;
		}
		
		int i = 0;
		while(i < colButtons.length) {
			int k = i;
			colButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					isCol = true;	
					shoot(k);
					
					if((isShootBullet == true && bullets == 0) || (isShootGrenade == true && grenades == 0)) {
						if(isShootBullet == true) {
							isShootBullet = false;
							shootBullet.setEnabled(false);
							shootBullet.setBorder(null);
							shootBullet.setBackground(new Color(226, 226, 208));
						}
						else if(isShootGrenade == true) {
							isShootGrenade = false;
							shootGrenade.setEnabled(false);
							shootGrenade.setBorder(null);
							shootGrenade.setBackground(new Color(226, 226, 208));
						}
						colButtons("off");
						for(int i = 0; i < colButtons.length; i++) {
							colButtons[i].setBackground(new Color(245, 245, 240));
							colButtons[i].setBorder(null);
						}
						isColButtons = false;
					}
					else {
						colButtons("on");
					}
				}
			});
			if(isCol == true) {
				break;
			}
			else {
				i++;
			}
		}
		
		store.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				store.setBorder(new LineBorder(new Color(102, 68, 0)));
			}
			public void mouseExited(MouseEvent e) {
				store.setBorder(null);
			}
		});
		
		store.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStore = true;
				isQuitGame = false;
				disableButton(quitGame);
				store.hide();
				shootBullet.hide();
				shootGrenade.hide();
				activateShield.hide();
				shootPanel.remove(store);
				shootPanel.remove(shootBullet);
				shootPanel.remove(shootGrenade);
				shootPanel.remove(activateShield);
				
				for(int i = 0; i < colButtons.length; i++) {
					colButtons[i].hide();
					shootPanel.remove(colButtons[i]);
				}
						
				JButton exitStore = new JButton("Exit Store"); 																//exit store button
				shootPanel.add(exitStore);
				exitStore.setBounds(80, 300, 90, 30);
				exitStore.setBorder(null);
				exitStore.setBackground(new Color(255, 153, 51));
				exitStore.setForeground(Color.white);
				exitStore.setFocusable(false);
				
				exitStore.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						exitStore.setBorder(new LineBorder(new Color(255, 102, 0)));
					}
					public void mouseExited(MouseEvent e) {
						exitStore.setBorder(null);
					}
				});
				
				JButton upgrades = new JButton("Upgrades");																	//upgrade button
				shootPanel.add(upgrades);
				upgrades.setBounds(75, 215, 100, 30);
				upgrades.setBorder(null);
				upgrades.setBackground(new Color(77, 77, 255));
				upgrades.setForeground(Color.white);
				upgrades.setFocusable(false);
				
				JButton buy = new JButton("Buy");																//buy button
				shootPanel.add(buy);
				buy.setBounds(95, 175, 60, 30);
				buy.setBorder(null);
				buy.setBackground(new Color(77, 77, 255));
				buy.setForeground(Color.white);
				buy.setFocusable(false);
				
				JButton abilities = new JButton("Abilities");																//buy button
				shootPanel.add(abilities);
				abilities.setBounds(75, 255, 100, 30);
				abilities.setBorder(null);
				abilities.setFocusable(false);
				
				JLabel abilitiesScoreLabel = new JLabel("Score: 250");
				if(isAbilities == false) {
					shootPanel.add(abilitiesScoreLabel);
				}
				abilitiesScoreLabel.setBounds(180, 255, 120, 30);
				abilitiesScoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				if(score >= 50) {
					abilities.setEnabled(true);
					abilities.setBackground(new Color(77, 77, 255));
					abilities.setForeground(Color.white);
					abilitiesScoreLabel.setForeground(new Color(0, 51, 0));
				}
				else {
					disableButton(abilities);
					abilitiesScoreLabel.setForeground(new Color(153, 0, 0));
				}
				
				upgrades.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						upgrades.hide();
						buy.hide();
						if(isAbilities == true || isAbilities == false) {
							abilitiesScoreLabel.hide();
							shootPanel.remove(abilitiesScoreLabel);
						}
						exitStore.hide();
						shootPanel.remove(upgrades);
						shootPanel.remove(buy);
						shootPanel.remove(abilities);
						shootPanel.remove(exitStore);
						
						JButton back = new JButton("Back"); 																		//back button
						shootPanel.add(back);
						back.setBounds(80, 300, 90, 30);
						back.setBorder(null);
						back.setBackground(new Color(255, 153, 51));
						back.setForeground(Color.white);
						back.setFocusable(false);
						
						JButton upgradeBullets = new JButton("Bullets"); 															//upgrade bullet
						shootPanel.add(upgradeBullets);
						upgradeBullets.setBounds(60, 175, 80, 30);
						if(coins < upgradeBulletPrice || bulletPower == 3) {
							disableButton(upgradeBullets);
							
						}
						upgradeBullets.setFocusable(false);
						
						JLabel upgradeBulletLabel = new JLabel(upgradeBulletPrice + " Coins");
						if(bulletPower == 3) {
							upgradeBulletLabel.setText("Max Level");
						}
						shootPanel.add(upgradeBulletLabel);
						upgradeBulletLabel.setBounds(150, 175, 100, 30);
						upgradeBulletLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < upgradeBulletPrice) {
							upgradeBulletLabel.setForeground(new Color(179, 0, 0));;
						}
						
						JButton upgradeGrenades = new JButton("Grenades");															//upgrade grenade
						shootPanel.add(upgradeGrenades);
						upgradeGrenades.setBounds(55, 215, 90, 30);
						if(coins < upgradeGrenadePrice || grenadePower == 3) {
							disableButton(upgradeGrenades);
						}
						upgradeGrenades.setFocusable(false);
						
						JLabel upgradeGrenadeLabel = new JLabel(upgradeGrenadePrice + " Coins");
						if(grenadePower == 3) {
							upgradeGrenadeLabel.setText("Max Level");
						}
						shootPanel.add(upgradeGrenadeLabel);
						upgradeGrenadeLabel.setBounds(155, 215, 100, 30);
						upgradeGrenadeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < upgradeGrenadePrice) {
							upgradeGrenadeLabel.setForeground(new Color(179, 0, 0));;
						}
						
						JButton upgradeShields = new JButton("Shields"); 															//upgrade shield
						shootPanel.add(upgradeShields);
						upgradeShields.setBounds(60, 255, 80, 30);
						if(coins < upgradeShieldPrice || shieldPower == 6) {
							disableButton(upgradeShields);
						}
						upgradeShields.setFocusable(false);
						
						JLabel upgradeShieldLabel = new JLabel(upgradeShieldPrice + " Coins");
						if(shieldPower == 6) {
							upgradeShieldLabel.setText("Max Level");
						}
						shootPanel.add(upgradeShieldLabel);
						upgradeShieldLabel.setBounds(150, 255, 100, 30);
						upgradeShieldLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < upgradeShieldPrice) {
							upgradeShieldLabel.setForeground(new Color(179, 0, 0));;
						}
						upgrades.hide();
						buy.hide();
						abilities.hide();
						exitStore.hide();
						back.show();
						
						if(coins >= upgradeBulletPrice && bulletPower != 3) {
							upgradeBullets.setEnabled(true);
							upgradeBullets.setBorder(null);
							upgradeBullets.setBackground(new Color(153, 153, 0));
							upgradeBullets.setForeground(Color.white);
							upgradeBulletLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(upgradeBullets);
							upgradeBulletLabel.setForeground(new Color(179, 0, 0));
						}
						upgradeBullets.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								bulletPower++;
								coins -= upgradeBulletPrice;
								upgradeBulletPrice += 75;
								coinLabel.setText("Coins: " + coins);
								upgradeBulletLabel.setText(upgradeBulletPrice + " Coins");
								if(coins < upgradeBulletPrice || bulletPower == 3) {
									if(bulletPower == 3) {
										upgradeBulletLabel.setText("Max Level");
									}
									disableButton(upgradeBullets);
									upgradeBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeGrenadePrice) {
									disableButton(upgradeGrenades);
									upgradeGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeShieldPrice) {
									disableButton(upgradeShields);
									upgradeShieldLabel.setForeground(new Color(179, 0, 0));
								}
							}
						});
						
						if(coins >= upgradeGrenadePrice && grenadePower != 4) {
							upgradeGrenades.setEnabled(true);
							upgradeGrenades.setBorder(null);
							upgradeGrenades.setBackground(new Color(153, 153, 0));
							upgradeGrenades.setForeground(Color.white);
							upgradeGrenadeLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(upgradeGrenades);
							upgradeGrenadeLabel.setForeground(new Color(179, 0, 0));
						}
						upgradeGrenades.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								grenadePower++;
								coins -= upgradeGrenadePrice;
								coinLabel.setText("Coins: " + coins);
								upgradeGrenadePrice += 100;
								upgradeGrenadeLabel.setText(upgradeGrenadePrice + " Coins");
								if(coins < upgradeBulletPrice || bulletPower == 3) {
									disableButton(upgradeBullets);
									upgradeBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeGrenadePrice || grenadePower == 3) {
									disableButton(upgradeGrenades);
									if(grenadePower == 3) {
										upgradeGrenadeLabel.setText("Max Level");
									}
									upgradeGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeShieldPrice || shieldPower == 6) {
									disableButton(upgradeShields);
									upgradeShieldLabel.setForeground(new Color(179, 0, 0));
								}
							}
						});
						
						if(coins >= upgradeShieldPrice && shieldPower != 7) {
							upgradeShields.setEnabled(true);
							upgradeShields.setBorder(null);
							upgradeShields.setBackground(new Color(153, 153, 0));
							upgradeShields.setForeground(Color.white);
							upgradeShieldLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(upgradeShields);
							upgradeShieldLabel.setForeground(new Color(179, 0, 0));
						}
						upgradeShields.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shieldPower += 2;
								coins -= upgradeShieldPrice;
								coinLabel.setText("Coins: " + coins);
								upgradeShieldPrice += 125;
								upgradeShieldLabel.setText(upgradeShieldPrice + " Coins");
								if(coins < upgradeBulletPrice) {
									disableButton(upgradeBullets);
									upgradeBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeGrenadePrice) {
									disableButton(upgradeGrenades);
									upgradeGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < upgradeShieldPrice || shieldPower == 6) {
									disableButton(upgradeShields);
									if(shieldPower == 6) {
										upgradeShieldLabel.setText("Max Level");
									}
									upgradeShieldLabel.setForeground(new Color(179, 0, 0));
								}
							}
						});
						back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shootPanel.add(upgrades);
								shootPanel.add(buy);
								shootPanel.add(abilities);
								if(isAbilities == false) {
									shootPanel.add(abilitiesScoreLabel);
									abilitiesScoreLabel.show();
								}
								shootPanel.add(exitStore);
								shootPanel.remove(back);
								back.hide();
								upgrades.show();
								buy.show();
								abilities.show();
								exitStore.show();
								upgradeBullets.hide();
								upgradeBulletLabel.hide();
								upgradeGrenades.hide();
								upgradeGrenadeLabel.hide();
								upgradeShields.hide();
								upgradeShieldLabel.hide();
								shootPanel.remove(upgradeBullets);
								shootPanel.remove(upgradeBulletLabel);
								shootPanel.remove(upgradeGrenades);
								shootPanel.remove(upgradeGrenadeLabel);
								shootPanel.remove(upgradeShields);
								shootPanel.remove(upgradeShieldLabel);
							}
						});
					}
				});
				
				buy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						upgrades.hide();
						buy.hide();
						abilities.hide();
						if(isAbilities == false || isAbilities == false) {
							abilitiesScoreLabel.hide();
							shootPanel.remove(abilitiesScoreLabel);
						}
						exitStore.hide();
						shootPanel.remove(upgrades);
						shootPanel.remove(buy);
						shootPanel.remove(abilities);
						shootPanel.remove(exitStore);
						
						JButton back = new JButton("Back"); 																		//back button
						shootPanel.add(back);
						back.show();
						back.setBounds(80, 300, 90, 30);
						back.setBorder(null);
						back.setBackground(new Color(255, 153, 51));
						back.setForeground(Color.white);
						back.setFocusable(false);
						
						JButton buyBullets = new JButton("Bullet"); 																//buy bullet
						shootPanel.add(buyBullets);
						buyBullets.setBounds(60, 175, 80, 30);
						if(coins < buyBulletPrice) {
							disableButton(buyBullets);
						}
						buyBullets.setFocusable(false);
						
						JLabel buyBulletLabel = new JLabel(buyBulletPrice + " Coins");
						shootPanel.add(buyBulletLabel);
						buyBulletLabel.setBounds(150, 175, 180, 30);
						buyBulletLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < buyBulletPrice) {
							buyBulletLabel.setForeground(new Color(179, 0, 0));;
						}
						
						JButton buyGrenades = new JButton("Grenade"); 																//buy grenade
						shootPanel.add(buyGrenades);
						buyGrenades.setBounds(55, 215, 90, 30);
						if(coins < buyGrenadePrice) {
							disableButton(buyGrenades);
						}
						buyGrenades.setFocusable(false);
						
						JLabel buyGrenadeLabel = new JLabel(buyGrenadePrice + " Coins");
						shootPanel.add(buyGrenadeLabel);
						buyGrenadeLabel.setBounds(155, 215, 180, 30);
						buyGrenadeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < buyGrenadePrice) {
							buyGrenadeLabel.setForeground(new Color(179, 0, 0));;
						}
						
						JButton buyShields = new JButton("Shield"); 																//buy shield
						shootPanel.add(buyShields);
						buyShields.setBounds(60, 255, 80, 30);
						if(coins < buyShieldPrice) {
							disableButton(buyShields);
						}
						buyShields.setFocusable(false);
						
						JLabel buyShieldLabel = new JLabel(buyShieldPrice + " Coins");
						shootPanel.add(buyShieldLabel);
						buyShieldLabel.setBounds(150, 255, 180, 30);
						buyShieldLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(coins < buyShieldPrice) {
							buyShieldLabel.setForeground(new Color(179, 0, 0));;
						}
						if(coins >= buyBulletPrice) {
							buyBullets.setEnabled(true);
							buyBullets.setBorder(null);
							buyBullets.setBackground(new Color(153, 122, 0));
							buyBullets.setForeground(Color.white);
							buyBulletLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(buyBullets);
						}
						buyBullets.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								bullets += 5;
								coins -= buyBulletPrice;
								coinLabel.setText("Coins: " + coins);
								bulletLabel.setText("Bullets: " + bullets);
								buyBulletPrice += 15;
								buyBulletLabel.setText(buyBulletPrice + " Coins");
								if(isShootBullet == false) {
									shootBullet.setEnabled(true);
								}
								if(coins < buyBulletPrice) {
									disableButton(buyBullets);
									buyBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyGrenadePrice) {
									disableButton(buyGrenades);
									buyGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyShieldPrice) {
									disableButton(buyShields);
									buyShieldLabel.setForeground(new Color(179, 0, 0));
								}
								if(isShootBullet == false) {
									shootBullet.setBorder(null);
									shootBullet.setBackground(new Color(194, 194, 163));
									shootBullet.setForeground(new Color(80, 80, 48));
								}
								else {
									shootBullet.setBorder(new LineBorder(new Color(112, 112, 67)));
									shootBullet.setBackground(new Color(226, 226, 208));
								}
							}
						});
						
						if(coins >= buyGrenadePrice) {
							buyGrenades.setEnabled(true);
							buyGrenades.setBorder(null);
							buyGrenades.setBackground(new Color(153, 122, 0));
							buyGrenades.setForeground(Color.white);
							buyGrenadeLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(buyGrenades);
						}
						buyGrenades.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								grenades++;
								coins -= buyGrenadePrice;
								coinLabel.setText("Coins: " + coins);
								grenadeLabel.setText("Grenades: " + grenades);
								buyGrenadePrice += 25;
								buyGrenadeLabel.setText(buyGrenadePrice + " Coins");
								if(isShootGrenade == false) {
									shootGrenade.setEnabled(true);
								}
								if(coins < buyBulletPrice) {
									disableButton(buyBullets);
									buyBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyGrenadePrice) {
									disableButton(buyGrenades);
									buyGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyShieldPrice) {
									disableButton(buyShields);
									buyShieldLabel.setForeground(new Color(179, 0, 0));
								}
								if(isShootGrenade == false) {
									shootGrenade.setBorder(null);
									shootGrenade.setBackground(new Color(194, 194, 163));
									shootGrenade.setForeground(new Color(80, 80, 48));
								}
								else {
									shootGrenade.setBorder(new LineBorder(new Color(112, 112, 67)));
									shootGrenade.setBackground(new Color(226, 226, 208));
								}
							}
						});
						
						if(coins >= buyShieldPrice) {
							buyShields.setEnabled(true);
							buyShields.setBorder(null);
							buyShields.setBackground(new Color(153, 122, 0));
							buyShields.setForeground(Color.white);
							buyShieldLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							disableButton(buyShields);
						}
						buyShields.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shields++;
								coins -= buyShieldPrice;
								coinLabel.setText("Coins: " + coins);
								shieldLabel.setText("Shields: " + shields);
								buyShieldPrice += 50;
								buyShieldLabel.setText(buyShieldPrice + " Coins");
								if(isShield == false && shootingMap[shootingMap.length-2][4].equals(" ")) {
									activateShield.setEnabled(true);
								}
								else {
									activateShield.setEnabled(false);
								}
								if(coins < buyBulletPrice) {
									disableButton(buyBullets);
									buyBulletLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyGrenadePrice) {
									disableButton(buyGrenades);
									buyGrenadeLabel.setForeground(new Color(179, 0, 0));
								}
								if(coins < buyShieldPrice) {
									disableButton(buyShields);
									buyShieldLabel.setForeground(new Color(179, 0, 0));
								}
								if(isShield == false) {
									activateShield.setBorder(null);
									activateShield.setBackground(new Color(194, 194, 163));
									activateShield.setForeground(new Color(80, 80, 48));
								}
								else {
									activateShield.setBorder(new LineBorder(new Color(112, 112, 67)));
									activateShield.setBackground(new Color(226, 226, 208));
								}
							}
						});
						back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shootPanel.add(upgrades);
								shootPanel.add(buy);
								shootPanel.add(abilities);
								if(isAbilities == false) {
									shootPanel.add(abilitiesScoreLabel);
									abilitiesScoreLabel.show();
								}
								shootPanel.remove(back);
								back.hide();
								upgrades.show();
								buy.show();
								abilities.show();
								shootPanel.add(exitStore);
								exitStore.show();
								buyBullets.hide();
								buyBulletLabel.hide();
								buyGrenades.hide();
								buyGrenadeLabel.hide();
								buyShields.hide();
								buyShieldLabel.hide();
								shootPanel.remove(buyBullets);
								shootPanel.remove(buyBulletLabel);
								shootPanel.remove(buyGrenades);
								shootPanel.remove(buyGrenadeLabel);
								shootPanel.remove(buyShields);
								shootPanel.remove(buyShieldLabel);
							}
						});
					}
				});
				
				abilities.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isAbilities = true;
						abilities.hide();
						abilitiesScoreLabel.hide();
						upgrades.hide();
						buy.hide();
						exitStore.hide();
						shootPanel.remove(abilities);
						shootPanel.remove(upgrades);
						shootPanel.remove(buy);
						shootPanel.remove(exitStore);
						if(isAbilities == false) {
							abilitiesScoreLabel.hide();
						}
						
						JButton back = new JButton("Back"); 																		//back button
						shootPanel.add(back);
						back.setBounds(80, 300, 90, 30);
						back.setBorder(null);
						back.setBackground(new Color(255, 153, 51));
						back.setForeground(Color.white);
						back.setFocusable(false);
						
						JButton freezeAbility = new JButton("Freeze");																	//freeze button
						shootPanel.add(freezeAbility);
						freezeAbility.setBounds(50, 175, 80, 30);
						freezeAbility.setBorder(null);
						freezeAbility.setBackground(new Color(0, 128, 0));
						freezeAbility.setForeground(Color.white);
						freezeAbility.setFocusable(false);
						if(eliminateRedCount < 25 || isFreeze == true) {
							disableButton(freezeAbility);
						}
						
						JLabel eliminateRedLabel = new JLabel(eliminateRedCount + "/" + 25 + " Reds");
						shootPanel.add(eliminateRedLabel);
						eliminateRedLabel.setBounds(140, 175, 120, 30);
						eliminateRedLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(eliminateRedCount >= 25) {
							eliminateRedLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							eliminateRedLabel.setForeground(new Color(153, 0, 0));
						}
						
						freezeAbility.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								isFreeze = true;
								freezePower = 5;
								eliminateRedCount -= 25;
								eliminateRedLabel.setText(eliminateRedCount + "/" + 25 + " Reds");
								if(eliminateRedCount >= 25) {
									eliminateRedLabel.setForeground(new Color(0, 51, 0));
								}
								else {
									eliminateRedLabel.setForeground(new Color(153, 0, 0));
								}
								disableButton(freezeAbility);
								
								freezePowerLabel = new JLabel("Freeze Power: " + freezePower);
								shootPanel.add(freezePowerLabel);
								freezePowerLabel.show();
								freezePowerLabel.setBounds(450, 250, 150, 100);
								freezePowerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
								freezePowerLabel.setForeground(new Color(0, 0, 204));
							}
						});
						
						JButton mapDownAbility = new JButton("Move Map");																	//map down button
						shootPanel.add(mapDownAbility);
						mapDownAbility.setBounds(40, 215, 100, 30);
						mapDownAbility.setBorder(null);
						mapDownAbility.setBackground(new Color(0, 128, 0));
						mapDownAbility.setForeground(Color.white);
						mapDownAbility.setFocusable(false);
						if(score < 1000 || isMoveMapCurrent == true) {
							disableButton(mapDownAbility);
						}
						
						JLabel scoreThousandLabel = new JLabel();
						if(isMoveMapCurrent == true) {
							scoreThousandLabel.setText("Press S");
						}
						else {
							scoreThousandLabel.setText("Score 1000");
						}
						shootPanel.add(scoreThousandLabel);
						scoreThousandLabel.setBounds(155, 215, 120, 30);
						scoreThousandLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(isMoveMapCurrent == true) {
							scoreThousandLabel.setForeground(new Color(128, 128, 128));
						}
						else if(score >= 1000) {
							scoreThousandLabel.setForeground(new Color(0, 51, 0));
						}
						else {
							scoreThousandLabel.setForeground(new Color(153, 0, 0));
						}
						
						mapDownAbility.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								isMoveMapCurrent = true;
								if(isMoveMap == false && isMoveMapCurrent == true) {
									mainFrame.addKeyListener(moveMapDownListener);
								}
								isMoveMap = true;
								disableButton(mapDownAbility);
								scoreThousandLabel.setText("Press S");
								scoreThousandLabel.setForeground(new Color(128, 128, 128));
							}
						});
						
						JButton laserAbility = new JButton("Laser");																	//laser button
						shootPanel.add(laserAbility);
						laserAbility.setBounds(60, 255, 60, 30);
						laserAbility.setBorder(null);
						laserAbility.setBackground(new Color(0, 128, 0));
						laserAbility.setForeground(Color.white);
						laserAbility.setFocusable(false);
						if(bombKills < 3) {
							disableButton(laserAbility);
						}
						
						JLabel bombKillLabel = new JLabel(bombKills + "/" + 3 + " Bombkills");
						shootPanel.add(bombKillLabel);
						bombKillLabel.setBounds(130, 255, 120, 30);
						bombKillLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
						if(bombKills < 3) {
							bombKillLabel.setForeground(new Color(153, 0, 0));
						}
						else {
							bombKillLabel.setForeground(new Color(0, 51, 0));
						}
						
						laserAbility.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								back.hide();
								laserAbility.hide();
								mapDownAbility.hide();
								freezeAbility.hide();
								bombKillLabel.hide();
								scoreThousandLabel.hide();
								eliminateRedLabel.hide();
								
								shootPanel.remove(laserAbility);
								shootPanel.remove(mapDownAbility);
								shootPanel.remove(freezeAbility);
								shootPanel.remove(back);
								
								int randj1 = (int)(Math.random() * 5);
								int randj2 = (int)(Math.random() * 4) + 5;
								
								JButton randj1Button = new JButton(randj1 + "");
								shootPanel.add(randj1Button);
								randj1Button.setBounds(100, 240, 20, 20);
								randj1Button.setBorder(null);
								randj1Button.setBackground(new Color(0, 128, 0));
								randj1Button.setForeground(Color.white);
								randj1Button.setFocusable(false);
								
								JButton randj2Button = new JButton(randj2 + "");
								shootPanel.add(randj2Button);
								randj2Button.setBounds(125, 240, 20, 20);
								randj2Button.setBorder(null);
								randj2Button.setBackground(new Color(0, 128, 0));
								randj2Button.setForeground(Color.white);
								randj2Button.setFocusable(false);
								
								JButton selectButton = new JButton("Select");
								shootPanel.add(selectButton);
								selectButton.setBounds(85, 270, 70, 25);
								selectButton.setBorder(null);
								disableButton(selectButton);
								selectButton.setFocusable(false);
								
								randj1Button.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										laserCol = randj1;
										randj1Button.setBorder(new LineBorder(Color.BLACK, 2));
										randj2Button.setBorder(null);
										selectButton.setEnabled(true);
										selectButton.setBackground(new Color(0, 128, 0));
										selectButton.setForeground(Color.white);
									}
								});
								
								randj2Button.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										laserCol = randj2;
										randj2Button.setBorder(new LineBorder(Color.BLACK, 2));
										randj1Button.setBorder(null);
										selectButton.setEnabled(true);
										selectButton.setBackground(new Color(0, 128, 0));
										selectButton.setForeground(Color.white);
									}
								});
								
								selectButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										bombKills -= 3;
										for(int j = laserCol; j < laserCol + 1; j++) {
											for(int i = 0; i < shootingMap.length; i++) {
												switch(shootingMap[i][j]) {
												case "*":
													shootingMap[i][j] = " ";
													eliminateRedCount++;
													break;
												case "Q":
													int num = 0;
													bombKills++;
													shootingMap[i][j] = " ";
													if(j - 1 >= 0 && i >= 0 && shootingMap[i-1][j-1].equals("*")) {
														shootingMap[i-1][j-1] = " ";
														num++;
													}
													if(j + 1 < shootingMap[0].length && i >= 0 && shootingMap[i-1][j+1].equals("*")) {
														shootingMap[i-1][j+1] = " ";
														num++;
													}
													if(j - 1 >= 0 && shootingMap[i][j-1].equals("*")) {
														shootingMap[i][j-1] = " ";
														num++;
													}
													if(j + 1 < shootingMap[0].length && shootingMap[i][j+1].equals("*")) {
														shootingMap[i][j+1] = " ";
														num++;
													}
													if(i - 1 >= 0 && shootingMap[i-1][j].equals("*")) {
														shootingMap[i-1][j] = " ";
														num++;
													}
													eliminateRedCount += num;
													score += num * 25;
													break;
												case "G":
													shootingMap[i][j] = " ";
													grenades++;
													score += 30;
													break;
												case "S":
													shootingMap[i][j] = " ";
													shields++;
													score += 30;
													break;
												case "B":
													shootingMap[i][j] = " ";
													bullets++;
													score += 30;
													break;
												case "C":
													shootingMap[i][j] = " ";
													coins += 50;
													score += 30;
													break;
												case "+":
													shootingMap[i][j] = " ";
													if(health + 15 > 100) {
														health = 100;
													}
													else {
														health += 15;
													}
													break;
												}
											}
										}
											
										eliminateRedLabel.setText(eliminateRedCount + "/" + 25 + " Reds");
										if(eliminateRedCount >= 25) {
											eliminateRedLabel.setForeground(new Color(0, 51, 0));
										}
										else {
											eliminateRedLabel.setForeground(new Color(153, 0, 0));
										}
										if(eliminateRedCount >= 25 && isFreeze == false) {
											freezeAbility.setEnabled(true);
											freezeAbility.setBounds(50, 175, 80, 30);
											freezeAbility.setBorder(null);
											freezeAbility.setBackground(new Color(0, 128, 0));
											freezeAbility.setForeground(Color.white);
										}
										scoreLabel.setText("Score: " + score);
										grenadeLabel.setText("Grenades: " + grenades);
										bulletLabel.setText("Bullets: " + bullets);
										shieldLabel.setText("Shields: " + shields);
										
										bombKillLabel.setText(bombKills + "/" + 3 + " Bombkills");
										if(bombKills < 3) {
											bombKillLabel.setForeground(new Color(153, 0, 0));
										}
										else {
											bombKillLabel.setForeground(new Color(0, 51, 0));
										}
										
										healthBarLabel.setText("Health: " + health);
										
										if(health <= 75) {
											healthBarLabel.setForeground(new Color(179, 107, 0));
										}
										else if(health <= 25) {
											healthBarLabel.setForeground(new Color(255, 0, 0));
										}
										else {
											healthBarLabel.setForeground(new Color(0, 77, 26));
										}
											
										updateMapLabel();
										
										randj1Button.hide();
										randj2Button.hide();
										selectButton.hide();
										shootPanel.remove(randj1Button);
										shootPanel.remove(randj2Button);
										shootPanel.remove(selectButton);
										
										shootPanel.add(back);
										shootPanel.add(laserAbility);
										shootPanel.add(mapDownAbility);
										shootPanel.add(freezeAbility);
										laserAbility.show();
										mapDownAbility.show();
										freezeAbility.show();
										back.show();
										bombKillLabel.show();
										scoreThousandLabel.show();
										eliminateRedLabel.show();
										
										if(bombKills < 3) {
											disableButton(laserAbility);
										}
									}
								});
							}
						});
						
						back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shootPanel.add(upgrades);
								shootPanel.add(buy);
								shootPanel.add(abilities);
								shootPanel.add(exitStore);
								back.hide();
								upgrades.show();
								buy.show();
								abilities.show();
								exitStore.show();
								freezeAbility.hide();
								eliminateRedLabel.hide();
								mapDownAbility.hide();
								scoreThousandLabel.hide();
								bombKillLabel.hide();
								shootPanel.remove(mapDownAbility);
								shootPanel.remove(back);
								shootPanel.remove(freezeAbility);
								shootPanel.remove(laserAbility);
							}
						});
					}
				});
				
				exitStore.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent j) {
						upgrades.hide();
						buy.hide();
						abilities.hide();
						abilitiesScoreLabel.hide();
						exitStore.hide();
						shootPanel.remove(upgrades);
						shootPanel.remove(buy);
						shootPanel.remove(abilities);
						shootPanel.remove(exitStore);
						shootPanel.add(store);
						shootPanel.add(shootBullet);
						shootPanel.add(shootGrenade);
						shootPanel.add(activateShield);
						store.show();
						shootBullet.show();
						shootGrenade.show();
						activateShield.show();
						
						for(int i = 0; i < colButtons.length; i++) {
							shootPanel.add(colButtons[i]);
							colButtons[i].show();
						}
						
						isQuitGame = true;
						quitGame.setEnabled(true);
						quitGame.setBorder(null);
						quitGame.setBackground(new Color(230, 0, 0));
						quitGame.setForeground(Color.WHITE);
						
						isStore = false;
					}
				});
				
			}
		});
	}	
	public void updateMapLabel() {
		
		for(int i = 0; i < mapLabel.length; i++) {
			for(int j = 0; j < mapLabel[0].length; j++) {
				shootPanel.remove(mapLabel[i][j]);
			}
		}
		
		int y = 150;
		for(int i = 0; i < shootingMap.length; i++) {
			int x = 250;
			for(int j = 0; j < shootingMap[0].length; j++) {
				if(shootingMap[i][j].equals("*")) {
					ImageIcon redGuys = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\smallRed.png");
					mapLabel[i][j] = new JLabel(redGuys);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("G")) {
					ImageIcon grenadeImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\GrenadeImage.png");
					mapLabel[i][j] = new JLabel(grenadeImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("B")) {
					ImageIcon bulletImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\bulletImage.png");
					mapLabel[i][j] = new JLabel(bulletImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("S")) {
					ImageIcon shieldImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shieldImage.png");
					mapLabel[i][j] = new JLabel(shieldImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("C")) {
					ImageIcon coinImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\coinImage.png");
					mapLabel[i][j] = new JLabel(coinImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("Q")) {
					ImageIcon bombImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\bombImage.png");
					mapLabel[i][j] = new JLabel(bombImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("+")) {
					ImageIcon healImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\healImage.png");
					mapLabel[i][j] = new JLabel(healImage);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
				}
				else if(shootingMap[i][j].equals("^")) {
					if(isShield == false) {
						ImageIcon shootCharacImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shootCharacterImage.png");
						mapLabel[i][j] = new JLabel(shootCharacImage);
						shootPanel.add(mapLabel[i][j]);
						mapLabel[i][j].setBounds(x, y, 20, 20);
					}
					else if(isShield == true && shieldCurrentPower > 0) {
						ImageIcon shieldActivatedImage = new ImageIcon("C:\\Users\\Vipul\\Desktop\\Java Projects\\arrowShooterData\\shieldActivatedIMG.png");
						mapLabel[i][j] = new JLabel(shieldActivatedImage);
						shootPanel.add(mapLabel[i][j]);
						mapLabel[i][j].setBounds(x, y, 20, 20);
					}
				}
				else {
					mapLabel[i][j] = new JLabel(shootingMap[i][j]);
					shootPanel.add(mapLabel[i][j]);
					mapLabel[i][j].setBounds(x, y, 20, 20);
					mapLabel[i][j].setForeground(Color.blue);
					mapLabel[i][j].setFont(new Font("Open Sans", Font.PLAIN, 18));
				}
				x += 20;
				if(x >= 420) {y += 20;}
			}
		}
	}	
	
	public void disableButton(JButton disable) {
		disable.setEnabled(false);
		disable.setBorder(null);
		disable.setBackground(new Color(230, 230, 230));
		disable.setForeground(new Color(115, 115, 115));
	}	

	public void colButtons(String str) {
		if(str.equals("on")) {
			for(int i = 0; i < colButtons.length; i++) {
				colButtons[i].setEnabled(true);
			}
		}
		else {
			for(int i = 0; i < colButtons.length; i++) {
				colButtons[i].setEnabled(false);
			}
		}
	}
	
	public String[][] startingMap() {
		
		for(int i = 0; i < shootingMap.length; i++) {
			for(int j = 0; j < shootingMap[0].length; j++) {
				int randEmptyNum = (int)(Math.random() * 6) + 1;
				if(randEmptyNum >= 3) {
					if(i < 4) {
						shootingMap[i][j] = "*"; 
					}
					else {
						shootingMap[i][j] = " ";
					}
				}
				else {
					shootingMap[i][j] = " ";
				}
				if(i == shootingMap.length - 1 && j == movePlayer) {
					shootingMap[i][j] = "^";
				}
			}
		}
		
		int nums = 3;
		while(nums > 0) {
			int randNum = (int)(Math.random() * specialThings.length);
			int randi = (int)(Math.random() * 3);
			int randj = (int)(Math.random() * 9);
			shootingMap[randi][randj] = specialThings[randNum];
			nums--;
		}
		
		updateMapNum = 4;
		
		return shootingMap;
	}
	
	public void shoot(int col) {
		int row = (int)(Math.random() * shootingMap.length);
		
		for(int j = col; j < col + 1; j++) {
			for(int i = 0; i < shootingMap.length; i++) {
				if(shootingMap[i][j].equals(" ") == false && shootingMap[i][j].equals("^") == false) {
					row = i;
				}
			}
		}
		
		if(bullets > 0 && isShootBullet == true) {
			switch(shootingMap[row][col]) {
				case "G":
					grenades++;
					if(grenades > 0 && isShootGrenade == false) {
						shootGrenade.setEnabled(true);
						shootGrenade.setForeground(new Color(80, 80, 48));
						shootGrenade.setBorder(null);
						shootGrenade.setBackground(new Color(194, 194, 163));
					}
					score += 30;
					break;
				case "C":
					coins += 50;
					score += 30;
					break;
				case "Q":
					int num = 0;
					bombKills++;
					shootingMap[row][col] = " ";
					if(col - 1 >= 0 && row - 1 >= 0 && shootingMap[row-1][col-1].equals("*")) {
						shootingMap[row-1][col-1] = " ";
						num++;
					}
					if(col + 1 < shootingMap[0].length && row - 1 >= 0 && shootingMap[row-1][col+1].equals("*")) {
						shootingMap[row-1][col+1] = " ";
						num++;
					}
					if(col - 1 >= 0 && shootingMap[row][col-1].equals("*")) {
						shootingMap[row][col-1] = " ";
						num++;
					}
					if(col + 1 < shootingMap[0].length && shootingMap[row][col+1].equals("*")) {
						shootingMap[row][col+1] = " ";
						num++;
					}
					if(row - 1 >= 0 && shootingMap[row-1][col].equals("*")) {
						shootingMap[row-1][col] = " ";
						num++;
					}
					eliminateRedCount += num;
					redKills += num;
					score += num * 25;
					break;
				case "B":
					bullets += 5;
					if(bullets > 0 && isShootBullet == false) {
						shootBullet.setEnabled(true);
						shootBullet.setForeground(new Color(80, 80, 48));
						shootBullet.setBorder(null);
						shootBullet.setBackground(new Color(194, 194, 163));
					}
					score += 30;
					break;
				case "S":
					shields++;
					if(shields > 0 && isShield == false) {
						activateShield.setEnabled(true);
						activateShield.setForeground(new Color(80, 80, 48));
						activateShield.setBackground(new Color(194, 194, 163));
						activateShield.setBorder(null);
					}
					score += 30;
					break;
				case "+":
					health += 15;
					if(health > 100) {
						health = 100;
					}
					healthBarLabel.setText("Health: " + health);
					if(health <= 75) {
						healthBarLabel.setForeground(new Color(179, 107, 0));
					}
					else if(health <= 25) {
						healthBarLabel.setForeground(new Color(255, 0, 0));
					}
					else {
						healthBarLabel.setForeground(new Color(0, 77, 26));
					}
					score += 30;
					break;
				case "*":
					eliminateRedCount++;
					redKills++;
					score += 25;
					coins += 5;
					if(row - 1 >= 0 && bulletPower >= 2 && shootingMap[row-1][col].equals("*")) {
						shootingMap[row-1][col] = " ";
						score += 25;
						eliminateRedCount++;
						redKills++;
						coins += 5;
					}
					if(bulletPower >= 3) {
						if( col + 1 < shootingMap[0].length && shootingMap[row][col+1].equals("*")) {
							shootingMap[row][col+1] = " ";
							score += 25;
							eliminateRedCount++;
							redKills++;
							coins += 5;
						}
						if(col - 1 >= 0 && shootingMap[row][col-1].equals("*")) {
							shootingMap[row][col-1] = " ";
							score += 25;
							eliminateRedCount++;
							redKills++;
							coins += 5;
						}
					}
					break;
				}
				shootingMap[row][col] = " ";
				bullets--;
		}
		else if(isShootGrenade == true && grenades > 0) {
			int num = 0;
			shootingMap[row][col] = " ";
			if(col - 1 >= 0 && row >= 0 && shootingMap[row-1][col-1].equals("*")) {
				shootingMap[row-1][col-1] = " ";
				num++;
			}
			if(col + 1 < shootingMap[0].length && row >= 0 && shootingMap[row-1][col+1].equals("*")) {
				shootingMap[row-1][col+1] = " ";
				num++;
			}
			if(col - 1 >= 0 && shootingMap[row][col-1].equals("*")) {
				shootingMap[row][col-1] = " ";
				num++;
			}
			if(col + 1 < shootingMap[0].length && shootingMap[row][col+1].equals("*")) {
				shootingMap[row][col+1] = " ";
				num++;
				eliminateRedCount++;
			}
			if(row - 1 >= 0 && shootingMap[row-1][col].equals("*")) {
				shootingMap[row-1][col] = " ";
				num++;
			}
			if(grenadePower >= 2) {
				if(col - 2 >= 0 && shootingMap[row][col-2].equals("*")) {
					shootingMap[row][col-2] = " ";
					num++;
				}
				if(col + 2 < shootingMap[0].length && shootingMap[row][col+2].equals("*")) {
					shootingMap[row][col+2] = " ";
					num++;
				}
			}
			if(grenadePower >= 3) {
				if(col - 2 >= 0 && row - 1 >= 0 && shootingMap[row-1][col-2].equals("*")) {
					shootingMap[row-1][col-2] = " ";
					num++;
				}
				if(col + 2 < shootingMap[0].length && row - 1 >= 0 && shootingMap[row-1][col+2].equals("*")) {
					shootingMap[row-1][col+2] = " ";
					num++;
				}
			}
			eliminateRedCount += num;
			redKills += num;
			score += num * 25;
			grenades--;
		}
		
		if(isFreeze == false) {
			updateTheMap();
		}
		else if(freezePower > 0) {
			freezePower--;
			freezePowerLabel.setText("Freeze Power: " + freezePower);
			if(freezePower == 0) {
				freezePower = 5;
				isFreeze = false;
				freezePowerLabel.hide();
				shootPanel.remove(freezePowerLabel);
			}
		}
		
		scoreLabel.setText("Score: " + score);
		coinLabel.setText("Coins: " + coins);
		bulletLabel.setText("Bullets: " + bullets);
		grenadeLabel.setText("Grenades: " + grenades);
		shieldLabel.setText("Shields: " + shields);
		
		updateMapLabel();
	}
	
	public void updateTheMap() {
		int index = shootingMap.length - 1;
		String[][] update_shootingMap = new String [10][9];
		int randj = (int)(Math.random() * shootingMap[0].length);
		int randSpecialThings = (int)(Math.random() * specialThings.length);
		int isSpecial = (int)(Math.random() * 6);
					
		for(int i = 0; i < update_shootingMap.length; i++) {
			for(int j = 0; j < update_shootingMap[0].length; j++) {
				int randEmptyNum = (int)(Math.random() * 6) + 1;
				if(i == 0) {
					if(randEmptyNum >= 3) {
						if(j == randj && isSpecial >= 3) {
							update_shootingMap[i][j] = specialThings[randSpecialThings];
						}
						else {
							update_shootingMap[i][j] = "*";
						}
					}
					else {
						update_shootingMap[i][j] = " ";
					}
				}
				if(i == update_shootingMap.length - 1) {
					if(j == movePlayer) {
						update_shootingMap[i][j] = "^";
					}
					else {
						update_shootingMap[i][j] = " ";
					}
				}
				else if(i > updateMapNum && i < update_shootingMap.length - 1) {
					update_shootingMap[i][j] = " ";
				}
			}
		}
		for(int i = 1; i <= updateMapNum; i++) {
			for(int j = 0; j < update_shootingMap[0].length; j++) {
				update_shootingMap[i][j] = shootingMap[i-1][j];
			}
		}
		
		if(health > 0) {
			switch(update_shootingMap[index][movePlayer]) {
			case "G":
				grenades++;
				if(grenades > 0 && isShootGrenade == false) {
					shootGrenade.setEnabled(true);
					shootGrenade.setForeground(new Color(80, 80, 48));
					shootGrenade.setBorder(null);
					shootGrenade.setBackground(new Color(194, 194, 163));
				}
				score += 30;
				break;
			case "C":
				coins += 50;
				score += 30;
				break;
			case "S":
				shields++;
				if(shields > 0 && isShield == false) {
					activateShield.setEnabled(true);
					activateShield.setForeground(new Color(80, 80, 48));
					activateShield.setBackground(new Color(194, 194, 163));
					activateShield.setBorder(null);
				}
				score += 30;
				break;
			case "B":
				bullets += 5;
				if(bullets > 0 && isShootBullet == false) {
					shootBullet.setEnabled(true);
					shootBullet.setForeground(new Color(80, 80, 48));
					shootBullet.setBorder(null);
					shootBullet.setBackground(new Color(194, 194, 163));
				}
				score += 30;
				break;
			case "+":
				health += 15;
				if(health > 100) {
					health = 100;
				}
				healthBarLabel.setText("Health: " + health);
				if(health <= 75) {
					healthBarLabel.setForeground(new Color(179, 107, 0));
				}
				else if(health <= 25) {
					healthBarLabel.setForeground(new Color(255, 0, 0));
				}
				else {
					healthBarLabel.setForeground(new Color(0, 77, 26));
				}
				break;
			case "*":
				if(isShield == false) {
					health -= 10;
					healthBarLabel.setText("Health: " + health);
					if(health <= 75) {
						healthBarLabel.setForeground(new Color(179, 107, 0));
					}
					else if(health <= 25) {
						healthBarLabel.setForeground(new Color(255, 0, 0));
					}
					else {
						healthBarLabel.setForeground(new Color(0, 77, 26));
					}
				}
				else if(update_shootingMap[update_shootingMap.length-1][movePlayer].equals(" ") == false && isShield == true) {
					shieldCurrentPower--;
					if(shieldCurrentPower == 0) {
						isShield = false;
						shieldCurrentPower = 0;
						if(shields > 0) {
							activateShield.setEnabled(true);
							activateShield.setForeground(new Color(80, 80, 48));
							activateShield.setBackground(new Color(194, 194, 163));
						}
						activateShield.setBorder(null);
					}
				}
				break;
			}
			update_shootingMap[index][movePlayer] = "^";
		}
		for(int i = 0; i < shootingMap.length; i++) {
			for(int j = 0; j < shootingMap[0].length; j++) {
				shootingMap[i][j] = update_shootingMap[i][j];
			}
		}

		updateMapNum++;
			
		if(updateMapNum > shootingMap.length - 1) {
			updateMapNum = shootingMap.length - 1;
		}

		scoreLabel.setText("Score: " + score);
		coinLabel.setText("Coins: " + coins);
		bulletLabel.setText("Bullets: " + bullets);
		grenadeLabel.setText("Grenades: " + grenades);
		shieldLabel.setText("Shields: " + shields);
		
		if(health <= 0 || (bullets == 0 && grenades == 0 && coins == 0 && eliminateRedCount < 25 && bombKills < 3)) { 				// Game Over
			shootBullet.hide();
			shootGrenade.hide();
			activateShield.hide();
			store.hide();
			quitGame.hide();
			shootPanel.remove(shootBullet);
			shootPanel.remove(shootGrenade);
			shootPanel.remove(activateShield);
			shootPanel.remove(store);
			shootPanel.remove(quitGame);
			
			for(int i = 0; i < colButtons.length; i++) {
				colButtons[i].hide();
				shootPanel.remove(colButtons[i]);
			}
			
			gameOverLabel = new JLabel("Game Over!");
			shootPanel.add(gameOverLabel);
			gameOverLabel.setBounds(90, 90, 150, 150);
			gameOverLabel.setFont(new Font("Open Sans", Font.BOLD, 22));
			gameOverLabel.setForeground(Color.RED);
			
			finalScoreLabel = new JLabel("Score: " + score);
			shootPanel.add(finalScoreLabel);
			finalScoreLabel.setBounds(110, 150, 100, 100);
			finalScoreLabel.setFont(new Font("Open Sans", Font.PLAIN, 18));
			
			redKillsLabel = new JLabel("Red Kills: " + redKills);
			shootPanel.add(redKillsLabel);
			redKillsLabel.setBounds(105, 180, 150, 100);
			redKillsLabel.setFont(new Font("Open Sans", Font.PLAIN, 18));
			
			shootPanel.add(tryAgain);
			
			tryAgain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameOverLabel.hide();
					scoreLabel.hide();
					redKillsLabel.hide();
					tryAgain.hide();
					finalScoreLabel.hide();
					coinLabel.hide();
					bulletLabel.hide();
					grenadeLabel.hide();
					shieldLabel.hide();
					healthBarLabel.hide();
					if(isFreeze == true) {
						freezePowerLabel.hide();
						shootPanel.remove(freezePowerLabel);
					}
					shootPanel.remove(scoreLabel);
					shootPanel.remove(coinLabel);
					shootPanel.remove(bulletLabel);
					shootPanel.remove(grenadeLabel);
					shootPanel.remove(shieldLabel);
					shootPanel.remove(healthBarLabel);
					shootPanel.remove(gameOverLabel);
					shootPanel.remove(finalScoreLabel);
					shootPanel.remove(redKillsLabel);
					shootPanel.remove(tryAgain);
					for(int i = 0; i < mapLabel.length; i++) {
						for(int j = 0; j < mapLabel[0].length; j++) {
							shootPanel.remove(mapLabel[i][j]);
						}
					}
					startGame();
				}
			});
		}
	}

 }

