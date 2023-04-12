import React, {useEffect, useState} from 'react'
import './searchbar.css'
import PlaceBox from './PlaceBox'
import DateBox from './DateBox'
import axios from "axios"
import {BUSCARDOR_CIUDAD , BUSCARDOR_PRODUCTOS_POR_CIUDAD, BUSCARDOR_PRODUCTOS_POR_FECHA} from "../../../staticData/url.js"



const SearhBar = (props) => {

    const [ciudades , setCiudades] = useState([])
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [selectedOption, setSelectedOption] = useState({id: null, nombre: '¿A dónde vamos?', pais: null},);


    useEffect(() =>{
        axios.get(BUSCARDOR_CIUDAD).then((response) =>{
            
            setCiudades(response.data)

        }).catch(err => console.log(err))
} , [])

function convert(str) {
    var date = new Date(str),
    mnth = ("0" + (date.getMonth() + 1)).slice(-2),
    day = ("0" + date.getDate()).slice(-2);
    return [date.getFullYear(), mnth, day].join("-");
  }

function buscar() {
    
    if (selectedOption.id == null  && (startDate === null || endDate === null)) {
        return 
    }

    if (startDate === null || endDate === null) {     
        axios.get(BUSCARDOR_PRODUCTOS_POR_CIUDAD+selectedOption.id).then((response) =>{
            props.setProductos(response.data)
            props.setTitle("Recomendaciones en la ciudad de " + response.data[0].ciudad.nombre)
    
        }).catch(err => props.setTitle("No se encontraron resultados para la ciudad " + response.data[0].ciudad.nombre)) 
        return 
    } 

    if (selectedOption.id == null) {
        axios.get(BUSCARDOR_PRODUCTOS_POR_FECHA+convert(startDate)+"&&"+convert(endDate)).then((response) =>{
            props.setProductos(response.data)
            props.setTitle("Recomendaciones por la fecha " + convert(startDate) +" y " +convert(endDate))
    
        }).catch(err => props.setTitle("No se encontro resultado para estas fechas" + convert(startDate) +" y " +convert(endDate)))
        return
    }

    axios.get(BUSCARDOR_PRODUCTOS_POR_CIUDAD+selectedOption.id+"/fecha/"+convert(startDate)+"&&"+convert(endDate)).then((response) =>{
        props.setProductos(response.data)
        props.setTitle("Recomendaciones por la ciudad  " + response.data[0].ciudad.nombre + " entre las fechas " + convert(startDate) +"  y "+ convert(endDate))

    }).catch(err =>props.setTitle("No se encontro resultado para estas fechas " + convert(startDate) +" y " +convert(endDate)) )

}

    return (
        <div className='search_bar container'>
            <h2>Busca ofertas en hoteles, casas y mucho más</h2>
            <div className='button_container'>
                <div className='button_wrapper'>
                    <PlaceBox ciudades = {ciudades} selectedOption = {selectedOption} setSelectedOption = {setSelectedOption} />
                </div>
                
                <div className='button_wrapper'>
                    <DateBox setStartDate={setStartDate} setEndDate={setEndDate} startDate={startDate} endDate={endDate}  productId={null}/>
                </div>
                
                <button className='button_main' onClick={buscar}>Buscar</button>
            </div>
        </div>
    )
}

export default SearhBar