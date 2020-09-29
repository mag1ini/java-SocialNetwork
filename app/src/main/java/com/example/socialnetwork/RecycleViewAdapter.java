package com.example.socialnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.socialnetwork.Models.Result;

import java.util.List;

class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<Result> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public Context mContext;
    // data is passed into the constructor
    RecycleViewAdapter(Context context, List<Result> data) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_element, parent, false);
        return new ViewHolder(view);
    }
    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String picture = mData.get(position).getPicture().getMedium();
        Glide.with(mContext)
                .load(picture)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                .into(holder.picture);


    }
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView gender;
        TextView email;
        TextView phone;
        ImageView picture;
        ViewHolder(View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.user_photo);
            itemView.setOnClickListener(this);



        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    Result getItem(int id) {
        return mData.get(id);
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
