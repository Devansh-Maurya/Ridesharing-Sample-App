package com.mindorks.ridesharing.utils

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

/**
 * Created by Devansh on 4/5/20
 */

object AnimationUtils {

    fun polyLineAnimator(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofInt(0, 100)
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 2000
        return valueAnimator
    }

    fun cabAnimator(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = 3000
        valueAnimator.interpolator = LinearInterpolator()
        return valueAnimator
    }

}