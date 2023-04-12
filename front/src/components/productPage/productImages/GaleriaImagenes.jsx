import React from 'react'
import './GaleriaImagenes.css'
import { useState, useEffect } from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faChevronLeft,  faChevronRight } from '@fortawesome/free-solid-svg-icons'

function GaleriaImagenes ({imagenes, cantidad}) {


    //Variables y estados
    const [imagenActual, setImagenActual] = useState(imagenes != [] ? imagenes[0] : null);
    const [index, setIndex] = useState(0)
    const [autoPlay, setAutoPlay] = useState(true)
    

    // Return prematuro para evitar errores
    if (!Array.isArray(imagenes) || cantidad === 0)
    return ;

    useEffect(()=>{setTimeout(()=>{autoPlay ? siguenteImagen(): console.log(autoPlay)},3000)})

    //funciones botones
    const siguenteImagen = () => {
        setIndex( index === imagenes.length - 1 ? 0 : index + 1)
        setImagenActual(imagenes[index])
    };

    const anteriorImagen = () => {
        setAutoPlay(false)
        setIndex( index === 0 ? imagenes.length - 1 : index - 1)
        setImagenActual(imagenes[index])
    };

    const cambiarImagen = (imagen) => {
        setAutoPlay(false)
        setImagenActual(imagen)
        setIndex(imagenes.findIndex(img => img === imagenActual))
    }


    return (
        <div className='galeria'>
            <div className='galeria-row'>
            <FontAwesomeIcon onClick={anteriorImagen} className = "chevronLeft fa-2x" icon={faChevronLeft} />
            
            <img src={imagenActual.url} alt="imagen" />
            <FontAwesomeIcon onClick={()=>{siguenteImagen(); setAutoPlay(false)}} className = "chevronRight fa-2x" icon={faChevronRight} />
            </div>
            <div className='imageCounter'>{index + 1 + " / " + imagenes.length}</div>
            <div className='imagenesInferiores'>
                {
                    imagenes.filter(img => img != imagenActual).slice(0, 4).map(img =>{
                        return (
                            <img key={img.id} src={img.url} alt="imagen" onClick={()=>{cambiarImagen(img)}}/>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default GaleriaImagenes