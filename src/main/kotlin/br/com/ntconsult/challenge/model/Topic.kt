package br.com.ntconsult.challenge.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Topic(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID? = null,
        val title: String,
        val description: String
)