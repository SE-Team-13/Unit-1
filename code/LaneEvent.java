
import java.util.HashMap;

public class LaneEvent {

	private final Party p;
	int ball;
	Bowler bowler;
	int[][] cumulScore;
	HashMap score;
	int index;
	int frameNum;
	int[] curScores;
	boolean mechProb;
	
	public LaneEvent( Party pty, int theIndex, Bowler theBowler, int[][] theCumulScore, HashMap theScore, int theFrameNum, int[] theCurScores, int theBall, boolean mechProblem) {
		p = pty;
		index = theIndex;
		bowler = theBowler;
		cumulScore = theCumulScore;
		score = theScore;
		curScores = theCurScores;
		frameNum = theFrameNum;
		ball = theBall;	
		mechProb = mechProblem;
	}

	public boolean isMechanicalProblem() {
		return mechProb;
	}
	
	public HashMap getScore( ) {
		return score;
	}

	public int[][] getCumulScore(){
		return cumulScore;
	}

	public Party getParty() {
		return p;
	}

	public Bowler getBowler() {
		return bowler;
	}
	public int getdata(int x)
	{
		if(x==2)
			return frameNum;
		if(x==3)
			return index;

		return ball;
	}
}
 
