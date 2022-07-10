package repository.customer;

public interface CustomerDao {
    void createTable();

    void createCustomer(String name);

    void addCar(int id, int carId);

    void removeCar(int id);

    void getAllCustomers();

    Integer getCar(int id);

}
