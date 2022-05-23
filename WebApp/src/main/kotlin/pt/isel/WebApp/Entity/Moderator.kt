package pt.isel.WebApp.Entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table
data class Moderator (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : BigInteger,
    val mod_Name : String,
    var mod_description : String?,
    val uid : BigInteger

    )
