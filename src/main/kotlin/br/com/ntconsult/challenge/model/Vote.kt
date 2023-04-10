package br.com.ntconsult.challenge.model

import br.com.ntconsult.challenge.definitions.VoteOptions
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "session_vote")
data class Vote(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        @ManyToOne
        @JoinColumn(name="session_id", nullable=false)
        val session: Session,
        @ManyToOne
        @JoinColumn(name="associate_id", nullable=false)
        val associate: Associate,
        @Enumerated(EnumType.STRING)
        val vote: VoteOptions
)
