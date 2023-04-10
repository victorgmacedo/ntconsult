package br.com.ntconsult.challenge.controller.data

data class TopicResponseDTO(
        val id: String?,
        val title: String,
        val description: String,
        var session: SessionDTO?
)
