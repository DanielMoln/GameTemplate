package hu.molnard.world;

import hu.molnard.entity.Entity;
import hu.molnard.model.ModelTexture;
import hu.molnard.model.RawModel;
import hu.molnard.model.TexturedModel;
import hu.molnard.obj.ModelData;
import hu.molnard.obj.OBJFileLoader;
import hu.molnard.render.Loader;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class EntityEnviroment
{
    private ModelData tree = OBJFileLoader.loadOBJ("Tree/tree1");

    public List<Entity> fill(Loader loader)
    {
        List<Entity> entities = new ArrayList<>();


        entities.add(
                new Entity(
                    new Vector3f(0, 0, -20),
                        new TexturedModel(
                             loader.createVAO(
                                     tree.getVertices(),
                                     tree.getTextureCoords(),
                                     tree.getNormals(),
                                     tree.getIndices()
                             ),
                             new ModelTexture(
                                     loader.loadTexture(
                                             "src/main/resources/models/Tree/tree1.png"
                                     )
                             )
                        ),
                        new Vector3f(0, 0, 0),
                        0.5f
                ));

        return entities;
    }
}
