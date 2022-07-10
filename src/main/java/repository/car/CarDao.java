package repository.car;

public interface CarDao {
    void createTable();

    void getCarList(int companyId);

    void addCar(String car, int companyId);

    String getCarName(int carId);
}
