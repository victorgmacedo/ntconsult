package br.com.ntconsult.challenge.domain

import br.com.ntconsult.challenge.model.Topic

data class SessionDefinition(val topic: Topic, val timeToVote: Long = 1L) {
    init {
        if(timeToVote < 1) throw IllegalArgumentException("Time to vote cannot be less than 1")
    }
}
