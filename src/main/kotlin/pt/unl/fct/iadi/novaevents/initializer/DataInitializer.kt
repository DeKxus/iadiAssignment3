package pt.unl.fct.iadi.novaevents.initializer

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.model.ClubCategory
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.model.EventType
import pt.unl.fct.iadi.novaevents.repository.ClubRepository
import pt.unl.fct.iadi.novaevents.repository.EventRepository
import pt.unl.fct.iadi.novaevents.repository.EventTypeRepository
import java.time.LocalDate

@Component
class DataInitializer(
    private val clubRepository: ClubRepository,
    private val eventRepository: EventRepository,
    private val eventTypeRepository: EventTypeRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        if (clubRepository.count() > 0) return

        // ----------------------
        // EVENT TYPES (6 total)
        // ----------------------
        val workshop = eventTypeRepository.save(EventType(name = "WORKSHOP"))
        val competition = eventTypeRepository.save(EventType(name = "COMPETITION"))
        val meeting = eventTypeRepository.save(EventType(name = "MEETING"))
        val seminar = eventTypeRepository.save(EventType(name = "SEMINAR"))
        val social = eventTypeRepository.save(EventType(name = "SOCIAL"))
        val hackathon = eventTypeRepository.save(EventType(name = "HACKATHON"))

        // ----------------------
        // CLUBS
        // ----------------------
        val chessClub = clubRepository.save(
            Club(
                name = "Chess Club",
                description = "A club for chess enthusiasts of all levels.",
                category = ClubCategory.ACADEMIC
            )
        )

        val roboticsClub = clubRepository.save(
            Club(
                name = "Robotics Club",
                description = "The Robotics Club is the place to turn ideas into machines",
                category = ClubCategory.TECHNOLOGY
            )
        )

        val photographyClub = clubRepository.save(
            Club(
                name = "Photography Club",
                description = "Capture and share moments through photography.",
                category = ClubCategory.ARTS
            )
        )

        val hikingClub = clubRepository.save(
            Club(
                name = "Hiking & Outdoors Club",
                description = "Explore nature and enjoy outdoor adventures.",
                category = ClubCategory.SPORTS
            )
        )

        val filmClub = clubRepository.save(
            Club(
                name = "Film Society",
                description = "Watch, discuss, and analyze films together.",
                category = ClubCategory.CULTURAL
            )
        )

        // ----------------------
        // EVENTS (at least 1 per club)
        // ----------------------

        // Chess Club (2 events)
        eventRepository.save(
            Event(
                club = chessClub,
                name = "Beginner's Chess Workshop",
                date = LocalDate.now().plusDays(5),
                location = "Room A",
                type = workshop,
                description = "Learn the basics of chess."
            )
        )

        eventRepository.save(
            Event(
                club = chessClub,
                name = "Spring Chess Tournament",
                date = LocalDate.now().plusDays(10),
                location = "Main Hall",
                type = competition,
                description = "Compete with other players."
            )
        )

        // Robotics Club (2 events)
        eventRepository.save(
            Event(
                club = roboticsClub,
                name = "Robotics Intro Session",
                date = LocalDate.now().plusDays(7),
                location = null,
                type = meeting,
                description = null
            )
        )

        eventRepository.save(
            Event(
                club = roboticsClub,
                name = "Mini Hackathon",
                date = LocalDate.now().plusDays(15),
                location = "Lab 2",
                type = hackathon,
                description = "Build a small robot in teams."
            )
        )

        // Photography Club (1 event)
        eventRepository.save(
            Event(
                club = photographyClub,
                name = "City Photo Walk",
                date = LocalDate.now().plusDays(6),
                location = "Downtown",
                type = social,
                description = "Capture urban life together."
            )
        )

        // Hiking Club (1 event)
        eventRepository.save(
            Event(
                club = hikingClub,
                name = "Mountain Trail Hike",
                date = LocalDate.now().plusDays(12),
                location = "Serra de Sintra",
                type = social,
                description = "A full-day hiking experience."
            )
        )

        // Film Club (2 events)
        eventRepository.save(
            Event(
                club = filmClub,
                name = "Classic Movie Night",
                date = LocalDate.now().plusDays(8),
                location = "Auditorium",
                type = social,
                description = "Screening of a classic film."
            )
        )

        eventRepository.save(
            Event(
                club = filmClub,
                name = "Film Analysis Seminar",
                date = LocalDate.now().plusDays(14),
                location = "Room B",
                type = seminar,
                description = "Discussion on film techniques."
            )
        )
    }
}
