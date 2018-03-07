
package alex.com.mycolectioncars.api.modules.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import alex.com.mycolectioncars.R;
import alex.com.mycolectioncars.api.helper.ImageHandler;
import alex.com.mycolectioncars.api.mvp.model.Car;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Car> mCarList = new ArrayList<>();

    public CarAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mCarList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public void addCars(List<Car> car) {
        mCarList.addAll(car);
        notifyDataSetChanged();
    }

    public void clearCars() {
        mCarList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.car_icon)
        protected ImageView mCarIcon;
        @Bind(R.id.textview_title)
        protected TextView mCarTitle;
        @Bind(R.id.textview_preview_description)
        protected TextView mCarPreviewDescription;

        private Context mContext;
        private Car mCar;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Car car) {
            mCar = car;
            mCarTitle.setText(car.getTitle());
            mCarPreviewDescription.setText(car.getPreviewDescription());

            Glide.with(mContext).load(car.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(new ImageHandler(mCarIcon));
        }

        @Override
        public void onClick(View v) {
            if (mCarClickListener != null) {
                mCarClickListener.onClick(mCarIcon, mCar, getAdapterPosition());
            }
        }
    }

    public void setCarClickListener(OnCarClickListener listener) {
        mCarClickListener = listener;
    }

    private OnCarClickListener mCarClickListener;

    public interface OnCarClickListener {

        void onClick(View v, Car car, int position);
    }
}
