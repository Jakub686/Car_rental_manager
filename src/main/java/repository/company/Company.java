package repository.company;

import lombok.Data;

@Data
public class Company {
    private int id;
    private String name;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
