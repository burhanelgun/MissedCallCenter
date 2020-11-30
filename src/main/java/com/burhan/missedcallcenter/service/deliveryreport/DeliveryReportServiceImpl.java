package com.burhan.missedcallcenter.service.deliveryreport;

import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.mapper.CallMapper;
import com.burhan.missedcallcenter.repository.CallRepository;
import com.burhan.missedcallcenter.service.messagegenerator.MessageGeneratorService;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeliveryReportServiceImpl implements DeliveryReportService {

    private CallRepository callRepository;
    private CallMapper callMapper;
    private NotificationService notificationService;
    private MessageGeneratorService messageGeneratorService;


    DeliveryReportServiceImpl(CallRepository callRepository, CallMapper callMapper, NotificationService notificationService
            , MessageGeneratorService messageGeneratorService) {
        this.callRepository = callRepository;
        this.callMapper = callMapper;
        this.notificationService = notificationService;
        this.messageGeneratorService = messageGeneratorService;
    }

    @Override
    public ResponseEntity<String> deliverReport(UserDto userDto) {

        Optional<List<CallEntity>> callerEntitiesOpt = callRepository
                .findAllByCalledPhone(userDto.getPhone());

        List<CallEntity> callerEntities = null;
        if (callerEntitiesOpt.isPresent()) {

            callerEntities = callerEntitiesOpt.get();
            //reset missed call notification count
            for (CallEntity callEntity : callerEntities) {
                callEntity.setNotNotifiedCallCount(0);
            }
            callRepository.saveAll(callerEntities);

        }

        //send available notification if there was a caller for the userDto
        if(callerEntities!=null){
            sendAvailableNotification(callerEntities);
        }

        log.info("Delivery report was successfully processed for user: " + userDto);
        return ResponseEntity.ok("Delivery report was successfully processed.");

    }

    private void sendAvailableNotification(List<CallEntity> callerEntities) {
        for (CallEntity callEntity : callerEntities) {
            String message = messageGeneratorService.generateMessageForAvailableNotification(callEntity);
            notificationService.sendNotification(callEntity.getCallerUserEntity().getPhone(), message);
            log.info("Available notification was sent to user: "+ callEntity);
        }
    }
}
