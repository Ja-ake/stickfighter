/*
 * Human that is controllable by user input
 */
package engine.elements.entities;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import engine.elements.Entity;
import engine.states.RoomAppState;

public class Player extends Entity implements AnimEventListener {

    private AnimControl animControl;
    private AnimChannel animChannel;
    private float runSpeed;

    public Player(RoomAppState appState, Vector3f position) {
        super(appState, position);
        runSpeed = 10;
        getBCC().setViewDirection(new Vector3f(1, 0, 0));

        animControl = spatial.getControl(AnimControl.class);
        animControl.addListener(this);
        animChannel = animControl.createChannel();
        animChannel.setAnim("Run");
        animChannel.setLoopMode(LoopMode.Loop);
        System.out.println(animControl.getAnimationNames());
    }

    private BetterCharacterControl getBCC() {
        return (BetterCharacterControl) physicsControl;
    }

    @Override
    public Vector3f getVelocity() {
        return getBCC().getVelocity();
    }

    @Override
    protected PhysicsControl initialCollisionShape() {
        return new BetterCharacterControl(2, 5, 1000);
    }

    @Override
    protected Spatial initialSpatial() {
        Node s = (Node) appState.getApp().getAssetManager().loadModel("Models/S/StickMesh.mesh.xml");
        Material mat = new Material(appState.getApp().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        //mat.setColor("Color", ColorRGBA.Blue);
        s.getChild("StickMat").setMaterial(mat);
        return s;
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
    }

    public void setAnimation(String name, boolean interrupt) {
        if (!animChannel.getAnimationName().equals(name) || interrupt) {
            animChannel.setAnim(name, .5f);
        }
    }

    @Override
    public void setPosition(Vector3f newPosition) {
        getBCC().warp(newPosition);
    }

    @Override
    public void setVelocity(Vector3f newVelocity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(float tpf) {
        appState.getCamera().positionBehind(getPosition().add(0, 5, 0), getBCC().getViewDirection(), 20);
        if (appState.getInputPacket().isDown("Move Forward")) {
            getBCC().setWalkDirection(getBCC().getViewDirection().mult(runSpeed));
            setAnimation("Run", false);
        } else if (appState.getInputPacket().isDown("Move Back")) {
            getBCC().setWalkDirection(getBCC().getViewDirection().mult(-runSpeed));
            setAnimation("Run", false);
        } else {
            getBCC().setWalkDirection(new Vector3f(0, 0, 0));
            setAnimation("Stand", false);
        }
        System.out.println(getVelocity());
    }
}
