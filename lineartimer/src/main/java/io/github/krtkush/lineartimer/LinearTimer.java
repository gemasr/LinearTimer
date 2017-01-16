package io.github.krtkush.lineartimer;

import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by kartikeykushwaha on 18/12/16.
 */

public class LinearTimer {

    public static final int CLOCK_WISE_PROGRESSION = 0;
    public static final int COUNTER_CLOCK_WISE_PROGRESSION = 1;

    private LinearTimerView linearTimerView;
    private ArcProgressAnimation arcProgressAnimation;

    public LinearTimer(LinearTimerView linearTimerView, int progressDirection) {

        this.linearTimerView = linearTimerView;

        // If the user wants to show the progress in counter clock wise manner,
        // we flip the view on its Y-Axis and let it function as is.
        if(progressDirection == COUNTER_CLOCK_WISE_PROGRESSION) {

            ObjectAnimator objectAnimator = ObjectAnimator
                    .ofFloat(linearTimerView, "rotationY", 0.0f, 180f);
            objectAnimator.setDuration(0);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();
        }
    }

    /**
     * @param endingAngle Value between 0-360; The point up-till which the user wants the progression.
     * @param duration Value in milliseconds; Progress animation time period.
     */
    public void startTimer(int endingAngle, long duration) {

        if(arcProgressAnimation == null) {

            arcProgressAnimation = new ArcProgressAnimation(linearTimerView, endingAngle);
            arcProgressAnimation.setDuration(duration);
            linearTimerView.startAnimation(arcProgressAnimation);
        }
    }

    /**
     * Method to reset the timer to start angle and then start the progress again.
     */
    public void restartTimer() {
        if(arcProgressAnimation != null) {
            arcProgressAnimation.cancel();
            linearTimerView.startAnimation(arcProgressAnimation);
        }
    }

    /**
     * Method to reset the timer to start angle.
     */
    public void resetTimer() {
        if(arcProgressAnimation != null) {
            arcProgressAnimation.cancel();
            arcProgressAnimation = null;
        } else {
            linearTimerView.invalidate();
        }
    }
}
