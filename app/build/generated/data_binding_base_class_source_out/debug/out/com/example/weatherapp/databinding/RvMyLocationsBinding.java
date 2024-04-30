// Generated by data binding compiler. Do not edit!
package com.example.weatherapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.weatherapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class RvMyLocationsBinding extends ViewDataBinding {
  @NonNull
  public final TextView itemLocation;

  @NonNull
  public final TextView itemTemperature;

  @NonNull
  public final LinearLayout location;

  protected RvMyLocationsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView itemLocation, TextView itemTemperature, LinearLayout location) {
    super(_bindingComponent, _root, _localFieldCount);
    this.itemLocation = itemLocation;
    this.itemTemperature = itemTemperature;
    this.location = location;
  }

  @NonNull
  public static RvMyLocationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.rv_my_locations, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static RvMyLocationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<RvMyLocationsBinding>inflateInternal(inflater, R.layout.rv_my_locations, root, attachToRoot, component);
  }

  @NonNull
  public static RvMyLocationsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.rv_my_locations, null, false, component)
   */
  @NonNull
  @Deprecated
  public static RvMyLocationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<RvMyLocationsBinding>inflateInternal(inflater, R.layout.rv_my_locations, null, false, component);
  }

  public static RvMyLocationsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static RvMyLocationsBinding bind(@NonNull View view, @Nullable Object component) {
    return (RvMyLocationsBinding)bind(component, view, R.layout.rv_my_locations);
  }
}
