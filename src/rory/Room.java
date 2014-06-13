package rory;

import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;

public class Room {

    private Spatial levelModel;

    public Room(GameAppState appState, String levelName) {
        levelModel = appState.getApp().getAssetManager().loadModel("main.scene");
        appState.getApp().getRootNode().attachChild(levelModel);
        MeshCollisionShape levelShape = new MeshCollisionShape(levelModel.getMesh());
        RigidBodyControl phys = new RigidBodyControl(levelShape, 0);
        
    }
}
