package Tic;

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




public class MainWindow extends JFrame{

    private static final Color3f SPECULAR_LIGHT_COLOR = new Color3f(Color.WHITE);
    private static final Color3f AMBIENT_LIGHT_COLOR = new Color3f(Color.LIGHT_GRAY);
    private static final Color3f EMISSIVE_LIGHT_COLOR = new Color3f(Color.BLACK);
    public PickCanvas pickCanvas;
    private SimpleUniverse universe = null;
    private Scene woodenTable;
    public BranchGroup branchGroup;

    public GameApp3D app;
    public Canvas3D canvas;


    public void regApp(GameApp3D gameApp3D) {
        this.app = gameApp3D;
    }

    private int Xwon = 0;
    private int Owon = 0;

    JLabel footerText;
    JPanel footerPanel;

    public void incXWon(){
        Xwon++;
        footerText.setText("X hat gewonnen " + Xwon + " mal -- O hat gewonnen: " + Owon + " mal");

    }

    public void incOWon(){
        Owon++;
        footerText.setText("X hat gewonnen " + Xwon + " mal -- O hat gewonnen: " + Owon + " mal");


    }


    public void refresh() {
        footerPanel.repaint();
    }


    public MainWindow(String title) {
        super(title);

       System.setProperty("sun.awt.noerasebackground", "true"); //verhindert Grafikfehler wie flackern etc, ohne diese einstellung starke Anzeigefehler, weißer background etc


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new Canvas3D(config);
        universe = new SimpleUniverse(canvas);
        setLayout(new BorderLayout());

/*
        JButton button1 = new JButton("Player vs Player");
        JButton button2 = new JButton("Player vs Computer");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.setBackground(new Color(100,100,100));
*/
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(200,200,200));

        footerText = new JLabel("X hat gewonnen " + Xwon + " mal -- O hat gewonnen: " + Owon + " mal");
        footerPanel.add(footerText);

//3d import
/*
        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();

        try {
            Scene s = null;
            ObjectFile f = new ObjectFile ();
            f.setFlags (ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);

            String s1 = "src/Wood_Table.obj";
            s = f.load (s1);
            tg.addChild (s.getSceneGroup ());

        }

        catch (java.io.FileNotFoundException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);

        }
        catch (com.sun.j3d.loaders.ParsingErrorException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

*/      Appearance woodTextureAppearance = null;
        Scene scene = null;

