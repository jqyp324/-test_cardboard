package se.jayway.opengl.tutorial;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements Renderer {
	private float cr,cg,cb;
	private float[] mTriangleArray = {
			0f,1f,0f,
			-1f,-1f,0f,
			1f,-1f,0f
	};

	private FloatBuffer mTriangleBuffer;

	private Bitmap mBitmap;

	public OpenGLRenderer() {
	}

	public OpenGLRenderer(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	/*
             * (non-Javadoc)
             * @see
             * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.
             * microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
             */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);  // OpenGL docs.
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);// OpenGL docs.
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);// OpenGL docs.
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.
				GL10.GL_NICEST);


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
//		gl.glClearColor(cr,cg,cb,1.0f);

		gl.glColor4f(123.0f, 255.0f, 1.0f, 1.0f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//		gl.glDrawElements(GL10.GL_TRIANGLES,3,GL10.GL_FLOAT, mTriangleBuffer);
//		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//
//		// Enable vertex-array and define the buffers
//		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
//		// Draw the primitives via index-array
//		gl.glDrawElements(GL10.GL_TRIANGLES, 3, GL10.GL_UNSIGNED_BYTE, mTriangleBuffer);
//		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


//		IntBuffer intBuffer = IntBuffer.allocate(1);
//		gl.glGenTextures(1, intBuffer);
//		int texture = intBuffer.get();
//		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
//		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
//		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
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
		GLU.gluPerspective(gl, 45.0f,
				(float) width / (float) height,
				0.1f, 100.0f);
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
