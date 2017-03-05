package Tic;

import java.util.Scanner;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javafx.animation.RotateTransition;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.util.Map;
import java.util.Properties;

import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.picking.PickIntersection;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;


import com.sun.j3d.utils.geometry.Box;



public class GameApp3D {
    private Board board;

    private GameState gameState;

    private ActivePlayer activePlayer;
    private ComputerPlayer computerPlayer;

    private boolean isTwoPlayerGame;
    private Scanner scanner = new Scanner(System.in);

    private int playerXWinCount;
    private int playerOWinCount;

    public int aaa = 0;

    private MainWindow mainWindow;

    public GameApp3D() {

        /*
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas = new Canvas3D(config);
        add("North", new Label("This is the top"));
        add("Center", canvas);
        add("South", new Label("This is the bottom"));

        SimpleUniverse universe = new SimpleUniverse(canvas);
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.addChild(new ColorCube(0.3));
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(branchGroup);

*/

        mainWindow  = new MainWindow("Tic Tac Toe");

        mainWindow.regApp(this);
        playerOWinCount = 0;
        playerXWinCount = 0;
        board = new Board();

        isTwoPlayerGame = askIfTwoPlayerGame();
        initGame(isTwoPlayerGame);

        gameLoop();







    }

    private boolean askIfTwoPlayerGame() {
        Object[] options = {"1 Spieler", "2 Spieler"};
        int choice = JOptionPane.showOptionDialog(mainWindow, "1 oder 2 Spieler?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            return false;
        }
        else {
            return true;
        }

    }

    private boolean isMoveDone;


    private void gameLoop() {

        do {

            /*
            isMoveDone = false;
            System.out.print("set to false: " + isMoveDone);
            do {

            } while(!isMoveDone);

            System.out.println("Move Done");
*/
        } while (gameState == GameState.PLAYING);

    }

    private void initGame(boolean isTwoPlayerGame) {
        this.isTwoPlayerGame = isTwoPlayerGame;
        computerPlayer = ComputerPlayer.O;
        board.init();
        activePlayer = ActivePlayer.X;
//        mainWindow.resetMove();

        // gameState = "PLAYING";
        board.paint();
        updateGame();

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

    private void refreshAll() {
        board.paint();
        updateGame();
        String message;
        if(gameState!=GameState.PLAYING) {
            if(gameState==GameState.X_WON) {
                message = "X hat gewonnen!";
                System.out.println(message);
                JOptionPane.showMessageDialog(mainWindow, message);

            }
            else if(gameState==GameState.O_WON) {
                message = "O hat gewonnen!";
                System.out.println(message);
                JOptionPane.showMessageDialog(mainWindow, message);
            }
            else if(gameState==GameState.DRAW) {
                message = "Unentschieden!";
                System.out.println(message);
            }

            Object[] options = {"Ja", "Nein"};
            int choice = JOptionPane.showOptionDialog(mainWindow, "Neues Spiel", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == JOptionPane.YES_OPTION) {
                isTwoPlayerGame = askIfTwoPlayerGame();
                initGame(isTwoPlayerGame);
                mainWindow.addEvent();
                gameLoop();
            }
            else {
                System.exit(0);
            }



        }



        if(activePlayer==ActivePlayer.X) { // switch player
            activePlayer = ActivePlayer.O;
        }
        else {
            activePlayer = ActivePlayer.X;
        }
    }



    public void playerMove(int rowInput, int columnInput) {


        if(isTwoPlayerGame) {
            if(board.move(rowInput, columnInput, activePlayer.toString())){
                System.out.println("Spieler " + activePlayer.toString() + " setzt auf Zeile " + rowInput + " Spalte "+ columnInput);
                refreshAll();
            }
            else {
                System.out.println("Move not valid try again");
            }

        }

    }

    public void test() {
        System.out.println("test");
    }

    public void updateGame() {
        if(board.hasWon(activePlayer.toString())) {
            if(activePlayer==ActivePlayer.X) {
                gameState = GameState.X_WON;
                playerXWinCount++;
                mainWindow.incXWon();
                //gameState = "XWON";
            }
            else {
                //gameState = "OWON";
                gameState = GameState.O_WON;
                playerOWinCount++;
                mainWindow.incOWon();
            }
        }
        if(board.isDraw()) {
            //gameState = "DRAW";
            gameState = GameState.DRAW;
        }

    }

    public static void main(String[] args) {

        GameApp3D app = new GameApp3D();

    }
}

