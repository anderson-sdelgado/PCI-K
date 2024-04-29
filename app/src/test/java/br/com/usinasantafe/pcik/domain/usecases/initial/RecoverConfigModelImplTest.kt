package br.com.usinasantafe.pcik.domain.usecases.initial

import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.presenter.initial.config.ConfigModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RecoverConfigImplTest {

    @Test
    fun `Retorno nulo se nao tiver dados no Config no Shared Preferences`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(false)
        val usecase = RecoverConfigImpl(configRepository)
        val result = usecase()
        assertEquals(result, null)
    }

    @Test
    fun `Retorno objeto Config quando tiver dados no Config no Shared Preferences`() = runBlocking {
        val configRepository = mock<ConfigRepository>()
        whenever(configRepository.hasConfig()).thenReturn(true)
        whenever(configRepository.getPassword()).thenReturn("12345")
        whenever(configRepository.getNroAparelho()).thenReturn(16997417840)
        val usecase = RecoverConfigImpl(configRepository)
        val result = usecase()
        assertEquals(result, ConfigModel(nroAparelho = 16997417840, senha = "12345"))
    }
}