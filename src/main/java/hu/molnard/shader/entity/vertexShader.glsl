# version 130

in vec3 vertexPosition;
in vec2 vertexTextureCoord;
in vec3 vertexNormal;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform float useFakeLighting;
uniform vec3 lightPosition;

out vec3 toCameraVector;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec2 pass_vertexTexture;

void main(void)
{
    vec4 worldPosition = transformationMatrix  * vec4( vertexPosition, 1.0 );
    /* transparency check */
    vec3 actualNormal = vertexNormal;
    if (useFakeLighting > 0.5)
    {
        actualNormal = vec3(0.0, 1.0, 0.0);
    }

    /* lighting - If you found any error, try to change actualNormal to vertexNormal below this comment. */
    surfaceNormal = (transformationMatrix * vec4(actualNormal, 0.0)).xyz;
    toLightVector = lightPosition - worldPosition.xyz;
    toCameraVector = (transpose(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

    /* texturing */
    pass_vertexTexture = vertexTextureCoord;

    gl_Position = projectionMatrix * viewMatrix * worldPosition;
}