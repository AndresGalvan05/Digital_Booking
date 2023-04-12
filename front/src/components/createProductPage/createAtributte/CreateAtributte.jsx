import React, {useEffect, useState} from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSquarePlus} from '@fortawesome/free-solid-svg-icons';

const CreateAtributte = ( props ) => {
    const [open, setOpen] = useState(true)
    const [atributo, setAtributo]= useState(props.actual)

    const handleChange = (option) => {
        setOpen(false)
        props.setAtributoActual(option,props.index);
        setAtributo(option)
    }  



    useEffect(()=>{},[atributo])

  return (
    <div className='container-atribute'>

    <div className="select-box-atribute">
         <label htmlFor="category">Nombre</label>
            <div className='dropdown-header-atribute' onClick={() =>setOpen(!open)}>
                <div>
                    <p> {atributo.nombre} </p>
                </div>
            </div>

            {open ? <ul className='dropdown-list-atribute'> 
                {props.caracteristicas.map( (item) =>{
                    return (
                        <li className='option-content' onClick={() => {handleChange(item)}} key= {item.id}  >
                            <div className=''>
                                <p>{item.nombre} </p>
                            </div>
                        </li>

                    )
                } )}
            </ul> : null }

        <div>
            <FontAwesomeIcon onClick={props.agregarAtributo} className="faSquarePlus " icon={faSquarePlus} />
        </div>
    </div>
    
    </div>
    
  )
}

export default CreateAtributte