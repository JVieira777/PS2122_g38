import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams, useHistory } from 'react-router-dom'

export   function  NewExchange(product,quant){
    const history = useHistory()
    const url = 'http://localhost:8081/api/exchange'
      axios.post(url,{
            client_id: "188b70fa-9f66-4dfe-b712-6696859e9ffa",
            seller_id: "9d5a6288-c4ba-4874-a546-c1d4bc8cafde",
            pid: product.id,
            value: product.price*quant,
            quantity: quant
        })
        .then(response => {
            alert(response)
            return history.push(`payment/${response.data.id}`)
            
        })
   
            
        

}

export function GetExchangesFromUser(){
    const {id} = useParams()
    const url = `http://localhost:8081/api/exchange/user/${id}`
    const [exchanges,setExchanges] = useState()
  

    useEffect(() =>{
        axios.get(url)
        .then(response => {
            setExchanges(response.data)
        })
    },[url])
 

    if(exchanges){
        return(
            <div>
            <h1>
                {exchanges.map(ex => (
                    <div key={ex.id}>
                    <p>Product id: {ex.pid}</p>
                    <p>value: {ex.value}</p>
                    <p>quantity: {ex.quantity}</p>
                    <p>totalprice: {ex.quantity*ex.value}</p>
                    <p>end date: {ex.end_Date}</p>
                    </div>

                  //idprod nomeprod price quantity totalprice=quantity*price button comfirm cancel
                ))}
            </h1>
        </div>

    )}
}

    export function GetExchange(){
        const {id} = useParams()
        const url = `http://localhost:8081/api/exchange/${id}`
        const [exchange,setExchange] = useState()
      
    
        useEffect(() =>{
            axios.get(url)
            .then(response => {
                setExchange(response.data)
            })
        },[url])
     
    
        if(exchange){
            return(
                <div>
                <h1>
                    {
                        <div key={exchange.id}>
                        <p>Product id: {exchange.pid}</p>
                        <p>value: {exchange.value}</p>
                        <p>quantity: {exchange.quantity}</p>
                        <p>totalprice: {exchange.quantity*ex.value}</p>
                        <p>end date: {exchange.end_Date}</p>
                        </div>
    
                      //idprod nomeprod price quantity totalprice=quantity*price button comfirm cancel
                    }
                </h1>
            </div>
    
        )
    }

}