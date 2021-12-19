package uz.pdp.appApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appApi.dto.CompanyDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.entity.Company;
import uz.pdp.appApi.response.AddressRepository;
import uz.pdp.appApi.response.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;


    /**
     * KAMPANIYANI YARATISH
     * @param companyDto CompanyDto
     * @return ApiResponse
     */
    public ApiResponse addCompany(CompanyDto companyDto){

        if (companyRepository.existsByCorpNameAndDirectorNameAndAddressId(companyDto.getCorpName(),companyDto.getDirectorName(),companyDto.getAddressId())){
            return new ApiResponse("Bunday companiya mavjud",false);
        }

        if (!addressRepository.existsById(companyDto.getAddressId())){
            return new ApiResponse("Address topilmadi.",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());

        Company company = new Company();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Kampaniya qo'shildi.",true);
    }


    /** KAMPANIYANI TAHRIRLASH
     *
     * @param id
     * @param companyDto
     * @return
     */
    public ApiResponse editCompany(Integer id,CompanyDto companyDto){
        if (!companyRepository.existsById(id)){
            return new ApiResponse("Bunday companiya topilmadi.",false);
        }
        if (!addressRepository.existsById(companyDto.getAddressId())){
            return new ApiResponse("Bunday address topilmadi.",false);
        }
        if (companyRepository.existsByCorpNameAndDirectorNameAndAddressId(companyDto.getCorpName(),companyDto.getDirectorName(),companyDto.getAddressId())){
            return new ApiResponse("Bunday companiya mavjud",false);
        }

        Optional<Company> optionalCompany = companyRepository.findById(id);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());

        Company company = optionalCompany.get();
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        companyRepository.save(company);
        return new ApiResponse("Companya tahrirlandi",true);


    }


    /**
     * KAMPANIYANI QAYTARISH ID SI ORQALI
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getCompanyById(Integer id){
        if (!companyRepository.existsById(id)){
            return new ApiResponse("Bunday companiya topilmadi.",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return new ApiResponse(optionalCompany.get(),true);
    }


    /**
     * KAMPANIYALAR RO"YXATINI BERISH
     * @param page int
     * @return Page
     */
    public Page<Company> getCompanyPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return companyRepository.findAll(pageable);
    }


    /**
     * KAMPANIYANI O"CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteCompany(Integer id){
        if (!companyRepository.existsById(id)){
            return new ApiResponse("Bunday companiya topilmadi.",false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Kampaniya o'chirildi.",true);
    }



}
