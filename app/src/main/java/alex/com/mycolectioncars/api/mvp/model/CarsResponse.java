package alex.com.mycolectioncars.api.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsResponse {

    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("cars")
    @Expose
    private List<Car> cars = null;
    @SerializedName("version")
    @Expose
    private int version;

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Car> getCars() {
        return cars;
    }


    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
