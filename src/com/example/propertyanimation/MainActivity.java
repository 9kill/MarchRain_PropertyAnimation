package com.example.propertyanimation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

    private ScrollView upmenu;
    private FrameLayout.LayoutParams upLayoutParams;
    private int sh;
    private Button down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = getResources().getDisplayMetrics().heightPixels;
        //add line
        //add line2
    }

    /**
     * 
     * @author sunzhenyu
     * @date 2014年11月20日下午6:03:20 功能：旋转
     */
    public void rotate(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F).setDuration(500);
        anim.start();

    }

    /**
     * 
     * @author sunzhenyu
     * @date 2014年11月20日下午6:03:20 功能：缩放
     */
    public void scale(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationX", 1.0F, 0.0F).setDuration(500);
        anim.start();
        anim.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /**
     * 是否要滑动到底部
     */
    private boolean isSlidingToDown = true;

    /**
     * 
     * @author sunzhenyu
     * @date 2014年11月20日下午6:03:20 功能：垂直平移
     */
    public void translate(final View view) {
        int vh = view.getHeight();
        if (isSlidingToDown) {
            ValueAnimator animator = ValueAnimator.ofFloat(0, 250);
            animator.setTarget(view);// 设置移动的控件
            animator.setDuration(500);
            animator.addUpdateListener(new AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float cVal = (Float) animation.getAnimatedValue();
                    Log.v("log", "cVal=" + cVal);
                    view.setY(cVal);
                }
            });
            animator.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                    Log.v("log", "DOWNonAnimationStart");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.v("log", "DOWNonAnimationEnd");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
            animator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofFloat(250, 0);
            animator.setTarget(view);
            animator.setDuration(500);
            animator.addUpdateListener(new AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float cVal = (Float) animation.getAnimatedValue();
                    Log.v("log", "cVal=" + cVal);
                    view.setY(cVal);
                }
            });
            animator.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {
                    Log.v("log", "UPonAnimationStart");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.v("log", "UPonAnimationEnd");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });
            animator.start();
        }
        isSlidingToDown = !isSlidingToDown;
    }

}
