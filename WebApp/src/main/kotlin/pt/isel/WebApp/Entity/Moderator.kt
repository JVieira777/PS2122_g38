package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "moderator")
data class Moderator (
    @Id
    val id : UUID = UUID.randomUUID(),
    val mod_Name : String,
    var mod_description : String?,
    val uid : UUID

    ){
    constructor() : this(UUID(0L, 0L), "", "",UUID(0L, 0L) )
}
