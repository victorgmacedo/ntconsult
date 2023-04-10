package br.com.ntconsult.challenge.repository

import br.com.ntconsult.challenge.definitions.VoteOptions
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Tuple
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ResultRepository(@PersistenceContext
                       private val entityManager: EntityManager) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun totalVotes(sessionId: UUID): Long {
        return try {
            entityManager.createQuery("""select count(a) 
                                              from Vote a 
                                             where a.session.id = :id""".trimMargin(), Long::class.java)
                    .setParameter("id", sessionId)
                    .singleResult
        } catch (e: Exception) {
            logger.info("There is no votes for session {ID} $sessionId")
            0
        }
    }

    fun voteDetail(sessionId: UUID): Map<VoteOptions, Long> {
        return entityManager.createQuery("""select a.vote as vote, count(a) as total
                                              from Vote a 
                                             where a.session.id = :id
                                             group by a.vote""".trimMargin(), Tuple::class.java)
                .setParameter("id", sessionId)
                .resultList.associate {
                    it["vote"] as VoteOptions to it["total"] as Long
                }
    }

}