package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.service.deliveryreport.DeliveryReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DeliveryReportController {

    DeliveryReportService deliveryReportService;

    DeliveryReportController(DeliveryReportService deliveryReportService){
        this.deliveryReportService=deliveryReportService;
    }

    @PostMapping("/delivered")
    private ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto)
    {

        return deliveryReportService.deliverReport(userDto);


    }
}
