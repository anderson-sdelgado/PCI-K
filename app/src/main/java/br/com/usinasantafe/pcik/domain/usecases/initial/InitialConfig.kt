package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.entities.Config
import br.com.usinasantafe.pcik.domain.error.FailureIntegrationUseCase
import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcik.utils.TEXT_FINISH_UPDATE
import br.com.usinasantafe.pcik.utils.TEXT_RECEIVE_TOKEN
import br.com.usinasantafe.pcik.utils.TEXT_SAVE_DATA_CONFIG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface InitialConfig {
    suspend operator fun invoke(
        nroAparelho: String,
        password: String,
        version: String,
        contador: Int = 0,
        qtde: Int = 3,
    ): Flow<Result<ResultUpdateDatabase>>
}

class InitialConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository,
) : InitialConfig {
    override suspend fun invoke(
        nroAparelho: String,
        password: String,
        version: String,
        contador: Int,
        qtde: Int,
    ): Flow<Result<ResultUpdateDatabase>> = flow<Result<ResultUpdateDatabase>> {
        var contRecoverToken = contador
        val config = Config(
            nroAparelhoConfig = nroAparelho.toLong(),
            passwordConfig = password,
            version = version,
        )
        emit(Result.success(ResultUpdateDatabase(++contRecoverToken, TEXT_RECEIVE_TOKEN, qtde)))
        configRepository.recoverToken(config).fold(
            onSuccess = {
                config.idBDConfig = it.idBDConfig
                emit(
                    Result.success(
                        ResultUpdateDatabase(
                            ++contRecoverToken,
                            TEXT_SAVE_DATA_CONFIG,
                            qtde
                        )
                    )
                )
                configRepository.saveConfig(config)
                emit(Result.success(ResultUpdateDatabase(qtde, TEXT_FINISH_UPDATE, qtde)))
            },
            onFailure = { exception ->
                emit(Result.failure(exception))
            }
        )
    }.catch {
        emit(Result.failure(FailureIntegrationUseCase(it.message)));
    }
}
