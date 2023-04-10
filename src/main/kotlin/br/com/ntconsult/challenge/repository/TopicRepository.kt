package br.com.ntconsult.challenge.repository

import br.com.ntconsult.challenge.model.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TopicRepository: JpaRepository<Topic, UUID>