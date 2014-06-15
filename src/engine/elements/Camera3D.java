/*
 * 3D camera!
 */
package engine.elements;

import com.jme3.math.Vector3f;
import com.jme3.math.Quaternion;
import engine.util.MathEx;
import engine.util.SphericalCoords;

public class Camera3D {

    public final Vector3f UP = new Vector3f(0, 1, 0);
    public Vector3f position;
    public SphericalCoords direction;
    com.jme3.renderer.Camera camera;
    private boolean automatic; // unused for now

    public Camera3D(com.jme3.renderer.Camera cam) {
        position = new Vector3f(0, 0, 0);
        camera = cam;

        automatic = false;
    }

    public void update() {
        if (!automatic) {
            camera.setLocation(position);
            camera.setRotation(new Quaternion().fromAngles(direction.t, direction.p, 0));
        }
    }

    public void setPosition(Vector3f pos) {
        position.x = pos.x;
        position.y = pos.y;
        position.z = pos.z;
    }

    public void setDirection(SphericalCoords newDirection) {
        direction = newDirection;
    }

    public void lookAt(Vector3f at) {
        setDirection(MathEx.rectangularToSpherical(at.subtract(position)));
    }

    public void positionBehind(Vector3f pos, Vector3f dir, float radius) {
        Vector3f newPos = pos.subtract(dir.normalize().mult(radius));;
        setPosition(newPos);
        lookAt(pos);
    }
}
