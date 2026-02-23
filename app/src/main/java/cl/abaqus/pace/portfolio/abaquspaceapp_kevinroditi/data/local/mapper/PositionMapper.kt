package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PortfolioValueDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import java.math.BigDecimal

fun PortfolioValueDto.toEntity() : PositionEntity = 
        PositionEntity(
            symbol = symbol,
            name = name,
            quantity = quantity,
            price = price,
            marketValue = marketValue,
            performance = performance.toBigDecimalSafe(),
            isFavorite = false
        )

fun PositionEntity.toDomain():Position = 
    Position(
        symbol,
        name,
        quantity,
        price,
        marketValue,
        performance,
        isFavorite
    )

fun String.toBigDecimalSafe(): BigDecimal {
    return try {
        toBigDecimal()
    } catch (e: NumberFormatException) {
        BigDecimal.ZERO
    }
}