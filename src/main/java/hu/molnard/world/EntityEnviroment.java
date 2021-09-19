package hu.molnard.world;

import hu.molnard.entity.Entity;
import hu.molnard.model.ModelTexture;
import hu.molnard.model.RawModel;
import hu.molnard.model.TexturedModel;
import hu.molnard.module.Path;
import hu.molnard.obj.ModelData;
import hu.molnard.obj.OBJFileLoader;
import hu.molnard.render.Loader;
import org.lwjgl.util.vector.Vector3f;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EntityEnviroment
{
    public List<Entity> fill(Loader loader)
    {
        List<Entity> entities = new ArrayList<>();

        ModelData cloud = OBJFileLoader.loadOBJ("Box/box");
        ModelData grass = OBJFileLoader.loadOBJ("Grass/grassModel");

        RawModel cloudRawModel = loader.createVAO(
                cloud.getVertices(),
                cloud.getTextureCoords(),
                cloud.getNormals(),
                cloud.getIndices()
        );
        RawModel grassRawModel = loader.createVAO(
                grass.getVertices(),
                grass.getTextureCoords(),
                grass.getNormals(),
                grass.getIndices()
        );

        Entity grassEntity = new Entity(
                new Vector3f(-12,8,-30),
                new TexturedModel(
                        grassRawModel,
                        new ModelTexture(
                                loader.loadTexture(Path.getInstance().join("src/main/resources/models/Grass", "fern.png"))
                        )
                ),
                new Vector3f(2,5,4),
                1f
        ); entities.add(grassEntity);


        Entity cloudEntity = new Entity(
                new Vector3f(-12,8,-30),
                new TexturedModel(
                        cloudRawModel,
                        new ModelTexture(
                                loader.loadTexture(Path.getInstance().join("src/main/resources/models/Box", "box.png"))
                        )
                ),
                new Vector3f(2,5,4),
                1.8f
        ); entities.add(cloudEntity);

        Entity cloudEntity2 = new Entity(
                new Vector3f(-2,8,-30),
                new TexturedModel(
                        cloudRawModel,
                        new ModelTexture(
                                loader.loadTexture(Path.getInstance().join("src/main/resources/models/Box", "box.png"))
                        )
                ),
                new Vector3f(2,5,4),
                1.7f
        ); entities.add(cloudEntity2);

        Entity cloudEntity3 = new Entity(
                new Vector3f(-8,2,-30),
                new TexturedModel(
                        cloudRawModel,
                        new ModelTexture(
                                loader.loadTexture(Path.getInstance().join("src/main/resources/models/Box", "box.png"))
                        )
                ),
                new Vector3f(2,5,4),
                1.9f
        ); entities.add(cloudEntity3);


        return entities;
    }
}
