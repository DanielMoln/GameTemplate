# version 130

in vec3 vertexPosition;
in vec2 vertexTextureCoord;
in vec3 vertexNormal;
out vec3 toCameraVector;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

uniform vec3 lightPosition;

out vec3 surfaceNormal;
out vec3 toLightVector;
out vec2 pass_vertexTexture;

void main(void)
{
    vec4 worldPosition = transformationMatrix  * vec4( vertexPosition, 1.0 );
    // lighting
    surfaceNormal = (transformationMatrix * vec4(vertexNormal, 0.0)).xyz;
    toLightVector = lightPosition - worldPosition.xyz;
    toCameraVector = (transpose(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

    // texturing
    pass_vertexTexture = vertexTextureCoord;

    gl_Position = projectionMatrix * viewMatrix * worldPosition;
}