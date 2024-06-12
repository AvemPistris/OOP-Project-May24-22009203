// Safiqur Rahman 22008929

import java.util.ArrayList;
import java.util.List;

public class Competition {
    private List<Match> matches;

    public Competition() {
        matches = new ArrayList<>();
    }

    public void add(Match match) {
        matches.add(match);
    }

    public void remove(Match match) {
        matches.remove(match);
    }

    public void print() {
        for (Match match : matches) {
            System.out.println("Round: " + match.getRoundNumber() + ", Result: " + match.getResult());
        }
    }

    public Match find(int roundNumber) {
        for (Match match : matches) {
            if (match.getRoundNumber() == roundNumber) {
                return match;
            }
        }
        return null;
    }
}
