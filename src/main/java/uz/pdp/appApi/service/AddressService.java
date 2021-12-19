package uz.pdp.appApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.response.AddressRepository;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    /**
     * ADDRESS QO'SHISH
     * @param addressDto Address
     * @return ApiResponse
     */
    public ApiResponse addAddress(Address addressDto){
            if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(),addressDto.getHomeNumber())){
                return new ApiResponse("Bunday address mavjud",false);
            }
            Address address = new Address();
            address.setHomeNumber(addressDto.getHomeNumber());
            address.setStreet(addressDto.getStreet());
            addressRepository.save(address);
            return new ApiResponse("Address saqlandi.",true);
        }

    /**
     * ADDRESSNI TAHRIRLASH
     * @param addressDto Address
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse editAddress(Address addressDto,Integer id){

            if (!addressRepository.existsById(id)){
                return new ApiResponse("Bunday address topilmadi.",false);
            }

            if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(),addressDto.getHomeNumber())){
                return new ApiResponse("Bunday address mavjud",false);
            }

            Optional<Address> optionalAddress = addressRepository.findById(id);

            Address address = optionalAddress.get();
            address.setHomeNumber(addressDto.getHomeNumber());
            address.setStreet(addressDto.getStreet());
            addressRepository.save(address);
            return new ApiResponse("Address tahrirlandi.",true);

        }

    /**
     * ADDRESS PAGE NI QAYTARISH
     * @param page int
     * @return Page
     */
    public Page<Address> getAddressPage(int page){
            Pageable pageable = PageRequest.of(page,10);
            return addressRepository.findAll(pageable);
        }

    /**
     * Address QAYTARISH
     * @param id Integer
     * @return Address
     */
    public ApiResponse getAddressById(Integer id){
            if (!addressRepository.existsById(id)){
                return new ApiResponse("Bunday address topilmadi.",false);
            }
            Optional<Address> optionalAddress = addressRepository.findById(id);
            return new ApiResponse(optionalAddress.get(),true);
        }

    /**
     * ADDRESSNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteAddress(Integer id){
            try {
                addressRepository.deleteById(id);
                return new ApiResponse("Address o'chirildi",true);
            }catch (Exception e){
                return new ApiResponse("Xatolik!!!",false);
            }
        }


}
