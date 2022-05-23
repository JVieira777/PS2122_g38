package pt.isel.WebApp.Entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table
data class Image (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : BigInteger,
    var image_Name : String?,
    val image_Path : String,
    val pid : BigInteger,
    )
