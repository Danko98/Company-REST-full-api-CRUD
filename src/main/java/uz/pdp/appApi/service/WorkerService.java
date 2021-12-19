package uz.pdp.appApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appApi.dto.WorkerDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Address;
import uz.pdp.appApi.entity.Department;
import uz.pdp.appApi.entity.Worker;
import uz.pdp.appApi.response.AddressRepository;
import uz.pdp.appApi.response.DepartmentRepository;
import uz.pdp.appApi.response.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService {


    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    /**
     * HODIM QO"SHISH
     * @param workerDto WorkerDto
     * @return ApiResponse
     */
    public ApiResponse addWorker(WorkerDto workerDto){
        if (workerRepository.existsByNameAndPhoneNumber(workerDto.getName(),workerDto.getPhoneNumber())){
            return new ApiResponse("Bunday hodim mavjud.",false);
        }

        if (!addressRepository.existsById(workerDto.getAddressId())){
            return new ApiResponse("Bunday address topilmadi.",false);
        }

        if (!departmentRepository.existsById(workerDto.getDepartmentId())){
            return new ApiResponse("Bunday bo'lim topilmadi.",false);
        }

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Hodim saqlandi.",true);

    }


    /**
     * HODIM TAHRIRLASH
     * @param id Integer
     * @param workerDto WorkerDto
     * @return ApiResponse
     */
    public ApiResponse editWorker(Integer id, WorkerDto workerDto){

        if (!workerRepository.existsById(id)){
            return new ApiResponse("Bunday hodim topilmadi.",false);
        }

        if (workerRepository.existsByNameAndPhoneNumber(workerDto.getName(),workerDto.getPhoneNumber())){
            return new ApiResponse("Bunday hodim mavjud.",false);
        }

        if (!addressRepository.existsById(workerDto.getAddressId())){
            return new ApiResponse("Bunday address topilmadi.",false);
        }

        if (!departmentRepository.existsById(workerDto.getDepartmentId())){
            return new ApiResponse("Bunday bo'lim topilmadi.",false);
        }

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Hodim tahrirlandi.",true);

    }


    /**
     * HODIMLAR RO"YXATINI QAYTARISH
     * @param page int
     * @return Page
     */
    public Page<Worker> getWorkerPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Worker> workerPage = workerRepository.findAll(pageable);
        return workerPage;
    }


    /**
     * HODIM QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getWorkerById(Integer id){
        if (!workerRepository.existsById(id)){
            return new ApiResponse("Bunday hodim topilmadi.",false);
        }
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return new ApiResponse(optionalWorker.get(),true);
    }


    /**
     * HODIMNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteWorker(Integer id){
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Hodim o''chirildi",true);
        }catch (Exception e){
            return new ApiResponse("Xatolik",false);
        }
    }



}












