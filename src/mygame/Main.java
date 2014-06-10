package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //Creates the teapot geometry and loads the model
        Spatial teapot = assetManager.loadModel("Models/Teapot/Teapot.obj");
        //Creates the material for the teapot
        Material mat_default = new Material(
                assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        //Assigns the material to the teapot
        teapot.setMaterial(mat_default);
        //Adds the teapot to the world
        rootNode.attachChild(teapot);

        // Create a wall with a simple texture from test_data
        
        //Creates a box shape
        Box box = new Box(2.5f, 2.5f, 1.0f);
        //Creates the wall's geometry with the box as a shape
        Spatial wall = new Geometry("Box", box);
        //Creates the material for the wall
        Material mat_brick = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //Sets the texture of the material
        mat_brick.setTexture("ColorMap",
                assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        //Assigns the material to the wall
        wall.setMaterial(mat_brick);
        //Sets the wall's position
        wall.setLocalTranslation(2.0f, -2.5f, 0.0f);
        //Adds the wall to the world
        rootNode.attachChild(wall);

        // Display a line of text with a default font
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);

        // Load a model from test_data (OgreXML + material + texture)
        
        //Creates the geometry of the ninja
        Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        //Sets the ninja's position, rotation, and scale
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
        //Adds the ninja to the world
        rootNode.attachChild(ninja);
        
        
        // You must add a light to make the model visible
        
        //Creates a new light
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.1f, -0.7f, 1.0f));
        //Adds the light to the world
        rootNode.addLight(sun);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
