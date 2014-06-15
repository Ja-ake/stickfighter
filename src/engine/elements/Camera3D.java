/*
 * 3D camera!
 */
package engine.elements;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import engine.util.MathEx;
import engine.util.SphericalCoords;

public class Camera3D {

    Camera camera;

    public Camera3D(Camera cam) {
        camera = cam;
    }

    public void setPosition(Vector3f pos) {
        camera.setLocation(pos);
    }

    public void setDirection(SphericalCoords newDirection) {
        camera.setRotation(new Quaternion().fromAngles(newDirection.t, newDirection.p, 0));
    }

    public void lookAt(Vector3f at) {
        setDirection(MathEx.rectangularToSpherical(at.subtract(camera.getLocation())));
    }

    public void positionBehind(Vector3f pos, Vector3f dir, float radius) {
        Vector3f newPos = pos.subtract(dir.normalize().mult(radius));;
        setPosition(newPos);
        lookAt(pos);
    }
}
