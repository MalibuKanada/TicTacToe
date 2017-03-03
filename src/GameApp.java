import java.util.Scanner;
import java.util.Random;

public class GameApp {
    private Board board;
  //  private String gameState; // "PLAYING" "XWON" "OWON" "DRAW"
    private GameState gameState;
  //  private String activePlayer;
    private ActivePlayer activePlayer;
    private ComputerPlayer computerPlayer;

    private boolean isTwoPlayerGame;
    private Scanner scanner = new Scanner(System.in);

    private int playerXWinCount;
    private int playerOWinCount;



    public GameApp() {
        playerOWinCount = 0;
        playerXWinCount = 0;
        board = new Board();
        System.out.print("2 Player game(y/n)");
        String twoPlayer = scanner.nextLine();
        if (twoPlayer.equals("y")) {
            isTwoPlayerGame = true;
        }
        else {
            isTwoPlayerGame = false;
        }
        initGame(isTwoPlayerGame);

        do {
            gameLoop();

            System.out.println("\nX has won " + playerXWinCount + " times");
            System.out.println("O has won " + playerOWinCount + " times\n");
            System.out.print("New Game (Y/N) ");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("Y")) {
                System.out.print("Starting new game..\n\n");
                initGame(isTwoPlayerGame);
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
        computerPlayer = ComputerPlayer.O;
        board.init();
        activePlayer = ActivePlayer.X;

       // gameState = "PLAYING";
        gameState = GameState.PLAYING;
    }

    private void computerPlayerMove() {
        Random rand = new Random();
        boolean validMove = false;

        do {
            int row = rand.nextInt(3)+1;
            int column = rand.nextInt(3)+1;

            validMove = board.move(row,column,activePlayer.toString());
        } while(!validMove);



    }

    private void playerMove() {
        boolean validInput = false;

        do {
          System.out.print("Player " + activePlayer.toString() + " enter your row(1-3) ");
          int rowInput = scanner.nextInt();
          System.out.print("Player " + activePlayer.toString() + " enter your column(1-3) ");
          int columnInput = scanner.nextInt();

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
                playerXWinCount++;
                //gameState = "XWON";
            }
            else {
                //gameState = "OWON";
                gameState = GameState.O_WON;
                playerOWinCount++;
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
