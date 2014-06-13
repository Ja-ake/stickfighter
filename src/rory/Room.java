package rory;

import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.scene.Node;
import java.util.ArrayList;

public class Room {

    private GameAppState appState;
    private Node levelModel;
    private ArrayList<Entity> entityArray;

    public Room(GameAppState appState) {
        this.appState = appState;
        entityArray = new ArrayList();
    }

    public void loadLevel(String levelName) {
        appState.getApp().getAssetManager().registerLocator("town.zip", ZipLocator.class);
        levelModel = (Node) appState.getApp().getAssetManager().loadModel(levelName);
        appState.getApp().getRootNode().attachChild(levelModel);
        CollisionShape levelShape = CollisionShapeFactory.createMeshShape(levelModel);
        RigidBodyControl phys = new RigidBodyControl(levelShape, 0);
        appState.getPhysicsSpace().add(phys);
    }
    
    public ArrayList<Entity> getEntityArray() {
        return entityArray;
    }

    public void update(float tpf) {
        for (int i = 0; i < entityArray.size(); i++) {
            entityArray.get(i).update(tpf);
        }
    }
}
