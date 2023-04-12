
import { Link, useLocation} from "react-router-dom";
import './ProductReservation.css'
import React, { useState  } from 'react';
import  { registerLocale } from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import es from 'date-fns/locale/es';
import DateBox from '../../mainPage/searchBar/DateBox'
import { useContextProvider } from '../../../context/UserContext'



const ProductReservation = (props) => {

    const logState = useContextProvider().logState

    const location = useLocation();
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    registerLocale('es', es);

    return (
        <div className='container calendar-background'>
            <div className="">
                <h3>Fechas Disponibles</h3>
                <div className='calendar'>
                    <div className='box'>
                    <DateBox setStartDate={setStartDate} setEndDate={setEndDate} startDate={startDate} endDate={endDate} inline = {true} reservations={props.reservations} productId={props.productId}/>
                    </div>
                    <div className='section-booking'>
                        <p>Agregá tus fechas de viaje para obtener precios exactos</p>
                        
                        { logState.isLoged ? (
                        <Link to={`${location.pathname + "/booking"}`}>
                            <button className='button_main'> Inicie Reserva </button>
                        </Link>
                        ) : (
                            <Link
                            to={"/login"}
                            state={{
                                bookingMessage:
                                    "Para realizar una reserva necesitas estar logueado",
                                }}
                            >
                                <button className='button_main'> Inicie Reserva </button>
                            </Link>
                        )}
                    </div>
                </div>
            </div>
        </div>
    )
    }

export default ProductReservation