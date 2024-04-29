package br.com.usinasantafe.pcik.external.webservices.api.variable

import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcik.infra.models.webservice.ConfigWebServiceModelOutput
import br.com.usinasantafe.pcik.utils.WEB_SAVE_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConfigApi {

    @POST(WEB_SAVE_TOKEN)
    suspend fun send(@Body config: ConfigWebServiceModelOutput): Response<ConfigWebServiceModelInput>

}