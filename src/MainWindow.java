import javax.swing.*;
import java.awt.*;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javafx.animation.RotateTransition;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

import java.util.Properties;






public class MainWindow extends JFrame{

    private SimpleUniverse universe = null;
    private Scene woodenTable;
    private BranchGroup branchGroup;
    public MainWindow(String title) {
        super(title);

       System.setProperty("sun.awt.noerasebackground", "true"); //verhindert Grafikfehler wie flackern etc, ohne diese einstellung starke Anzeigefehler, weißer background etc


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        universe = new SimpleUniverse(canvas);
        setLayout(new BorderLayout());


        JButton button1 = new JButton("Player vs Player");
        JButton button2 = new JButton("Player vs Computer");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.setBackground(new Color(100,100,100));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(200,200,200));

        JLabel footerText = new JLabel("X hat gewonnen 0 mal -- O hat gewonnen: 0 mal");
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

*/

        branchGroup = new BranchGroup();
        try {
            Scene scene = getSceneFromFile("src/table.obj");
            branchGroup.addChild(scene.getSceneGroup());
        }
        catch(java.io.IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }

        addLightsToUniverse();








        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(branchGroup);


        add(canvas, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_START);
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

}
