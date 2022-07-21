package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "moderator")
data class Moderator (
    @Id
    val id : UUID = UUID.randomUUID(),
    val Name : String,
    var description : String?,
    var terminated : Boolean = false,
    val uid : UUID

    ){
    constructor() : this(UUID(0L, 0L), "", "",false,UUID(0L, 0L) )
}
