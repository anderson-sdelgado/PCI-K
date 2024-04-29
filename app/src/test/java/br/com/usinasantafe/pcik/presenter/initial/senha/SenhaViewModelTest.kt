package br.com.usinasantafe.pcik.presenter.initial.senha

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.usinasantafe.pcik.domain.usecases.initial.CheckPasswordConfig
import br.com.usinasantafe.pcik.presenter.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SenhaViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Retornar senha valida`() = runBlocking {
        val checkPasswordConfig = mock<CheckPasswordConfig>()
        whenever(checkPasswordConfig("12345")).thenReturn(true)
        val viewModel = SenhaViewModel(checkPasswordConfig)
        viewModel.verificarSenha("12345")
        val result = viewModel.uiLiveData.getOrAwaitValue()
        assertEquals(result, SenhaFragmentState.Success)
    }

    @Test
    fun `Retornar senha invalida`() = runBlocking {
        val checkPasswordConfig = mock<CheckPasswordConfig>()
        whenever(checkPasswordConfig("12345")).thenReturn(false)
        val viewModel = SenhaViewModel(checkPasswordConfig)
        viewModel.verificarSenha("12345")
        val result = viewModel.uiLiveData.getOrAwaitValue()
        assertEquals(result, SenhaFragmentState.Error)
    }
}