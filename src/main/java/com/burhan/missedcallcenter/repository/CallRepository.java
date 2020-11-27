package com.burhan.missedcallcenter.repository;

import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CallRepository extends JpaRepository<CallEntity, Long> {

    //TO DO id or entity itself?
    Optional<CallEntity> findByCallerUserEntity_IdAndCalledPhone(long callerUserEntityId, String calledPhone);
}
