package rory;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Player extends Entity {

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
        appState.getCamera().positionBehind(position, velocity, 20);
    }
}
