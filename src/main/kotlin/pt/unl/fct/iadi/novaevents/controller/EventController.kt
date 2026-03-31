package pt.unl.fct.iadi.novaevents.controller

import pt.unl.fct.iadi.novaevents.controller.dto.EventForm
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.service.ClubService
import pt.unl.fct.iadi.novaevents.service.EventService
import jakarta.validation.Valid
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import pt.unl.fct.iadi.novaevents.service.EventTypeService


@Controller
class EventController(
    private val eventService: EventService,
    private val clubService: ClubService,
    private val eventTypeService: EventTypeService,
) {

    @GetMapping("/events")
    fun listEvents(model: Model): String {
        model.addAttribute("events", eventService.findAll())
        return "events/list"
    }

    @GetMapping("/clubs/{clubId}/events/{eventId}")
    fun viewEventDetails( @PathVariable clubId: Long, @PathVariable eventId: Long, model: Model): String {
        val event = eventService.findById(eventId)
        val club = clubService.findById(clubId)
        model.addAttribute("event", event)
        model.addAttribute("club", club)
        return "events/detail"
    }

    @GetMapping("/clubs/{clubId}/events/new")
    fun createEventForm(@PathVariable clubId: Long, model: Model): String {
        model.addAttribute("eventForm", EventForm())
        model.addAttribute("clubId", clubId)
        model.addAttribute("eventTypes", eventTypeService.findAll())
        return "events/form"
    }

    @PostMapping("/clubs/{clubId}/events")
    fun createEvent(
        @PathVariable clubId: Long,
        @Valid @ModelAttribute eventForm: EventForm,
        bindingResult: BindingResult,
        model: Model
    ): String {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clubId", clubId)
            model.addAttribute("eventTypes", eventTypeService.findAll())
            return "events/form"
        }

        val club = clubService.findById(clubId)

        val event = Event(
            id = 0,
            club = club,
            name = eventForm.name!!,
            date = eventForm.date!!,
            location = eventForm.location,
            type = eventTypeService.findById(eventForm.type!!),
            description = eventForm.description
        )

        return try {
            val created = eventService.create(event)
            "redirect:/clubs/$clubId/events/${created.id}"
        } catch (e: IllegalArgumentException) {
            model.addAttribute("errorMessage", e.message)
            model.addAttribute("clubId", clubId)
            model.addAttribute("eventForm", eventForm)
            "events/form"
        }
    }


    @GetMapping("/clubs/{clubId}/events/{eventId}/edit")
    fun updateEventForm(
        @PathVariable clubId: Long,
        @PathVariable eventId: Long,
        model: Model
    ): String {
        val event = eventService.findById(eventId)

        val form = EventForm(
            name = event.name,
            date = event.date,
            location = event.location,
            type = event.type?.id,
            description = event.description
        )

        model.addAttribute("eventForm", form)
        model.addAttribute("clubId", clubId)
        model.addAttribute("eventId", eventId)
        model.addAttribute("eventTypes", eventTypeService.findAll())

        return "events/form"
    }

    @PostMapping("/clubs/{clubId}/events/{eventId}")
    fun updateEvent(
        @PathVariable clubId: Long,
        @PathVariable eventId: Long,
        @Valid @ModelAttribute eventForm: EventForm,
        bindingResult: BindingResult,
        model: Model
    ): String {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clubId", clubId)
            model.addAttribute("eventId", eventId)
            model.addAttribute("eventTypes", eventTypeService.findAll())
            return "events/form"
        }

        val club = clubService.findById(clubId)

        val updated = Event(
            id = eventId,
            club = club,
            name = eventForm.name!!,
            date = eventForm.date!!,
            location = eventForm.location,
            type = eventTypeService.findById(eventForm.type!!),
            description = eventForm.description
        )

        eventService.update(eventId, updated)

        return "redirect:/clubs/$clubId/events/$eventId"
    }

    @GetMapping("/clubs/{clubId}/events/{eventId}/delete")
    fun deleteConfirm(
        @PathVariable clubId: Long,
        @PathVariable eventId: Long,
        model: Model
    ): String {

        val event = eventService.findById(eventId)

        model.addAttribute("event", event)
        model.addAttribute("clubId", clubId)

        return "events/delete"
    }

    @PostMapping("/clubs/{clubId}/events/{eventId}/delete")
    fun deleteEvent(
        @PathVariable clubId: Long,
        @PathVariable eventId: Long
    ): String {
        eventService.delete(eventId)
        return "redirect:/clubs/$clubId"
    }
}