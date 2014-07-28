/*
 * Human that is controllable by user input
 */
package engine.elements.entities;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import engine.states.RoomAppState;
import engine.util.SphericalCoords;
import engine.util.MathEx;

public class Player extends Human implements AnimEventListener {

    private AnimControl animControl;
    private AnimChannel animChannel;
    private SphericalCoords facing;

    public Player(RoomAppState appState, Vector3f position) {
        super(appState, position);

        facing = new SphericalCoords(1, 0, FastMath.HALF_PI);

        animControl = spatial.getControl(AnimControl.class);
        animControl.addListener(this);
        animChannel = animControl.createChannel();
        animChannel.setAnim("Stand");
        animChannel.setLoopMode(LoopMode.Loop);
    }

    public SphericalCoords getFacing() {
        return facing;
    }

    public float getMoveDirection() {
        //Calculate Move Direction based on keyboard input
        //Returns -1 if no keys are pressed
        boolean up = appState.getInputPacket().isDown("Move Forward");
        boolean down = appState.getInputPacket().isDown("Move Back");
        boolean left = appState.getInputPacket().isDown("Move Left");
        boolean right = appState.getInputPacket().isDown("Move Right");

        if (up && !down) {
            if (left && !right) {
                return -FastMath.QUARTER_PI;
            } else if (right && !left) {
                return FastMath.QUARTER_PI;
            } else {
                return 0;
            }
        } else if (down && !up) {
            if (left && !right) {
                return FastMath.QUARTER_PI - FastMath.PI;
            } else if (right && !left) {
                return FastMath.PI - FastMath.QUARTER_PI;
            } else {
                return FastMath.PI;
            }
        } else {
            if (left && !right) {
                return -FastMath.HALF_PI;
            } else if (right && !left) {
                return FastMath.HALF_PI;
            } else {
                return -1;
            }
        }
    }

    public void move(float tpf) {
        float moveDir = getMoveDirection();
        //Move
        getControl().setMove(moveDir != -1);
        if (moveDir != -1) {
            getControl().setMove(true);
            SphericalCoords newMoveDir;
            if (Math.abs(moveDir) < .5) {
                newMoveDir = getFacing().addT(moveDir).setP(FastMath.HALF_PI);
            } else if (Math.abs(moveDir) < 1) {
                newMoveDir = getFacing().addT(moveDir).setP(FastMath.HALF_PI).setR(.75f);
            } else {
                newMoveDir = getFacing().addT(moveDir).setP(FastMath.HALF_PI).setR(.5f);
            }
            getControl().setWalkDirection(MathEx.sphericalToRectangular(newMoveDir));
            setAnimation("Run", false);
        } else {
            //getControl().setMove(false);
            getControl().setWalkDirection(new Vector3f(0, 0, 0));
            setAnimation("Stand", false);
        }
        //Jump
        //if (getBCC().isOnGround()) {
        if (appState.getInputPacket().isPressed("Jump")) {
            getControl().jump();
        }
        //}
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
        facing = s;
    }

    @Override
    public void update(float tpf) {
        move(tpf);

        getControl().setGravity(new Vector3f(0, -100, 0));
        getControl().setRotationInUpdate(new Quaternion().fromAngleAxis(-facing.t + FastMath.HALF_PI, Vector3f.UNIT_Y));

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
