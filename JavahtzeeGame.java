import java.util.*;

public class JavahtzeeGame {
	
	private int[] numbers;
	private boolean[] locked;
	private boolean javahtzee;
	private int rollCount;
	private int bonusahtzeeScore;
	
	public JavahtzeeGame() {
		numbers = new int[5];
		locked = new boolean[5];
		javahtzee = false;
		rollCount = 3;
	}
	
	public void roll() {
		for (int i = 0; i < 5; i++) {
			if (!locked[i]) {
				numbers[i] = (int)(Math.random()*6+1);
			}
		}
		rollCount--;
	}
	
	public void zeroRollCount() {
		rollCount = 0;
	}
	
	public int getRollCount() {
		return rollCount;
	}
	
	public void resetRollCount() {
		rollCount = 3;
	}
	
	public int[] getNumbers() {
		return numbers;
	}
	
	public void setLock(int position) {
		locked[position] = true;
	}
	
	public void setUnlock(int position) {
		locked[position] = false;
	}
	
	public int getStandardScore(int n) {
		int result = 0;
		for (int num : numbers) {
			if (num == n) {
				result = result + n;
			}
		}
		return result;
	}
	
	public int getThreeOfAKindScore() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int num : numbers) {
			nums.add(num);
		}
		Collections.sort(nums);
		if (nums.get(0) == nums.get(1) && nums.get(1) == nums.get(2)) {
			return getChanceScore();
		} else if (nums.get(1) == nums.get(2) && nums.get(2) == nums.get(3)) {
			return getChanceScore();
		} else if (nums.get(2) == nums.get(3) && nums.get(3) == nums.get(4)) {
			return getChanceScore();
		} else {
			return 0;
		}
	} 
	
	public int getFourOfAKindScore() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int num : numbers) {
			nums.add(num);
		}
		Collections.sort(nums);
		if (nums.get(0) == nums.get(1) && nums.get(1) == nums.get(2) && nums.get(2) == nums.get(3)) {
			return getChanceScore();
		} else if (nums.get(1) == nums.get(2) && nums.get(2) == nums.get(3) && nums.get(3) == nums.get(4)) {
			return getChanceScore();
		} else {
			return 0;
		}
	}

	public int getFullHouseScore() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int num : numbers) {
			nums.add(num);
		}
		Collections.sort(nums);
		if (nums.get(0) == nums.get(1) && nums.get(1) == nums.get(2) && nums.get(3) == nums.get(4)) {
			return 25;
		} else if (nums.get(0) == nums.get(1) && nums.get(2) == nums.get(3) && nums.get(3) == nums.get(4)) {
			return 25;
		} else {
			return 0;
		}
	}
	
	public int getSmallStraightScore() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int num : numbers) {
			nums.add(num);
		}
		if (nums.contains(1) && nums.contains(2) && nums.contains(3) && nums.contains(4)) {
			return 30;
		} else if (nums.contains(2) && nums.contains(3) && nums.contains(4) && nums.contains(5)) {
			return 30;
		} else if (nums.contains(3) && nums.contains(4) && nums.contains(5) && nums.contains(6)) {
			return 30;
		} else {
			return 0;
		}
	}
	
	public int getLargeStraightScore() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int num : numbers) {
			nums.add(num);
		}
		Collections.sort(nums);
		if (nums.get(0) + 1 == nums.get(1) && nums.get(1) + 1 == nums.get(2) && nums.get(2) + 1 == nums.get(3) && nums.get(3) + 1 == nums.get(4)) {
			return 40;
		} else {
			return 0;
		}
	}
	
	public int getJavahtzeeScore() {
		if (numbers[0] == numbers[1] && numbers[1] == numbers[2] && numbers[2] == numbers[3] && numbers[3] == numbers[4]) {
			javahtzee = true;
			return 50;
		} else {
			return 0;
		}
	}
	
	public int getChanceScore() {
		int result = 0;
		for (int num : numbers) {
			result = result + num;
		}
		return result;
	}
	
	public int getBonusahtzeeScore() {
		int score;
		if (!javahtzee) {
			score = 0;
		} else if (numbers[0] == numbers[1] && numbers[1] == numbers[2] && numbers[2] == numbers[3] && numbers[3] == numbers[4]) {
			score = 100;
		} else {
			score = 0;
		}
		bonusahtzeeScore += score;
		return bonusahtzeeScore;
	}		
}
