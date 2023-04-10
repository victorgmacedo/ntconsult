package br.com.ntconsult.challenge.mapper

import br.com.ntconsult.challenge.controller.data.TopicRequestDTO
import br.com.ntconsult.challenge.controller.data.TopicResponseDTO
import br.com.ntconsult.challenge.model.Topic
import org.mapstruct.Mapper

@Mapper
interface TopicMapper {

    fun toModel(topicDTO: TopicRequestDTO) : Topic
    fun toDTO(topic: Topic): TopicResponseDTO

}