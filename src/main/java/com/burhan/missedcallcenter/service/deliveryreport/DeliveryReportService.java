package com.burhan.missedcallcenter.service.deliveryreport;

import com.burhan.missedcallcenter.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface DeliveryReportService {
    
    ResponseEntity<String> deliverReport(UserDto userDto);
}
