/*
 * Human that is controllable by user input
 */
package engine.elements.entities;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import engine.states.RoomAppState;
import engine.util.SphericalCoords;
import engine.util.MathEx;

public class Player extends Human implements AnimEventListener {

    private AnimControl animControl;
    private AnimChannel animChannel;
    private float runSpeed;

    public Player(RoomAppState appState, Vector3f position) {
        super(appState, position);
        runSpeed = 20;

        getBCC().setViewDirection(new Vector3f(1, 0, 0));
        getBCC().setGravity(new Vector3f(0, -100, 0));
        getBCC().setJumpForce(new Vector3f(0, 50 * getMass(), 0));

        animControl = spatial.getControl(AnimControl.class);
        animControl.addListener(this);
        animChannel = animControl.createChannel();
        animChannel.setAnim("Stand");
        animChannel.setLoopMode(LoopMode.Loop);
    }

    public SphericalCoords getFacing() {
        return MathEx.rectangularToSpherical(getBCC().getViewDirection());
    }

    public float getMoveDirection() {
        //Calculate Move Direction based on keyboard input
        //Returns -1 if no keys are pressed
        if (appState.getInputPacket().isDown("Move Forward")) {
            if (appState.getInputPacket().isDown("Move Right")) {
                return -FastMath.QUARTER_PI;
            } else if (appState.getInputPacket().isDown("Move Left")) {
                return FastMath.QUARTER_PI;
            } else {
                return 0;
            }
        } else if (appState.getInputPacket().isDown("Move Back")) {
            if (appState.getInputPacket().isDown("Move Right")) {
                return FastMath.QUARTER_PI - FastMath.PI;
            } else if (appState.getInputPacket().isDown("Move Left")) {
                return FastMath.PI - FastMath.QUARTER_PI;
            } else {
                return FastMath.PI;
            }
        } else {
            if (appState.getInputPacket().isDown("Move Right")) {
                return -FastMath.HALF_PI;
            } else if (appState.getInputPacket().isDown("Move Left")) {
                return FastMath.HALF_PI;
            } else {
                return -1;
            }
        }
    }

    public void move(float tpf) {
        float moveDir = getMoveDirection();
        //Move
        if (moveDir != -1) {
            SphericalCoords newMoveDir = getFacing().addT(moveDir).setP(FastMath.HALF_PI).setR(runSpeed);
            getBCC().setWalkDirection(MathEx.sphericalToRectangular(newMoveDir));
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

    public void setFacing(SphericalCoords s) {
        getBCC().setViewDirection(MathEx.sphericalToRectangular(s));
    }

    @Override
    public void update(float tpf) {
        move(tpf);

        //Turn View
        setFacing(getFacing().addP(-appState.getInputPacket().getValue("Look Up")));
        setFacing(getFacing().addP(appState.getInputPacket().getValue("Look Down")));
        setFacing(getFacing().addT(-appState.getInputPacket().getValue("Look Left")));
        setFacing(getFacing().addT(appState.getInputPacket().getValue("Look Right")));

        //Cap View
        if (getFacing().p < .2) {
            setFacing(getFacing().setP(.2f));
        }
        if (getFacing().p > Math.PI - .2) {
            setFacing(getFacing().setP(FastMath.PI - .2f));
        }

        //Set Camera
        appState.getCamera().positionBehind(getPosition().add(0, 3, 0), MathEx.sphericalToRectangular(getFacing()), 10);
    }
}
