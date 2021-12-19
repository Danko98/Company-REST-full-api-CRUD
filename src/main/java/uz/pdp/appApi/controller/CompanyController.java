package uz.pdp.appApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appApi.dto.CompanyDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Company;
import uz.pdp.appApi.service.CompanyService;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    /**
     * KAMPANIYANI YARATISH
     * @param companyDto CompanyDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 201 : 409).body(apiResponse);
    }


    /** KAMPANIYANI TAHRIRLASH
     *
     * @param id Integer
     * @param companyDto CompanyDto
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editCompany(@RequestBody CompanyDto companyDto, @PathVariable Integer id){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 202 : 404).body(apiResponse);
    }


    /**
     * KAMPANIYANI QAYTARISH ID SI ORQALI
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getCompanyById(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.getCompanyById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * KAMPANIYALAR RO"YXATINI BERISH
     * @param page int
     * @return Page
     */
    @GetMapping
    public ResponseEntity<Page<Company>> getCompanyPage(@RequestParam int page){
        Page<Company> companyPage = companyService.getCompanyPage(page);
        return ResponseEntity.ok(companyPage);
    }


    /**
     * KAMPANIYANI O"CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }








}














