package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface PaceApi {
    @GET("portfolios/value/")
    suspend fun getPortfolioValue(@Query("currency") currency: String): PortfolioValueDto

    @GET("portfolios/performance/ytd/")
    suspend fun getPortfolioPerformance(@Query("currency") currency: String): PerformanceDto

    @GET("accounts/cash-balance/")
    suspend fun getCashBalance(): CashBalanceDto

    @GET("portfolios/returns/ytd/")
    suspend fun getReturns(@Query("currency") currency: String): ReturnsDto

    @GET("portfolios/positions/")
    suspend fun getPositions(@Query("currency") currency: String): List<PositionDto>
}
