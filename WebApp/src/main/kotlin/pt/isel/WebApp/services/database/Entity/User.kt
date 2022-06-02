package pt.isel.WebApp.services.database.Entity


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name = "New_User")
 data class User (
  @Id
  val id : UUID = UUID.randomUUID(),
  val username : String,
  var EmailAddress : String,
  var Password : String,
  var User_rate : Float?,
  var ProfilePicture : String?,
  var Wallet : String
 ) {

 constructor() : this(UUID(0L, 0L), "", "", "", 0.0f, "", "")

}

