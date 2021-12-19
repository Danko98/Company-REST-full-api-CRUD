package uz.pdp.appApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.service.AddressService;

@RestController
@RequestMapping(value = "/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;


    /**
     * ADDRESS QO'SHISH
     * @param addressDto Address
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addAddress(@RequestBody Address addressDto){
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


    /**
     * ADDRESSNI TAHRIRLASH
     * @param addressDto Address
     * @param id Integer
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editAddress(@RequestBody Address addressDto, @PathVariable Integer id){
        ApiResponse apiResponse = addressService.editAddress(addressDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * ADDRESS PAGE NI QAYTARISH
     * @param page int
     * @return Page
     */
    @GetMapping
    public ResponseEntity<Page<Address>> getAddressPage(@RequestParam int page){
        Page<Address> addressPage = addressService.getAddressPage(page);
        return ResponseEntity.ok(addressPage);
    }


    /**
     * Address QAYTARISH
     * @param id Integer
     * @return Address
     */
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getAddressById(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.getAddressById(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200 : 404).body(apiResponse);
    }


    /**
     * ADDRESSNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:409).body(apiResponse);
    }


}











