import React, { useState } from 'react'
import '../Components/NewUserEmailModal.css'
import axios from 'axios'



export function NewUserEmailModal({user,setModal}){

    const url = `http://localhost:8082/api/user/${user.id}`
    const [newEmail, setNewEmail] = useState(" ")
    const [oldEmail, setOldEmail] = useState(" ")


    function UpdateEmail() {

        if(oldEmail === user.emailAddress){
        axios.put(url, {
            id: user.id,
            username: user.username,
            emailAddress: newEmail,
            password: user.password,
            rate: 0.0,
            profilePicture: ""
        }).then(() => {
            setModal(false)
        })
        }else{
            setModal(false)
            
        }
    }

    
    function handleValuesOld(e) {
        setOldEmail(e.target.value)
    }

    function handleValuesNew(e) {
        setNewEmail(e.target.value)
    }
   
    return (
        <div className='ModalBackground3'>
        <div className='Content3'>
        <p>
        <label>Old EmailAddress</label>
        <input type="text" id='oldEmail'  onChange={(e) => handleValuesOld(e)}></input>
        </p>
        <p>
        <label>New EmailAddress</label>
        <input type="text" id='NewEmail'  onChange={(e) => handleValuesNew(e)}></input>
        </p>

        <button onClick = {() => {
        setModal(false)
        }}
        >
        Cancel
        </button>  
        
        <button onClick = { () =>  {
        UpdateEmail()
        }}
        >
        Confirm
        </button> 
   
    </div>
    
    </div>
    )
    
}