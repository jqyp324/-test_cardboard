package se.jayway.opengl.tutorial;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by yupeng on 2016/5/24.
 */
public class BufferUtil {

    public static FloatBuffer floatToBuffer(float[] a){
        //先初始化buffer，数组的长度*4，因为一个float占4个字节
        ByteBuffer mbb = ByteBuffer.allocateDirect(a.length*4);
        //数组排序用nativeOrder
        mbb.order(ByteOrder.nativeOrder());
        FloatBuffer mBuffer = mbb.asFloatBuffer();
        mBuffer.put(a);
        mBuffer.position(0);
        return mBuffer;
    }

}