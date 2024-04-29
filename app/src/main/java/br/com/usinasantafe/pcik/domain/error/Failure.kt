package br.com.usinasantafe.pcik.domain.error

class FailureIntegrationUseCase(override val message: String?) : Exception() {
}
class FailureIntegrationRepository(override val message: String?) : Exception() {
}

class FailureIntegrationWebServiceDatasource(override val message: String?) : Exception() {
}