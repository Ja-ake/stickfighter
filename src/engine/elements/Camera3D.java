/*
 * 3D camera!
 */
package engine.elements;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class Camera3D {

    private static final Vector3f UP = new Vector3f(0, 1, 0);
    Camera camera;

    public Camera3D(Camera cam) {
        camera = cam;
    }

    public void setPosition(Vector3f pos) {
        camera.setLocation(pos);
    }

    public void setDirection(Vector3f newDirection) {
        camera.lookAtDirection(newDirection, UP);
    }

    public void lookAt(Vector3f at) {
        camera.lookAt(at, UP);
    }

    public void positionBehind(Vector3f pos, Vector3f dir, float radius) {
        Vector3f newPos = pos.subtract(dir.normalize().mult(radius));;
        setPosition(newPos);
        lookAt(pos);
    }
}
