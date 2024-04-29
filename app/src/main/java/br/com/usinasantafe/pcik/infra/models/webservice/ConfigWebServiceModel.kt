package br.com.usinasantafe.pcik.infra.models.webservice

import br.com.usinasantafe.pcik.domain.entities.Config
import kotlinx.serialization.Serializable


@Serializable
data class ConfigWebServiceModelOutput(
    val nroAparelho: Long,
    val version: String,
)

@Serializable
data class ConfigWebServiceModelInput(
    var idBD: Long,
)


fun Config.toConfigWebServiceModel(): ConfigWebServiceModelOutput {
    return with(this){
        ConfigWebServiceModelOutput(
            nroAparelho = this.nroAparelhoConfig!!,
            version = this.version!!,
        )
    }
}

fun ConfigWebServiceModelInput.toConfig(): Config {
    return with(this){
        Config(
            idBDConfig = this.idBD
        )
    }
}