package wang.cn.com.optimize.ui.find.animation.property_animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.ArrayList;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-10-16
 * @time: 15:03
 */
public class PropertyAnimationActivity extends AppCompatActivity{

    private TextView tvDemo;
    private TextView doByXml;
    private TextView doByCode;
    private TextView doByCustom;
    private TextView viewPropertyAnimator;
    private TextView layoutAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_property);

        tvDemo = findViewById(R.id.tv_demo);
        doByXml = findViewById(R.id.do_by_xml);
        doByCode = findViewById(R.id.do_by_code);
        doByCustom = findViewById(R.id.do_by_custom);
        viewPropertyAnimator = findViewById(R.id.view_property_animator);
        layoutAnimator = findViewById(R.id.layout_animator);

        doByXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int height = tvDemo.getLayoutParams().height;
                final int width = tvDemo.getLayoutParams().width;
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.
                        loadAnimator(PropertyAnimationActivity.this, R.animator.animatorset);
                ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();
                ((ValueAnimator) childAnimations.get(childAnimations.size() - 1))
                        .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                float animatedValue = (float) valueAnimator.getAnimatedValue();
                                tvDemo.getLayoutParams().height = (int) (height * animatedValue);
                                tvDemo.getLayoutParams().width = (int) (width * animatedValue);
                                tvDemo.requestLayout();
                            }
                        });
                animatorSet.setTarget(tvDemo);
                animatorSet.start();
            }
        });

        doByCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(getObjectAnimatorByPropertyValuesHolder(), getValueAnimator());
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.start();
            }
        });

        doByCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int height = tvDemo.getLayoutParams().height;
                final int width = tvDemo.getLayoutParams().width;
                PropertyBean startPropertyBean = new PropertyBean(0xff009688, 0f, 1f);
                PropertyBean endPropertyBean = new PropertyBean(0xff795548, 360f, 3.0f);

//ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(),startPropertyBean,endPropertyBean);
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(3000);
                //custom interpolator
                valueAnimator.setInterpolator(new SpeedUpInterpolator());
                valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                valueAnimator.setRepeatCount(1);

                valueAnimator.setObjectValues(startPropertyBean, endPropertyBean);
                //custom evaluator
                valueAnimator.setEvaluator(new MyTypeEvaluator());

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        PropertyBean propertyBean = (PropertyBean) valueAnimator.getAnimatedValue();
                        if (propertyBean.getBackgroundColor() != 0 && propertyBean.getBackgroundColor() != 1) {
                            tvDemo.setBackgroundColor(propertyBean.getBackgroundColor());
                        }
                        tvDemo.setRotationX(propertyBean.getRotationX());
                        tvDemo.getLayoutParams().height = (int) (height * propertyBean.getSize());
                        tvDemo.getLayoutParams().width = (int) (width * propertyBean.getSize());
                        tvDemo.requestLayout();
