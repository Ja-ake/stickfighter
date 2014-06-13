package rory;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Entity {

    protected Vector3f position;
    protected Vector3f velocity;
    protected Vector3f gravity;
    protected CapsuleCollisionShape boundingbox;
    protected Spatial spatial;

    public Entity() {
        position = new Vector3f(0, 0, 0); // x, y, z
        velocity = new Vector3f(0, 0, 0); // xdir, ydir, zdir (mag=speed)
        gravity = new Vector3f(0, -.5f, 0);
    }

    public SphericalCoords getDirection() {
        return MathEx.rectangularToSpherical(velocity);
    }

    public Vector3f getMovementVector() {
        return new Vector3f(velocity);
    }

    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    public double getSpeed() {
        return velocity.length();
    }

    public void setDirection(SphericalCoords newDirection) {
        double speed = velocity.length();
        velocity = MathEx.sphericalToRectangular(newDirection);
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

    public void setSpeed(double speed) {
        velocity.normalizeLocal().multLocal((float) speed);
    }

    public void update(double tpf) {
        velocity.addLocal(gravity.mult((float) tpf));
        position.addLocal(velocity.mult((float) tpf));
    }
}
