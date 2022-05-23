package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Table(name = "moderator")
data class Moderator (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : UUID,
    val mod_Name : String,
    var mod_description : String?,
    val uid : UUID

    )
