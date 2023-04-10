package br.com.ntconsult.challenge.controller

import br.com.ntconsult.challenge.controller.data.*
import br.com.ntconsult.challenge.domain.AssociateDetail
import br.com.ntconsult.challenge.domain.SessionDefinition
import br.com.ntconsult.challenge.domain.VoteDetail
import br.com.ntconsult.challenge.exception.SessionNotFoundException
import br.com.ntconsult.challenge.mapper.SessionMapper
import br.com.ntconsult.challenge.mapper.TopicMapper
import br.com.ntconsult.challenge.service.SessionService
import br.com.ntconsult.challenge.service.TopicService
import br.com.ntconsult.challenge.service.VoteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(TopicController.PATH)
class TopicController(private val topicMapper: TopicMapper,
                      private val topicService: TopicService,
                      private val sessionService: SessionService,
                      private val voteService: VoteService,
                      private val sessionMapper: SessionMapper) {

    @PostMapping
    fun create(@RequestBody topicDTO: TopicRequestDTO): ResponseEntity<TopicResponseDTO> {
        val topic = topicDTO
                .run(topicMapper::toModel)
                .run(topicService::save)
                .let(topicMapper::toDTO)
        return ResponseEntity.created(URI.create("$PATH/${topic.id}")).body(topic)
    }

    @PostMapping("/{id}/session/open")
    fun open(@PathVariable("id") id: String,
             @RequestBody sessionDefinitionDTO: SessionDefinitionDTO): ResponseEntity<SessionDTO> {
        val session = with(sessionDefinitionDTO) {
            val sessionDefinition = SessionDefinition(
                    topic = topicService.findById(id),
                    timeToVote = timeToVote
            )
            val session = sessionService.create(sessionDefinition)
            sessionMapper.toDTO(session)
        }
        return ResponseEntity.created(URI.create("$PATH/${session.id}")).body(session)
    }

    @PostMapping("{id}/session/vote")
    fun vote(@PathVariable("id") id: String, @RequestBody voteDTO: VoteDTO): ResponseEntity<Void> {
        with(voteDTO) {
            val session = sessionService.findByTopicId(id) ?:
                throw SessionNotFoundException("There is no session open for topic {ID} $id")
            val voteDetail = VoteDetail(
                    session = session,
                    associateDetail = AssociateDetail(associate.cpf),
                    vote = vote
            )
            voteService.vote(voteDetail)
        }
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: String) : ResponseEntity<TopicResponseDTO> {
        val topic = topicService.findById(id).let(topicMapper::toDTO)
        topic.session = sessionService.findByTopicId(id)?.let(sessionMapper::toDTO)
        return ResponseEntity.ok(topic)
    }

    companion object {
        const val PATH = "/topics"
    }

}