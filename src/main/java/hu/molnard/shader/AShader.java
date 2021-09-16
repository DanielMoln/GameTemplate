package hu.molnard.shader;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class AShader
{
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public AShader(String vertexFileURL, String fragmentFileURL)
    {

        vertexShaderID = loadShader(vertexFileURL, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFileURL, GL20.GL_FRAGMENT_SHADER);

        programID = GL20.glCreateProgram();

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        bindAttributes();

        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);

        getAllUniformLocation();

    }

    protected int getUniformLocation(String uniformVariableName)
    {
        return GL20.glGetUniformLocation(programID, uniformVariableName);
    }

    protected abstract void getAllUniformLocation();

    protected void loadFloat( int uniformLocation, float value )
    {
        GL20.glUniform1f(uniformLocation, value);
    }

    protected void loadVector( int uniformLocation, Vector3f vector )
    {
        GL20.glUniform3f(uniformLocation, vector.x, vector.y,  vector.z);
    }

    protected void loadBoolean ( int uniformLocation, boolean value )
    {
        int toLoad = 0;

        if ( value )
            toLoad = 1;

        GL20.glUniform1f(uniformLocation, toLoad);
    }

    protected void loadInt(int location, int value)
    {
        GL20.glUniform1i(location, value);
    }

    protected void loadMatrix (int uniformLocation, Matrix4f matrix)
    {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(uniformLocation, false, matrixBuffer);
    }

    protected  void bindAttribute(int attrib, String GLSL_variableName)
    {
        GL20.glBindAttribLocation(programID, attrib, GLSL_variableName);
    }

    public void start()
    {
        GL20.glUseProgram(programID);
    }

    public void stop()
    {
        GL20.glUseProgram(0);
    }

    public  void cleanUp()
    {

        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);

    }

    private static int loadShader(String fileURL, int ShaderID)
    {
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileURL));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(ShaderID);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            System.err.println("Could not compile shader!" );
            System.exit(-1);
        }
        return shaderID;
    }

    protected abstract  void bindAttributes();
}
