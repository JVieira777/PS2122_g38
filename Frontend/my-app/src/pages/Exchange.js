import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams, Navigate } from 'react-router-dom'
import { GetSellerByid } from './Seller'
import { End_date } from '../Utils/End_date'
import { resolve } from 'url'

export async function  NewExchange(product,quant){
    const url = 'http://localhost:8081/api/exchange'
    NewBlockchainExchange(product.sid,product.price*quant,99999).then(
        response => console.log(response)
    )
    /*.then(response =>
            axios.post(url,{
            id : response.exchange_id,
            client_id: "188b70fa-9f66-4dfe-b712-6696859e9ffa",
            seller_id: product.sid,
            pid: product.id,
            value: product.price*quant,
            quantity: quant,
            end_Date: "99999"
        }))
        .then(response => {
            alert(response)
            return <Navigate to={`payment/${response}`} replace={true} />
            
    })*/
}

export async function GetExchangesFromUser(){
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

    export async function GetExchange(){
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
                        <p>totalprice: {exchange.quantity*exchange.value}</p>
                        <p>end date: {exchange.end_Date}</p>
                        </div>
    
                      //idprod nomeprod price quantity totalprice=quantity*price button comfirm cancel
                    }
                </h1>
            </div>
    
        )
        }
    }


    export async function NewBlockchainExchange(seller_id,value,date){
        const url = 'http://localhost:8081/api/ExchangeManager/new'
        axios.put(url,{
            destination:"0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590",
            value: value,
            expiration_date:date 
          })
          .then(response => {
                console.log("resposta =",response.data.exchange_id)
              //return <Navigate to={`payment/${response.data.id}`} replace={true} />
              
      })

    }

    
    export async function GetBlockchainExchange(){
        const {id} = useParams()
        const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/info`
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
                        <p>Price: {exchange.price}</p>
                        <p>destination: {exchange.destination}</p>
                        <p>end date: {exchange.end_Date}</p>
                        </div>
                    }
                </h1>
            </div>
        )}
    }

    export async function GetBlockchainExchangeInfo(){
        const {id} = useParams()
        const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/info`
            axios.get(url)
            .then(response => {
               return response.data
            })
        
     
    }