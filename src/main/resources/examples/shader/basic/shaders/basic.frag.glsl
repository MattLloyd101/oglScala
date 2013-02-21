#ifdef GL_ES
precision mediump float;
#endif

uniform vec4 vertColor;

const int MaximumRaySteps = 10;
float MinimumDistance = 0.25;
int Iterations = 5;
int Scale = 1;
int Offset = 0;

vec3 lightpos = vec3(500, 250, 0);
vec3 diffuseColour = vec3(0.0, .70, 1.0);
float diffusePower = 500.0;
vec3 specularColour = vec3(1.0, 1.0, 1.0);
float specularPower = 50.0;
float specularHardness = 1.0;

float sdSphere( vec3 p, float r )
{
  return length(p)-r;
}

float hollowSphere( vec3 p, float r )
{
  return abs(length(p)-r);
}

float DE(vec3 z)
{
  z.xy = mod((z.xy),vec2(1.0))-vec2(0.5); // instance on xy-plane
  return length(z)-0.3;             // sphere DE
}

vec3 unpackColor(float f) {
    vec3 color;
    color.b = floor(f / 256.0 / 256.0);
    color.g = floor((f - color.b * 256.0 * 256.0) / 256.0);
    color.r = floor(f - color.b * 256.0 * 256.0 - color.g * 256.0);
    return color / 256.0;
}

vec4 trace(vec3 from, vec3 direction) {
	float totalDistance = 0.0;
	int outSteps;
	vec4 outCol;
	for (int steps=0; steps < MaximumRaySteps; steps++) {
		vec3 p = from + totalDistance * direction;
		float distance = DE(p);
		totalDistance += distance;
		if (distance < MinimumDistance) break;
		outSteps = steps;
	}

	float d = 1.0-float(outSteps)/float(MaximumRaySteps);
	vec3 lightdir = lightpos - from;
	float dist = length (lightdir);
	lightdir = lightdir / dist;
	dist = dist * dist;

	vec3 n = normalize(vec3(DE(from+direction.x)-DE(from-direction.x),
			DE(from+direction.y)-DE(from-direction.y),
			DE(from+direction.z)-DE(from-direction.z)));
	float NdotL = dot(n, lightdir);
	float intensity = clamp(NdotL, 0.0, 1.0);
	vec3 diffuse = intensity * diffuseColour * diffusePower / dist;
	vec3 H = normalize(lightdir - direction);
	float NdotH = dot(n, H);
	intensity = pow( clamp(NdotH, 0.0, 1.0), specularHardness);
	vec3 specular = intensity * specularColour * specularPower / dist;
	outCol = vec4(diffuse + specular,1.0);

	return outCol;
}

void main() {
    vec3 pos = gl_FragCoord.xyz;
    vec4 col = trace(pos, pos - vec3(0,0,-500));

    gl_FragColor = col;
}