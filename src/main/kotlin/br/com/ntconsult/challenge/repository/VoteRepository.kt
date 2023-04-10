package br.com.ntconsult.challenge.repository

import br.com.ntconsult.challenge.model.Vote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VoteRepository: JpaRepository<Vote, UUID> {

    @Query("select count(1) > 0 from Vote where session.id = :sessionId and associate.cpf = :associateDocument")
    fun existsVoteFromAssociateAndSession(
            @Param("sessionId") sessionId: UUID,
            @Param("associateDocument") associateDocument: String
    ): Boolean

}