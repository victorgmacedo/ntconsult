package br.com.ntconsult.challenge.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Session(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        @OneToOne
        @JoinColumn(name = "topic_id")
        val topic: Topic,
        val finishAt: LocalDateTime,
        val startedAt: LocalDateTime,
        @OneToMany(mappedBy="session")
        val votes: Set<Vote> = setOf(),
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "result_id")
        var resultDetail: ResultDetail? = null
) {
        fun isOpenToVote() = finishAt.isAfter(LocalDateTime.now())
}