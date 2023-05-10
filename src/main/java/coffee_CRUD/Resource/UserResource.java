package coffee_CRUD.Resource;

import coffee_CRUD.Entity.Coffee;
import coffee_CRUD.Entity.CustomPair;
import coffee_CRUD.Entity.Topping;
import coffee_CRUD.Entity.User;
import coffee_CRUD.Entity.enums.UserRole;
import coffee_CRUD.Repository.CoffeeRepo;
import coffee_CRUD.Repository.ToppingRepo;
import coffee_CRUD.Repository.UserRepo;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Autowired
    private CoffeeRepo coffeeRepo;
    @Autowired
    private ToppingRepo toppingRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add")
    public void add(@RequestBody User user){
        userRepo.add(user);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userRepo.getAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id){
        return userRepo.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user, @PathVariable long id){
        user.setId(id);
        userRepo.update(user);
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable long id){
        userRepo.del(id);
    }

//    @PostMapping("/{userId}/coffee/{coffeeId}")
//    public void buyCoffee(@PathVariable long userId, @PathVariable long coffeeId){
//        userRepo.buyCoffee(userId, coffeeRepo.findById(coffeeId));
//    }

    @PostMapping("/{userId}/coffee/")
    public void buyCoffee(@PathVariable long userId, @RequestBody Coffee coffee){
        coffeeRepo.min(coffee);
        userRepo.buyCoffee(userId, coffee);
    }

//    @PostMapping("/{userId}/topping/{toppingId}")
//    public void buyTopping(@PathVariable long userId,@PathVariable long toppingId){
//        userRepo.buyTopping(userId, toppingRepo.findById(toppingId));
//    }

    @PostMapping("/{userId}/topping/")
    public void buyTopping(@PathVariable long userId,@RequestBody Topping topping){
        toppingRepo.min(topping);
        userRepo.buyTopping(userId, topping);
    }

    @GetMapping("/{id}/bill")
    public CustomPair bill(@PathVariable long id){
        User user = userRepo.findById(id);
        CustomPair customPair = new CustomPair(user.getCoffeeBill(), user.getToppingBill());
        return customPair;
    }

    @PostMapping("/{id}/upCoffee")
    public void upCoffee(@RequestBody Coffee coffee, @PathVariable long id){
        if(userRepo.findById(id).getUserRole().equals(UserRole.ADMIN)){
            coffeeRepo.update(coffee);
        }
    }

    @PostMapping("/{id}/upTopping")
    public void upTopping(@RequestBody Topping coffee, @PathVariable long id){
        if(userRepo.findById(id).getUserRole().equals(UserRole.ADMIN)){
            toppingRepo.update(coffee);
        }
    }

    @GetMapping("/test")
    public User tt(){
        User user = new User(1,"m", UserRole.CONSUMER, null, null);
        return user;
    }
}
