package br.com.ntconsult.challenge.model

import br.com.ntconsult.challenge.definitions.VoteOptions
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.util.*

@Entity
@Table(name = "result_detail")
data class ResultDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        val totalVotes: Long,
        @Enumerated(EnumType.STRING)
        val result: VoteOptions?,
        @Type(JsonType::class)
        @Column(columnDefinition = "json")
        val voteDetail: Map<VoteOptions, Long>
)
