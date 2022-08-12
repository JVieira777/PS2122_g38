import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams, useNavigate } from 'react-router-dom'
import { GetSellerByid } from './Seller'
import { End_date } from '../Utils/End_date'



export  function  NewExchange(product,quant){
    const navigate = useNavigate()
    var id = 0;
    GetSellerByid(product.sid).then(response =>

   NewBlockchainExchange(response.data.wallet,product.price*quant,99999)
   ).then( response =>
     NewExchangeDB(product,quant,id = response.data.exchange_id)
   ).then ( ()=>
     navigate( `payment/${id}`)
   )
   
           
}
 


async function NewExchangeDB(product,quant,id){
    const url = 'http://localhost:8082/api/exchange'
    const date = End_date()
    console.log(date)
    axios.post(url,{
        id :id,
        client_id: "e29b23c9-cde5-4167-abc9-7a3266a5991a",
        seller_id: product.sid,
        pid: product.id,
        value: product.price*quant,
        quantity: quant,
        end_Date: date
    })
}





export async function GetExchangesFromUser(id){
    const url = `http://localhost:8082/api/exchange/User/${id}`
    return axios.get(url)
}

export async function GetExchangesFromSeller(id){
    const url = `http://localhost:8082/api/exchange/Seller/${id}`
    return axios.get(url)
}

export async function DeleteExchange(id){
    const url = `http://localhost:8082/api/exchange/${id}`
    return axios.delete(url)
}

export async function CompleteExchange(id){
    const url = `http://localhost:8082/api/exchange/complete/${id}`
    return axios.put(url)
}


export async function GetExchange(){
        const {id} = useParams()
        const url = `http://localhost:8082/api/exchange/${id}`
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
                    }
                </h1>
            </div>
    
        )
        }
}


export function NewBlockchainExchange(seller_wallet,value,date){
        const url = 'http://localhost:8081/api/ExchangeManager/new'
        return axios.put(url,{
            destination:seller_wallet,
            value: value,
            expiration_date:date 
          })
}

    
export async function GetBlockchainExchange(id){
       
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

export async function GetBlockchainExchangeInfo(id){
       const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/info`
            return axios.get(url)
}


