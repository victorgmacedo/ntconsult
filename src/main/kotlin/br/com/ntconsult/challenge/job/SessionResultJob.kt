package br.com.ntconsult.challenge.job

import br.com.ntconsult.challenge.domain.TopicResult
import br.com.ntconsult.challenge.model.ResultDetail
import br.com.ntconsult.challenge.model.Session
import br.com.ntconsult.challenge.service.MessageService
import br.com.ntconsult.challenge.service.ResultService
import br.com.ntconsult.challenge.service.SessionService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Component
class SessionResultJob(private val resultService: ResultService,
                       private val sessionService: SessionService,
                       private val messageService: MessageService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    @Transactional
    fun process() {
        val sessions = sessionService.findAllSessionsClosedWithTopicResultEmpty(LocalDateTime.now())
        sessions.forEach {
            logger.info("Processing result for session {ID} ${it.id}")
            val resultDetail = resultService.buildResultDetail(session = it)
            it.resultDetail = resultDetail
            sessionService.save(it)
            messageService.publishMessage(createMessageResult(it, resultDetail))
        }
    }

    fun createMessageResult(session: Session, resultDetail: ResultDetail): TopicResult {
        return TopicResult(
                topicId = session.topic.id!!,
                result = resultDetail.result,
                totalVotes = resultDetail.totalVotes,
                voteDetail = resultDetail.voteDetail
        )
    }

}