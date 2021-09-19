# version 130

in vec2 pass_vertexTexture;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

uniform sampler2D textureSampler;

uniform vec3 lightColour;

out vec4 out_Color;

void main(void)
{
    /* set up diffuse lighting */
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal,unitLightVector);
    float brightness = max(nDot1,  0.2);
    vec3 diffuse = brightness * lightColour;

    vec4 texuredColour = texture(textureSampler, pass_vertexTexture);

    /* transparency */
    if ( texuredColour.a < 0.5f )
    {
        discard;
    }

    out_Color = vec4(diffuse, 1.0)  *  texuredColour;
}