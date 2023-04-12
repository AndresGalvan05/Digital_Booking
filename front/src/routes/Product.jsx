import React, {useEffect, useState} from 'react'
import { useParams } from 'react-router-dom'

import ProductHeader from '../components/productPage/productHeader/ProductHeader'
import ProductLocation from '../components/productPage/productLocation/ProductLocation'
import ProductDescription from '../components/productPage/productDescription/ProductDescription'
import ProductImages from '../components/productPage/productImages/ProductImages'
import ProductCaracteristics from '../components/productPage/productCaracteristics/ProductCaracteristics'
import ProductReservation from '../components/productPage/productReservations/ProductReservation'
import ProductMap from '../components/productPage/productMap/ProductMap'
import ProductPolitics from '../components/productPage/productPolitics/ProductPolitics'
import ProductShare from '../components/productPage/productShare/ProductShare'

import {PRODUCTOS_ID} from "../staticData/url.js"
import axios from "axios"

const Product = () => {

    let {id} = useParams();
    const [producto , setProducto] = useState(null);

    useEffect(() =>{
        axios.get(PRODUCTOS_ID+id).then((response) =>{
            
            setProducto(response.data)

        }).catch(err => console.log(err))
    } , [])
    return (
        
        <main className=''>
            { producto !== null ? 
                <div>
                    <ProductHeader key={producto.id} category={producto.categoria} title={producto.titulo}/>

                    <ProductLocation location = {producto.ciudad} direccion ={producto.direccion} calification= {producto.promedio} prodId={producto.id}/>

                    <ProductShare />

                    <ProductImages crimg={producto.imagenes} />

                    <ProductDescription description = {producto.descripcion} city ={producto.ciudad.nombre} />

                    <ProductCaracteristics caracteristic ={producto.caracteristicas} />

                    <ProductReservation reservations={producto.reservas} productId = {producto.id}/>

                    <ProductMap location= {producto.ciudad} lat = {producto.latitud} lng = {producto.longitud}/>

                    <ProductPolitics politic ={producto.politicas} />  
                </div>:
                <div></div>    
            }
        </main>
    )
}

export default Product