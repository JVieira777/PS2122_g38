package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "new_user")
 data class User (
 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id : UUID,
 val username : String,
 var EmailAddress : String,
 var Password : String,
 var User_rate : Float?,
 var ProfilePicture : String?,
 var Wallet : String
  )