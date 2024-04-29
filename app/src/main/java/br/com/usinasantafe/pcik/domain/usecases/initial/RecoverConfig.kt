package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.presenter.initial.config.ConfigModel
import javax.inject.Inject

interface RecoverConfig {
    suspend operator fun invoke(): ConfigModel?
}

class RecoverConfigImpl @Inject constructor (
    private val configRepository: ConfigRepository,
): RecoverConfig {
    override suspend fun invoke(): ConfigModel? {
        if(!configRepository.hasConfig()){
            return null
        }
        val nroAparelho = configRepository.getNroAparelho()
        val senha = configRepository.getPassword()
        return ConfigModel(nroAparelho = nroAparelho, senha = senha)
    }

}