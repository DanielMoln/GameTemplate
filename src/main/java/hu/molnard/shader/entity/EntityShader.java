package hu.molnard.shader.entity;

import hu.molnard.help.MathHelper;
import hu.molnard.shader.AShader;
import hu.molnard.util.Settings;
import org.lwjgl.util.vector.Matrix4f;

public class EntityShader extends AShader
{
    private static final String RES_LOC = "src/main/java/hu/molnard/shader/";
    public static final String vertexShader = RES_LOC + "entity/vertexShader.glsl";
    public static final String fragmentShader = RES_LOC + "entity/fragmentShader.glsl";

    private Settings settings;
    private MathHelper mathHelper;

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;

    public EntityShader(Settings settings, MathHelper mathHelper)
    {
        super(vertexShader, fragmentShader);
        this.settings = settings;
        this.mathHelper = mathHelper;
    }

    @Override
    protected void getAllUniformLocation() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightColour = super.getUniformLocation("lightColour");
        location_lightPosition = super.getUniformLocation("lightPosition");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "vertexPosition");
        super.bindAttribute(1, "vertexTextureCoord");
        super.bindAttribute(2, "vertexNormal");
    }

    public void loadTransformationMatrix(Matrix4f matrix)
    {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix)
    {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix()
    {
        Matrix4f matrix = mathHelper.createViewMatrix(settings.getCamera());
        super.loadMatrix(location_viewMatrix, matrix);

    }

    public void loadLightValues()
    {
        super.loadVector(location_lightColour, settings.getLightColor());
        super.loadVector(location_lightPosition, settings.getLightPosition());
    }
}
