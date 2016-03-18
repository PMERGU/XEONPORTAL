package za.co.xeon.service;

import za.co.xeon.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Employee.
 */
public interface EmployeeService {

    /**
     * Save a employee.
     * @return the persisted entity
     */
    public Employee save(Employee employee);

    /**
     *  get all the employees.
     *  @return the list of entities
     */
    public Page<Employee> findAll(Pageable pageable);

    /**
     *  get the "id" employee.
     *  @return the entity
     */
    public Employee findOne(Long id);

    /**
     *  delete the "id" employee.
     */
    public void delete(Long id);
}
