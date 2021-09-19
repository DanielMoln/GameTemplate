package hu.molnard.render;

import hu.molnard.entity.Entity;
import hu.molnard.help.MathHelper;
import hu.molnard.model.RawModel;
import hu.molnard.model.TexturedModel;
import hu.molnard.shader.entity.EntityShader;
import hu.molnard.util.Settings;
import hu.molnard.world.EntityEnviroment;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityRenderer
{
    /*
        firstly, you add your entities to the `inGameEntities` and It going to separate to the memory
        and the program going to load objects to game from memory.
    */
    private List<Entity> inGameEntities;
    private Map<TexturedModel, List<Entity>> memory = new HashMap<>();

    private Loader loader;
    private MathHelper mathHelper;
    private Settings settings;

    private EntityEnviroment entityEnviroment;
    private EntityShader entityShader;

    public EntityRenderer(Settings settings, MathHelper mathHelper, Loader loader)
    {
        this.mathHelper = mathHelper;
        this.settings = settings;
        this.loader = loader;

        entityEnviroment = new EntityEnviroment();
        inGameEntities = entityEnviroment.fill(loader);
        entityShader = new EntityShader(settings, mathHelper);

        mathHelper.createProjectionMatrix();
        entityShader.start();
            entityShader.loadProjectionMatrix(mathHelper.getProjectionMatrix());
        entityShader.stop();
    }

    public void render()
    {
        /* separate incoming entity */
        inGameEntities.forEach(e ->  { separateEntity(e); e.increaseRotation(1.1f, 1f, 0.1f); });

        /* start shader */
        entityShader.start();

        entityShader.loadViewMatrix();
        entityShader.loadLightValues();

        memory.forEach((a, b) ->
        {
            bindAttribs(a);

            b.forEach(e ->
            {
                loadEntityTransformationMatrix(e);
                GL11.glDrawElements(GL11.GL_TRIANGLES, a.getRawModel().getVertexCount(),
                        GL11.GL_UNSIGNED_INT, 0);
            });

            unbindAttribs();
        });

        /* stop shadering */
        entityShader.stop();

        memory.clear();
    }

    /* separate every textured model and entity. */
    void separateEntity(Entity e)
    {
        TexturedModel et = e.getTexturedModel();
        List<Entity> strg = memory.get(et);
        if (strg == null )
        {
            List<Entity> newStrg = new ArrayList<>();
            newStrg.add(e);
            memory.put(et, newStrg);
        }
        else
            strg.add(e);
    }

    void bindAttribs(TexturedModel tm)
    {
        RawModel rm = tm.getRawModel();

        GL30.glBindVertexArray(rm.getId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        if ( tm.getModelTexture().isHasTransparency() )
        {
            disableCulling();
            entityShader.loadFakeLighting(true);
        }

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, tm.getModelTexture().getId());
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

    private void unbindAttribs()
    {
        enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);

    }

    private void loadEntityTransformationMatrix(Entity entity)
    {
        Matrix4f transmatrix = mathHelper.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());

        entityShader.loadTransformationMatrix(transmatrix);
    }
}
