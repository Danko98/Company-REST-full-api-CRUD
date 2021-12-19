package uz.pdp.appApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appApi.dto.DepartmentDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Department;
import uz.pdp.appApi.service.DepartmentService;

@RestController
@RequestMapping(value = "/api/department")
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;


    /**
     * BO"LIM QO"SHISH
     * @param departmentDto DepartmentDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    /**
     * BO"LIM QO"SHISH
     * @param departmentDto DepartmentDto
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@RequestBody DepartmentDto departmentDto,@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * BO"LIMLARNI PAGE QILIB QAYTARISH
     * @param page Integer
     * @return Page
     */
    @GetMapping
    public ResponseEntity<Page<Department>> getDepartmentPage(@RequestParam int page){
        Page<Department> departmentPage = departmentService.getDepartmentPage(page);
        return ResponseEntity.ok(departmentPage);
    }


    /**
     * BO'LIM QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getDepartmentById(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.getDepartmentById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * BO"LIMNI O"CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


}
