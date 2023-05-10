package coffee_CRUD.Entity;

import coffee_CRUD.Entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private long id;
    private String name;
    private UserRole userRole;
    private List<Coffee> coffeeBill = new ArrayList<>();
    private List<Topping> toppingBill = new ArrayList<>();
}
