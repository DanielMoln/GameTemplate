package hu.molnard.render;

import hu.molnard.model.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader
{
    /* storages */
    private List<Integer> vaoArray = new ArrayList<>();
    private List<Integer> vboArray = new ArrayList<>();
    private List<Integer> textureCoordArray = new ArrayList<>();

    /* storing vao in the memory */
    public RawModel createVAO(float[] positions, float[] textCoords, float[] normals, int[] indices)
    {
        int id = GL30.glGenVertexArrays();  vaoArray.add(id); GL30.glBindVertexArray(id);
        convertIndicesToBuffer(indices);

        /*
            VAO object Components:
                0: positions
                1: texture coords
                2: normal vectors
        */
        int dimension = 2;

        insertDataToAttribute(0, dimension, positions);
        insertDataToAttribute(1, dimension, textCoords);
        insertDataToAttribute(2, dimension, normals);

        GL30.glBindVertexArray(0); /* unbind the current vao */
        return new RawModel(id, positions.length / 2);
    }

    /* load and save texture */
    public int loadTexture(String path)
    {
        Texture texture = null;

        try {
            texture = TextureLoader.getTexture("PNG", new FileInputStream(path));
            GL30.glGenerateMipmap(
                    GL11.GL_TEXTURE_2D
            );
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D,
                    GL11.GL_TEXTURE_MIN_FILTER,
                    GL11.GL_LINEAR_MIPMAP_LINEAR
            );
            GL11.glTexParameterf(
                    GL11.GL_TEXTURE_2D,
                    GL14.GL_TEXTURE_LOD_BIAS,
                    -1
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        int textureID = texture.getTextureID(); textureCoordArray.add(textureID);
        return textureID;
    }

    public void cleanUp()
    {
        vaoArray.forEach(a -> GL30.glDeleteVertexArrays(a));
        vboArray.forEach(a -> GL15.glDeleteBuffers(a));
        textureCoordArray.forEach(a -> GL11.glDeleteTextures(a));
    }

    /** @param dimension It can be 2D or 3D */
    private void insertDataToAttribute(int attributeNumber, int dimension, float[] data) {
        int vboID = GL15.glGenBuffers(); vboArray.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, dimension, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /* enabling indices */
    private void convertIndicesToBuffer(int[] indices)
    {
        int vboId = GL15.glGenBuffers(); vboArray.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /* convert data to intbuffer because it get more functionality and it is require for opengl. */
    private IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip(); /* You must flip because If you don't do this then you can read/write to it*/
        return buffer;
    }

    /* convert data to intbuffer because it get more functionality and it is require for opengl. */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip(); /* You must flip because If you don't do this then you can read/write to it*/
        return buffer;
    }
}
