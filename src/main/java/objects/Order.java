package objects;

import lombok.Data;

@Data
public class Order {
    private long id;
    private long petId;
    private long quantity;
    private String shipDate;
    private String status;
}