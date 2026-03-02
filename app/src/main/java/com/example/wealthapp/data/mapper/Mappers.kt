package com.example.wealthapp.data.mapper

import com.example.wealthapp.data.local.entity.PortfolioEntity
import com.example.wealthapp.data.local.entity.PositionEntity
import com.example.wealthapp.data.remote.dto.PositionDto
import com.example.wealthapp.domain.model.Portfolio
import com.example.wealthapp.domain.model.Position

// --- Remote (DTO) to Entity --- //

fun PositionDto.toEntity(): PositionEntity {
    return PositionEntity(
        symbol = symbol,
        name = name,
        quantity = quantity,
        price = price,
        marketValue = marketValue,
        performance = performance,
        isFavorite = isFavorite
    )
}

// --- Entity to Domain --- //

fun PositionEntity.toDomain(): Position {
    return Position(
        symbol = symbol,
        name = name,
        quantity = quantity,
        price = price,
        marketValue = marketValue,
        performance = performance,
        isFavorite = isFavorite
    )
}

fun PortfolioEntity.toDomain(): Portfolio {
    return Portfolio(
        totalValue = totalValue,
        performancePercentage = performancePercentage,
        cashBalance = cashBalance,
        returnsYtd = returnsYtd
    )
}

// --- Domain to Entity --- //

fun Portfolio.toEntity(): PortfolioEntity {
    return PortfolioEntity(
        totalValue = totalValue,
        performancePercentage = performancePercentage,
        cashBalance = cashBalance,
        returnsYtd = returnsYtd
    )
}
