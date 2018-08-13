package wang.cn.com.optimize.ui.widget.vr;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VRImageView的 陀螺仪 控制器
 */
public class VRManager implements SensorEventListener{

    /**
     * 将纳秒转化为秒
     */
    private static final float NS2S = 1.0f / 1000000000.0f;

    /**
     * 维护 VRImageView 的 状态, 需要传感器处理的 GyroscopeImageView 其对应的 value 为 true
     */
    private Map<VRImageView, Boolean> mViewsMap = new HashMap<>(9);

    /**
     * 维护需要传感器处理的 Activity
     */
    private List<Activity> mActivityList = new ArrayList<>();

    private SensorManager sensorManager;
    private long mLastTimestamp;
    private double mMaxAngle = Math.PI / 2;

    private VRManager(){

    }

    private static class InstanceHolder {
        private static final VRManager sVRManager = new VRManager();
    }

    public static VRManager getInstance() {
        return InstanceHolder.sVRManager;
    }

    protected void addView(VRImageView vrImageView) {
        if (mActivityList.contains(getActivityFromView(vrImageView))) {
            mViewsMap.put(vrImageView,true);
        }else {
            mViewsMap.put(vrImageView,false);
        }
    }

    protected void removeView(VRImageView vrImageView) {
        mViewsMap.remove(vrImageView);
    }

    public void register(Activity activity){
        mActivityList.add(activity);
        for (VRImageView vrImageView : mViewsMap.keySet()){
            for (Activity a : mActivityList){
                if (getActivityFromView(vrImageView) == a){
                    mViewsMap.put(vrImageView,true);
                }
            }
        }

        if (sensorManager == null) {
            sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        }

        Sensor sensor;

        if (sensorManager != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            //灵敏度从快到慢 可选择: SENSOR_DELAY_FASTEST; SENSOR_DELAY_GAME; SENSOR_DELAY_NORMAL; SENSOR_DELAY_UI
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
            mLastTimestamp = 0;
        }
    }

    public void unregister(Activity activity) {
        mActivityList.remove((activity));
        for (VRImageView vrImageView : mViewsMap.keySet()) {
            if (getActivityFromView(vrImageView) == activity) {
                mViewsMap.put(vrImageView, false);
            }
        }
        sensorManager.unregisterListener(this);
        sensorManager = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            if (mLastTimestamp != 0){
                for (Map.Entry<VRImageView, Boolean> entry : mViewsMap.entrySet()) {
                    //只处理集合中 value 为 true 的 view
                    if (entry.getValue()) {
                        entry.getKey().mAngleX +=
                                event.values[0] * (event.timestamp - mLastTimestamp) * NS2S * 2.0f;
                        entry.getKey().mAngleY +=
                                event.values[1] * (event.timestamp - mLastTimestamp) * NS2S * 2.0f;
                        if (entry.getKey().mAngleX > mMaxAngle) {
                            entry.getKey().mAngleX = mMaxAngle;
                        }
                        if (entry.getKey().mAngleX < -mMaxAngle) {
                            entry.getKey().mAngleX = -mMaxAngle;
                        }
                        if (entry.getKey().mAngleY > mMaxAngle) {
                            entry.getKey().mAngleY = mMaxAngle;
                        }
                        if (entry.getKey().mAngleY < -mMaxAngle) {
                            entry.getKey().mAngleY = -mMaxAngle;
                        }
                        entry.getKey()
                                .update(entry.getKey().mAngleY / mMaxAngle, entry.getKey().mAngleX / mMaxAngle);
                    }
                }
            }

            mLastTimestamp = event.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Activity getActivityFromView(View view) {
        return (Activity) view.getContext();
    }
}
