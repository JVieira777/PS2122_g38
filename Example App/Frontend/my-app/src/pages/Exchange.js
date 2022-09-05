import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'
import { GetSellerByid } from './Seller'
import { End_date } from '../Utils/End_date'
import Cookies from 'universal-cookie'


export  function  NewExchange(product,quant){
    var id = 0;
    GetSellerByid(product.sid).then(response =>
    NewBlockchainExchange(response.data.wallet,product.price*quant,99)
   ).then( response => {
    console.log(response.data)
     NewExchangeDB(product,quant,id = response.data.exchange_id)}
   ).then ( ()=>{
    //navigate( `payment/${id}`)
    window.location.replace(`payment/${id}`)
   }
   )

}
 


async function NewExchangeDB(product,quant,id){
    
    const cookie_name = "User_Cookie"

    const cookie = new Cookies()
    
    const url = 'http://localhost:8082/api/exchange'
    const cookie_values = cookie.get(cookie_name)
    const date = End_date()
    console.log(date)
    await axios.post(url,{
        id :id,
        client_id: cookie_values.uid,
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


export async function NewBlockchainExchange(seller_wallet,value,date){
        const url = 'http://localhost:8081/ExchangeManager/new'
        return await axios.put(url,{
            destination:seller_wallet,
            value: value,
            expiration_date:date 
          }, { headers: {'token' : 'f65d8b4a-8bfa-44d5-b985-cf07ab7fe4ee', 'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': '*',
          'Access-Control-Allow-Credentials': 'true'}  } )
}

    
export async function GetBlockchainExchange(id){
       
        const url = `http://localhost:8081/ExchangeManager/exchange/${id}/info`
        const [exchange,setExchange] = useState()
      
    
        useEffect(() =>{
            axios.get(url,{ headers: {'token': 'f65d8b4a-8bfa-44d5-b985-cf07ab7fe4ee'} })
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
        const url = `http://localhost:8081/ExchangeManager/exchange/${id}/info`
      return axios.get(url,{ headers: {'token' : 'f65d8b4a-8bfa-44d5-b985-cf07ab7fe4ee'} })
     
}


