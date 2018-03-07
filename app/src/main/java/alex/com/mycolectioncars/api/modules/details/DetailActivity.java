
package alex.com.mycolectioncars.api.modules.details;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import alex.com.mycolectioncars.R;
import alex.com.mycolectioncars.api.base.BaseActivity;
import alex.com.mycolectioncars.api.helper.ImageHandler;
import alex.com.mycolectioncars.api.mvp.model.Car;
import butterknife.Bind;

public class DetailActivity extends BaseActivity {

    public static final String CAR = "car";

    @Bind(R.id.carImage)
    protected ImageView mCarImage;
    @Bind(R.id.carTitle)
    protected TextView mCarsTitle;
    @Bind(R.id.carDescription)
    protected TextView mCarsDescription;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCarImage.setTransitionName("carImageAnimation");
        }

        showBackArrow();

        Car car = (Car) intent.getSerializableExtra(CAR);
        setTitle("Car Detail");

        mCarsTitle.setText(car.getTitle());
        mCarsDescription.setText(car.getDetailDescription());

        Glide.with(this).load(car.getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(mCarImage));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
