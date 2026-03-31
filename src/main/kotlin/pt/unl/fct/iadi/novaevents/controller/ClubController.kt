package pt.unl.fct.iadi.novaevents.controller

import org.springframework.ui.Model
import pt.unl.fct.iadi.novaevents.service.ClubService
import pt.unl.fct.iadi.novaevents.service.EventService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/clubs")
class ClubController(
    private val clubService: ClubService,
    private val eventService: EventService
) {
    @GetMapping
    fun listClubs(model: Model): String {
        model.addAttribute("clubs", clubService.findAllWithEventCount())
        return "clubs/list"
    }

    @GetMapping("/{id}")
    fun clubDetail(@PathVariable id: Long, model: Model): String {
        val club = clubService.findById(id)
        val events = eventService.findByClubId(id)

        model.addAttribute("club", club)
        model.addAttribute("events", events)
        return "clubs/detail"
    }
}