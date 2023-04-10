package br.com.ntconsult.challenge.domain

import br.com.ntconsult.challenge.definitions.VoteOptions
import java.util.*

data class TopicResult(
     val topicId: UUID,
     val result: VoteOptions?,
     val totalVotes: Long,
     val voteDetail: Map<VoteOptions, Long>
)
