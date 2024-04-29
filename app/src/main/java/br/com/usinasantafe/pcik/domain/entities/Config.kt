package br.com.usinasantafe.pcik.domain.entities

import br.com.usinasantafe.pcik.utils.FlagUpdate
import br.com.usinasantafe.pcik.utils.StatusData
import br.com.usinasantafe.pcik.utils.StatusSend

data class Config(
    var nroAparelhoConfig: Long? = null,
    var passwordConfig: String? = null,
    var idBDConfig: Long? = null,
    var version: String? = null,
    var flagUpdate: FlagUpdate = FlagUpdate.OUTDATED,
    var matricVigia: Long? = null,
    var idLocal: Long? = null,
    var statusEnvio: StatusSend = StatusSend.SENT,
    var statusApont: StatusData = StatusData.CLOSE,
)
