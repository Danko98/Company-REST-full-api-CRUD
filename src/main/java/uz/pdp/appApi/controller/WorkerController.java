package uz.pdp.appApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appApi.dto.WorkerDto;
import uz.pdp.appApi.dto.template.ApiResponse;
import uz.pdp.appApi.entity.Worker;
import uz.pdp.appApi.service.WorkerService;


@RestController
@RequestMapping(value = "/api/worker")
public class WorkerController {


    @Autowired
    WorkerService workerService;


    /**
     * HODIM QO"SHISH
     * @param workerDto WorkerDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 404).body(apiResponse);
    }


    /**
     * HODIM TAHRIRLASH
     * @param id Integer
     * @param workerDto WorkerDto
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * HODIMLAR RO"YXATINI QAYTARISH
     * @param page int
     * @return Page
     */
    @GetMapping
    public ResponseEntity<Page<Worker>> getWorkerPage(@RequestParam int page){
        Page<Worker> workerPage = workerService.getWorkerPage(page);
        return ResponseEntity.ok(workerPage);
    }


    /**
     * HODIM QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getWorkerById(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.getWorkerById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * HODIMNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}











