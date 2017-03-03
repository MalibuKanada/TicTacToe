import java.util.Scanner;

public class GameApp {
    private Board board;
  //  private String gameState; // "PLAYING" "XWON" "OWON" "DRAW"
    private GameState gameState;
  //  private String activePlayer;
    private ActivePlayer activePlayer;
    private ComputerPlayer computerPlayer;

    private boolean isTwoPlayerGame;
    private Scanner scanner = new Scanner(System.in);

    public GameApp() {
        board = new Board();
        initGame(true);

        do {
            gameLoop();

            System.out.print("New Game (Y/N) ");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("Y")) {
                System.out.print("Starting new game..\n\n");
                initGame(true);
            }
            else {
                break;
            }

        } while(true);

    }

    private void gameLoop() {
        do {
            if(isTwoPlayerGame) {
                playerMove();
            }
            else {
                if(activePlayer.toString() == computerPlayer.toString()) {
                    computerPlayerMove();
                }
                else {
                    playerMove();
                }
            }
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


            if(activePlayer==ActivePlayer.X) { // switch player
                activePlayer = ActivePlayer.O;
            }
            else {
                activePlayer = ActivePlayer.X;
            }

        } while (gameState==GameState.PLAYING);
    }

    private void initGame(boolean isTwoPlayerGame) {
        this.isTwoPlayerGame = isTwoPlayerGame;
        board.init();
        activePlayer = ActivePlayer.X;
        if(isTwoPlayerGame) {
            computerPlayer = ComputerPlayer.O;
        }
       // gameState = "PLAYING";
        gameState = GameState.PLAYING;
    }

    private void computerPlayerMove() {

    }

    private void playerMove() {
        boolean validInput = false;

        do {
          System.out.print("Player " + activePlayer.toString() + " enter your row(1-3) ");
          int rowInput = scanner.nextInt() - 1;
          System.out.print("Player " + activePlayer.toString() + " enter your column(1-3) ");
          int columnInput = scanner.nextInt() - 1;

          if(board.move(rowInput, columnInput, activePlayer.toString())){
              validInput = true;
          }
          else {
              System.out.println("Move not valid try again");
          }

        } while(!validInput);


    }



    public void updateGame() {
        if(board.hasWon(activePlayer.toString())) {
            if(activePlayer==ActivePlayer.X) {
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
