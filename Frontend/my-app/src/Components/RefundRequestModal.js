
import React, { useState } from 'react'
import '../Components/RefundRequestModal.css'
import axios from 'axios'


export function RefundRequestModal({exchange,setModal}){

    const url = 'http://localhost:8082/api/RefundRequest'
    const [description, setDescription] = useState(" ")



    function addRequest() {
        axios.post(url, {
            client_id: exchange.client_id,
            exchange_id: exchange.id,
            description: description.toString()
        }).then(() => {
            setModal(false)
        })
    }

    
    function handleValues(e) {
        setDescription(e.target.value)
    }
   
    return (
        <div className='ModalBackground'>
        <div className='Content'>
        <h2><p>id: {exchange.id}</p></h2>

        <label>Description</label>
            <input type="text" id='description'  onChange={(e) => handleValues(e)}></input>

    (<button onClick = {() => {
        setModal(false)
        }}
        >
        Cancel
    </button>)  
    (<button onClick = { () =>  {
        addRequest()
    }}
        >
        Confirm
    </button>)  
   
    </div>
    
    </div>
    )
    
}