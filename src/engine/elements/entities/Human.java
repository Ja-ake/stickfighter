package engine.elements.entities;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import engine.elements.Entity;
import engine.states.RoomAppState;

public class Human extends Entity {

    public Human(RoomAppState appState, Vector3f position) {
        super(appState, position);
    }

    protected BetterCharacterControl getBCC() {
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

    @Override
    public void setPosition(Vector3f newPosition) {
        getBCC().warp(newPosition);
    }

    @Override
    public void setVelocity(Vector3f newVelocity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
