package rory;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;

public class Player extends CharacterControl {

    public Player(Vector3f pos) {
        super(new CapsuleCollisionShape(1.5f, 3f, 1), 0.05f);
        setJumpSpeed(30);
        setFallSpeed(300);
        setGravity(100);
        setPhysicsLocation(pos);
    }
}
