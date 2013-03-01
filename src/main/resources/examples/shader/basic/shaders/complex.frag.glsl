#version 150 core
#ifdef GL_ES
precision highp float;
#endif

uniform float time = 0;
uniform vec2 resolution = vec2(1024, 768);

struct Lighting
{
    vec3 Diffuse;
    vec3 Specular;
};

struct PointLight
{
        vec3 position;
        vec3 diffuseColor;
        float diffusePower;
        vec3 specularColor;
        float specularPower;
        float specularHardness;
};

Lighting GetPointLight( PointLight light, vec3 pos3D, vec3 viewDir, vec3 normal )
{
        Lighting OUT;
        if( light.diffusePower > 0 )
        {
                vec3 lightDir = light.position - pos3D; //3D position in space of the surface
                float distance = length( lightDir );
                lightDir = lightDir / distance; // = normalize( lightDir );
                distance = distance * distance; //This line may be optimised using Inverse square root

                //Intensity of the diffuse light. Saturate to keep within the 0-1 range.
                float NdotL = dot( normal, lightDir );
                float intensity = clamp( NdotL, 0.0, 1.0);

                // Calculate the diffuse light factoring in light color, power and the attenuation
                OUT.Diffuse = intensity * light.diffuseColor * light.diffusePower / distance;

                //Calculate the half vector between the light vector and the view vector.
                //This is faster than calculating the actual reflective vector.
                vec3 H = normalize( lightDir + viewDir );

                //Intensity of the specular light
                float NdotH = dot( normal, H );
                intensity = pow( clamp( NdotH, 0.0, 1.0 ), light.specularHardness );

                //Sum up the specular light factoring
                OUT.Specular = intensity * light.specularColor * light.specularPower / distance;
        }
        return OUT;
}

float sdTorus( vec3 p, vec2 t )
{
  vec2 q = vec2(length(p.xz)-t.x,p.y);
  return length(q)-t.y;
}

float sdSphere( vec3 p, float r )
{
  return length(p)-r;
}

float smoothcurve( float x ) {
    return ((x * x) * (3.00000 - (2.00000 * x)));
}


float DE(vec3 z)
{
	vec3 a1 = vec3(1,1,1);
	vec3 a2 = vec3(-1,-1,1);
	vec3 a3 = vec3(1,-1,-1);
	vec3 a4 = vec3(-1,1,-1);
	vec3 c;
	int n = 0;
	float dist, d;
	while (n < 100) {
		 c = a1; dist = length(z-a1);
	        d = length(z-a2); if (d < dist) { c = a2; dist=d; }
		 d = length(z-a3); if (d < dist) { c = a3; dist=d; }
		 d = length(z-a4); if (d < dist) { c = a4; dist=d; }
		z = 1*z-c*(1-1.0);
		n++;
	}

	return length(z) * pow(1, float(-n));
}

float e(vec3 c)
{
    // c=cos(vec3(cos(c.r+time/6.0)*c.r-cos(c.g*3.0+time/5.0)*c.g, cos(time/4.0)*c.b/3.0*c.r-cos(time/7.0)*c.g, c.r+c.g+c.b+time));
    float a = sdSphere(c, .5);
    float b = sdSphere(c - vec3(.4,0,0), .5);
    float dd = smoothcurve(a-b);
    return mix(a, b, dd);
}


void main(void)
{
    vec2 c=-1.0+2.0*gl_FragCoord.rg/resolution.xy;
    vec3 o=vec3(c,0.0),g=vec3(c,1.0)/64.0,v=vec3(0.5);
//    float m = 1.0-1.5*mouse.x/resolution.x;

    int r = 0;
    for(r=0;r<100;r++)
    {
      float h=e(o);
      if(h<0.0)break;
      o+=h*10.0*g;
    }
    // light (who needs a normal?)
    //v+=-e(o);//*vec3(0.4,0.7,1.0);

    vec3 n = normalize(vec3(e(o+g.x)-e(o-g.x),
                            e(o+g.y)-e(o-g.y),
                            e(o+g.z)-e(o-g.z)));

    PointLight light;
    light.position = vec3(0.1, 1.0, 0.0);
    light.diffuseColor = vec3(.0, .25, .75);
    light.diffusePower = .50;
    light.specularColor = vec3(1.0, 1.0, 1.0);
    light.specularPower = 1.0;
    light.specularHardness = 1.0;

    Lighting l = GetPointLight(light, o, g, n);
    v+= l.Diffuse + l.Specular;


    // ambient occlusion
    float a=0.0;
    for(int q=0;q<100;q++)
    {
       float l = e(o+0.5*vec3(cos(1.1*float(q)),cos(1.6*float(q)),cos(1.4*float(q))));
       a+=clamp(4.0*l,0.0,1.0);
    }
    v*=a/100.0;
    gl_FragColor=vec4(v,1.0);
}    
    