package se.jayway.opengl.tutorial;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yupeng on 2016/5/24.
 */
public class OpenGlSurfaceView extends GLSurfaceView {

    private OpenGLRenderer mRenderer;

    /**
     * Standard View constructor. In order to render something, you
     * must call {@link #setRenderer} to register a renderer.
     *
     * @param context
     */
    public OpenGlSurfaceView(Context context) {
        super(context);
    }

    public OpenGlSurfaceView(Context context,OpenGLRenderer renderer){
        super(context);
        mRenderer = renderer;
        setRenderer(renderer);
    }

    /**
     * Standard View constructor. In order to render something, you
     * must call {@link #setRenderer} to register a renderer.
     *
     * @param context
     * @param attrs
     */
    public OpenGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mRenderer.setColor(event.getX()/getWidth(),event.getY()/getHeight(),1.0f);

        return super.onTouchEvent(event);
    }
}
