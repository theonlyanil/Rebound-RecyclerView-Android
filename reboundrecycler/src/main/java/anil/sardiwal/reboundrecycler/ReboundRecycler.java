package anil.sardiwal.reboundrecycler;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ReboundRecycler
{
    @SuppressLint("StaticFieldLeak")
    private static RecyclerViewAnimator recyclerViewAnimator;

    private ReboundRecycler() {
        /*Private constructor will prevent instantiation of this class directly*/
    }

    public static synchronized ReboundRecycler init(RecyclerView recyclerView)
    {

        recyclerViewAnimator = new RecyclerViewAnimator(recyclerView);
        return new ReboundRecycler();
    }

    public ReboundRecycler delay(int delay)
    {
        RecyclerViewAnimator.setInitDelay(delay);
        return this;
    }

    public ReboundRecycler initTension(int initTension)
    {
        RecyclerViewAnimator.setInitTension(initTension);
        return this;
    }

    public ReboundRecycler initFriction(int initFriction)
    {
        RecyclerViewAnimator.setInitFriction(initFriction);
        return this;
    }

    public ReboundRecycler scrollTension(int scrollTension)
    {
        RecyclerViewAnimator.setScrollTension(scrollTension);
        return this;
    }

    public ReboundRecycler scrollFriction(int scrollFriction)
    {
        RecyclerViewAnimator.setScrollFriction(scrollFriction);
        return this;
    }


    /**
     * Whether animation should happen only once per row - while scrolling
     * default false
     *
     * @return instance of {@link ReboundRecycler}
     */
    public ReboundRecycler animateOnlyOnce() {
        RecyclerViewAnimator.setAnimateOnlyOnce();
        return this;
    }

    public static void first(ViewGroup layout) {
        recyclerViewAnimator.onCreateViewHolder(layout);
    }

    public static void bind(@NonNull View view, int position)
    {
        recyclerViewAnimator.onBindViewHolder(view, position);
    }
}
