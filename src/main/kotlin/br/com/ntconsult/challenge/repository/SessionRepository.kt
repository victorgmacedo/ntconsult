package br.com.ntconsult.challenge.repository

import br.com.ntconsult.challenge.model.Session
import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import org.hibernate.LockOptions
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface SessionRepository: JpaRepository<Session, UUID> {

    @Query("select count(1) > 0 from Session where topic.id = :topicId")
    fun existSessionForTopic(
            @Param("topicId") topicId: UUID
    ): Boolean

    fun findByTopicId(id: UUID): Session?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(
         value = [QueryHint(name = "jakarta.persistence.lock.timeout", value = LockOptions.SKIP_LOCKED.toString())]
    )
    @Query("select a from Session a where resultDetail is null and finishAt < :date")
    fun findAllSessionsClosedWithResultDetailEmpty(
            @Param("date") date: LocalDateTime,
            pageable: Pageable = PageRequest.of(0,100, Sort.by(Sort.Order.desc("finishAt")))
    ): List<Session>

}