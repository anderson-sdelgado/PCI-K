package br.com.usinasantafe.pcik.infra.repositories.variable

import br.com.usinasantafe.pcik.domain.entities.Config
import br.com.usinasantafe.pcik.domain.error.FailureIntegrationRepository
import br.com.usinasantafe.pcik.domain.error.FailureIntegrationWebServiceDatasource
import br.com.usinasantafe.pcik.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.pcik.infra.datasource.webservice.ConfigWebServiceDatasource
import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcik.infra.models.webservice.toConfig
import br.com.usinasantafe.pcik.infra.models.webservice.toConfigWebServiceModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ConfigRepositoryImplTest {

    private lateinit var configSharedPreferencesDatasource: ConfigSharedPreferencesDatasource
    private lateinit var configWebServiceDatasource: ConfigWebServiceDatasource

    @Before
    fun init() {
        configSharedPreferencesDatasource = mock()
        configWebServiceDatasource = mock()
    }

    @Test
    fun `Retornar True se tiver dados interno na base config Shared Preferences`() = runBlocking {
        whenever(configSharedPreferencesDatasource.hasConfig()).thenReturn(true)
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource);
        val result = repository.hasConfig()
        assertTrue(result)
    }

    @Test
    fun `Retornar False se não tiver dados interno na base config Shared Preferences`() = runBlocking {
        whenever(configSharedPreferencesDatasource.hasConfig()).thenReturn(false)
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        val result = repository.hasConfig()
        assertFalse(result)
    }

    @Test
    fun  `Retornar password da base de dados Shared Preferences`() = runBlocking {
        whenever(configSharedPreferencesDatasource.getConfig()).thenReturn(Config(passwordConfig = "12345"))
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        val result = repository.getPassword()
        assertEquals(result, "12345")
    }

    @Test
    fun  `Retornar nro aparelho da base de dados Shared Preferences`() = runBlocking {
        whenever(configSharedPreferencesDatasource.getConfig()).thenReturn(Config(nroAparelhoConfig = 16997417840))
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        val result = repository.getNroAparelho()
        assertEquals(result, 16997417840)
    }

    @Test
    fun  `Retornar sucesso no retorno de busca de token`() = runBlocking {
        val config = Config(
            nroAparelhoConfig = 16997417840,
            passwordConfig = "12345",
            version = "1.00"
        )
        val configModelInput = ConfigWebServiceModelInput(
            idBD = 10
        )
        whenever(configWebServiceDatasource.recoverToken(config.toConfigWebServiceModel())).thenReturn(
            Result.success(configModelInput))
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        val result = repository.recoverToken(config)
        assertEquals(result, Result.success(configModelInput.toConfig()))
    }

    @Test
    fun  `Retornar falha de Integracao de Repository`() = runTest {
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        val result = repository.recoverToken(Config())
        assertEquals(result.isFailure, true)
        val exception = assertThrows(FailureIntegrationRepository::class.java){
            result.getOrThrow()
        }
        assertEquals(exception.message, null)
    }

    @Test
    fun  `Retornar falha de Integracao de Datasource`() = runTest {
        val config = Config(
            nroAparelhoConfig = 16997417840,
            passwordConfig = "12345",
            version = "1.00"
        )
        val repository = ConfigRepositoryImpl(configSharedPreferencesDatasource, configWebServiceDatasource)
        whenever(configWebServiceDatasource.recoverToken(config.toConfigWebServiceModel())).thenReturn(
            Result.failure(FailureIntegrationWebServiceDatasource("Erro")))
        val result = repository.recoverToken(config)
        assertEquals(result.isFailure, true)
        val exception = assertThrows(FailureIntegrationWebServiceDatasource::class.java){
            result.getOrThrow()
        }
        assertEquals(exception.message, "Erro")
    }


}