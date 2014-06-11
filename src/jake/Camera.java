/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jake;

import com.jme3.math.Vector3f;
import com.jme3.math.Quaternion;

/**
 *
 * @author Jake
 */
public class Camera {
    public final Vector3f UP = new Vector3f(0, 1, 0);
    
    public Vector3f position;
    public Vector3f direction;
    com.jme3.renderer.Camera camera;
    
    private boolean automatic; // unused for now
    
    public Camera(com.jme3.renderer.Camera cam) {
        position = new Vector3f(0, 0, 0);
        camera = cam;
        
        automatic = false;
    }
    
    public void update() {
        if (!automatic) {
            Vector3f dir = MathEx.sphericalToRectangular(direction);
        
            camera.setLocation(position);
            camera.setRotation((new Quaternion()).fromAngles(direction.y, direction.z, 0));
        }
    }
    
    public void setPosition(Vector3f pos) {
        position.x = pos.x;
        position.y = pos.y;
        position.z = pos.z;
    }
    
    public void setDirection(Vector3f dir) {
        direction.x = dir.x;
        direction.y = dir.y;
        direction.z = dir.z;
        direction = direction.normalize();
    }
    
    public void lookAt(Vector3f at) {
        setDirection(MathEx.rectangularToSpherical(at.subtract(position)));
    }
    
    public void positionBehind(Vector3f pos, Vector3f dir, double radius) {
        Vector3f posi = new Vector3f(pos);
        Vector3f dire = new Vector3f(dir);
        dire.x = (float)-radius;
        dire = MathEx.sphericalToRectangular(dire);
        posi = posi.add(dire);
        setPosition(posi);
        lookAt(pos);
    }
}
