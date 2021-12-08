package com.ray3k.template;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.crashinvaders.vfx.VfxManager;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;
import com.ray3k.template.transitions.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.transitions.Transitions.*;

public abstract class JamGame implements ApplicationListener {
    protected JamScreen screen;
    
    private final static long MS_PER_UPDATE = 10;
    private static final int MAX_VERTEX_SIZE = 32767;
    private long previous;
    private long lag;
    public static AssetManager assetManager;
    public static TransitionEngine transitionEngine;
    public static TwoColorPolygonBatch batch;
    public Transition defaultTransition;
    public float defaultTransitionDuration;
    public static ShapeRenderer shapeRenderer;
    public static VfxManager vfxManager;
    public static SceneComposerStageBuilder sceneBuilder;
    public static ShapeDrawer shapeDrawer;
    
    @Override
    public void create() {
        batch = new TwoColorPolygonBatch(MAX_VERTEX_SIZE);
        
        previous = TimeUtils.millis();
        lag = 0;
        
        assetManager = new AssetManager(new InternalFileHandleResolver());
        shapeRenderer = new ShapeRenderer();
        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        
        transitionEngine = new TransitionEngine(this, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        defaultTransition = crossFade();
        defaultTransitionDuration = .5f;
    
        sceneBuilder = new SceneComposerStageBuilder();
        
        loadAssets();
    }
    
    @Override
    public void render() {
        if (screen != null) {
            long current = TimeUtils.millis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;
            
            while (lag >= MS_PER_UPDATE) {
                float delta = MS_PER_UPDATE / 1000.0f;
                
                if (!transitionEngine.inTransition) {
                    screen.updateMouse();
                    screen.act(delta);
                    screen.clearStates();
                } else {
                    transitionEngine.update(delta);
                }
                
                lag -= MS_PER_UPDATE;
            }
            
            if (transitionEngine.inTransition) {
                transitionEngine.draw(batch, lag / MS_PER_UPDATE);
            } else {
                ((JamScreen) screen).draw(lag / MS_PER_UPDATE);
            }
        }
    }
    
    @Override
    public void pause() {
        if (screen != null) screen.pause();
    }
    
    @Override
    public void resume() {
        if (screen != null) screen.resume();
    }
    
    @Override
    public void dispose() {
        if (screen != null) screen.hide();
    
        batch.dispose();
        vfxManager.dispose();
        assetManager.dispose();
        transitionEngine.dispose();
        shapeRenderer.dispose();
    }
    
    @Override
    public void resize(int width, int height) {
        if (screen != null) screen.resize(width, height);
        
        if (width != 0 && height != 0) transitionEngine.resize(width, height);
    }
    
    public abstract void loadAssets();
    
    public void transition(JamScreen nextScreen, Transition transition, float duration) {
        transitionEngine.transition((JamScreen) getScreen(), nextScreen, transition, duration);
    }
    
    public void transition(JamScreen nextScreen) {
        transition(nextScreen, defaultTransition, defaultTransitionDuration);
    }
    
    public void setScreen (JamScreen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
    
    /** @return the currently active {@link JamScreen}. */
    public JamScreen getScreen () {
        return screen;
    }
}