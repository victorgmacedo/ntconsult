package br.com.ntconsult.challenge.controller.data

import jakarta.validation.constraints.Size

data class AssociateDTO(
        @Size(min = 11, max = 11, message = "Document must have only numbers") val cpf: String)
