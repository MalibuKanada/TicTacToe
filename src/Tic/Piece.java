package Tic;

import com.sun.j3d.utils.geometry.Box;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 * Created by Marvin on 05.03.2017.
 */
public class Piece extends TransformGroup {
    private static final float DIMX = 0.1f;
    private static final float DIMY = 0.05f;
    private static final float DIMZ = 0.1f;

    Box box;

    public Piece(float x, float y, float z, Appearance appearance) {
        box = new Box(DIMX, DIMY, DIMZ, appearance);

        Transform3D transform = new Transform3D();


        Vector3f vector = new Vector3f(x, y, z);
        transform.setTranslation(vector);
        this.setTransform(transform);
        this.addChild(box);
        System.out.println("Piece created at position " +x+ " " +y+" " + z);
    }
}
