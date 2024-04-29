package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.entities.Config
import br.com.usinasantafe.pcik.domain.error.FailureIntegrationRepository
import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.utils.ResultUpdateDatabase
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.Result

class InitialConfigImplTest {

    @Test
    fun `Checar Enviando dados para Token`() = runTest {
        val configRepository = mock<ConfigRepository>()
        val usecase = InitialConfigImpl(configRepository)
        val firstItem =
            usecase(nroAparelho = "16997417840", password = "12345", version = "1.0").first()
        assertEquals(
            firstItem,
            Result.success(ResultUpdateDatabase(1, "Enviando dados do Token", 3, percentage = 33))
        )
    }

    @Test
    fun `Checar Qtde de Emit Enviando dados para Token e Salvando dados de configuracao`() =
        runTest {
            val configRepository = mock<ConfigRepository>()
            val config = Config(
                nroAparelhoConfig = 16997417840,
                passwordConfig = "12345",
                version = "1.0",
            )
            whenever(configRepository.recoverToken(config)).thenReturn(Result.success(config))
            val usecase = InitialConfigImpl(configRepository)
            val count =
                usecase(nroAparelho = "16997417840", password = "12345", version = "1.0").count()
            assertEquals(count, 3)
        }

    @Test
    fun `Checar Lista de Emit Enviando dados para Token e Salvando dados de configuracao`() =
        runTest {
            val configRepository = mock<ConfigRepository>()
            val config = Config(
                nroAparelhoConfig = 16997417840,
                passwordConfig = "12345",
                version = "1.0",
            )
            whenever(configRepository.recoverToken(config)).thenReturn(Result.success(config))
            val usecase = InitialConfigImpl(configRepository)
            val list =
                usecase(nroAparelho = "16997417840", password = "12345", version = "1.0").toList()
            assertEquals(
                list[0],
                Result.success(
                    ResultUpdateDatabase(
                        1,
                        "Enviando dados do Token",
                        3,
                        percentage = 33
                    )
                )
            )
            assertEquals(
                list[1],
                Result.success(
                    ResultUpdateDatabase(
                        2,
                        "Salvandos Dados de Configurações",
                        3,
                        percentage = 66
                    )
                )
            )
        }

    @Test
    fun `Checar Lista de Emit Enviando dados para Token, Salvando dados de configuracao e Finalização com Sucesso`() =
        runTest {
            val configRepository = mock<ConfigRepository>()
            val config = Config(
                nroAparelhoConfig = 16997417840,
                passwordConfig = "12345",
                version = "1.0",
            )
            whenever(configRepository.recoverToken(config)).thenReturn(Result.success(config))
            val usecase = InitialConfigImpl(configRepository)
            val list =
                usecase(nroAparelho = "16997417840", password = "12345", version = "1.0").toList()
            assertEquals(
                list[0],
                Result.success(
                    ResultUpdateDatabase(
                        1,
                        "Enviando dados do Token",
                        3,
                        percentage = 33
                    )
                )
            )
            assertEquals(
                list[1],
                Result.success(
                    ResultUpdateDatabase(
                        2,
                        "Salvandos Dados de Configurações",
                        3,
                        percentage = 66
                    )
                )
            )
            assertEquals(
                list[2],
                Result.success(
                    ResultUpdateDatabase(
                        3,
                        "Termino de Atualização",
                        3,
                        percentage = 100
                    )
                )
            )
        }

    @Test
    fun `Checar Falha de envio de Dados`() =
        runTest {
            val configRepository = mock<ConfigRepository>()
            val config = Config(
                nroAparelhoConfig = 16997417840,
                passwordConfig = "12345",
                version = "1.0",
            )
            whenever(configRepository.recoverToken(config)).thenReturn(Result.failure(FailureIntegrationRepository("Erro")))
            val usecase = InitialConfigImpl(configRepository)
            val list =
                usecase(nroAparelho = "16997417840", password = "12345", version = "1.0").toList()
            assertEquals(
                list[0],
                Result.success(
                    ResultUpdateDatabase(
                        1,
                        "Enviando dados do Token",
                        3,
                        percentage = 33
                    )
                )
            )
            assertEquals(
                list[1].isFailure,
                true
            )
            val exception = assertThrows(FailureIntegrationRepository::class.java){
                list[1].getOrThrow()
            }
            assertEquals(exception.message, "Erro")
        }

}