package se.jayway.opengl.tutorial;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements Renderer {
	private float cr,cg,cb;
	private float[] mTriangleArray = {
			0f, 0f, 0f,
			320f, 0f, 0f,
			160f, 480f, 0f
	};

	private FloatBuffer mTriangleBuffer;
	private ShortBuffer indices;

	private float[] mQuadsArray = {
		1f,1f,0f,                           //右上
		-1f,1f,0f,                          //左上
		-1f,-1f,0f,                         //左下
		1f,-1f,0f                           //右下
	};
	//从这里可以看出，我们按照逆时针的方向画图
	private FloatBuffer mQuadsBuffer;

	private Bitmap mBitmap;

	private int mWidth,mHeight;

	public OpenGLRenderer() {
	}

	public OpenGLRenderer(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	public void setWidth(int width) {
		mWidth = width;
	}

	public void setHeight(int height) {
		mHeight = height;
	}


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
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);

		mTriangleBuffer = BufferUtil.floatToBuffer(mTriangleArray);
		ByteBuffer indicesBuffer = ByteBuffer.allocateDirect(3 * 2);
		indicesBuffer.order(ByteOrder.nativeOrder());
		indices = indicesBuffer.asShortBuffer();
		indices.put(new short[] { 0, 1, 2 });
		//indices.flip() == indices.position(0)
		indices.flip();

		float ratio = (float) this.mWidth / mHeight;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

		mQuadsBuffer = BufferUtil.floatToBuffer(mQuadsArray);

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
		// 启用顶点座标数据
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// 启用贴图座标数组数据
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY); // ①
		// 设置当前矩阵模式为模型视图。
		gl.glMatrixMode(GL10.GL_PROJECTION);
//		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
//		gl.glClearColor(cr,cg,cb,1.0f);
		//glOrtho是创建一个正交平行的视景体(int left, int right, int bottom, int top, int near, int far)
		gl.glOrthof(0, mWidth, 0, mHeight, 0, -1);

		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP,3,GL10.GL_UNSIGNED_SHORT,indices );

		gl.glTranslatef(1.5f, 0.0f, -6.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mQuadsBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

		gl.glFinish();
		// 禁用顶点、纹理座标数组
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

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
//		GLU.gluPerspective(gl, 45.0f,
//				(float) width / (float) height,
//				0.1f, 100.0f);
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
