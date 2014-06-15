/*
 * Manages the state of the game, global information, and what renderers are
 * supposed to be drawing on the screen at any given time
 */
package engine;

import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public interface State {
    
    public GameControl getApp();

    public ArrayList<Renderer> getRenderers();
}
