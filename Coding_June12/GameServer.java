// Danish Harith 22009489

import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private List<Competition> competitions;

    public GameServer() {
        competitions = new ArrayList<>();
    }

    public void add(Competition competition) {
        competitions.add(competition);
    }

    public void remove(Competition competition) {
        competitions.remove(competition);
    }

    public void print() {
        for (int i = 0; i < competitions.size(); i++) {
            System.out.println("Competition " + (i + 1) + ":");
            competitions.get(i).print();
        }
    }

    public Competition find(int index) {
        if (index >= 0 && index < competitions.size()) {
            return competitions.get(index);
        }
        return null;
    }
}
