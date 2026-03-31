package pt.unl.fct.iadi.novaevents.controller.dto

import pt.unl.fct.iadi.novaevents.model.ClubCategory

data class ClubWithEventCount(
    val id: Long,
    val name: String,
    val description: String,
    val category: ClubCategory,
    val eventCount: Long
)