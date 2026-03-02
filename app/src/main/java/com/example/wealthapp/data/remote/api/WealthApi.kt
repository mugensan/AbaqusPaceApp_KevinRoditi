package com.example.wealthapp.data.remote.api

import com.example.wealthapp.data.remote.dto.*
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
    ): PerformanceDto // Assuming returns also returns a percentage/value similar to performance

    @GET("portfolios/positions/")
    suspend fun getPositions(
        @Query("currency") currency: String = "USD"
    ): List<PositionDto>
}
