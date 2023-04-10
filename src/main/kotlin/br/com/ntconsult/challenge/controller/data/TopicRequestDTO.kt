package br.com.ntconsult.challenge.controller.data

import jakarta.validation.constraints.Max

data class TopicRequestDTO(
        @Max(100) val title: String,
        @Max(500) val description: String,
)
