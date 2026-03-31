package pt.unl.fct.iadi.novaevents.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.model.EventType
import pt.unl.fct.iadi.novaevents.repository.EventTypeRepository

@Service
class EventTypeService (val eventTypeRepository: EventTypeRepository) {

    fun findAll(): List<EventType> = eventTypeRepository.findAll()

    fun findById(id: Long): EventType =  eventTypeRepository.findById(id).orElseThrow { NoSuchElementException("Event Type not found") }
}