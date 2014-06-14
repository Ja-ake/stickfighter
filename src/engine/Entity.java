package engine;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;

/**
 *
 * @author Jake
 */
public class Entity {
    public Vector3f position;
    public Vector3f velocity;
    public Vector3f gravity;
    public CapsuleCollisionShape boundingbox;
    public Mesh mesh;
    
    public Entity() {
        position = new Vector3f(0, 0, 0); // x, y, z
        velocity = new Vector3f(0, 0, 0); // xdir, ydir, zdir (mag=speed)
        gravity = new Vector3f(0, -1, 0);
    }
    
    public void update(double tpf) {
        velocity = velocity.add((new Vector3f(gravity)).mult((float)tpf));
        position = position.add((new Vector3f(velocity)).mult((float)tpf));
    }
    
    public void setSpeed(double speed) {
        velocity = velocity.normalize();
        velocity = velocity.mult((float)speed);
    }
    
    public void setDirection(Vector3f rotations) {
        double speed = velocity.length();
        Vector3f direction = MathEx.sphericalToRectangular(rotations);
        velocity.x = direction.x;
        velocity.y = direction.y;
        velocity.z = direction.z;
        setSpeed(speed);
    }
    
    public void setMovementVector(Vector3f movement) {
        velocity = new Vector3f(movement);
    }
    
    public void setPosition(Vector3f pos) {
        position.x = pos.x;
        position.y = pos.y;
        position.z = pos.z;
    }
    
    public double getSpeed() {
        return velocity.length();
    }
    
    public Vector3f getDirection() {
        return MathEx.rectangularToSpherical(velocity);
    }
    
    public Vector3f getMovementVector() {
        return new Vector3f(velocity);
    }
    
    public Vector3f getPosition() {
        return new Vector3f(position);
    }
}
