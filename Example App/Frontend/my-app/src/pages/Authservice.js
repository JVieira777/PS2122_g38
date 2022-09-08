import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Cookies from 'universal-cookie'
import  { useNavigate} from 'react-router-dom'
import '../pages/Authservice.css'


export function Authservice(){






}



export function Login(){
    const cookie_name = "User_Cookie"

    const cookie = new Cookies()
   
   
    
    const navigate = useNavigate()
    const [LoginReq, setLoginReq] = useState({
        email: "",
        password: ""
    })
    
  

   

    function handlelogin(e) {
        e.preventDefault()
        const url = `http://localhost:8082/api/auth/login/`
        axios.get(url, { params: {
            email: LoginReq.email,
            password: LoginReq.password
          }}).then(response => {
            if(response.data!=null){
                cookie.set(cookie_name,response.data)
            }
            navigate("/")
        })
    }

    function handleValues(e) {
        const newloginreq = { ...LoginReq }
        newloginreq[e.target.id] = e.target.value
        setLoginReq(newloginreq)
    }


    return (
        <div className="login-page">
            <div className="form">
            <form  className="login-form" onSubmit={(e) => handlelogin(e)}>
                <label>Email Adress</label>
                <input type="text"  placeholder="email address" id='email' value={LoginReq.email} onChange={(e) => handleValues(e)}></input>
                <label>Password</label>
                <input type="text" placeholder="password" id='password' value={LoginReq.password} onChange={(e) => handleValues(e)}></input>
                <button>Login</button>
                <p className="message">Not registered? <a href="/signup">Create an account</a></p>
            </form>
        </div>
    </div>
    )
}









export function Signup() {
    const url = 'http://localhost:8082/api/user'
    
    const navigate = useNavigate()
    const [user, setUser] = useState({
        username: "",
        emailAddress: "",
        password: "",
    })

    function handleSignup(e) {
        e.preventDefault()
        console.log(user.email)
        axios.post(url, {
            username: user.username,
            emailAddress: user.emailAddress,
            password: user.password,
            rate: 0.0,
            profilePicture: ""
        }).then(res => {
            if(res.data === "user was successfully created"){
            return navigate("/login")
            }
            else{
                return navigate("/signup")
            }
        })
    }

    function handleValues(e) {
        const newuser = { ...user }
        newuser[e.target.id] = e.target.value
        setUser(newuser)
    }


    return (
        <div className="login-page">
            <div className="form">
            <form  className="login-form" onSubmit={(e) => handleSignup(e)}>
                <label>Username</label>
                <input type="text" id='username' placeholder="Username" value={user.username} onChange={(e) => handleValues(e)}></input>
                <label>Email Adress</label>
                <input type="text" id='emailAddress' placeholder="email address" value={user.emailAddress} onChange={(e) => handleValues(e)}></input>
                <label>Password</label>
                <input type="text" id='password' placeholder="password" value={user.password} onChange={(e) => handleValues(e)}></input>
                <button>Signup</button>
                <p class="message">Already registered? <a href="/login">Sign In</a></p>
            </form>
        </div>
    </div>
    )

}


export function Logout() {
    
    const navigate = useNavigate()
    
    const cookie_name = "User_Cookie"

    const cookie = new Cookies()

    const cookie_values = cookie.get(cookie_name)

    console.log(cookie_values.tid)

    const url = `http://localhost:8082/api/auth/logout/${cookie_values.tid}`

    useEffect(() => {
        axios.delete(url,{ headers: {'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': 'true'}  } )
            .then(() => {
               cookie.remove(cookie_name)
                navigate("/")
            })
    }, [])
   
   


   
}

