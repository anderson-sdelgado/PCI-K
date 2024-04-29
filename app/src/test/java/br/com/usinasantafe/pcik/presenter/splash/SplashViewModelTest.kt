package br.com.usinasantafe.pcik.presenter.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.usinasantafe.pcik.domain.usecases.common.StartApp
import br.com.usinasantafe.pcik.presenter.utils.getOrAwaitValue
import br.com.usinasantafe.pcik.utils.PointerStart
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
class SplashViewModelTest {

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
    fun `Teste inicial do Aplicativo`() = runBlocking {
        val startApp = mock<StartApp>()
        whenever(startApp()).thenReturn(PointerStart.MENUINICIAL)
        val viewModel = SplashViewModel(startApp)
        viewModel.startSent()
        val result = viewModel.uiLiveData.getOrAwaitValue()
        assertEquals(result, SplashState.CheckStartAPP(PointerStart.MENUINICIAL))
    }
}