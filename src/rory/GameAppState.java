package rory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

public class GameAppState extends AbstractAppState {

    private AppStateManager stateManager;
    private SimpleApplication app;
    

    @Override
    public void cleanup() {
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
    }

    public SimpleApplication getApp() {
        return app;
    }

    public AppStateManager getStateManager() {
        return stateManager;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
