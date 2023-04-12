import React, {useState, useEffect} from 'react'
import { CATEGORY, BUSCARDOR_CIUDAD, CARACTERISTICAS, PRODUCTOS_ID} from "../staticData/url";
import UpdatePropiedad from '../components/adminPage/updateProduct/UpdatePropiedad';
import UpdateAtributte from '../components/adminPage/updateProduct/UpdateAtributte';
import UpdateImages from '../components/adminPage/updateProduct/UpdateImages'
import UpdatePolitics from '../components/adminPage/updateProduct/UpdatePolitics'
import { useParams } from 'react-router-dom';
import axios from "axios"


const UpdateProduct = () => {
    const [producto, setProducto] = useState(null)
    const [categorias , setCategorias] = useState([])
    const [ciudades , setCiudades] = useState([])
    const [caracteristicas, setCaracteristicas] = useState([])
    const [titulo, setTitulo] = useState(null)
    const [direccion, setDireccion] = useState(null)
    const [ciudad, setCiudad] = useState(null)
    const [latitud, setLatitud] = useState(null)
    const [longitud, setLongitud] = useState(null)
    const [descripcion, setDescripcion] = useState(null)

    const [selectedOption, setSelectedOption] = useState({id: null, nombre: '',pais: null})
    const [selectedOptionCity, setSelectedOptionCity] = useState({id: null, nombre: '',pais: null})
    const [selectedOptionCaracteristic, setSelectedOptionCaracteristic] = useState([{id : "1", nombre : "WiFi"}])
    const [selectedOptionImages, setSelectedOptionImages] = useState([{titulo : "img", url : ""}])
    const [descripcionNormas, setDescripcionNormas] = useState(null)
    const [descripcionSeguridad, setDescripcionSeguridad] = useState(null)
    const [descripcionPolitica, setDescripcionPolitica] = useState(null)
    let id = useParams().id

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

        axios.get(PRODUCTOS_ID+ id).then((response) =>{
            setProducto(response.data)
        }).catch(err => console.log(err))

} , [])

const childs ={ producto, ciudades,
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
    setLongitud}

    


    return (
    <div className="createProduct-view">
        {producto == null ? <div></div> :  <div className="top-container">
                     <div className="main-container">
                         <h3>Modificar propiedad</h3>
                <form className="form-product" >
                            <UpdatePropiedad {...childs} />
                            
                            <h3>Agregar atributos</h3>
                            {/* {
                    
                    selectedOptionCaracteristic.map((caracteristica,index) => { 
                        return (<UpdateAtributte key={index} actual = {caracteristica} index = {index} {...childsAtributte} />)
                        }
                    )}
                            
                            <UpdatePolitics {...childsPolitics} />
                            <h3>Cargar imágenes</h3>
                            {                    
                    selectedOptionImages.map((imagen,index) => { 
                        return (<UpdateImages key={index} actual = {imagen} index = {index} {...childsImages} />)
                        }
                    )} */}

                            <div className="createProduct-button">
                                <button>
                                    Crear producto
                                </button>
                            </div>
                         </form>
                             
                    </div>
                </div>  }
                
            </div>
    )
}

export default UpdateProduct