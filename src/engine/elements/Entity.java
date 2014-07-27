/*
 * Element for objects in the room (Player, trees)
 */
package engine.elements;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import engine.Element;
import engine.states.RoomAppState;
import engine.util.MathEx;
import engine.util.SphericalCoords;

public abstract class Entity extends Element {

    protected RoomAppState appState;
    protected Spatial spatial;
    protected RigidBodyControl physicsControl;

    public Entity(RoomAppState appState, Vector3f position) {
        //Variables
        this.appState = appState;

        //Create the spatial
        spatial = initialSpatial();
        spatial.move(position);

        //Create the physicsControl
        physicsControl = initialCollisionShape();
        spatial.addControl(physicsControl);
        physicsControl.setSpatial(spatial);

        //Add to room
        appState.getNode().attachChild(spatial);
        appState.getPhysicsSpace().add(physicsControl);
        appState.getRoom().getEntityArray().add(this);
    }

    public SphericalCoords getDirection() {
        return MathEx.rectangularToSpherical(getVelocity().normalize());
    }

    public Vector3f getPosition() {
        return spatial.getLocalTranslation();
    }

    public Vector3f getVelocity() {
        return physicsControl.getLinearVelocity();
    }

    abstract protected RigidBodyControl initialCollisionShape();

    abstract protected Spatial initialSpatial();

    public void remove() {
        appState.getApp().getRootNode().detachChild(spatial);
        appState.getPhysicsSpace().remove(physicsControl);
        appState.getRoom().getEntityArray().remove(this);
    }

    public void setDirection(SphericalCoords newDirection) {
        float speed = getVelocity().length();
        setVelocity(MathEx.sphericalToRectangular(newDirection));
        setSpeed(speed);
    }

    public void setPosition(Vector3f newPosition) {
        physicsControl.setPhysicsLocation(newPosition);
    }

    public void setSpeed(float newSpeed) {
        setVelocity(getVelocity().normalize().mult(newSpeed));
    }

    public void setVelocity(Vector3f newVelocity) {
        physicsControl.setLinearVelocity(newVelocity);
    }

    @Override
    public void update(float tpf) {
    }
}
