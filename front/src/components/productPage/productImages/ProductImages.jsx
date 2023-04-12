import React, { useState } from 'react';
import Modal from './Modal.jsx'
import './ProductImages.css'
import GaleriaImagenes from './GaleriaImagenes'

const ProductImages = (props) => {
    const [estadoModal1, cambiarEstadoModal1] = useState(false);
    const mockimagenes = props.crimg;
    const cantidad = mockimagenes?.length;

    return (
    <>
        <div className='container imageContainer'>
            <div className='BloqueImg'>
                <div className='ImagenP'>
                    
                    <img src={props.crimg[0].url} alt={props.id}/>
                </div>
                
                <div className='ImagenO'>
                    <img src={props.crimg[1].url} alt={props.id}/>
                    <img src={props.crimg[2].url} alt={props.id}/>
                    <img src={props.crimg[3].url} alt={props.id}/>
                    <img src={props.crimg[4].url} alt={props.id}/>
                </div>
            </div>
            <div className='detalle-galeria'>
                <div className='galeria-imagenes'>
                <button className='viewmore' onClick={() => cambiarEstadoModal1(!estadoModal1)}>Ver más</button>
                <Modal estado={estadoModal1} cambiarEstado={cambiarEstadoModal1}>
                    <GaleriaImagenes imagenes={mockimagenes} cantidad={cantidad}/>
                </Modal>
                </div>
            </div>
        </div>
    </> 
    )
}

export default ProductImages