//              mPuppet.postInvalidate();
                    }
                });

                valueAnimator.start();
            }
        });

        viewPropertyAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimator viewPropertyAnimator = tvDemo.animate()
                        .rotationX(360f)
                        .alpha(0.5f)
                        .scaleX(3).scaleY(3)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(3000)
                        .setStartDelay(0);
                viewPropertyAnimator.start();
            }
        });

        layoutAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutTransition layoutTransition = new LayoutTransition();

                layoutTransition.setAnimator(LayoutTransition.APPEARING, getObjectAnimator(false));
                layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, getObjectAnimator(true));
                layoutTransition.setDuration(2000);

                //mPuppet's parentView
                ViewGroup contentView = (ViewGroup) ((ViewGroup) getWindow().getDecorView().
                        findViewById(android.R.id.content)).getChildAt(0);
                contentView.setLayoutTransition(layoutTransition);
                if (contentView.findViewById(R.id.tv_demo) == null) {
                    contentView.addView(tvDemo);
                } else {
                    contentView.removeView(tvDemo);
                }
            }
        });

    }

    public ObjectAnimator getObjectAnimator(boolean b) {
        if (b) {
            ObjectAnimator bgColorAnimator = ObjectAnimator.ofArgb(tvDemo,
                    "backgroundColor",
                    0xff009688, 0xff795548);
            bgColorAnimator.setRepeatCount(1);
            bgColorAnimator.setDuration(3000);
            bgColorAnimator.setRepeatMode(ValueAnimator.REVERSE);
            bgColorAnimator.setStartDelay(0);
            return bgColorAnimator;
        } else {
            ObjectAnimator rotationXAnimator = ObjectAnimator.ofFloat(tvDemo,
                    "rotationX",
                    0f, 360f);
            rotationXAnimator.setRepeatCount(1);
            rotationXAnimator.setDuration(3000);
            rotationXAnimator.setRepeatMode(ValueAnimator.REVERSE);
            return rotationXAnimator;
        }
    }

    public Animator getObjectAnimatorByPropertyValuesHolder() {

        PropertyValuesHolder bgColorAnimator = PropertyValuesHolder.ofObject("backgroundColor",
                new ArgbEvaluator(),
                0xff009688, 0xff795548);
        PropertyValuesHolder rotationXAnimator = PropertyValuesHolder.ofFloat("rotationX",
                0f, 360f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvDemo,
                bgColorAnimator,rotationXAnimator);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    public ValueAnimator getValueAnimator() {
        final int height = tvDemo.getLayoutParams().height;
        final int width = tvDemo.getLayoutParams().width;

        ValueAnimator sizeValueAnimator = ValueAnimator.ofFloat(1f, 3f);
        sizeValueAnimator.setDuration(3000);
        sizeValueAnimator.setRepeatCount(1);
        sizeValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        sizeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tvDemo.getLayoutParams().height = (int) (height * animatedValue);
                tvDemo.getLayoutParams().width = (int) (width * animatedValue);
                tvDemo.requestLayout();
            }
        });
        return sizeValueAnimator;
    }



    /**
     *  Java 方式属性动画
     *  1、ObjectAnimator
     * ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360.0f).setDuration(1000).start()
     * mObjectAnimator.addUpdateListener(new AnimatorUpdateListener({
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                //int value = animation.getAnimatedValue();  可以获取当前属性值
                //view.postInvalidate();  可以主动刷新
                //view.setXXX(value);
                //view.setXXX(value);
                //......可以批量修改属性
            }
     });
     *
     * 2、PropertyValuesHolder：
     * 多属性动画同时工作管理类。有时候我们需要同时修改多个属性
     *
     *
     * 3、ValueAnimator：
     * 属性动画中的时间驱动，管理着动画时间的开始、结束属性值，相应时间属性值计算方法等
     * ValueAnimator animator = ValueAnimator.ofFloat(0, mContentHeight);  //定义动画
        animator.setTarget(view);   //设置作用目标
        animator.setDuration(5000).start();
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                float value = (float) animation.getAnimatedValue();
                view.setXXX(value);  //必须通过这里设置属性值才有效
                view.mXXX = value;  //不需要setXXX属性方法
            }
        });
     *
     *
     *
     * 4、AnimationSet：
     * 动画集合，提供把多个动画组合成一个组合的机制，并可设置动画的时序关系，
     * 如同时播放、顺序播放或延迟播放。
     * ObjectAnimator a1 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(view, "translationY", 0f, viewWidth);
        ......
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(5000);
        animSet.setInterpolator(new LinearInterpolator());
        //animSet.playTogether(a1, a2, ...); //两个动画同时执行
        animSet.play(a1).after(a2); //先后执行
        ......//其他组合方式
        animSet.start();
     *
     *
     * 5、Evaluators 相关类解释：
     * Evaluators 就是属性动画系统如何去计算一个属性值。
     * 它们通过 Animator 提供的动画的起始和结束值去计算一个动画的属性值。
     *
     *
     * 6、Interpolators 相关类解释：
     * AccelerateDecelerateInterpolator：先加速后减速。
     * AccelerateInterpolator：加速。
     * DecelerateInterpolator：减速。
     * AnticipateInterpolator：先向相反方向改变一段再加速播放。
     * AnticipateOvershootInterpolator：先向相反方向改变，再加速播放，
     * 会超出目标值然后缓慢移动至目标值，类似于弹簧回弹。
     * BounceInterpolator：快到目标值时值会跳跃。
     * CycleIinterpolator：动画循环一定次数，值的改变为一正弦函数：Math.sin(2 * mCycles * Math.PI * input)。
     * LinearInterpolator：线性均匀改变。
     * OvershottInterpolator：最后超出目标值然后缓慢改变到目标值。
     * TimeInterpolator：一个允许自定义 Interpolator 的接口，以上都实现了该接口。
     *
     *
     * 7、ViewPropertyAnimator ：
     * 提供了一种非常方便的方法为 View 的部分属性设置动画（切记，是部分属性）
     *  它可以直接使用一个 Animator 对象设置多个属性的动画
     *  在多属性设置动画时，它比上面的 ObjectAnimator 更加牛逼、高效，
     *  因为他会管理多个属性的 invalidate 方法统一调运触发
     * 所以还会有一些性能优化
     * myView.animate().x(0f).y(100f).start();
     *
     *
     * 8、LayoutAnimator 容器布局动画
     * ViewGroup 中 View 添加时的动画功能
     * LayoutTransition 对 ViewGroup 中的 View 进行动画设置显示
     * LayoutTransition 的动画效果都是设置给 ViewGroup
     * 然后当被设置动画的 ViewGroup 中添加删除 View 时体现出来。
     * 当在一个 LinerLayout 中隐藏一个 View 的时候，我们可以自定义 整个由于 LinerLayout 隐藏 View 而改变的动画
     * 同时还可以自定义被隐藏的 View 自己消失时候的动画等.
     *
     * LayoutTransition 类中主要有五种容器转换动画类型:
     * LayoutTransition.APPEARING：当 View 出现或者添加的时候 View 出现的动画。
     * LayoutTransition.CHANGE_APPEARING：当添加 View 导致布局容器改变的时候整个布局容器的动画。
     * LayoutTransition.DISAPPEARING：当View消失或者隐藏的时候 View 消失的动画。
     * LayoutTransition.CHANGE_DISAPPEARING：当删除或者隐藏 View 导致布局容器改变的时候整个布局容器的动画。
     * LayoutTransition.CHANGE：当不是由于 View 出现或消失造成对其他 View 位置造成改变的时候整个布局容器的动画。
     *
     * XML 方式使用系统提供的默认 LayoutTransition 动画：
     * android:animateLayoutChanges=”true”
     * 调用 ViewGroup 的 addView、removeView 方法时就能看见系统默认的动画效果了。
     *
     * 还有一种就是通过如下方式设置：
     * android:layoutAnimation=”@anim/customer_anim”
     *
     * Java 方式使用系统提供的默认 LayoutTransition 动画：
     * mTransitioner = new LayoutTransition();
        mViewGroup.setLayoutTransition(mTransitioner);
     *稍微再高端一点吧，我们来自定义这几类事件的动画，分别实现他们，
     * mTransitioner = new LayoutTransition();
     ......
     ObjectAnimator anim = ObjectAnimator.ofFloat(this, "scaleX", 0, 1);
     ......//设置更多动画
     mTransition.setAnimator(LayoutTransition.APPEARING, anim);
     ......//设置更多类型的动画                mViewGroup.setLayoutTransition(mTransitioner);
     *
     *
     * 9、ViewPropertyAnimator 动画(属性动画拓展)
     *  ViewPropertyAnimator 类
     *  专门针对View对象动画而操作的类。
        提供了更简洁的链式调用设置多个属性动画，这些动画可以同时进行的。
        拥有更好的性能，多个属性动画是一次同时变化，只执行一次UI刷新（也就是只调用一次 invalidate ,
        而 n 个 ObjectAnimator 就会 进行 n 次属性变化，就有 n 次 invalidate）。
        每个属性提供两种类型方法设置。
        该类只能通过 View 的 animate() 获取其实例对象的引用
     *
     * 设置一个View控件旋转 360 的代码是这样：
     * btn.animate().rotation(360).setDuration(200);
     * 复杂动画
     * btn.animate().alpha(0.5f).rotation(360).scaleX(1.5f).scaleY(1.5f)
     * .translationX(50).translationY(50).setDuration(5000);
     *
     Method	Discription
     alpha(float value)	                 设置透明度，value表示变化到多少，1不透明，0全透明。
     scaleY(float value)	             设置Y轴方向的缩放大小，value表示缩放到多少。1表示正常规格。小于1代表缩小，大于1代表放大。
     scaleX(float value)	             设置X轴方向的缩放大小，value表示缩放到多少。1表示正常规格。小于1代表缩小，大于1代表放大。
     translationY(float value)	          设置Y轴方向的移动值，作为增量来控制View对象相对于它父容器的左上角坐标偏移的位置，即移动到哪里。
     translationX(float value)	          设置X轴方向的移动值，作为增量来控制View对象相对于它父容器的左上角坐标偏移的位置。
     rotation(float value)	             控制View对象围绕支点进行旋转， rotation针对2D旋转
     rotationX (float value)	         控制View对象围绕X支点进行旋转， rotationX针对3D旋转
     rotationY(float value)	            控制View对象围绕Y支点进行旋转， rotationY针对3D旋转
     x(float value)	                   控制View对象相对于它父容器的左上角坐标在X轴方向的最终位置。
     y(float value)	                   控制View对象相对于它父容器的左上角坐标在Y轴方向的最终位置
     void cancel()	                   取消当前正在执行的动画
     setListener(Animator.AnimatorListener listener)	设置监听器，监听动画的开始，结束，取消，重复播放
     setUpdateListener(ValueAnimator.AnimatorUpdateListener listener)	设置监听器，监听动画的每一帧的播放
     setInterpolator(TimeInterpolator interpolator)	  设置插值器
     setStartDelay(long startDelay)	   设置动画延长开始的时间
     setDuration(long duration)	       设置动画执行的时间
     withLayer()	                    设置是否开启硬件加速
     withStartAction(Runnable runnable)	设置用于动画监听开始（Animator.AnimatorListener）时运行的Runnable任务对象
     withEndAction(Runnable runnable)	设置用于动画监听结束（Animator.AnimatorListener）时运行的Runnable任务对象
     *
     *
     *
     * 10、LayoutTransition 动画
     * setLayoutTransition(LayoutTransition lt) 方法把这些动画以一个 LayoutTransition 对象设置给一个 ViewGroup。
     * mTransitioner = new LayoutTransition();
     * mViewGroup.setLayoutTransition(mTransitioner);
     *
     *如果在xml中文件已经写好 LayoutAnimation，可以使用 AnimationUtils 直接加载：
     * AnimationUtils.loadLayoutAnimation(context, id)
     *
     * //通过加载XML动画设置文件来创建一个Animation对象；
     Animation animation=AnimationUtils.loadAnimation(this, R.anim.slide_right);
     //得到一个LayoutAnimationController对象；
     LayoutAnimationController controller = new LayoutAnimationController(animation);
     //设置控件显示的顺序；
     controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
     //设置控件显示间隔时间；
     controller.setDelay(0.3);
     //为ListView设置LayoutAnimationController属性；
     listView.setLayoutAnimation(controller);
     listView.startLayoutAnimation();

     *稍微再高端一点
     * mTransitioner = new LayoutTransition();
     ......
     ObjectAnimator anim = ObjectAnimator.ofFloat(this, "scaleX", 0, 1);
     ......//设置更多动画
     mTransition.setAnimator(LayoutTransition.APPEARING, anim);
     ......//设置更多类型的动画
     mViewGroup.setLayoutTransition(mTransitioner);
     *
     */

}
