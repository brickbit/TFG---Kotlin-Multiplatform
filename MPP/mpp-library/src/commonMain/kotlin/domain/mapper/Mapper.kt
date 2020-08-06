package domain.mapper

import data.model.MonumentMainItemData
import domain.model.MonumentMainItemDomain

fun MonumentMainItemData.toDomain() = MonumentMainItemDomain(id,title,geocoordinates)