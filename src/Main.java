import java.util.Scanner;

public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];
    private static String currentPlayer;

    public static void main(String[] args) {
        boolean playersDone = false;
        Scanner in = new Scanner(System.in);

        do {
            clearBoard();
            currentPlayer = "X";

            do {
                display();
                int[] move = getPlayerMove(in);
                int row = move[0];
                int col = move[1];

                // Check if the move is valid
                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Please enter a valid move.");
                    move = getPlayerMove(in);
                    row = move[0];
                    col = move[1];
                }

                // Update the board with the player's move
                board[row][col] = currentPlayer;

                // Check for win or tie
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins! Poggers!");
                    break;
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    break;
                }

                // Toggle player for the next turn
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            } while (true);

            playersDone = SafeInput.getYNConfirm(in, "Are you finished playing?");

        } while (!playersDone);

        System.out.println("Thank you for playing");
        in.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("  1   2   3");
        for (int i = 0; i < ROW; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < COL; j++) {
                System.out.print(" " + board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" |");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    private static int[] getPlayerMove(Scanner console) {
        System.out.println("Player " + currentPlayer + ", please enter your move (row and column):");
        int row = SafeInput.getRangedInt(console, "Please enter a row (1-3): ", 1, 3) - 1;
        int col = SafeInput.getRangedInt(console, "Please enter a column (1-3): ", 1, 3) - 1;
        return new int[]{row, col};
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean playAgain(Scanner console) {
        return SafeInput.getYNConfirm(console, "Would you like to play again?");
    }
}