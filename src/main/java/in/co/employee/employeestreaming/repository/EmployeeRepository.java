package in.co.employee.employeestreaming.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.employee.employeestreaming.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	Optional<Employee> findById(String id);

}
