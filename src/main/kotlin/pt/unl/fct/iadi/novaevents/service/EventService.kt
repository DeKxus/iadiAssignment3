package pt.unl.fct.iadi.novaevents.service

import pt.unl.fct.iadi.novaevents.model.Event
import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.repository.EventRepository


@Service
class EventService( val eventRepository: EventRepository) {

    fun findAll(): List<Event> = eventRepository.findAll()

    fun findById(id: Long): Event =
        eventRepository.findById(id).orElseThrow{ NoSuchElementException("Event not found") }

    fun findByClubId(clubId: Long): List<Event> =
        eventRepository.findByClubId(clubId)

    fun create(event: Event): Event {
        validateUniqueName(event.name, null)
        return eventRepository.save(event)
    }

    fun update(id: Long, updated: Event): Event {
        val existing = findById(id)

        validateUniqueName(updated.name, id)

        existing.name = updated.name
        existing.date = updated.date
        existing.location = updated.location
        existing.type = updated.type
        existing.description = updated.description

        return eventRepository.save(existing)
    }

    fun delete(id: Long) {
        val event = findById(id)
        eventRepository.delete(event)
    }

    private fun validateUniqueName(name: String, currentId: Long?) {

        val exists = if (currentId != null) {
            eventRepository.existsByNameIgnoreCaseAndIdNot(name, currentId)
        } else{
            eventRepository.existsByNameIgnoreCase(name)
        }

        if(exists){
            throw IllegalArgumentException("An event with this name already exists")
        }
    }
}