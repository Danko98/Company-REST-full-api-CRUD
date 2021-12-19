package uz.pdp.appApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appApi.dto.DepartmentDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Company;
import uz.pdp.appApi.entity.Department;
import uz.pdp.appApi.response.CompanyRepository;
import uz.pdp.appApi.response.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    /**
     * BO"LIM QO"SHISH
     * @param departmentDto DepartmentDto
     * @return ApiResponse
     */
    public ApiResponse addDepartment(DepartmentDto departmentDto){
        if (departmentRepository.existsByNameAndCompanyId(departmentDto.getName(),departmentDto.getCompanyId())){
            return new ApiResponse("Bunday bo'lim mavjud.",false);
        }

        if (!companyRepository.existsById(departmentDto.getCompanyId())){
            return new ApiResponse("Bunday kampaniya mavjud.",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return new ApiResponse("Bo'lim saqlandi",true);
    }


    /**
     * BO'LIMNI TAHRIRLASH
     * @param id Integer
     * @param departmentDto DepartmentDto
     * @return ApiResponse
     */
    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto){
        if (!departmentRepository.existsById(id)){
            return new ApiResponse("Bunday bo'lim topilmadi.",false);
        }

        if (departmentRepository.existsByNameAndCompanyId(departmentDto.getName(),departmentDto.getCompanyId())){
            return new ApiResponse("Bunday bo'lim mavjud.",false);
        }

        if (!companyRepository.existsById(departmentDto.getCompanyId())){
            return new ApiResponse("Bunday kampaniya topilmadi.",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);

        return new ApiResponse("Bo'lim tahrirlandi",true);
    }


    /**
     * BO'LIM QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getDepartmentById(Integer id){
        if (!departmentRepository.existsById(id)){
            return new ApiResponse("Bunday bo'lim topilmadi.",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return new ApiResponse(optionalDepartment.get(),true);
    }


    /**
     * BO"LIMLARNI PAGE QILIB QAYTARISH
     * @param page Integer
     * @return Page
     */
    public Page<Department> getDepartmentPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        return departmentPage;
    }


    /**
     * BO"LIMNI O"CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteDepartment(Integer id){
        if (!departmentRepository.existsById(id)){
            return new ApiResponse("Bunday bo'lim topilmadi.",false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Bo'lim o'chirildi.",true);
    }


}

















