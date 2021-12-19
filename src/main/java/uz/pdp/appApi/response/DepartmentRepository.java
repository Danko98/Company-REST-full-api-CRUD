package uz.pdp.appApi.response;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appApi.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompanyId(String name, Integer company_id);

}
