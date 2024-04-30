package com.example.weatherapp;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.weatherapp.databinding.ActivityCurrentLocationSelectionBindingImpl;
import com.example.weatherapp.databinding.ActivityLocationsListBindingImpl;
import com.example.weatherapp.databinding.RvForecastBindingImpl;
import com.example.weatherapp.databinding.RvLocationSearchBindingImpl;
import com.example.weatherapp.databinding.RvMyLocationsBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCURRENTLOCATIONSELECTION = 1;

  private static final int LAYOUT_ACTIVITYLOCATIONSLIST = 2;

  private static final int LAYOUT_RVFORECAST = 3;

  private static final int LAYOUT_RVLOCATIONSEARCH = 4;

  private static final int LAYOUT_RVMYLOCATIONS = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.weatherapp.R.layout.activity_current_location_selection, LAYOUT_ACTIVITYCURRENTLOCATIONSELECTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.weatherapp.R.layout.activity_locations_list, LAYOUT_ACTIVITYLOCATIONSLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.weatherapp.R.layout.rv_forecast, LAYOUT_RVFORECAST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.weatherapp.R.layout.rv_location_search, LAYOUT_RVLOCATIONSEARCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.weatherapp.R.layout.rv_my_locations, LAYOUT_RVMYLOCATIONS);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCURRENTLOCATIONSELECTION: {
          if ("layout/activity_current_location_selection_0".equals(tag)) {
            return new ActivityCurrentLocationSelectionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_current_location_selection is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOCATIONSLIST: {
          if ("layout/activity_locations_list_0".equals(tag)) {
            return new ActivityLocationsListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_locations_list is invalid. Received: " + tag);
        }
        case  LAYOUT_RVFORECAST: {
          if ("layout/rv_forecast_0".equals(tag)) {
            return new RvForecastBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_forecast is invalid. Received: " + tag);
        }
        case  LAYOUT_RVLOCATIONSEARCH: {
          if ("layout/rv_location_search_0".equals(tag)) {
            return new RvLocationSearchBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_location_search is invalid. Received: " + tag);
        }
        case  LAYOUT_RVMYLOCATIONS: {
          if ("layout/rv_my_locations_0".equals(tag)) {
            return new RvMyLocationsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_my_locations is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_current_location_selection_0", com.example.weatherapp.R.layout.activity_current_location_selection);
      sKeys.put("layout/activity_locations_list_0", com.example.weatherapp.R.layout.activity_locations_list);
      sKeys.put("layout/rv_forecast_0", com.example.weatherapp.R.layout.rv_forecast);
      sKeys.put("layout/rv_location_search_0", com.example.weatherapp.R.layout.rv_location_search);
      sKeys.put("layout/rv_my_locations_0", com.example.weatherapp.R.layout.rv_my_locations);
    }
  }
}
