package rory;

import com.jme3.math.Vector3f;

public abstract class Projectile extends Entity {

    public Projectile(GameAppState appState, Vector3f position) {
        super(appState, position);
    }
}
