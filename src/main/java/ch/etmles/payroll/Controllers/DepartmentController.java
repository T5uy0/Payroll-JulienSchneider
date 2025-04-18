package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Department;
import ch.etmles.payroll.Repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentRepository department;

    DepartmentController(DepartmentRepository department){
        this.department = department;
    }

    /* curl sample :
    curl -i localhost:8080/departments
    */
    @GetMapping("/departments")
    List<Department> all(){
        return department.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/departments ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"RH\"}"
    */
    @PostMapping("/departments")
    Department newDepartment(@RequestBody Department newDepartment){
        return department.save(newDepartment);
    }

    /* curl sample :
    curl -i localhost:8080/departments/RH
    */
    @GetMapping("/departments/{name}")
    Department one(@PathVariable String name){
        return department.findById(name)
                .orElseThrow(() -> new DepartmentNotFoundException(name));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/departments/RH ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise\"}"
     */
    @PutMapping("/departments/{name}")
    Department replaceDepartment(@RequestBody Department newDepartment, @PathVariable String name) {
        return department.findById(name)
                .map(employee -> {
                    department.setName(newDepartment.getName());
                    return department.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setName(name);
                    return department.save(newDepartment);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/departments/Samwise
    */
    @DeleteMapping("/departments/{name}")
    void deleteDepartment(@PathVariable String name){
        department.deleteByName(name);
    }
}
