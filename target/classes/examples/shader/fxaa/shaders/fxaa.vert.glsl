#version 400

uniform vec3 position;
uniform vec3 rotation;

mat4x4 projectionMatrix() {
    mat4x4 p=mat4x4(1.0);
    p[3].x=position.x;
    p[3].y=position.y;
    p[3].z=position.z;
    mat4x4 heading=mat4x4(1.0);
    heading[0][0]=cos(rotation.y);
    heading[0][2]=-(sin(rotation.y));
    heading[2][0]=sin(rotation.y);
    heading[2][2]=cos(rotation.y);
    mat4x4 pitch=mat4x4(1.0);
    pitch[1][1]=cos(rotation.x);
    pitch[1][2]=sin(rotation.x);
    pitch[2][1]=-(sin(rotation.x));
    pitch[2][2]=cos(rotation.x);
    mat4x4 roll=mat4x4(1.0);
    roll[0][0]=cos(rotation.z);
    roll[0][1]=sin(rotation.z);
    roll[1][0]=-(sin(rotation.z));
    roll[1][1]=cos(rotation.z);
    return p*heading*pitch*roll;
}

void main(){
    gl_Position = gl_ModelViewProjectionMatrix*projectionMatrix()*gl_Vertex;
}
