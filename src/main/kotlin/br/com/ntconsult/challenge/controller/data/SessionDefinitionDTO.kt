package br.com.ntconsult.challenge.controller.data

import jakarta.validation.constraints.Min

data class SessionDefinitionDTO(@Min(1, message = "Time to vote cannot be less than 1") val timeToVote: Long = 1)
