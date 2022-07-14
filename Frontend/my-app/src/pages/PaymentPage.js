import  {useEffect,useState} from 'react'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import { Header} from '../Components/Header';
import {ethers} from 'ethers'
import {GetBlockchainExchangeInfo} from './Exchange'
import  { useNavigate } from 'react-router-dom'


export function PaymentPage(){
    
    const Blockexchange = GetBlockchainExchangeInfo()
 
    const navigate = useNavigate()
    const { chainId: chainIdHex} = useMoralis()
    const [msgValue,setmsgValue] = useState(0)

    const chainId = parseInt(chainIdHex)
   
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : pay} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"pay",
        msgValue:msgValue,
        params: {_id : Blockexchange.id},
    })
    
    function cancel() {
        return navigate("/product")
    }
    
useEffect(() => {
        //setmsgValue(ethers.utils.formatUnits(15,"ether"))
        pay()
 },[msgValue])
    
    
    return(
        <div>
            <Header />
            
            (<button onClick = {() => {
                setmsgValue(Blockexchange.value)
                }}
                >
                Pay
            </button>)  
            (<button onClick = {async () => {
                await cancel()
                }}
                >
                Cancel
            </button>)    
                
            
        
        </div>
    
    )
}