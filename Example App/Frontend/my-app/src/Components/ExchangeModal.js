
import { NewExchange } from '../pages/Exchange'
import React, { useState } from 'react'
import '../Components/ExchangeModal.css'
import { Loading } from '../Utils/Loading'

export function ExchangeModal({product,quantity,setModal}){
   //TODO images
   const [loading,setLoading] = useState(false)
    
   if(loading){
    return(
        <div className='ModalBackground'>
        <div className='Content'>
        <h2><p>name: {product.name}</p></h2>
        <h2><p>description: {product.description}</p></h2>
        <h2><p>Product Rating: {product.rate}</p></h2>
        <h2><p>Totalprice: {(product.price*quantity)}</p></h2>
        <Loading />
        </div>
    
    </div>

    )
   }
    return (
        <div className='ModalBackground'>
        <div className='Content'>
        <h2><p>name: {product.name}</p></h2>
        <h2><p>description: {product.description}</p></h2>
        <h2><p>Product Rating: {product.rate}</p></h2>
        <h2><p>Totalprice: {(product.price*quantity)}</p></h2>
    (<button onClick = {() => {
        setLoading(false)
        setModal(false)
        }}
        >
        Cancel
    </button>)  
    (<button onClick = { () =>  {
        setLoading(true)
        NewExchange(product, quantity)
    }}
        >
        Confirm
    </button>)  
   
    </div>
    
    </div>
    )
    
}