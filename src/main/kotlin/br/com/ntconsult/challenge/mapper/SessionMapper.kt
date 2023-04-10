package br.com.ntconsult.challenge.mapper

import br.com.ntconsult.challenge.controller.data.SessionDTO
import br.com.ntconsult.challenge.model.Session
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface SessionMapper {

    @Mapping(target = "isOpenToVote", expression = "java(session.isOpenToVote())")
    fun toDTO(session: Session): SessionDTO

}