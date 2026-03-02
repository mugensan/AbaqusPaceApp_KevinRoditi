package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.mapper

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.local.entity.PositionEntity
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.data.remote.dto.PositionDto
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import java.math.BigDecimal

fun PositionDto.toEntity() : PositionEntity = 
        PositionEntity(
            symbol = symbol,
            name = name,
            quantity = quantity,
            price = price,
            marketValue = marketValue,
            performance = performance,
            isFavorite = isFavorite
        )

fun PositionEntity.toDomain(): Position = 
    Position(
        symbol = symbol,
        name = name,
        quantity = quantity,
        price = price,
        marketValue = marketValue,
        performance = performance,
        isFavorite = isFavorite
    )

fun PositionDto.toDomain(): Position = 
    Position(
        symbol = symbol,
        name = name,
        quantity = quantity,
        price = price,
        marketValue = marketValue,
        performance = performance,
        isFavorite = isFavorite
    )
