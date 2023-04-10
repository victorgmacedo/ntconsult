package br.com.ntconsult.challenge.domain

import br.com.ntconsult.challenge.definitions.VoteOptions
import br.com.ntconsult.challenge.model.Session

data class VoteDetail(val session: Session, val associateDetail: AssociateDetail, val vote: VoteOptions)
