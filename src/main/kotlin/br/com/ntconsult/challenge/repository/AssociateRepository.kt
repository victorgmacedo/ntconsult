package br.com.ntconsult.challenge.repository

import br.com.ntconsult.challenge.model.Associate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AssociateRepository: JpaRepository<Associate, UUID> {

    fun findByCpf(cpf: String) : Optional<Associate>

}