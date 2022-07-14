import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams} from 'react-router-dom'
import {  NewExchange } from './Exchange'


export function GetProducts() {
    const url = 'http://localhost:8081/api/product'
    const [products,setProducts] = useState([])
    
    useEffect(() =>{
        axios.get(url)
        .then(response => {
            setProducts(response.data)
        })
    },[url])
    if(products){return(
       
        <div>
   
            <h1>
                {products.map(prod => (
                     <div key={prod.id} className="card text-center"  style={{width: 18}} >
                     <img className="card-img-top" src="https://new.custamenos.com/images/thumbs/default-image_450.png" alt="Card image cap" ></img>
                     <div className="card-body">
                       <p className="card-title">{prod.name}</p>
                       <h5 className="card-text">{prod.description}</h5>

                       <a href={'http://localhost:3000/product/' + prod.id} className="btn btn-primary">info</a>
                     </div>
                   </div>
                ))}
            </h1>
          
        </div>

    )
    }
    

    return(
        <div>
            <h1>No products</h1>
        </div>
    )
}

export   function CreateProduct(){
    const url = 'http://localhost:8081/api/product'
    const [product,setProduct] = useState({
        name: "",
        description: "",
        price: 0,
        sid: ""
    })
    
 

    function addProduct(e){
        e.preventDefault()
        
        axios.post(url,{
            name: product.name,
            description: product.description,
            price: product.price,
            sid: product.sid
        }).then(res => {
            console.log("product successfully created with the name:"+res.name)
        })
    }

    function handleValues(e){
        const newprod ={...product}
        newprod[e.target.id] = e.target.value
        setProduct(newprod)
    } 


    return(
        <div>
            <form onSubmit={(e) => addProduct(e)}>
                    <label>Product Name</label>
                    <input type="text" id='name' value={product.name}  onChange={(e) => handleValues(e)}></input>
                    <label>Description</label>
                    <input type="text" id='description' value={product.description} onChange={(e) => handleValues(e)}></input>
                    <label>Price</label>
                    <input type="number" id='price'value={product.price} onChange={(e) => handleValues(e)}></input>
                    <label>Seller</label>
                    <input type="text" id='sid' value={product.sid} onChange={(e) => handleValues(e)}></input>
                    <button>Add Product</button>
            </form>
        </div>
    )
}

export function GetProduct() {
 
    const {id} = useParams()
    const url = `http://localhost:8081/api/product/${id}`
    const [product,setProduct] = useState()
    //const [quantity,setQuantity] = useState()

    useEffect(() =>{
        axios.get(url)
        .then(response => {
            setProduct(response.data)
        })
    },[url])
    

    if(product){
        return(
        <div>
            <h1>
                
                    <div key={product.id}>
                    <h2><p>name: {product.name}</p></h2>
                    <p>description: {product.description}</p>
                    <p>price: {product.price}</p>
                    <p>Product Rating: {product.rate}</p>
                    <p>Seller: <a href = {'http://localhost:3000/seller/' + product.sid} >{product.sid}</a></p>
                 
                    <label>Quantity</label>
                    <input type="number" id='quantity' defaultValue={1} ></input>
                    <button onClick={ async () => await NewExchange(product,document.getElementById('quantity').value)}>Buy </button>
                    </div>
                
            </h1>
        </div>

    )
    }
    
    return(
        <div>
            <h1>Product does not exist</h1>
        </div>
    )
}






 
