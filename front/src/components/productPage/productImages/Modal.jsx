import React from 'react'
import './Modal.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faX } from '@fortawesome/free-solid-svg-icons'
const Modal = ({children, estado, cambiarEstado}) => {
    return (
    <>
        {
            <div className={estado ? "": "displayNone"}>
            <div className='overlay'>
                <div className='contenedorModal'>
                    <button onClick={ () => cambiarEstado(false)} className='botonCerrar'>
                    <FontAwesomeIcon icon={faX}/>
                    </button>
                    {children}
                </div>
            </div>
            </div>
        }
    </>
    )
}

export default Modal;