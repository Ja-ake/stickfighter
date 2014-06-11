package engine;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private Spatial player;
    private InputListener inputListener = new InputListener();

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        assetManager.registerLocator("town.zip", ZipLocator.class);
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);
        // Load a model from test_data (OgreXML + material + texture)

        //Creates the geometry of the player
        player = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        //Sets the player's position, rotation, and scale
        player.scale(0.05f, 0.05f, 0.05f);
        player.rotate(0.0f, -3.0f, 0.0f);
        player.setLocalTranslation(0, 0, 0);
        //Adds the player to the world
        rootNode.attachChild(player);


        // You must add a light to make the model visible

        //Creates a new light
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.1f, -0.7f, -1.0f));
        //Adds the light to the world
        rootNode.addLight(sun);

        flyCam.setMoveSpeed(50);
        initKeys();
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Back", new KeyTrigger(KeyInput.KEY_S));
        // Add the names to the action listener.
        inputManager.addListener(inputListener, "Pause");
        inputManager.addListener(inputListener, "Left", "Right", "Rotate");
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private class InputListener implements ActionListener, AnalogListener {

        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Pause") && !isPressed) {
                paused = !paused;
            }
        }

        public void onAnalog(String name, float value, float tpf) {
            //flyCam.
        }
    }
}
