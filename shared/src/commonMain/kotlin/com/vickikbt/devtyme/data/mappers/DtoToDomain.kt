package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.data.network.models.*
import com.vickikbt.devtyme.domain.models.*

internal fun SummariesDto.toDomain(): Summaries {
    return Summaries(
        summary = this.summary?.map { it.toDomain() },
        end = this.end,
        start = this.start
    )
}

internal fun SummaryDto.toDomain(): Summary {
    return Summary(
        categories = this.categories?.map { it.toDomain() },
        grandTotal = this.grandTotal?.toDomain(),
        languages = this.languages?.map { it.toDomain() },
        projects = this.projects?.map { it.toDomain() },
        range = null
    )
}

internal fun CategoryDto.toDomain(): Category {
    return Category(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun GrandTotalDto.toDomain(): GrandTotal {
    return GrandTotal(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun LanguageDto.toDomain(): Language {
    return Language(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun ProjectDto.toDomain(): Project {
    return Project(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun RangeDto.toDomain(): Range {
    return Range(
        date = this.date,
        end = this.end,
        start = this.start,
        text = this.text,
        timezone = this.timezone
    )
}
