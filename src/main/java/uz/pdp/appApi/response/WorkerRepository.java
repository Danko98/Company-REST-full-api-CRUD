package uz.pdp.appApi.response;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
}