        branchGroup = new BranchGroup();
        try {
            scene = getSceneFromFile("src/table.obj");
            listSceneNamedObjects(scene);

            woodTextureAppearance = getAppearance("src/Wood_Table_C.jpg", canvas, 2048);

        }
        catch(java.io.IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
        Map<String, Shape3D> nameMap = scene.getNamedObjects();
        Color darkgrey = new Color(200, 200, 200);
        Color white = new Color(255, 255, 255);
        Color red = new Color(255,0,0);
        Appearance whiteAppearance = getAppearance(white);
        Appearance redAppearance = getAppearance(red);
        Appearance darkgreyAppearance = getAppearance(darkgrey);
        nameMap.get("unnamed_group").setAppearance(woodTextureAppearance);
        branchGroup.addChild(scene.getSceneGroup());

      //  Box box = new Box(0.6291f,.01f,0.6291f, redAppearance);


        Transform3D transform = new Transform3D();
        Box box1 = new Box(0.2097f,.01f,0.2097f, darkgreyAppearance);
        Box box2 = new Box(0.2097f,.01f,0.2097f, redAppearance);
        Box box3 = new Box(0.2097f,.01f,0.2097f, darkgreyAppearance);
        Box box4 = new Box(0.2097f,.01f,0.2097f, redAppearance);
        Box box5 = new Box(0.2097f,.01f,0.2097f, darkgreyAppearance);
        Box box6 = new Box(0.2097f,.01f,0.2097f, redAppearance);
        Box box7 = new Box(0.2097f,.01f,0.2097f, darkgreyAppearance);
        Box box8 = new Box(0.2097f,.01f,0.2097f, redAppearance);
        Box box9 = new Box(0.2097f,.01f,0.2097f, darkgreyAppearance);

        box1.setPickable(true);
        box2.setPickable(true);
        box3.setPickable(true);
        box4.setPickable(true);
        box5.setPickable(true);
        box6.setPickable(true);
        box7.setPickable(true);
        box8.setPickable(true);
        box9.setPickable(true);

        box1.setCapability(Node.ENABLE_PICK_REPORTING);
        box2.setCapability(Node.ENABLE_PICK_REPORTING);
        box3.setCapability(Node.ENABLE_PICK_REPORTING);
        box4.setCapability(Node.ENABLE_PICK_REPORTING);
        box5.setCapability(Node.ENABLE_PICK_REPORTING);
        box6.setCapability(Node.ENABLE_PICK_REPORTING);
        box7.setCapability(Node.ENABLE_PICK_REPORTING);
        box8.setCapability(Node.ENABLE_PICK_REPORTING);
        box9.setCapability(Node.ENABLE_PICK_REPORTING);

        box1.setUserData("box1");
        box2.setUserData("box2");
        box3.setUserData("box3");
        box4.setUserData("box4");
        box5.setUserData("box5");
        box6.setUserData("box6");
        box7.setUserData("box7");
        box8.setUserData("box8");
        box9.setUserData("box9");








        TransformGroup boxTG1 = new TransformGroup();
        TransformGroup boxTG2 = new TransformGroup();
        TransformGroup boxTG3 = new TransformGroup();
        TransformGroup boxTG4 = new TransformGroup();
        TransformGroup boxTG5 = new TransformGroup();
        TransformGroup boxTG6 = new TransformGroup();
        TransformGroup boxTG7 = new TransformGroup();
        TransformGroup boxTG8 = new TransformGroup();
        TransformGroup boxTG9 = new TransformGroup();


        Vector3f vector1 = new Vector3f(-0.4194f,.7f,-0.4194f);
        Vector3f vector2 = new Vector3f(.0f,.7f,-0.4194f);
        Vector3f vector3 = new Vector3f(0.4194f,.7f,-0.4194f);
        Vector3f vector4 = new Vector3f(-0.4194f,.7f,.0f);
        Vector3f vector5 = new Vector3f(.0f,.7f,.0f);
        Vector3f vector6 = new Vector3f(0.4194f,.7f,.0f);
        Vector3f vector7 = new Vector3f(-0.4194f,.7f,0.4194f);
        Vector3f vector8 = new Vector3f(.0f,.7f,0.4194f);
        Vector3f vector9 = new Vector3f(0.4194f,.7f,0.4194f);

        transform.setTranslation(vector1);
        boxTG1.setTransform(transform);
        boxTG1.addChild(box1);

        transform.setTranslation(vector2);
        boxTG2.setTransform(transform);
        boxTG2.addChild(box2);

        transform.setTranslation(vector3);
        boxTG3.setTransform(transform);
        boxTG3.addChild(box3);

        transform.setTranslation(vector4);
        boxTG4.setTransform(transform);
        boxTG4.addChild(box4);

        transform.setTranslation(vector5);
        boxTG5.setTransform(transform);
        boxTG5.addChild(box5);

        transform.setTranslation(vector6);
        boxTG6.setTransform(transform);
        boxTG6.addChild(box6);

        transform.setTranslation(vector7);
        boxTG7.setTransform(transform);
        boxTG7.addChild(box7);

        transform.setTranslation(vector8);
        boxTG8.setTransform(transform);
        boxTG8.addChild(box8);

        transform.setTranslation(vector9);
        boxTG9.setTransform(transform);
        boxTG9.addChild(box9);

        branchGroup.addChild(boxTG1);
        branchGroup.addChild(boxTG2);
        branchGroup.addChild(boxTG3);
        branchGroup.addChild(boxTG4);
        branchGroup.addChild(boxTG5);
        branchGroup.addChild(boxTG6);
        branchGroup.addChild(boxTG7);
        branchGroup.addChild(boxTG8);
        branchGroup.addChild(boxTG9);

      //  boxTG5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      //  boxTG5.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        addLightsToUniverse();




        pickCanvas = new PickCanvas(canvas, branchGroup);
        pickCanvas.setMode(PickCanvas.GEOMETRY);




        addEvent();

        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(branchGroup);





        add(canvas, BorderLayout.CENTER);
      //  add(buttonPanel, BorderLayout.PAGE_START);
        add(footerPanel, BorderLayout.PAGE_END);

      //  ViewingPlatform vp = universe.getViewingPlatform();
      //  TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);

      //  Transform3D vpTranslation = new Transform3D();
      //  Vector3f translationVector = new Vector3f(0f, 1f, 4f);



      // vpTranslation.setTranslation(translationVector);



       // vpGroup.setTransform(vpTranslation);

        Transform3D look = new Transform3D();
        look.lookAt(new Point3d(0.0, 2.5, 3.2), new Point3d(0.0, 1.0, 0.7), new Vector3d(0.0, 1.0, 0.0));
        look.invert();
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(look);
        canvas.setDoubleBufferEnable(true);
        setSize(1024,768);
        setVisible(true);

    }

