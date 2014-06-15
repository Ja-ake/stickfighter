/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.states;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.Client;
import com.jme3.network.Network;
import java.io.IOException;

/**
 *
 * @author Jake
 */
public class MultiplayerRoomAppState extends RoomAppState {
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        try {
            Client myClient = Network.connectToServer("localhost", 6143);
            myClient.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    }
}
