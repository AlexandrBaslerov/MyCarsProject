
package alex.com.mycolectioncars.api.modules.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import alex.com.mycolectioncars.R;
import alex.com.mycolectioncars.api.base.BaseActivity;
import alex.com.mycolectioncars.api.di.components.DaggerCarsComponent;
import alex.com.mycolectioncars.api.di.module.CarModule;
import alex.com.mycolectioncars.api.modules.details.DetailActivity;
import alex.com.mycolectioncars.api.modules.home.adapter.CarAdapter;
import alex.com.mycolectioncars.api.mvp.model.Car;
import alex.com.mycolectioncars.api.mvp.presenter.CarPresenter;
import alex.com.mycolectioncars.api.mvp.view.MainView;
import alex.com.mycolectioncars.api.utilities.NetworkUtils;
import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.car_list)
    protected RecyclerView mCarList;
    @Inject
    protected CarPresenter mPresenter;
    private CarAdapter carAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadCars();
    }

    private void loadCars() {
        if (NetworkUtils.isNetAvailable(this)) {
            mPresenter.getCars();
        } else {
            mPresenter.getCarsFromDatabase();
        }
    }

    private void initializeList() {
        mCarList.setHasFixedSize(true);
        mCarList.setLayoutManager(new GridLayoutManager(this, 2));
        carAdapter = new CarAdapter(getLayoutInflater());
        carAdapter.setCarClickListener(mCarsClickListener);
        mCarList.setAdapter(carAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reload:
                loadCars();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerCarsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .carModule(new CarModule(this))
                .build().inject(this);
    }

    @Override
    public void onCarsLoaded(List<Car> cars) {
        carAdapter.addCars(cars);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        carAdapter.clearCars();
    }

    private CarAdapter.OnCarClickListener mCarsClickListener = (v, car, position) -> {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.CAR, car);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "carImageAnimation");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    };
}
