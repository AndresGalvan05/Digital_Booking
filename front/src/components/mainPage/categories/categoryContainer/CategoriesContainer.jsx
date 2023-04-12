import React , {useEffect, useState} from 'react'
import './CategoriesContainer.css'
import Category from '../categoryCard/Category'
import {CATEGORY} from "../../../../staticData/url.js"
import axios from "axios"

const CategoriesContainer = (props) => {

    const [categories, setCategories] = useState([]);

    useEffect(() =>{
        axios.get(CATEGORY).then((response) =>{
            setCategories(response.data)
        }).catch(err => console.log(err))
    } , [])
    return (
        <div className="categoryBlock container">
            <section className='section-title'>Buscar por tipo de alojamiento</section>
            <div className="flexWrapper">
                {
                categories.map( category=>

                                <Category 
                                    key={category.id}
                                    setProductos = {props.setProductos}
                                    setTitle ={props.setTitle}
                                    image={category.urlImagen}
                                    category={category.titulo}
                                    // amount={Math.floor((Math.random()*10000))}
                                    amount = {category.productos}
                                    type={category.descripcion}
                                    id = {category.id}
                                />                        
                )
                }
            </div>
        </div>
    )
}
export default CategoriesContainer