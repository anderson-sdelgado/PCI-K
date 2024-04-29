package br.com.usinasantafe.pcik.external.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.pcik.domain.entities.Config
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigSharedPreferencesDatasourceImplTest {

    private lateinit var context : Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var configSharedPreferencesDatasourceImpl : ConfigSharedPreferencesDatasourceImpl

    @Before
    fun init() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("teste", Context.MODE_PRIVATE)
        configSharedPreferencesDatasourceImpl = ConfigSharedPreferencesDatasourceImpl(sharedPreferences)
    }

    @Test
    fun `Retornar Falso se nao tiver dados no Config Shared Preferences`() = runBlocking {
        assertFalse(configSharedPreferencesDatasourceImpl.hasConfig())
    }

    @Test
    fun `Retornar Verdadeiro se tiver dados no Config Shared Preferences`() = runBlocking {
        configSharedPreferencesDatasourceImpl.saveConfig(Config(passwordConfig = "12345"))
        assertTrue(configSharedPreferencesDatasourceImpl.hasConfig())
    }

    @Test
    fun `Se nao tiver dados no Config Shared Preferences retornar um objeto Config vazio`() = runBlocking {
        assertEquals(configSharedPreferencesDatasourceImpl.getConfig(), Config())
    }

    @Test
    fun `Se tiver dados no Config Shared Preferences retornar o objeto Config`() = runBlocking {
        val config = Config(passwordConfig = "12345", nroAparelhoConfig = 16997417840)
        configSharedPreferencesDatasourceImpl.saveConfig(config)
        assertEquals(configSharedPreferencesDatasourceImpl.getConfig(), config)
    }
}