package com.burhan.missedcallcenter.service.messagegenerator;

import com.burhan.missedcallcenter.entity.CallEntity;

public interface MessageGeneratorService {

    String generateMessageForAvailableNotification(CallEntity callEntity);

    String generateMessageForMissedCalls(String name);
}
