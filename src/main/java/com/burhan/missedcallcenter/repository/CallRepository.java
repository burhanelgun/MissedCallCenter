package com.burhan.missedcallcenter.repository;

import com.burhan.missedcallcenter.entity.CallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CallRepository extends JpaRepository<CallEntity, Long> {

    Optional<CallEntity> findByCallerUserEntity_IdAndCalledPhone(long callerUserEntityId, String calledPhone);

    Optional<List<CallEntity>> findAllByCalledPhone(String calledPhone);

}
