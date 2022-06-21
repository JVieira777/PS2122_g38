import React, {useEffect,useState} from 'react'
import axios from 'axios'


function Product() {
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
                    <div key={prod.id}>
                        <h1>
                            {prod.name}
                        </h1>
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

export default  function CreateProduct(){
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

 export { Product}

