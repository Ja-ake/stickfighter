/*
 * Element for objects in the room (Player, trees)
 */

package jake.engine.elements;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import jake.engine.Element;
import jake.engine.State;
import jake.engine.renderers.Room;
import jake.engine.util.MathEx;
import jake.engine.util.SphericalCoords;

/**
 *
 * @author Jake
 */
public class Entity extends Element {
    
    protected State appState;
    protected Vector3f position;
    protected Vector3f velocity;
    protected Vector3f gravity;
    protected Spatial spatial;
    protected RigidBodyControl physicsControl;
    
    protected boolean affectedByGravity;

    public Entity(State appState, Vector3f position) {
        this.appState = appState;
        this.position = new Vector3f(position); // x, y, z
        velocity = new Vector3f(0, 0, 0); // xdir, ydir, zdir (mag=speed)
        gravity = new Vector3f(0, -.5f, 0);

        spatial = initialSpatial();
        appState.getApp().getRootNode().attachChild(spatial);
        physicsControl = new RigidBodyControl(initialCollisionShape(), 1);
        spatial.addControl(physicsControl);
        appState.getPhysicsSpace().add(physicsControl);
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

    abstract protected CollisionShape initialCollisionShape();
    abstract protected Spatial initialSpatial();

    public void remove() {
        appState.getApp().getRootNode().detachChild(spatial);
        appState.getPhysicsSpace().remove(physicsControl);
        ((Room)appState.getRenderers(Room.class).get(0)).removeEntity(this);
    }

    public void setDirection(SphericalCoords newDirection) {
        float speed = velocity.length();
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

    public void setSpeed(float speed) {
        velocity.normalizeLocal().multLocal(speed);
    }

    public void update(float tpf) {
        //Increment velocity by gravity
        if (affectedByGravity) {
            velocity.addLocal(gravity.mult(tpf));
        }
        //Increment position by speed
        position.addLocal(velocity.mult(tpf));

        spatial.setLocalTranslation(position);
    }
}