    public void addEvent() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked..");

                pickCanvas.setShapeLocation(e);
                PickResult result = pickCanvas.pickClosest();
                if (result==null) {
                    System.out.println("Nothing picked...");
                }
                else {
                    Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
                    Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
                    if(p!=null) {
                        System.out.println(p.getUserData());
                        int r = 0;
                        int c = 0;

                        boolean valid = false;
                        switch(p.getUserData().toString()) {
                            case "box1":
                                r=1;
                                c=1;
                                valid = true;
                                break;
                            case "box2":
                                r=1;
                                c=2;
                                valid = true;
                                break;
                            case "box3":
                                r=1;
                                c=3;
                                valid = true;
                                break;
                            case "box4":
                                r=2;
                                c=1;
                                valid = true;
                                break;
                            case "box5":
                                r=2;
                                c=2;
                                valid = true;
                                break;
                            case "box6":
                                r=2;
                                c=3;
                                valid = true;
                                break;
                            case "box7":
                                r=3;
                                c=1;
                                valid = true;
                                break;
                            case "box8":
                                r=3;
                                c=2;
                                valid = true;
                                break;
                            case "box9":
                                r=3;
                                c=3;
                                valid = true;
                                break;
                            default:
                                break;

                        }

                        if(valid) {
                            app.test();
                            app.playerMove(r,c);
                        }
                    }
                    else if(s!=null) {
                        System.out.println(s.getClass().getName());
                    }
                    else {
                        System.out.println("null");
                    }
                }
            }
        });
    }

    public int nextMove[] = new int[2];

    public void resetMove() {
        nextMove[0] = 0;
        nextMove[1] = 0;
    }

    public void setMove(int row, int column) {
        nextMove[0] = row;
        nextMove[1] = row;
    }

    private void doThings() {
        System.out.println("done");
        this.app.test();
    }

    private void addLightsToUniverse() {
        Bounds influenceRegion = new BoundingSphere();
        Color3f lightColor = new Color3f(Color.WHITE);
        Vector3f lightDirection = new Vector3f(-1F, -1F, -1F);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(influenceRegion);
        branchGroup.addChild(light);
    }

    public static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        return file.load(new FileReader(location));
    }

    void listSceneNamedObjects(Scene scene) {
        Map<String, Shape3D> nameMap = scene.getNamedObjects();

        for (String name : nameMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    Appearance getAppearance(String path, Component canvas, int dimension) {
        Appearance appearance = new Appearance();
        appearance.setTexture(getTexture(path, canvas, dimension));
        return appearance;
    }

    Appearance getAppearance(Color color) {
        Appearance app = new Appearance();
        app.setMaterial(getMaterial(color));
        return app;
    }

    Material getMaterial(Color color) {
        return new Material(AMBIENT_LIGHT_COLOR,
                EMISSIVE_LIGHT_COLOR,
                new Color3f(color),
                SPECULAR_LIGHT_COLOR,
                100F);
    }

    Texture getTexture(String path, Component canvas, int dimension) {
        TextureLoader textureLoader = new TextureLoader(path, canvas);

        Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL,
                Texture2D.RGB,
                dimension,
                dimension);

        texture.setImage(0, textureLoader.getImage());

        return texture;
    }

}
