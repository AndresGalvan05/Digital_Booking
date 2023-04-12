import React, {useState,useEffect} from 'react'
import './placebox.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLocationDot } from '@fortawesome/free-solid-svg-icons';



const PlaceBox = (props) => {

    const [open, setOpen] = useState(true)



  // handle onChange event of the dropdown

    const handleChange = (option) => {
        setOpen(false)
        props.setSelectedOption(option);
    }   
    

    return (
        <>
        <div className='select-box' >
            <div className='dropdown-header' onClick={()=> setOpen(!open)}>
                <div className='select-content'>
                <FontAwesomeIcon beat style={{color:"var(--naranja)"}} icon={faLocationDot} className={props.selectedOption.id === null ? "placeholder-text" : ""}/>
                    <p className={props.selectedOption.id === null ? "option-text placeholder-text" : "option-text"}>{props.selectedOption.nombre}</p>
                </div>
            </div>

            { open ? 
            <ul className='dropdown-list'>
                {props.ciudades.map((item) => {
                    return (
                        <li className='option-content' onClick={()=> {handleChange(item)}} key={item.id}>
                            <FontAwesomeIcon icon={faLocationDot} />
                            <div className='option-text'>
                                <h3>{item.nombre}</h3>
                                <h4>{item.pais}</h4>
                            </div>
                        </li>
                    )
                })}
            </ul>
            : null}
        </div>
        </>
    )
}

export default PlaceBox