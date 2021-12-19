package uz.pdp.appApi.response;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

        boolean existsByCorpNameAndDirectorNameAndAddressId(String corpName, String directorName, Integer address_id);

}
