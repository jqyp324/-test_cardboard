package se.jayway.opengl.tutorial;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class TutorialPartI extends Activity implements GestureDetector.OnGestureListener {

    // 定义手势检测器实例
    GestureDetector detector;
    // 定义旋转角度
    private float anglex = 0f;
    private float angley = 0f;
    static final float ROTATE_FACTOR = 60;

    OpenGlSurfaceView view;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
// 		OpenGlSurfaceView view = new OpenGlSurfaceView(this,new OpenGLRenderer(bitmap));
 		view = new OpenGlSurfaceView(this,new MyRenderer(this));
   		setContentView(view);

        // 创建手势检测器
        detector = new GestureDetector(this, this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent me) {
        // 将该Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(me);
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2,
                            float distanceX, float distanceY) {

        setAnglexAndAndley(distanceX, distanceY);

        Log.d("chen", "onScroll(),distanceX=" + distanceX + ",distanceY="
                + distanceY);
        return true;
    }

    private void setAnglexAndAndley(float velocityX, float velocityY) {
        velocityX = velocityX > 2000 ? 2000 : velocityX;
        velocityX = velocityX < -2000 ? -2000 : velocityX;
        velocityY = velocityY > 2000 ? 2000 : velocityY;
        velocityY = velocityY < -2000 ? -2000 : velocityY;

        // 根据横向上的速度计算沿Y轴旋转的角度
        angley -= velocityX * ROTATE_FACTOR / 2000;
        // 根据纵向上的速度计算沿X轴旋转的角度
        anglex -= velocityY * ROTATE_FACTOR / 2000;

        view.setAngle(anglex,angley);

    }

    @Override
    public void onShowPress(MotionEvent event) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return false;
    }
}
