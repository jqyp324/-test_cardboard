package se.jayway.opengl.tutorial;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLOtherRenderer implements Renderer {

	private float cr,cg,cb;

	private static float[] mTriangleArray = {
			0f,1f,0f,
			-1f,-1f,0f,
			1f,-1f,0f
	};

	private FloatBuffer mTriangleBuffer;

	/*
	 * (non-Javadoc)
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.
     * microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		mTriangleBuffer = BufferUtil.floatToBuffer(mTriangleArray);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.
         * microedition.khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
//		gl.glClearColor(cr,cr,cb,1.0f);

		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//		gl.glDrawElements(GL10.GL_TRIANGLES,3,GL10.GL_FLOAT, mTriangleBuffer);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.* microedition.khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);// OpenGL docs.
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.
		// Reset the projection matrix
		gl.glLoadIdentity();// OpenGL docs.
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f,(float) width / (float) height,0.1f, 100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.
		// Reset the modelview matrix
		gl.glLoadIdentity();// OpenGL docs.
	}


	public void setColor(float r,float g,float b){
		cr = r;
		cg = g;
		cb = b;
	}




}
