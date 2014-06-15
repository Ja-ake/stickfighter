/*
 * Manages the state of the game, global information, and what renderers are
 * supposed to be drawing on the screen at any given time
 */
package engine;

import com.jme3.app.SimpleApplication;
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public interface State {
    
    public SimpleApplication getApp();

    public ArrayList<Renderer> getRenderers();
}
