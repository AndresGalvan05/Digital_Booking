import React from 'react'
import {faStar} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { useContextProvider } from '../../context/UserContext';
import axios from 'axios';
import { ENVIAR_PUNTUACION } from '../../staticData/url';
import Swal from "sweetalert2"
import "animate.css"
import { Navigate, useNavigate } from 'react-router-dom';

const Stars = (props) => {
    const location = (window.location.href)
    const isReserved = location.includes("/booking/list/")
    const logState = useContextProvider().logState
    const navigate = useNavigate()

    const sendScore = ( ratingValue) =>{
        const config = {
            headers: {
                "Authorization": `Bearer ${logState.jwt}`,
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        }

        logState.isLoged?( axios.post( ENVIAR_PUNTUACION, {
            puntuacion: ratingValue,
            producto : {
                id : props.prodId
            },
            cliente :{
                id : logState.user.id
            }
        }, config).then(( res) =>{
            console.log(res);
            window.location.reload() 
        }) 
        ):(Swal.fire({
                icon: 'error',
                title: 'Oops...',
                showClass: {
                    popup: 'animate__animated animate__fadeInDown'
                  },
                  hideClass: {
                    popup: 'animate__animated animate__fadeOutUp'
                  },
                text: 'Para realizar una puntuacion debe de estar logueado',
                confirmButtonText : "Ir al login"
        }).then( (result) =>{

            if (result.isConfirmed) {
                navigate("/login")
            }
        }  ))
    }

    return (
        <>

            {isReserved ?  
                <div className="stars_container">
                    {
                    
                    [...Array(5)].map((star, i) => { 
                        const ratingValue = i + 1;
                        return (<FontAwesomeIcon key={i} icon={faStar} onClick = {() => sendScore(ratingValue)}  className={(ratingValue <= (props.avg ) ? "star_orange" : "star_black")} id="star"/>)
                        }
                    )}
                </div>
            : (
             <div className=''>
                    {
                    
                    [...Array(5)].map((star, i) => { 
                        const ratingValue = i + 1;
                        return (<FontAwesomeIcon key={i} icon={faStar} className={(ratingValue <= (props.avg ) ? "star_orange" : "star_black")} id="star"/>)
                        }
                    )}
                </div>
            )
         }
        </>
)
}

export default Stars