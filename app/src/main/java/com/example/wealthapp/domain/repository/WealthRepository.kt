package com.example.wealthapp.domain.repository

import com.example.wealthapp.core.network.Resource
import com.example.wealthapp.domain.model.Portfolio
import com.example.wealthapp.domain.model.Position
import kotlinx.coroutines.flow.Flow

/**
 * Main repository interface for accessing wealth-related data.
 * Adheres to the Dependency Inversion Principle.
 */
interface WealthRepository {

    /**
     * Observes the portfolio summary from the local cache.
     */
    fun getPortfolio(): Flow<Resource<Portfolio>>

    /**
     * Observes the list of positions from the local cache.
     */
    fun getPositions(): Flow<Resource<List<Position>>>

    /**
     * Refreshes the local cache from the remote API.
     */
    suspend fun refreshPortfolio()
    
    /**
     * Searches for positions matching the query.
     */
    fun searchPositions(query: String): Flow<List<Position>>
}
