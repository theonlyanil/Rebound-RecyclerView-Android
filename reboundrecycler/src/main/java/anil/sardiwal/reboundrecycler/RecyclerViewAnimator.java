package anil.sardiwal.reboundrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
/*
    Thanks to Anthony. http://anthony-skr.com/article/recyclerview-items-animation-with-rebound-effect
    Github: https://github.com/anthony-skr

    Originally used in AskScience app (Scholar Q&A)
 */

public class RecyclerViewAnimator {
    /**
     * Initial delay before to show items - in ms
     */
    private static int INIT_DELAY = 200;

    /**
     * Initial entrance tension parameter.
     * See https://facebook.github.io/rebound/
     */
    private static int INIT_TENSION = 250;
    /**
     * Initial entrance friction parameter.
     */
    private static int INIT_FRICTION = 25;

    /**
     * Scroll entrance animation tension parameter.
     */
    private static int SCROLL_TENSION = 300;
    /**
     * Scroll entrance animation friction parameter.
     */
    private static int SCROLL_FRICTION = 25;

    /**
     * Parameter to prevent animation from happening on the same row for more than once
     */
    private static boolean ANIMATE_ONLY_ONCE = false;


    private int mHeight;
    private RecyclerView mRecyclerView;
    private SpringSystem mSpringSystem;

    private boolean mFirstViewInit = true;
    private int mLastPosition = -1;
    private int mStartDelay;

    public RecyclerViewAnimator(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mSpringSystem = SpringSystem.create();

        // Use height of RecyclerView to slide-in items from bottom.
        mHeight = mRecyclerView.getResources().getDisplayMetrics().heightPixels;

        mStartDelay = INIT_DELAY;
    }


    public void onCreateViewHolder(View item) {
        /**
         * mFirstViewInit is used because we only want to show animation once at initialization.
         * (onCreateViewHolder can be called after if you use multiple view types).
         */
        if (mFirstViewInit) {
            slideInBottom(item, mStartDelay, INIT_TENSION, INIT_FRICTION);
            mStartDelay += 70;
        }
    }

    public void onBindViewHolder(View item, int position) {
        /**
         * After init, animate once item by item when user scroll down.
         */
        if (!mFirstViewInit) {
            if (position > mLastPosition) {
                slideInBottom(item, 0, SCROLL_TENSION, SCROLL_FRICTION);
                mLastPosition = position;
            } else if (!ANIMATE_ONLY_ONCE) {
                // if user decides not to animate item more than once than we stop changing mLastPosition value
                // so that it can hold real last visible item position when someone scrolls up
                slideInTop(item, 0, SCROLL_TENSION, SCROLL_FRICTION);
                mLastPosition = position;
            }
        }
    }

    private void slideInBottom(final View item,
                               final int delay,
                               final int tension,
                               final int friction) {
        // Move item far outside the RecyclerView
        item.setTranslationY(mHeight);

        Runnable startAnimation = new Runnable() {
            @Override
            public void run() {
                SpringConfig config = new SpringConfig(tension, friction);
                Spring spring = mSpringSystem.createSpring();
                spring.setSpringConfig(config);
                spring.addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        /**
                         * Decrease translationY until 0.
                         */
                        float val = (float) (mHeight - spring.getCurrentValue());
                        item.setTranslationY(val);
                    }

                    @Override
                    public void onSpringEndStateChange(Spring spring) {
                        mFirstViewInit = false;
                    }
                });

                // Set the spring in motion; moving from 0 to height
                spring.setEndValue(mHeight);
            }
        };

        mRecyclerView.postDelayed(startAnimation, delay);
    }

    private void slideInTop(final View item,
                            final int delay,
                            final int tension,
                            final int friction) {
        // Move item far outside the RecyclerView
        item.setTranslationY(mHeight);

        Runnable startAnimation = new Runnable() {
            @Override
            public void run() {
                SpringConfig config = new SpringConfig(tension, friction);
                Spring spring = mSpringSystem.createSpring();
                spring.setSpringConfig(config);
                spring.addListener(new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        /**
                         * Decrease translationY until 0.
                         */
                        float val = (float) (spring.getCurrentValue() - mHeight);
                        item.setTranslationY(val);
                    }

                    @Override
                    public void onSpringEndStateChange(Spring spring) {
                        mFirstViewInit = false;
                    }
                });

                // Set the spring in motion; moving from 0 to height
                spring.setEndValue(mHeight);
            }
        };

        mRecyclerView.postDelayed(startAnimation, delay);
    }

    public static void setInitDelay(int delayTime)
    {
        INIT_DELAY = delayTime;
    }

    public static void setInitTension(int initTension)
    {
        INIT_TENSION = initTension;
    }

    public static void setInitFriction(int initFriction)
    {
        INIT_FRICTION = initFriction;
    }

    public static void setScrollTension(int scrollTension)
    {
        SCROLL_TENSION = scrollTension;
    }

    public static void setScrollFriction(int scrollFriction)
    {
        SCROLL_FRICTION = scrollFriction;
    }

    public static void setAnimateOnlyOnce() {
        ANIMATE_ONLY_ONCE = true;
    }

}
