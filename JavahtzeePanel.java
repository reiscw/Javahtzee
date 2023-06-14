import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class JavahtzeePanel extends JPanel {
    private JToggleButton[] dice;
    private JButton rollButton;
    private JTextField[] singleDigitFields;
    private JButton[] singleDigitButtons;
    private JButton newGameButton;
    private JButton highScoresButton;
	private JButton undoButton;
    private JButton quitButton;
	private JTextField[] rightFields;
	private JTextField[] additionalLeftFields;
    private JLabel leftSubtotal;
    private JLabel bonus;
    private JLabel leftTotal;
    private JLabel rollsLeft;
    private JButton threeOfAKind;
    private JButton fourOfAKind;
    private JButton fullHouse;
    private JButton smallStraight;
    private JButton largeStraight;
    private JButton javahtzee;
    private JButton chance;
    private JButton bonusahtzee;
    private JLabel rightTotal;
    private JLabel gameTotal;
    private JButton lastButtonPressed;
    private JTextField lastFieldAltered;
    private JavahtzeeGame game;
    private ArrayList<HighScore> highScores;
    public static final int SMALL = 12;
    public static final int LARGE = 20;
    
    public JavahtzeePanel() {
		// load high scores from file
		highScores = new ArrayList<HighScore>();
		try {
			Scanner in = new Scanner(new File("scores.txt"));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] tokens = line.split(" ");
				int score = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				String date = tokens[2] + " " + tokens[3] + " " + tokens[4];
				highScores.add(new HighScore(score, name, date));
			}
		} catch (Exception e) {
			createNewScoresFile();
		}
		Collections.sort(highScores);
		// adjust size and set layout
        setPreferredSize(new Dimension (470, 451));
        setLayout(null);
        // construct, add, and set bounds for components
        // dice
        dice = new JToggleButton[5];
        String[] diceStrings = {"JA", "VA", "HT", "ZE", "E!"};
        for (int i = 0; i < 5; i++) {
			dice[i] = new JToggleButton(diceStrings[i], false);
			add(dice[i]);
			dice[i].setBounds(15+i*75, 330, 68, 68);
			dice[i].setEnabled(false);
			dice[i].setSelected(false);
			dice[i].setFont(new Font("Arial", Font.BOLD, LARGE));
		}
        // roll button
        rollButton = new JButton("ROLL");
        add(rollButton);
        rollButton.setBounds(390, 330, 68, 68);
        rollButton.setFont(new Font("Arial", Font.BOLD, 11));  
        // new game, high score, undo, and quit buttons
        newGameButton = new JButton("New Game");
        highScoresButton = new JButton("High Scores");
        undoButton = new JButton("Undo");
        quitButton = new JButton("Quit");
        add(newGameButton);
        add(highScoresButton);
        add(undoButton);
        add(quitButton);
        undoButton.setEnabled(false);
        newGameButton.setBounds(15, 410, 127, 25);
        highScoresButton.setBounds(147, 410, 127, 25);
        undoButton.setBounds(279, 410, 87, 25);
        quitButton.setBounds(371, 410, 87, 25);
        // single digit buttons
        singleDigitButtons = new JButton[6];
        String[] singleDigitButtonStrings = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes"};
        for (int i = 0; i < 6; i++) {
			singleDigitButtons[i] = new JButton(singleDigitButtonStrings[i]);
			add(singleDigitButtons[i]);
			singleDigitButtons[i].setBounds(15, 20+30*i, 100, 25);
			singleDigitButtons[i].setFont(new Font("Arial", Font.BOLD, SMALL));
		}
		// single digit fields on left side
        singleDigitFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
			singleDigitFields[i] = new JTextField(5);
			add(singleDigitFields[i]);
			singleDigitFields[i].setBounds(120, 20+30*i, 80, 25);
			singleDigitFields[i].setEditable(false);
		} 
		// additional left side fields
		additionalLeftFields = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			additionalLeftFields[i] = new JTextField(5);
			add(additionalLeftFields[i]);
			additionalLeftFields[i].setBounds(120, 200+30*i, 80, 25);
			additionalLeftFields[i].setEditable(false);
		}
		// additional left side labels
		leftSubtotal = new JLabel("Left subtotal:");
        bonus = new JLabel("Bonus:");
        leftTotal = new JLabel("Left total:");
        rollsLeft = new JLabel("Rolls left: ");
        add(leftSubtotal);
        add(bonus);
        add(leftTotal);
        add(rollsLeft);
        leftSubtotal.setBounds(15, 200, 100, 25);
        bonus.setBounds(15, 230, 100, 25);
        leftTotal.setBounds(15, 260, 100, 25);
        rollsLeft.setBounds(15, 290, 100, 25);
        leftSubtotal.setFont(new Font("Arial", Font.BOLD, SMALL));
        bonus.setFont(new Font("Arial", Font.BOLD, SMALL));
		leftTotal.setFont(new Font("Arial", Font.BOLD, SMALL));
		rollsLeft.setFont(new Font("Arial", Font.BOLD, SMALL));
        // right side fields
        rightFields = new JTextField[10];
        for (int i = 0; i < 10; i++) {
			rightFields[i] = new JTextField(5);
			add(rightFields[i]);
			rightFields[i].setBounds(380, 20+30*i, 80, 25);
			rightFields[i].setEditable(false);
		}        
        // right side buttons
        threeOfAKind = new JButton("Three of a kind");
        fourOfAKind = new JButton("Four of a kind");
        fullHouse = new JButton("Full House");
        smallStraight = new JButton("Small Straight");
        largeStraight = new JButton("Large Straight");
        javahtzee = new JButton("Javahtzee");
        chance = new JButton("Chance");
        bonusahtzee = new JButton("Bonusahtzee!");
        add(threeOfAKind);
        add(fourOfAKind);
        add(fullHouse);
        add(smallStraight);
        add(largeStraight);
        add(javahtzee);
        add(chance);
        add(bonusahtzee); 
        bonusahtzee.setEnabled(false);
        threeOfAKind.setBounds(235, 20, 140, 25);
        fourOfAKind.setBounds(235, 50, 140, 25);
        fullHouse.setBounds(235, 80, 140, 25);
        smallStraight.setBounds(235, 110, 140, 25);
        largeStraight.setBounds(235, 140, 140, 25);
        javahtzee.setBounds(235, 170, 140, 25);
        chance.setBounds(235, 200, 140, 25);
        bonusahtzee.setBounds(235, 230, 140, 25);
        threeOfAKind.setFont(new Font("Arial", Font.BOLD, SMALL));
        fourOfAKind.setFont(new Font("Arial", Font.BOLD, SMALL));
        fullHouse.setFont(new Font("Arial", Font.BOLD, SMALL));
        smallStraight.setFont(new Font("Arial", Font.BOLD, SMALL));
        largeStraight.setFont(new Font("Arial", Font.BOLD, SMALL));
        javahtzee.setFont(new Font("Arial", Font.BOLD, SMALL));
        chance.setFont(new Font("Arial", Font.BOLD, SMALL));
        bonusahtzee.setFont(new Font("Arial", Font.BOLD, SMALL));
        // additional right side labels
        rightTotal = new JLabel("Right total:");
        gameTotal = new JLabel("Game total:");
        add(rightTotal);
        add(gameTotal);
        rightTotal.setBounds(235, 260, 100, 25);
        gameTotal.setBounds(235, 290, 100, 25);
        rightTotal.setFont(new Font("Arial", Font.BOLD, SMALL));
        gameTotal.setFont(new Font("Arial", Font.BOLD, SMALL));
		// initialize game object
		game = new JavahtzeeGame();
        // action listeners
		newGameButton.addActionListener(e -> {
			try {
				if (doubleCheck()) {
					reset();
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});    
		highScoresButton.addActionListener(e -> {
			try {
				displayHighScores();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});    	
		undoButton.addActionListener(e -> {
			try {
				undo();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
        quitButton.addActionListener(e -> {
			try {
				if (doubleCheck()) {
					System.exit(0);
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});	
		rollButton.addActionListener(e -> {
			try {
				roll();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});	
		singleDigitButtons[0].addActionListener(e -> {
			try {
				score("ones");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});	
		singleDigitButtons[1].addActionListener(e -> {
			try {
				score("twos");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});	
		singleDigitButtons[2].addActionListener(e -> {
			try {
				score("threes");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});		
		singleDigitButtons[3].addActionListener(e -> {
			try {
				score("fours");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});
		singleDigitButtons[4].addActionListener(e -> {
			try {
				score("fives");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});	
		singleDigitButtons[5].addActionListener(e -> {
			try {
				score("sixes");
			} catch (Exception exc) {
					exc.printStackTrace();
			}
		});
		threeOfAKind.addActionListener(e -> {
			try {
				score("toak");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
		fourOfAKind.addActionListener(e -> {
			try {
				score("foak");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
		fullHouse.addActionListener(e -> {
			try {
				score("fh");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
		smallStraight.addActionListener(e -> {
			try {
				score("ss");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
		largeStraight.addActionListener(e -> {
			try {
				score("ls");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});	
		javahtzee.addActionListener(e -> {
			try {
				score("javahtzee");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
		chance.addActionListener(e -> {
			try {
				score("chance");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});	
		bonusahtzee.addActionListener(e -> {
			try {
				score("bj");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		});
    }

	public void createNewScoresFile() {
		// create new file
		File scores = new File("scores.txt");
		try {
			scores.createNewFile();
		} catch (Exception f) {
			System.exit(0);
		}
	}
	
	public boolean danger() {
		boolean danger = false;
		for (int i = 0; i < 6; i++) {
			if (singleDigitButtons[i].isEnabled()) {
				danger = true;
			}
		}
		if (threeOfAKind.isEnabled()) {
			danger = true;
		} else if (fourOfAKind.isEnabled()) {
			danger = true;
		} else if (fullHouse.isEnabled()) {
			danger = true;
		} else if (smallStraight.isEnabled()) {
			danger = true;
		} else if (largeStraight.isEnabled()) {
			danger = true;
		} else if (javahtzee.isEnabled()) {
			danger = true;
		} else if (chance.isEnabled()) {
			danger = true;
		}
		return danger;
	}

	public boolean doubleCheck() {
		if (danger()) {
			String message = "Are you sure? Your game is not over!";
			int result = JOptionPane.showConfirmDialog(null,  message, "Careful!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public void reset() {
		// initialize dice to their standard "JAVAHTZEE!" message
		String[] diceStrings = {"JA", "VA", "HT", "ZE", "E!"};
		for (int i = 0; i < 5; i++) {
			dice[i].setIcon(null);
			dice[i].setText(diceStrings[i]);
			dice[i].setEnabled(false);
			dice[i].setSelected(false);
			dice[i].setFont(new Font("Arial", Font.BOLD, LARGE));
		}
		// set all of the buttons to be enabled
		for (int i = 0; i < 6; i++) {
			singleDigitButtons[i].setEnabled(true);
		}
		undoButton.setEnabled(false);
		rollButton.setEnabled(true);
		newGameButton.setEnabled(true);
        highScoresButton.setEnabled(true);
        quitButton.setEnabled(true);
		threeOfAKind.setEnabled(true);
        fourOfAKind.setEnabled(true);
        fullHouse.setEnabled(true);
        smallStraight.setEnabled(true);
        largeStraight.setEnabled(true);
        javahtzee.setEnabled(true);
        chance.setEnabled(true);
        bonusahtzee.setEnabled(false);		
		// clear all the fields
		for (int i = 0; i < 6; i++) {
			singleDigitFields[i].setText(null);
		}
		for (int i = 0; i < 10; i++) {
			rightFields[i].setText(null);
		}
		for (int i = 0; i < 4; i++) {
			additionalLeftFields[i].setText(null);
		}
		// initialize a new Game object
		game = new JavahtzeeGame();
		additionalLeftFields[3].setText("3");
	}
	
	public void roll() {
		undoButton.setEnabled(false);
		// make sure the dice are enabled
		for (int i = 0; i < 5; i++) {
			dice[i].setEnabled(true);
		}
		// set all dice to unselected IF rollCount = 3
		if (game.getRollCount() == 3) {
			for (int i = 0; i < 5; i++) {
				dice[i].setSelected(false);
			}
		}
		// lock the appropriate dice
		for (int i = 0; i < 5; i++) {
			if (dice[i].isSelected()) {
				game.setLock(i);
			} else {
				game.setUnlock(i);
			}
		}
		// call the roll method in the game and update the roll count on screen
		game.roll();
		additionalLeftFields[3].setText("" + game.getRollCount());
		// set the icons appropriately
		for (int i = 0; i < 5; i++) {
			dice[i].setText(null);
			if (!dice[i].isSelected()) {
				int number = game.getNumbers()[i];
				switch (number) {
					case 1: dice[i].setIcon(new ImageIcon(Dice.one(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.one(true))); break;
					case 2: dice[i].setIcon(new ImageIcon(Dice.two(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.two(true))); break;
					case 3: dice[i].setIcon(new ImageIcon(Dice.three(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.three(true))); break;
					case 4: dice[i].setIcon(new ImageIcon(Dice.four(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.four(true))); break;
					case 5: dice[i].setIcon(new ImageIcon(Dice.five(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.five(true))); break;
					case 6: dice[i].setIcon(new ImageIcon(Dice.six(false))); dice[i].setSelectedIcon(new ImageIcon(Dice.six(true))); break;
				}
			}
		}
		if (game.getRollCount() == 0) {
			rollButton.setEnabled(false);
		}	
	}
	
	public void score(String type) {
		// check for rollCount = 3, if so, do nothing
		if (game.getRollCount() == 3) return;
		// compute score and deactivate relevant button
		switch (type) {
			case "ones":
				int score = game.getStandardScore(1);
				singleDigitFields[0].setText("" + score);
				singleDigitButtons[0].setEnabled(false);
				break;
			case "twos":
				score = game.getStandardScore(2);
				singleDigitFields[1].setText("" + score);
				singleDigitButtons[1].setEnabled(false);
				break;
			case "threes":
				score = game.getStandardScore(3);
				singleDigitFields[2].setText("" + score);
				singleDigitButtons[2].setEnabled(false);
				break;
			case "fours":
				score = game.getStandardScore(4);
				singleDigitFields[3].setText("" + score);
				singleDigitButtons[3].setEnabled(false);
				break;
			case "fives":
				score = game.getStandardScore(5);
				singleDigitFields[4].setText("" + score);
				singleDigitButtons[4].setEnabled(false);
				break;
			case "sixes":
				score = game.getStandardScore(6);
				singleDigitFields[5].setText("" + score);
				singleDigitButtons[5].setEnabled(false);
				break;
			case "toak":
				score = game.getThreeOfAKindScore();
				rightFields[0].setText("" + score);
				threeOfAKind.setEnabled(false);
				break;
			case "foak":
				score = game.getFourOfAKindScore();
				rightFields[1].setText("" + score);
				fourOfAKind.setEnabled(false);
				break;
			case "fh":
				score = game.getFullHouseScore();
				rightFields[2].setText("" + score);
				fullHouse.setEnabled(false);
				break;
			case "ss":
				score = game.getSmallStraightScore();
				rightFields[3].setText("" + score);
				smallStraight.setEnabled(false);
				break;
			case "ls":
				score = game.getLargeStraightScore();
				rightFields[4].setText("" + score);
				largeStraight.setEnabled(false);
				break;
			case "javahtzee":
				score = game.getJavahtzeeScore();
				rightFields[5].setText("" + score);
				javahtzee.setEnabled(false);
				// activate bonus yahtzee button if necessary
				if (score == 50) {
					bonusahtzee.setEnabled(true);
				}
				break;
			case "chance":
				score = game.getChanceScore();
				rightFields[6].setText("" + score);
				chance.setEnabled(false);
				break;
			case "bj":
				// check that all numbers match, if not, do nothing
				int[] nums = game.getNumbers();
				if (!(nums[0] == nums[1] && nums[1] == nums[2] && nums[2] == nums[3] && nums[3] == nums[4])) {
					return;
				}
				score = game.getBonusahtzeeScore();
				rightFields[7].setText("" + score);
				break;
		}
		if (gameStillValid()) {
			nextRoll();
			// enable and setup undo
			switch (type) {
				case "ones": 
					lastButtonPressed = singleDigitButtons[0];
					lastFieldAltered = singleDigitFields[0];
					break;
				case "twos": 
					lastButtonPressed = singleDigitButtons[1];
					lastFieldAltered = singleDigitFields[1];
					break;
				case "threes": 
					lastButtonPressed = singleDigitButtons[2];
					lastFieldAltered = singleDigitFields[2];
					break;
				case "fours": 
					lastButtonPressed = singleDigitButtons[3];
					lastFieldAltered = singleDigitFields[3];
					break;
				case "fives": 
					lastButtonPressed = singleDigitButtons[4];
					lastFieldAltered = singleDigitFields[4];
					break;
				case "sixes": 
					lastButtonPressed = singleDigitButtons[5];
					lastFieldAltered = singleDigitFields[5];
					break;
				case "toak": 
					lastButtonPressed = threeOfAKind;
					lastFieldAltered = rightFields[0];
					break;
				case "foak": 
					lastButtonPressed = fourOfAKind;
					lastFieldAltered = rightFields[1];
					break;
				case "fh": 
					lastButtonPressed = fullHouse;
					lastFieldAltered = rightFields[2];
					break;
				case "ss":
					lastButtonPressed = smallStraight;
					lastFieldAltered = rightFields[3];
					break;
				case "ls":
					lastButtonPressed = largeStraight;
					lastFieldAltered = rightFields[4];
					break;
				case "javahtzee":
					lastButtonPressed = javahtzee;
					lastFieldAltered = rightFields[5];
					break;
				case "chance":
					lastButtonPressed = chance;
					lastFieldAltered = rightFields[6];
					break;
				case "bj":
					undoButton.setEnabled(false);
					break;
			}
			if (!type.equals("bj")) {
				undoButton.setEnabled(true);
			}
		}
		// update score totals and lock the dice
		updateScore();
		// lock the dice
		for (int j = 0; j < 5; j++) {
			dice[j].setEnabled(false);
		}
	}
	
	public void nextRoll() {
		rollButton.setEnabled(true);
		game.resetRollCount();
		additionalLeftFields[3].setText("" + 3);
	}
	
	public void updateScore() {
		// left subtotal
		int leftSum = 0;
		for (int i = 0; i < 6; i++) {
			try {
				leftSum += Integer.parseInt(singleDigitFields[i].getText());
			} catch(Exception e) {
				leftSum += 0;
			}
		}
		additionalLeftFields[0].setText("" + leftSum);
		// bonus
		int lst = Integer.parseInt(additionalLeftFields[0].getText());
		if (lst >= 63) {
			additionalLeftFields[1].setText("" + 35);
		} else {
			additionalLeftFields[1].setText(null);
		}
		// left total
		if (additionalLeftFields[1].getText().equals("35")) {
			additionalLeftFields[2].setText("" + (lst + 35));
		} else {
			additionalLeftFields[2].setText("" + lst);
		}
		// right total
		int rightSum = 0;
		for (int i = 0; i < 8; i++) {
			if (rightFields[i].getText() != null && !rightFields[i].getText().equals("")) {
				rightSum += Integer.parseInt(rightFields[i].getText());
			}
		}
		rightFields[8].setText("" + rightSum);
		// game total
		int left = 0;
		int right = 0;
		if (rightFields[8].getText() != null && !rightFields[8].getText().equals("")) {
			right = Integer.parseInt(rightFields[8].getText());
		}
		if (additionalLeftFields[2].getText() != null && !additionalLeftFields[2].getText().equals("")) {
			left = Integer.parseInt(additionalLeftFields[2].getText());
		}
		rightFields[9].setText("" + (left + right));
	}
	
	public boolean gameStillValid() {
		if (danger()) return true;
		bonusahtzee.setEnabled(false);
		updateScore();
		int score = Integer.parseInt(rightFields[9].getText());
		endGame(score);
		return false;
	}
	
	public void endGame(int score) {
		rollButton.setEnabled(false);
		undoButton.setEnabled(false);
		String message = "Your final Javahtzee score is " + score;
		if (score >= 300) {
			message = message + "\n" + "Wow, you broke 300!";
		}
		JOptionPane.showConfirmDialog(null, message, "Game Over!", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
		checkHighScore(score);
	}
	
	public void checkHighScore(int score) {
		boolean success = false;
		// check if there are less than 10 high scores
		if (highScores.size() < 10) {
			success = true;
		} else { // check if new score is a high score
			Collections.sort(highScores);
			if (score > highScores.get(0).getScore()) {
				// if so, remove score 0
				highScores.remove(0);
				success = true;
			}
		}
		// add high score if necessary
		if (success) {
			// ask user for name
			JTextField nameEntry = new JTextField();
			Object[] message = {"Enter your name: ", nameEntry};
			int result = JOptionPane.showConfirmDialog(null,  message, "You got a high score!", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
			String name = "";
			if (result == JOptionPane.OK_OPTION) {
				name = nameEntry.getText();
			} else {
				return;
			}
			// create new high score entry
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			HighScore hs = new HighScore(score, name, formatter.format(date));
			// add high score to high scores list and sort the list
			highScores.add(hs);
			Collections.sort(highScores);
			// write list to file and display high scores
 			writeHighScores();
			displayHighScores();
		}
	}
	
	public void displayHighScores() {
		String message = "<HTML>";
		boolean offerDelete = true;
		if (highScores.size() < 10) {
			offerDelete = false;
		}
		for (HighScore hs : highScores) {
			if (hs.getScore() < 300) {
				offerDelete = false;
			}
			message += String.format("%6d %20s %16s", hs.getScore(), hs.getName(), hs.getDate());
			message += "<BR>";
		}
		JLabel label = new JLabel(message);
		label.setFont(new Font("Monospace", Font.BOLD, 12));
		JOptionPane.showConfirmDialog(null, label, "Javahtzee High Scores", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (offerDelete) {
			String message2 = "Do you want to reset your high scores?";
			int result = JOptionPane.showConfirmDialog(null,  message2, "You are the master of Javahtzee!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				highScores.clear();
				writeHighScores();
			} 
		}
	} 

	public void writeHighScores() {
		try {
			FileWriter writer = new FileWriter("scores.txt");
			for (HighScore hs : highScores) {
				writer.write(hs.getScore() + " " + hs.getName() + " " + hs.getDate() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		// check first for nullity 
		if (lastButtonPressed != null && lastFieldAltered != null) {
			// turn back on the last button that was turned off, and reset the last field changed to be blank
			lastButtonPressed.setEnabled(true);
			lastFieldAltered.setText(null);
			// clear the last data so the undo button cannot be pressed again
			lastButtonPressed = null;
			lastFieldAltered = null;
			// turn off the undo button
			undoButton.setEnabled(false);
			// set roll count to zero and update the roll count on screen
			game.zeroRollCount();
			additionalLeftFields[3].setText("" + game.getRollCount());
			// turn off the roll button
			rollButton.setEnabled(false);
			// if it was a jahtzee, turn off the bonusjahtzee button
			if (lastButtonPressed == javahtzee) {
				bonusahtzee.setEnabled(false);
			}
			// turn the dice back on and unselected
			for (int i = 0; i < 5; i++) {
				dice[i].setEnabled(true);
				dice[i].setSelected(false);
			}
			// recompute the scores
			updateScore();
		}
	}

    public static void main (String[] args) throws FileNotFoundException {
	        JFrame frame = new JFrame("Javahtzee 4.5 by Christopher Reis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JavahtzeePanel panel = new JavahtzeePanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
