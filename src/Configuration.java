/**
 * This class stores a string configuration of the game board and the score of that configuration.
 *
 * @author Tyler Bulley
 * UWO ID: 250498520
 *
 */
public class Configuration {
	
	String config;
	int score;
	
	public Configuration(String config, int score) {
		
		
	this.config = config;
	this.score = score;
		
	}
	
	public String getStringConfiguration() {
		
		return config;
		
	}
	
	public int getScore() {
		
		return score;
		
	}

}
