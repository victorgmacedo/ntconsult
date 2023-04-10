package br.com.ntconsult.challenge.controller.data

import br.com.ntconsult.challenge.definitions.VoteOptions

data class VoteDTO(
        val vote: VoteOptions,val associate: AssociateDTO)
