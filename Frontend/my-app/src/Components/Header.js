import { useMoralis } from "react-moralis"
import React, { useEffect } from 'react'
import '../Components/Header.css'



export function Header() {

    //To do CheckSeller
    const { enableWeb3, account, isWeb3Enable, deactivateWeb3, Moralis, isWeb3EnableLoading } = useMoralis()
    


    useEffect(() => {
        if (isWeb3Enable) return
        if (typeof window !== undefined) {
            if (window.localStorage.getItem("connected")) {
                enableWeb3()
            }
        }
    }, [isWeb3Enable])


    useEffect(() => {
        Moralis.onAccountChanged((account) => {
            if (account == null) {
                window.localStorage.removeItem("connected")
                deactivateWeb3()
            }
        })
    }, [])


    return (
        
        <div className="metamask">
            {account ?
                (<div>

                </div>) :
                (<button className="custom-btn4 metaMaskButton" onClick={
                    async () => {
                        await enableWeb3()
                        if (typeof window !== undefined) window.localStorage.setItem("connected", "true")
                    }
                }
                    disabled={isWeb3EnableLoading}
                >
                    Connect Wallet
                </button>)
            }

              

               
                
        </div>

    )

}