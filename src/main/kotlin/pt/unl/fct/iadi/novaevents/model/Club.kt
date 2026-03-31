package pt.unl.fct.iadi.novaevents.model

import jakarta.persistence.*

@Entity
@Table(name = "clubs")
open class Club(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String = "",

    @Column(length = 2000)
    var description: String = "",

    @Enumerated(EnumType.STRING)
    var category: ClubCategory = ClubCategory.ACADEMIC,

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    var events: MutableList<Event> = mutableListOf()
)