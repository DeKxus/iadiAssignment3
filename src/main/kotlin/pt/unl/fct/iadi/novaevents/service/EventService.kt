package pt.unl.fct.iadi.novaevents.service

import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.model.EventType
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class EventService {

    private val events = mutableListOf<Event>()
    private var nextId = 3L

    init {
        // Required seeded events
        events.add(
            Event(
                1,
                1,
                "Beginner's Chess Workshop",
                LocalDate.now().plusDays(5),
                "Room A",
                EventType.WORKSHOP,
                "Learn the basics of chess."
            )
        )

        events.add(
            Event(
                2,
                1,
                "Spring Chess Tournament",
                LocalDate.now().plusDays(10),
                "Main Hall",
                EventType.COMPETITION,
                "Compete with other players."
            )
        )

        // Add at least one event per club
        events.add(
            Event(
                3,
                2,
                "Robotics Intro Session",
                LocalDate.now().plusDays(7),
                null,
                EventType.MEETING,
                null
            )
        )
    }

    fun findAll(): List<Event> = events

    fun findById(id: Long): Event =
        events.find { it.id == id }
            ?: throw NoSuchElementException("Event not found")

    fun findByClubId(clubId: Long): List<Event> =
        events.filter { it.clubId == clubId }

    fun create(event: Event): Event {
        validateUniqueName(event.name, null)

        val newEvent = event.copy(id = nextId++)
        events.add(newEvent)
        return newEvent
    }

    fun update(id: Long, updated: Event): Event {
        val existing = findById(id)

        validateUniqueName(updated.name, id)

        existing.name = updated.name
        existing.date = updated.date
        existing.location = updated.location
        existing.type = updated.type
        existing.description = updated.description

        return existing
    }

    fun delete(id: Long) {
        val event = findById(id)
        events.remove(event)
    }

    private fun validateUniqueName(name: String, currentId: Long?) {
        val exists = events.any {
            it.name.equals(name, ignoreCase = true) &&
                    it.id != currentId
        }

        if (exists) {
            throw IllegalArgumentException("An event with this name already exists")
        }
    }
}