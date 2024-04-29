package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CheckPasswordConfigImplTest {

    @Test
    fun `Teste Checando acesso se nao tiver dados de Configuracao salvo`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(false)
        val usecase = CheckPasswordConfigImpl(configRepository)
        val result = usecase("")
        assertTrue(result)
    }

    @Test
    fun `Teste Checando retorno falso se tiver dados de Configuracoes`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(true)
        val usecase = CheckPasswordConfigImpl(configRepository)
        val result = usecase("")
        assertFalse(result)
    }

    @Test
    fun `Teste Checando retorno Verdadeiro se tiver dados de Configuracoes e Password certo`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(true)
        whenever(configRepository.getPassword()).thenReturn("12345")
        val usecase = CheckPasswordConfigImpl(configRepository)
        val result = usecase("12345")
        assertTrue(result)
    }

    @Test
    fun `Teste Checando retorno Falso se tiver dados de Configuracoes e Password errado`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(true)
        whenever(configRepository.getPassword()).thenReturn("12345")
        val usecase = CheckPasswordConfigImpl(configRepository)
        val result = usecase("1234")
        assertFalse(result)
    }
}