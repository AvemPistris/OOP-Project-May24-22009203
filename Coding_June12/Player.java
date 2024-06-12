// Adam Marwan 22009203

public class Player {
    private String name;
    private String emailAddress;
    private int id;
    private int level;
    private int points;

    public Player() {
        // Default constructor
    }

    public Player(String name, String emailAddress, int id, int level, int points) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.id = id;
        this.level = level;
        this.points = points;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
