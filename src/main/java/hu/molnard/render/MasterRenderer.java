package hu.molnard.render;

import hu.molnard.entity.Camera;
import hu.molnard.help.MathHelper;
import hu.molnard.util.Settings;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class MasterRenderer
{
    private EntityRenderer entityRenderer;
    private Settings settings = new Settings();
    private MathHelper mathHelper = new MathHelper(settings);
    private Loader loader = new Loader();

    public MasterRenderer()
    {
        enableCulling();
    }

    /* loading guis, entities, worlds... */
    public void render()
    {
        prepare();
        entityRenderer = new EntityRenderer(settings, mathHelper, loader);
    }

    public void cleanUp()
    {
        loader.cleanUp();
    }

    private void prepare()
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(settings.getSkyColor().x, settings.getSkyColor().y, settings.getSkyColor().z, .8f);
    }

    private void enableCulling()
    {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    private void disableCulling()
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
}
