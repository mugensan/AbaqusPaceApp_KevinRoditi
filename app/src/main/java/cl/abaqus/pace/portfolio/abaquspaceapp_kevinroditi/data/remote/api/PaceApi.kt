package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PortfolioValueDto
import retrofit2.http.GET

interface PaceApi {
    @GET("portfolio")
    suspend fun getPortfolioValue(): List<PortfolioValueDto>
}
