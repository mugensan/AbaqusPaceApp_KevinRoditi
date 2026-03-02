package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.mapper

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PortfolioEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PositionDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position

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

fun PortfolioEntity.toDomain(positions: List<Position> = emptyList()): Portfolio {
    return Portfolio(
        totalValue = totalValue,
        performancePercentage = performancePercentage,
        cashBalance = cashBalance,
        returnsYtd = returnsYtd,
        positions = positions
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
