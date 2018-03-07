
package alex.com.mycolectioncars.api.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alex.com.mycolectioncars.api.mvp.model.Car;
import alex.com.mycolectioncars.api.mvp.model.CarsResponse;
import alex.com.mycolectioncars.api.mvp.model.CarsResponseCars;
import alex.com.mycolectioncars.api.mvp.model.Storage;

public class CarMapper {

    @Inject
    public CarMapper() {
    }

    public List<Car> mapCars(Storage storage, CarsResponse response) {
        List<Car> carList = new ArrayList<>();

        if (response != null) {
            List<Car> responseCars = response.getCars();
            if (responseCars != null) {
                for (Car car : responseCars) {
                    Car myCar = new Car();
                    myCar.setId(car.getId());
                    myCar.setTitle(car.getTitle());
                    myCar.setDetailDescription(car.getDetailDescription());
                    myCar.setPreviewDescription(car.getPreviewDescription());
                    myCar.setImage(car.getImage());
                    storage.addCar(myCar);
                    carList.add(myCar);
                }
            }
        }
        return carList;
    }
}
