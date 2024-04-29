package br.com.usinasantafe.pcik.external.webservices.datasource.variable

import br.com.usinasantafe.pcik.infra.datasource.webservice.ConfigWebServiceDatasource
import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigWebServiceDatasourceImpl @Inject constructor(
): ConfigWebServiceDatasource {
    override suspend fun recoverToken(config: ConfigWebServiceModelOutput): Result<ConfigWebServiceModelInput> {
        TODO("Not yet implemented")
    }
}