package pt.isel.WebApp.lib.services.database.Entities

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "client")
data class Client( @Id val id: UUID, val name : String ){

    constructor():this(UUID(0,0), "")

}