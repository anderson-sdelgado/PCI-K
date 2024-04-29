package br.com.usinasantafe.pcik.domain.usecases.common

import br.com.usinasantafe.pcik.utils.PointerStart
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class StartAppImplTest {

    @Test
    fun `Teste inicial do Start do Aplicativo`() = runBlocking {
        val usecase = StartAppImpl()
        assertEquals(usecase, PointerStart.MENUAPONT)
    }
}