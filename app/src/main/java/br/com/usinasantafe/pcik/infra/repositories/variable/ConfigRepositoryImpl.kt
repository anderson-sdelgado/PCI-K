package br.com.usinasantafe.pcik.infra.repositories.variable

import br.com.usinasantafe.pcik.domain.entities.Config
import br.com.usinasantafe.pcik.domain.error.FailureIntegrationRepository
import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.pcik.infra.datasource.webservice.ConfigWebServiceDatasource
import br.com.usinasantafe.pcik.infra.models.webservice.toConfig
import br.com.usinasantafe.pcik.infra.models.webservice.toConfigWebServiceModel
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource,
    private val configWebServiceDatasource: ConfigWebServiceDatasource,
) : ConfigRepository {
    override suspend fun hasConfig(): Boolean {
        return configSharedPreferencesDatasource.hasConfig()
    }

    override suspend fun getPassword(): String {
        val config = configSharedPreferencesDatasource.getConfig()
        return config.passwordConfig!!
    }

    override suspend fun getNroAparelho(): Long {
        val config = configSharedPreferencesDatasource.getConfig()
        return config.nroAparelhoConfig!!
    }

    override suspend fun recoverToken(config: Config): Result<Config> {
        try {
            configWebServiceDatasource.recoverToken(config.toConfigWebServiceModel())
                .fold(
                    onSuccess = {
                        return Result.success(it.toConfig())
                    },
                    onFailure = {
                        return Result.failure(it)
                    }
                )
        } catch (exception: Exception) {
            return Result.failure(FailureIntegrationRepository(exception.message))
        }
    }

    override suspend fun saveConfig(config: Config) {
        TODO("Not yet implemented")
    }
}