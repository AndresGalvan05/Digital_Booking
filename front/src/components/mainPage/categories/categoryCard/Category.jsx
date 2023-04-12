import React from 'react'
import './Category.css'
import axios from "axios"
import {BUSCARDOR_PRODUCTOS_POR_CATEGORIA} from "../../../../staticData/url.js"

const Category = (props) => {
    function buscarPorCategoria() {
        axios.get(BUSCARDOR_PRODUCTOS_POR_CATEGORIA+props.id).then((response) =>{
            props.setProductos(response.data);
            props.setTitle("Recomendaciones de " +response.data[0].categoria.titulo)
            
        }).catch(err => console.log(err))
    }


    return (
    <div onClick={buscarPorCategoria} className='categoryContainer' key={props.id}>
        <img className='imgCategories' src={props.image} alt="img-categorias" />
        <div className='infoSection'>
            <div className='categoryText'>{props.category}</div>
            <div className='categoryInfo'>{props.amount} {props.type}</div>
        </div>

    </div>
    )
}

export default Category