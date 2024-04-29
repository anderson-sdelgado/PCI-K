package br.com.usinasantafe.pcik.infra.datasource.webservice

import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.flow.Flow

interface ConfigWebServiceDatasource {

    suspend fun recoverToken(config: ConfigWebServiceModelOutput): Result<ConfigWebServiceModelInput>

}