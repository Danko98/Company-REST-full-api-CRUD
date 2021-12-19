package uz.pdp.appApi.dto;

import lombok.Data;

@Data
public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer addressId;
    private Integer departmentId;

}
