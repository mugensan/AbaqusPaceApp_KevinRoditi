package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface PaceApi {
    @GET("portfolio/value")
    suspend fun getPortfolioValue(@Query("currency") currency: String): PortfolioValueDto

    @GET("portfolio/performance")
    suspend fun getPortfolioPerformance(@Query("currency") currency: String): PerformanceDto

    @GET("portfolio/cash")
    suspend fun getCashBalance(@Query("currency") currency: String): CashBalanceDto

    @GET("portfolio/returns")
    suspend fun getReturns(@Query("currency") currency: String): ReturnsDto

    @GET("portfolio/positions")
    suspend fun getPositions(@Query("currency") currency: String): List<PositionDto>
}
