package br.com.usinasantafe.pcik.domain.repostiories.variable

import br.com.usinasantafe.pcik.domain.entities.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    suspend fun hasConfig(): Boolean
    suspend fun getPassword(): String
    suspend fun getNroAparelho(): Long

    suspend fun recoverToken(config: Config): Result<Config>

    suspend fun saveConfig(config: Config)

}