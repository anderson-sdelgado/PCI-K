package br.com.usinasantafe.pcik.external.webservices.datasource.variable

import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class ConfigWebServiceDatasourceImplTest {

    @Test
    fun `Retorno de dados no sucesso de conexão`() = runBlocking {
        var result = ConfigWebServiceDatasourceImpl().recoverToken(ConfigWebServiceModelOutput(nroAparelho = 16997417840, version = "1.0"))

    }
}