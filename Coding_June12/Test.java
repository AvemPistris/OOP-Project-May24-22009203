// Adam Marwan 22009203, Muhammad Solihin 22009201

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create players
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 1 email: ");
        String player1Email = scanner.nextLine();
        Player player1 = new Player(player1Name, player1Email, 1, 5, 100);

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        System.out.print("Enter Player 2 email: ");
        String player2Email = scanner.nextLine();
        Player player2 = new Player(player2Name, player2Email, 2, 7, 150);

        // Create Players list
        Players players = new Players();
        players.add(player1);
        players.add(player2);
        players.print();

        // Create a Tic Tac Toe Board
        TicTacToeBoard board = new TicTacToeBoard(player1, player2);

        // Simulate a game
        board.printBoard();
        while (true) {
            System.out.print("Enter column (0-2) for player " + (board.getCurrentPlayerSymbol() == 'X' ? player1.getName() : player2.getName()) + ": ");
            int row = scanner.nextInt();
            System.out.print("Enter row (0-2) for player " + (board.getCurrentPlayerSymbol() == 'X' ? player1.getName() : player2.getName()) + ": ");
            int col = scanner.nextInt();

            if (board.makeMove(row, col)) {
                board.printBoard();
                char winner = board.checkWinner();
                if (winner != '-') {
                    System.out.println("Winner is: " + (winner == 'X' ? player1.getName() : player2.getName()));
                    break;
                }
            } else {
                System.out.println("Invalid move, try again.");
            }

            // Check for a draw
            if (board.isBoardFull()) {
                System.out.println("The game is a draw.");
                break;
            }
        }

        // Create a match
        String result = board.checkWinner() != '-' ? (board.checkWinner() == 'X' ? player1.getName() + " wins" : player2.getName() + " wins") : "Draw";
        Match match = new Match(players.getList(), result, 1);

        // Create a competition
        Competition competition = new Competition();
        competition.add(match);
        competition.print();

        // Create a game server
        GameServer gameServer = new GameServer();
        gameServer.add(competition);
        gameServer.print();

        scanner.close();
    }
}
