package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Table(name = "seller")
data class Seller (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : UUID,
    val seller_Name : String?,
    var contry : String?,
    var seller_description : String?,
    var seller_rate : Float?,
    val uid : UUID
)
