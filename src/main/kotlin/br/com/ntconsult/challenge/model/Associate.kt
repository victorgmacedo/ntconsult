package br.com.ntconsult.challenge.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Associate(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        val cpf: String
)
