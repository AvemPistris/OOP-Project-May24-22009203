// Muhammad Amin Zufar 24000162

//import java.util.ArrayList;//
import java.util.*;

public class Players {
    private List<Player> playerList;

    public Players() {
        playerList = new ArrayList<>();
    }

    public void add(Player player) {
        playerList.add(player);
    }

    public void remove(Player player) {
        playerList.remove(player);
    }

    public void print() {
        for (Player player : playerList) {
            System.out.println("Player ID: " + player.getId() + ", Name: " + player.getName());
        }
    }

    public Player find(int id) {
        for (Player player : playerList) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getList() {
        return playerList;
    }
}
