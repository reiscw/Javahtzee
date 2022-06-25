public class HighScore implements Comparable {
	
	private int score;
	private String name;
	private String date;
	
	public HighScore(int score, String name, String date) {
		this.score = score;
		this.name = name;
		this.date = date;
	}
	
	public int compareTo(Object other) {
		int a = this.getScore();
		int b = ((HighScore)other).getScore();
		return a - b;
	}
	
	public int getScore() {return score;}
	public String getName() {return name;}
	public String getDate() {return date;}
}
