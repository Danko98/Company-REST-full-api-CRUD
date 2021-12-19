package uz.pdp.appApi.response;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appApi.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByStreetAndHomeNumber(String street, Integer homeNumber);
}
