import React, { useState, useEffect, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import CreateProductHeader from "../components/createProductPage/createProductHeader/CreateProductHeader";
import "../components/createProductPage/CreateProduct.css";
import CreatePropiedad from "../components/createProductPage/createPropiedad/createPropiedad";
import CreateAtributte from "../components/createProductPage/createAtributte/CreateAtributte";
import CreatePolitics from "../components/createProductPage/createPolitics/CreatePolitics";
import CreateImages from "../components/createProductPage/createImages/CreateImages";
import { CATEGORY, BUSCARDOR_CIUDAD, CREAR_PRODUCTO, CARACTERISTICAS} from "../staticData/url";
import { useContextProvider } from "../context/UserContext"
import axios from "axios"

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSquarePlus} from '@fortawesome/free-solid-svg-icons';




const CreateProduct = () =>{
    const navigate = useNavigate()
    const logState = useContextProvider().logState

    const [selectedOption, setSelectedOption] = useState({id: null, nombre: '',pais: null})
    const [selectedOptionCity, setSelectedOptionCity] = useState({id: null, nombre: '',pais: null})
    const [selectedOptionCaracteristic, setSelectedOptionCaracteristic] = useState([{id : "1", nombre : "WiFi"}])
    const [selectedOptionImages, setSelectedOptionImages] = useState([{titulo : "img", url : ""}])


    const [ciudades , setCiudades] = useState([])
    const [categorias , setCategorias] = useState([])

    const [titulo, setTitulo] = useState(null)
    const [descripcion, setDescripcion] = useState(null)
    const [direccion, setDireccion] = useState(null)
    const [ciudad, setCiudad] = useState(null)
    const [latitud, setLatitud] = useState(null)
    const [longitud, setLongitud] = useState(null)
    // const [categoria, setCategoria] = useState(null)

    const [descripcionNormas, setDescripcionNormas] = useState(null)
    const [descripcionSeguridad, setDescripcionSeguridad] = useState(null)
    const [descripcionPolitica, setDescripcionPolitica] = useState(null)

    // const [imagenes, setImagenes] = useState(null)

    const [caracteristicas, setCaracteristicas] = useState([])

    const agregarImagen = () =>{
        setSelectedOptionImages([...selectedOptionImages, {titulo : "img", url : ""}])
}

const setImagenActual = (option,i) =>{
    const aux = selectedOptionImages;
    aux[i] = option
    setSelectedOptionImages(aux)
    console.log(selectedOptionImages);
}


    const agregarAtributo = () =>{
        setSelectedOptionCaracteristic([...selectedOptionCaracteristic, {id : "", nombre : ""}])
}

const setAtributoActual = (option,i) =>{
    const aux = selectedOptionCaracteristic;
    aux[i] = option
    setSelectedOptionCaracteristic(aux)
}

    const config = {
        headers: {
            "Authorization": `Bearer ${logState.jwt}`,
            'Accept' : 'application/json',
            'Content-Type': 'application/json'
        }
    }
    const data_product = {
        titulo: titulo,
        direccion : direccion ,
        descripcion : descripcion,
        latitud :latitud,
        longitud : longitud,
        categoria : { id :parseInt(selectedOption.id)},
        ciudad : {id : parseInt(selectedOptionCity.id)},
        imagenes : selectedOptionImages,
        politicas:[
            {titulo: "Normas de la casa",
            descripcion: descripcionNormas},
            {titulo: "Salud y seguridad",
            descripcion: descripcionSeguridad},
            {titulo: "Politica de cancelacion",
            descripcion: descripcionPolitica
           }
        ],
        caracteristicas : selectedOptionCaracteristic 
    }

    useEffect(() =>{
        axios.get(CARACTERISTICAS).then((response) =>{
            
            setCaracteristicas(response.data)

        }).catch(err => console.log(err))

        axios.get(BUSCARDOR_CIUDAD).then((response) =>{
                
            setCiudades(response.data)

        }).catch(err => console.log(err))

        axios.get(CATEGORY).then((response) =>{
            setCategorias(response.data)
        }).catch(err => console.log(err))
} , [])

       


    const childs = {
        ciudades,
        categorias,
        setTitulo,
        setCategorias,
        setDescripcion,
        setCiudad,
        setSelectedOption,
        selectedOption,
        selectedOptionCity,
        setSelectedOptionCity,
        setDireccion,
        setLatitud,
        setLongitud
    }

    const childsPolitics ={
        setDescripcionNormas,
        setDescripcionSeguridad,
        setDescripcionPolitica
    }

    const childsAtributte ={
        caracteristicas,
        agregarAtributo,
        setAtributoActual        
    }

    const childsImages ={
        agregarImagen,
        setImagenActual,
        selectedOptionImages
     }

 



    const sendNewProduct = (e) =>{
        e.preventDefault()
            axios.post(CREAR_PRODUCTO, JSON.stringify(data_product), config)
            .then((res) =>{
                navigate("/admin/createProduct/success")
            }) 
            .catch(err =>{
                JSON.stringify(data_product)
                console.log(err);
            })
    }


      
    return (
        <section className="createProduct-view">
                 <div className="top-container">
                     <div className="main-container">
                         <h3>Crear propiedad</h3>
                <form className="form-product" onSubmit={sendNewProduct}>
                            <CreatePropiedad {...childs} />
                            
                            <h3>Agregar atributos</h3>
                            {
                    
                    selectedOptionCaracteristic.map((caracteristica,index) => { 
                        return (<CreateAtributte key={index} actual = {caracteristica} index = {index} {...childsAtributte} />)
                        }
                    )}
                            
                            <CreatePolitics {...childsPolitics} />
                            <h3>Cargar imágenes</h3>
                            {                    
                    selectedOptionImages.map((imagen,index) => { 
                        return (<CreateImages key={index} actual = {imagen} index = {index} {...childsImages} />)
                        }
                    )}


                            <div className="createProduct-button">
                                <button>
                                    Crear producto
                                </button>
                            </div>
                         </form>
                             
                    </div>
                </div>
            </section>
    )



}

export default CreateProduct