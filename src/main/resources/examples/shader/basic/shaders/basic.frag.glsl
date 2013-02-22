uniform vec4 vertColor;

vec3 camera = vec3(0.0,0.0,-100.0);
const int MaximumRaySteps = 24;
float MinimumDistance = 1.;
int Iterations = 5;
int Scale = 1;
int Offset = 0;

vec3 lightpos = vec3(500, 250, 0);
vec3 diffuseColour = vec3(0.0, .70, 1.0);
float diffusePower = 500.0;
vec3 specularColour = vec3(1.0, 1.0, 1.0);
float specularPower = 500.0;
float specularHardness = 1.0;

float sdSphere( vec3 z, vec3 p, float r )
{
  return length(z - p)-(r*r);
}

float sdTorus( vec3 p, vec2 t )
{
  vec2 q = vec2(length(p.xz)-t.x,p.y);
  return length(q)-t.y;
}

float DE(vec3 p)
{
  mat4 m = mat4(1, 0, 0, 10,
		0, 1, 0, 10,
		0, 0, 1, 10,
		0, 0, 0, 1);
	
  vec3 q = inverse(m)*p;
  return sdTorus(q, vec2(20, 20));
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
	vec4 outCol = vec4(0.0,0.0,0.0,1.0);
	for (int steps=0; steps < MaximumRaySteps; steps++) {
		vec3 p = from + totalDistance * direction;
		float distance = DE(p);
		if(distance < 0) break;
		totalDistance += distance;
		if (distance < MinimumDistance) break;
		outSteps = steps;
	}

	float d = 1.0-float(outSteps)/float(MaximumRaySteps);
	outCol = vec4(d,d,d,1.0);
	
	//if(t > 23) outCol = vec4(1,0,0,1);
	

	return outCol;
}

void main() {
    vec3 pos = gl_FragCoord.xyz;
    vec4 col = trace(camera, pos - camera);
    gl_FragColor = col;
}