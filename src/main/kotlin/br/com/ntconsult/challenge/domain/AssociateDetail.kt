package br.com.ntconsult.challenge.domain

class AssociateDetail(private val document: String) {
    val cpf: String
        get() = document.replace(Regex("\\D+"), "")
}