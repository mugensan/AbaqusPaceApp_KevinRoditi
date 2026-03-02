package com.example.wealthapp.presentation.portfolio

import com.example.wealthapp.domain.model.Portfolio
import com.example.wealthapp.domain.model.Position

/**
 * UI State for the Portfolio screen.
 */
data class PortfolioState(
    val isLoading: Boolean = false,
    val portfolio: Portfolio? = null,
    val positions: List<Position> = emptyList(),
    val error: String? = null
)
