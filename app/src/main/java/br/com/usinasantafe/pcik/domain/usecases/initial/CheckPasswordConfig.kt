package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import javax.inject.Inject

interface CheckPasswordConfig {
    suspend operator fun invoke(senha: String): Boolean
}

class CheckPasswordConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository
): CheckPasswordConfig {
    override suspend fun invoke(senha: String): Boolean {
        if (!configRepository.hasConfig()) return true
        if (configRepository.getPassword() == senha) return true
        return false
    }

}