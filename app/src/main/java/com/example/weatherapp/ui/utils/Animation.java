package com.example.weatherapp.ui.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Animation {

    public Animation() {
    }

    public void rotateFabIcon(FloatingActionButton fab, boolean fabImage) {
        float fromDegrees = fabImage ? 0 : 180;
        ObjectAnimator animator = ObjectAnimator.ofFloat(fab, "rotation", fromDegrees, fromDegrees + 180);
        animator.setDuration(700);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    public void showButtons(View... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            View button = buttons[i];
            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(button, "translationY", 0f, -10f);
            translationYAnimator.setDuration(200);
            translationYAnimator.setInterpolator(new DecelerateInterpolator());

            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f);
            alphaAnimator.setDuration(200);
            alphaAnimator.setInterpolator(new DecelerateInterpolator());

            animatorSet.playTogether(translationYAnimator, alphaAnimator);
            animatorSet.setStartDelay(i * 100L);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    button.setVisibility(View.VISIBLE);
                }
            });

            animatorSet.start();
        }
    }

    public void hideButtons(View... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            View button = buttons[i];
            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(button, "translationY", -10f, 0f);
            translationYAnimator.setDuration(200);
            translationYAnimator.setInterpolator(new DecelerateInterpolator());

            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f);
            alphaAnimator.setDuration(200);
            alphaAnimator.setInterpolator(new DecelerateInterpolator());

            animatorSet.playTogether(translationYAnimator, alphaAnimator);
            animatorSet.setStartDelay((buttons.length - i - 1) * 100L);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    button.setVisibility(View.INVISIBLE);
                }
            });

            animatorSet.start();
        }
    }
}
