package pt.unl.fct.iadi.novaevents.service

import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.model.ClubCategory
import org.springframework.stereotype.Service

@Service
class ClubService {

    private val clubs = listOf(
        Club(
            1,
            "Chess Club",
            "A club for chess enthusiasts of all levels.",
            ClubCategory.ACADEMIC
        ),
        Club(
            2,
            "Robotics Club",
            "The Robotics Club is the place to turn ideas into machines",
            ClubCategory.TECHNOLOGY
        ),
        Club(
            3,
            "Photography Club",
            "Capture and share moments through photography.",
            ClubCategory.ARTS
        ),
        Club(
            4,
            "Hiking & Outdoors Club",
            "Explore nature and enjoy outdoor adventures.",
            ClubCategory.SPORTS
        ),
        Club(
            5,
            "Film Society",
            "Watch, discuss, and analyze films together.",
            ClubCategory.CULTURAL
        )
    )

    fun findAll(): List<Club> = clubs

    fun findById(id: Long): Club =
        clubs.find { it.id == id }
            ?: throw NoSuchElementException("Club not found")
}