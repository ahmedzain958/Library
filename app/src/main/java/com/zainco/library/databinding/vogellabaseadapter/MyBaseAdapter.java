package com.zainco.library.databinding.vogellabaseadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.zainco.library.databinding.RowlayoutBinding;


public abstract class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowlayoutBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getDataAtPosition(position));
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
                        /*Most of code in adapter can be the same if data binding is used. The only requirement is that the object name in the layout file is the same,
                        so that the generated entry in the BR class is the same. In our example we use obj for it.
To reuse most of our adapter, create an abstract class with the logic to bind to any object.*/
            binding.setVariable(BR.obj, obj);
            /**
             * Evaluates the pending bindings, updating any Views that have expressions bound to
             * modified variables. This <b>must</b> be run on the UI thread.
             *
             * Doing some changes on your binding does not mean that it will have an immediate effect on your View.
             * Changing things in binding means that you're really scheduling those changes to be applied in the nearest future. This is for many reasons,
             * performance being one of them.
             * So when NOT using executePendingBindings, setting a binding variable sets immediately its value BUT the view it is scheduled to updated in the near future right?
             * No, because the "scheduling" is not a scheduling in the traditional sense of the word. It's more like "marking the binding that something has changed",
             * rather than "applying a change in future". So when you call executePendingBindings(), the "marking" from the old code will be overwritten by the new change, and then it will be cleared by the executePendingBindings call. So after that call, there will be no "marking" because it has already been handled (by executeBindings() which is a consequence of executePendingBindings()).
             *
             *
             */
            binding.executePendingBindings();
        }
    }

}