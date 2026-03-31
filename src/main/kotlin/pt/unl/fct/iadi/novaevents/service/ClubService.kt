package pt.unl.fct.iadi.novaevents.service

import pt.unl.fct.iadi.novaevents.model.Club
import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.controller.dto.ClubWithEventCount
import pt.unl.fct.iadi.novaevents.repository.ClubRepository

@Service
class ClubService(val clubRepository: ClubRepository, repository: ClubRepository) {

    fun findAll(): List<Club> = clubRepository.findAll()

    fun findAllWithEventCount(): List<ClubWithEventCount> = clubRepository.findAllWithEventCount()

    fun findById(id: Long): Club =
        clubRepository.findById(id).orElseThrow { NoSuchElementException("Club not found") }
}