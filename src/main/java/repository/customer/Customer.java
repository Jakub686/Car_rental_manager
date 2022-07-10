package repository.customer;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private int rentedCarId;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
