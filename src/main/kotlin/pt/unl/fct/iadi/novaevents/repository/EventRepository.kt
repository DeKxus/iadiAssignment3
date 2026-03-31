package pt.unl.fct.iadi.novaevents.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.iadi.novaevents.model.Event

interface EventRepository : JpaRepository<Event, Long> {

    fun existsByNameIgnoreCase(name: String): Boolean

    fun existsByNameIgnoreCaseAndIdNot(name: String, id: Long): Boolean

    fun findByClubId(clubId: Long): List<Event>

    fun findByTypeId(typeId: Long): List<Event>

    //fun findByClubIdAndTypeId(clubId: Long, typeId: Long): List<Event>
}