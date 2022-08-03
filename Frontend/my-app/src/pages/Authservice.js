import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Cookies from 'universal-cookie'
import  { useNavigate} from 'react-router-dom'



export function Authservice(){

const cookie_name = "User_Cookie"

const cookie = Cookies()



}



export function Login(){

    
    
    const navigate = useNavigate()
    const [LoginReq, setLoginReq] = useState({
        email: "",
        password: ""
    })
    const [user, setUser] = useState({
        username: "",
        email: "",
        password: "",
        rate: 0.0,
        profilePicture: "",
        terminated: false
    })

    function handlelogin(e) {
        e.preventDefault()
        const url = `http://localhost:8082/api/auth/login/`
        axios.get(url, { params: {
            email: LoginReq.email,
            password: LoginReq.password
          }}).then(response => {
            if(response.data!=null){
                console.log(response.data)
                setUser(response.data)
            }
        })
    }

    function handleValues(e) {
        const newloginreq = { ...LoginReq }
        newloginreq[e.target.id] = e.target.value
        setLoginReq(newloginreq)
    }


    return (
        <div>
            <form onSubmit={(e) => handlelogin(e)}>
                <label>Email Adress</label>
                <input type="text" id='email' value={LoginReq.email} onChange={(e) => handleValues(e)}></input>
                <label>Password</label>
                <input type="text" id='password' value={LoginReq.password} onChange={(e) => handleValues(e)}></input>
                <button>Login</button>
            </form>
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
        <div>
            <form onSubmit={(e) => handleSignup(e)}>
                <label>Username</label>
                <input type="text" id='username' value={user.username} onChange={(e) => handleValues(e)}></input>
                <label>Email Adress</label>
                <input type="text" id='emailAddress' value={user.emailAddress} onChange={(e) => handleValues(e)}></input>
                <label>Password</label>
                <input type="text" id='password' value={user.password} onChange={(e) => handleValues(e)}></input>
                <button>Signup</button>
            </form>
        </div>
    )

}

