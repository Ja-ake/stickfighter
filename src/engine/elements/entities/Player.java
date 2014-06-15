/*
 * Human that is controllable by user input
 */
package engine.elements.entities;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import engine.elements.Entity;
import engine.states.RoomAppState;

public class Player extends Entity implements AnimEventListener {
    
    private AnimControl animControl;
    
    public Player(RoomAppState appState, Vector3f position) {
        super(appState, position);
    }
    
    @Override
    protected RigidBodyControl initialCollisionShape() {
        return new RigidBodyControl(new CapsuleCollisionShape(10, 4, 1), 1);
    }
    
    @Override
    protected Spatial initialSpatial() {
        return appState.getApp().getAssetManager().loadModel("Models/S/StickMesh.mesh.xml");
    }
    
    @Override
    public void update(float tpf) {
        appState.getCamera().positionBehind(getPosition(), getVelocity(), 20);
        System.out.println(appState.getApp().getInputListener().isDown("Move Forward"));
        System.out.println(getPosition());
    }
    
    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
    }
    
    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }
}
