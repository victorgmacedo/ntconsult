package br.com.ntconsult.challenge.mapper

import br.com.ntconsult.challenge.controller.data.VoteDTO
import br.com.ntconsult.challenge.model.Vote
import org.mapstruct.Mapper

@Mapper
interface VoteMapper {

    fun toDTO(vote: Vote): VoteDTO

}