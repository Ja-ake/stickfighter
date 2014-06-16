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
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import engine.elements.Entity;
import engine.states.RoomAppState;
import engine.util.SphericalCoords;
import engine.util.MathEx;

public class Player extends Entity implements AnimEventListener {

    private AnimControl animControl;
    private AnimChannel animChannel;
    private float runSpeed;
    private SphericalCoords facing;

    public Player(RoomAppState appState, Vector3f position) {
        super(appState, position);
        runSpeed = 20;
        facing = new SphericalCoords(1, FastMath.HALF_PI, FastMath.HALF_PI);
        getBCC().setViewDirection(new Vector3f(1, 0, 0));
        getBCC().setGravity(new Vector3f(0, -100, 0));
        getBCC().setJumpForce(new Vector3f(0, 50 * getMass(), 0));

        animControl = spatial.getControl(AnimControl.class);
        animControl.addListener(this);
        animChannel = animControl.createChannel();
        animChannel.setAnim("Stand");
        animChannel.setLoopMode(LoopMode.Loop);
    }

    private BetterCharacterControl getBCC() {
        return (BetterCharacterControl) physicsControl;
    }

    public float getMass() {
        return 1000;
    }

    @Override
    public Vector3f getVelocity() {
        return getBCC().getVelocity();
    }

    @Override
    protected PhysicsControl initialCollisionShape() {
        return new BetterCharacterControl(2, 5, getMass());
    }

    @Override
    protected Spatial initialSpatial() {
        Node s = (Node) appState.getApp().getAssetManager().loadModel("Models/S/StickMesh.mesh.xml");
        Material mat = new Material(appState.getApp().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        s.getChild("StickMat").setMaterial(mat);
        s.scale(.5f);
        return s;
    }

    public void move(float tpf) {
        //Calculate Move Direction
        int moveF = 0;
        int moveS = 0;
        if (appState.getInputPacket().isDown("Move Forward")) {
            moveF++;
        }
        if (appState.getInputPacket().isDown("Move Back")) {
            moveF--;
        }
        if (appState.getInputPacket().isDown("Move Right")) {
            moveS++;
        }
        if (appState.getInputPacket().isDown("Move Left")) {
            moveS--;
        }
        //Move
        if (moveF != 0 || moveS != 0) {
            setRotation(facing.addT((float) Math.atan2(moveS, moveF)), tpf);
            getBCC().setWalkDirection(getBCC().getViewDirection().mult(runSpeed));
            setAnimation("Run", false);
        } else {
            getBCC().setWalkDirection(new Vector3f(0, 0, 0));
            setAnimation("Stand", false);
        }
        //Jump
        if (getBCC().isOnGround()) {
            if (appState.getInputPacket().isPressed("Jump")) {
                getBCC().jump();
            }
        }
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

    public void setRotation(SphericalCoords rotation, float tpf) {
        Vector3f targetRotation = MathEx.sphericalToRectangular(rotation.setP(FastMath.HALF_PI));
        Vector3f change = targetRotation.subtract(getBCC().getViewDirection()).normalize().mult(10 * tpf);
        getBCC().setViewDirection(getBCC().getViewDirection().add(change).normalize());
    }

    @Override
    public void setVelocity(Vector3f newVelocity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(float tpf) {
        move(tpf);
        //Turn View
        facing = facing.addP(-appState.getInputPacket().getValue("Look Up"));
        facing = facing.addP(appState.getInputPacket().getValue("Look Down"));
        facing = facing.addT(-appState.getInputPacket().getValue("Look Left"));
        facing = facing.addT(appState.getInputPacket().getValue("Look Right"));
        //Cap View
        if (facing.p < .2) {
            facing = facing.setP(.2f);
        }
        if (facing.p > Math.PI - .2) {
            facing = facing.setP(FastMath.PI - .2f);
        }
        //Set Camera
        appState.getCamera().positionBehind(getPosition().add(0, 3, 0), MathEx.sphericalToRectangular(facing), 10);
    }
}
