package rory;

import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import java.util.ArrayList;

public class Room {
    
    private GameAppState appState;
    private Geometry levelModel;
    private ArrayList<Entity> entityArray;
    
    public Room(GameAppState appState) {
        this.appState = appState;
        entityArray = new ArrayList();
    }
    
    public void addEntity(Entity e) {
        entityArray.add(e);
    }
    
    public void loadLevel(String levelName) {
        levelModel = (Geometry) appState.getApp().getAssetManager().loadModel(levelName);
        appState.getApp().getRootNode().attachChild(levelModel);
        MeshCollisionShape levelShape = new MeshCollisionShape(levelModel.getMesh());
        RigidBodyControl phys = new RigidBodyControl(levelShape, 0);
        appState.getPhysicsSpace().add(phys);
    }
    
    public void removeEntity(Entity e) {
        entityArray.remove(e);
    }
    
    public void update(float tpf) {
        for (int i = 0; i < entityArray.size(); i++) {
            entityArray.get(i).update(tpf);
        }
    }
}
