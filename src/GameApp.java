import java.util.Scanner;

public class GameApp {
    private Board board;
  //  private String gameState; // "PLAYING" "XWON" "OWON" "DRAW"
    private GameState gameState;
    private String activePlayer;
    private Scanner scanner = new Scanner(System.in);

    public GameApp() {
        board = new Board();
        initGame(true);

        do {
            playerMove();
            board.paint();
            updateGame();
            if(gameState==GameState.X_WON) {
                System.out.println("Player X won");
            }
            if(gameState==GameState.O_WON) {
                System.out.println("Player O won");
            }
            if(gameState==GameState.DRAW) {
                System.out.println("Draw");
            }


            if(activePlayer.equals("X")) { // switch player
                activePlayer = "O";
            }
            else {
                activePlayer = "X";
            }
        } while (gameState==GameState.PLAYING);

        System.out.print("New Game (Y/N) ");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y")) {
            System.out.print("Starting new game..");
        }
    }

    private void initGame(boolean isTwoPlayerGame) {
        boolean isTwoPlayerGame1 = isTwoPlayerGame;
        board.init();
        activePlayer = "X";
       // gameState = "PLAYING";
        gameState = GameState.PLAYING;
    }

    private void playerMove() {
        boolean validInput = false;

        do {
          System.out.print("Player " + activePlayer + " enter your row(1-3) ");
          int rowInput = scanner.nextInt() - 1;
          System.out.print("Player " + activePlayer + " enter your column(1-3) ");
          int columnInput = scanner.nextInt() - 1;

          if(rowInput>=0 && rowInput<Board.ROWS && columnInput>=0 && columnInput<Board.COLUMNS && board.cells[rowInput][columnInput].getContent().equals(" ")){
              if (activePlayer.equals("X")) {
                  board.cells[rowInput][columnInput].putX();
              }

              if (activePlayer.equals("O")) {
                  board.cells[rowInput][columnInput].putO();
              }

              board.setCurrentRow(rowInput);
              board.setCurrentColumn(columnInput);

              validInput = true;
          }
          else {
              System.out.println("Move not valid try again");
          }

        } while(!validInput);


    }

    public void updateGame() {
        if(board.hasWon(activePlayer)) {
            if(activePlayer.equals("X")) {
                gameState = GameState.X_WON;
                //gameState = "XWON";
            }
            else {
                //gameState = "OWON";
                gameState = GameState.O_WON;
            }
        }
        if(board.isDraw()) {
            //gameState = "DRAW";
            gameState = GameState.DRAW;
        }

    }

    public static void main(String[] args) {
        new GameApp();
    }
}
