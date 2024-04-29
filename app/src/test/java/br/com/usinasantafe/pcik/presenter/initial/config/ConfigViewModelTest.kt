package br.com.usinasantafe.pcik.presenter.initial.config

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import br.com.usinasantafe.pcik.domain.usecases.initial.InitialConfig
import br.com.usinasantafe.pcik.domain.usecases.initial.RecoverConfig
import br.com.usinasantafe.pcik.presenter.initial.senha.SenhaFragmentState
import br.com.usinasantafe.pcik.presenter.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
import java.util.concurrent.TimeoutException

@RunWith(MockitoJUnitRunner::class)
class ConfigViewModelTest {

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
    fun `Retornar nro do aparelho e senha`() = runBlocking {
        val recoverConfig = mock<RecoverConfig>()
        val initialConfig = mock<InitialConfig>()
        val config = ConfigModel(nroAparelho = 16997417840, senha = "12345")
        whenever(recoverConfig()).thenReturn(config)
        val viewModel = ConfigViewModel(recoverConfig, initialConfig)
        viewModel.recoverDataConfig()
        val result = viewModel.uiLiveData.getOrAwaitValue()
        assertEquals(result, ConfigFragmentState.RecoverConfigModel(config))
    }

    //@Test
    //fun `Checar initial config`() = runBlocking {
    //    val recoverConfig = mock<RecoverConfig>()
    //    val initialConfig = mock<InitialConfig>()
    //    whenever(initialConfig("16997417840", "12345", "1.00")).thenReturn(
    //        flow {
    //        }
    //    )
    //    val viewModel = ConfigViewModel(recoverConfig, initialConfig)
    //    viewModel.saveDataInitialConfig("16997417840", "12345", "1.00")
    //    val result = viewModel.uiLiveData.getOrAwaitValue()
    //    assertEquals(result, null)
    //}
}
