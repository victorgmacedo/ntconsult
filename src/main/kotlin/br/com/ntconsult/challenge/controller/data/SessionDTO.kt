package br.com.ntconsult.challenge.controller.data

import java.time.LocalDateTime

data class SessionDTO(
        val id: String?,
        val finishAt: LocalDateTime,
        val startedAt: LocalDateTime,
        val isOpenToVote: Boolean
)
