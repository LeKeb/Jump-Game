uniform mat4 u_projTrans;

attribute vec2 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

varying vec4 v_color;
varying vec2 v_texCoord;

void main() {
	v_color = a_color;
	v_texCoord = a_texCoord0;
	gl_Position = u_projTrans * vec4(a_position, 0.0, 1.0);
}
