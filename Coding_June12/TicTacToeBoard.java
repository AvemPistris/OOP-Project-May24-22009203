// Safiqur Rahman 22008929, Danish Harith 22009489, Muhammad Amin Zufar 24000162

public class TicTacToeBoard {
    private char[][] board;
    private Player player1;
    private Player player2;
    private char currentPlayerSymbol;

    public TicTacToeBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new char[3][3];
        this.currentPlayerSymbol = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayerSymbol;
            currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
            return true;
        }
        return false;
    }

    public char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        // No winner
        return '-';
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }
}
