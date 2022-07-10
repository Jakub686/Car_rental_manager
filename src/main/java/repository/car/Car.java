package repository.car;

import lombok.Data;

@Data
public class Car {
    private int id;
    private String name;
    private int companyId;

    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
