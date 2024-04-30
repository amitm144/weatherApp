package com.example.weatherapp.databinding;
import com.example.weatherapp.R;
import com.example.weatherapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCurrentLocationSelectionBindingImpl extends ActivityCurrentLocationSelectionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.temp_layout, 1);
        sViewsWithIds.put(R.id.location, 2);
        sViewsWithIds.put(R.id.temperature, 3);
        sViewsWithIds.put(R.id.todayImg, 4);
        sViewsWithIds.put(R.id.progressBar1, 5);
        sViewsWithIds.put(R.id.todayDesc, 6);
        sViewsWithIds.put(R.id.todayForecastRv, 7);
        sViewsWithIds.put(R.id.progressBar2, 8);
        sViewsWithIds.put(R.id.toggleButton, 9);
        sViewsWithIds.put(R.id.tempBtn, 10);
        sViewsWithIds.put(R.id.windBtn, 11);
        sViewsWithIds.put(R.id.humidityBtn, 12);
        sViewsWithIds.put(R.id.attributesForecastRv, 13);
        sViewsWithIds.put(R.id.progressBar3, 14);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCurrentLocationSelectionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityCurrentLocationSelectionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.recyclerview.widget.RecyclerView) bindings[13]
            , (android.widget.Button) bindings[12]
            , (android.widget.TextView) bindings[2]
            , (android.widget.ProgressBar) bindings[5]
            , (android.widget.ProgressBar) bindings[8]
            , (android.widget.ProgressBar) bindings[14]
            , (android.widget.Button) bindings[10]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (androidx.recyclerview.widget.RecyclerView) bindings[7]
            , (android.widget.ImageView) bindings[4]
            , (com.google.android.material.button.MaterialButtonToggleGroup) bindings[9]
            , (android.widget.Button) bindings[11]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}