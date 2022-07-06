import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from './abi.json'
import contractAddresses from './contractAddress.json' 
import { Header} from './Header';
import {ethers} from 'ethers'
import { GetExchange} from './Exchange'
import  { Navigate } from 'react-router-dom'


export function PaymentPage(){
    const {isWeb3Enable, chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState()

    const chainId = parseInt(chainIdHex)
   
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : pay} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"pay",
        msgValue:15,
        params: {_id : 5},
    })
    
    function cancel() {
        return <Navigate to='/product' replace={true} />
    }
    
useEffect(() => {
    if(isWeb3Enable){
        //setmsgValue(ethers.utils.formatUnits(15,"ether"))
        //setmsgValue(exchange.value)
        //setmsgValue(15)
        console.log(msgValue)
    }
   
},[])
    
    
    return(
        <div>
            <Header />
            
            (<button onClick = {async () => {
                await pay()
                }}
                >
                Pay
                </button>)  
                
                
            
        
        </div>
    
    )
    /*(<button onClick = {async () => {
        await cancel()
        }}>
        cancel
        </button>)*/

}