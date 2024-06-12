// Muhammad Solihin 22009201

import java.util.List;

public class Match {
    private List<Player> players;
    private String result;
    private int roundNumber;

    public Match() {
        // Default constructor
    }

    public Match(List<Player> players, String result, int roundNumber) {
        this.players = players;
        this.result = result;
        this.roundNumber = roundNumber;
    }

    // Getters and setters
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public int getRoundNumber() { return roundNumber; }
    public void setRoundNumber(int roundNumber) { this.roundNumber = roundNumber; }
}
