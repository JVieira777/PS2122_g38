package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.UUID
import javax.persistence.*



@Table(name = "image")
data class Image (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : UUID,
    var image_Name : String?,
    val image_Path : String,
    val pid : BigInteger,
    )
