package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.api

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WealthApi {

    @GET("portfolios/value/")
    suspend fun getPortfolioValue(
        @Query("currency") currency: String = "USD"
    ): PortfolioValueDto

    @GET("portfolios/performance/ytd/")
    suspend fun getPortfolioPerformance(
        @Query("currency") currency: String = "USD"
    ): PerformanceDto

    @GET("accounts/cash-balance/")
    suspend fun getCashBalance(): CashBalanceDto

    @GET("portfolios/returns/ytd/")
    suspend fun getPortfolioReturns(
        @Query("currency") currency: String = "USD"
    ): PerformanceDto

    @GET("portfolios/positions/")
    suspend fun getPositions(
        @Query("currency") currency: String = "USD"
    ): List<PositionDto>
}
