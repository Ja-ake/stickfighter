package rory;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Player extends Entity {

    public Player(GameAppState appState, Vector3f position) {
        super(appState, position);
    }

    @Override
    protected CollisionShape initialCollisionShape() {
        return new CapsuleCollisionShape(10, 10, 10);
    }

    @Override
    protected Spatial initialSpatial() {
        return appState.getApp().getAssetManager().loadModel("Models/S/StickMesh.mesh.xml");
    }

    @Override
    public void update(float tpf) {
        appState.getCamera().positionBehind(position, velocity, 20);
    }
}
