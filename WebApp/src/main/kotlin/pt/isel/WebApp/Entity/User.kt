package pt.isel.WebApp.Entity

import java.math.BigInteger
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
 data class User (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id : BigInteger,
  val username : String,
  var EmailAddress : String,
  var Password : String,
  var User_rate : Float?,
  var ProfilePicture : String?,
  var Wallet : String
  )