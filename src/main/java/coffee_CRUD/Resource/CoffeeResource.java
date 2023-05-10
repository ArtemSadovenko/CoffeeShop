package coffee_CRUD.Resource;


import coffee_CRUD.Entity.Coffee;
import coffee_CRUD.Repository.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeResource {
    @Autowired
    private CoffeeRepo coffeeRepo;

    @PostMapping("/add")
    public void add(@RequestBody Coffee coffee){
        coffeeRepo.save(coffee);
    }

    @GetMapping("/{id}")
    public Coffee findById(@PathVariable long id){
        return coffeeRepo.findById(id);
    }

    @GetMapping("/all")
    public List<Coffee> getAll(){
        return coffeeRepo.getAll();
    }

    @GetMapping("/test")
    public Coffee coffee(){
        Coffee coffee = new Coffee(1, "late", 12);
        return coffee;
    }
}
