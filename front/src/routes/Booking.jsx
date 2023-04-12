import React, {useState, useEffect} from "react"
import { useParams, useNavigate } from "react-router-dom"
import { PRODUCTOS_ID } from "../staticData/url"
import ProductHeader from "../components/productPage/productHeader/ProductHeader"
import PersonalData from "../components/bookingPage/PersonalData"
import BookingDate from "../components/bookingPage/BookingDate"
import ReservaConfirm from "../components/bookingPage/ReservaConfirm"
import ProductPolitics from "../components/productPage/productPolitics/ProductPolitics" 
import DateBox from "../components/mainPage/searchBar/DateBox"
import axios from "axios"
import '../components/bookingPage/Booking.css'
import { convert } from "../utils/utils"
import { ENVIAR_RESERVA } from "../staticData/url"
import { useContextProvider } from "../context/UserContext"
import Swal from "sweetalert2"




const Booking = () => {

    let {id} = useParams();
    const navigate = useNavigate();
    const [producto , setProducto] = useState(null);
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [value, setValue] = useState()
    const dateState = useContextProvider().dateState

    const logState = useContextProvider().logState

    useEffect(()=>{
        if (logState.user == null){
            navigate("/")
        }
        axios.get(PRODUCTOS_ID + id).then(res =>{
            setProducto(res.data)
        }).catch(err => console.log("error"))
    }, [])



    // const handleSubmit = () =>{
    //     startDate
    //     setStartDate
    //     setEndDate
    //     endDate
    //     product = producto
    //     value
    // }

    const data_reserva = {
        horaReserva : value,
        fechaInicial : convert(dateState.start),
        fechaFinal : convert(dateState.end),
        producto :{
            id : parseInt(id)
        },
        cliente :{
            id: logState.user ? logState.user.id : null
        }
    }

    const config = {
        headers: {
            "Authorization": `Bearer ${logState.jwt}`,
            'Accept' : 'application/json',
            'Content-Type': 'application/json'
        }
    }

    const sendReserva = (e) =>{
        e.preventDefault()

        if (data_reserva.horaReserva === undefined ) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Debe ingresar una hora de llegada',
            })}
        if (data_reserva.fechaInicial === "") {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Debe ingresar una fecha para el checkIn',
            })
        }

        if (data_reserva.fechaFinal === "") {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Debe ingresar un dia para el checkOut',
            })
        } else {
           
            axios.post(ENVIAR_RESERVA, JSON.stringify(data_reserva), config)
                .then((res) =>{
                    navigate("/booking/succes")
                }) 
                .catch(err =>{
                    JSON.stringify(data_reserva)
                    console.log(err);
                })
        } 
    }

    return (
        <div className="">
            {producto !== null ? 
            <main className="">
            <ProductHeader key={producto.id} category={producto.categoria} title={producto.titulo}/>
            
            <div className="container body-booking">
                <h2 className="body-booking-title">Completá tus datos</h2>
                <form  onSubmit={sendReserva}>
                <div className="booking-main-section">
                    <div className="booking-izquierda">
                        <PersonalData/>
                        {/* <Calendar /> */}
                        <div className='calendar-container'>
                                <h3>
                                Selecciona tu fecha de reserva
                            </h3>
                                <div className='reserva'>
                                    <DateBox setStartDate={setStartDate} setEndDate={setEndDate} startDate={startDate} endDate={endDate} inline = {true} reservations={producto.reservas}/></div>
                                </div>
                        <BookingDate value={value} setValue={setValue} />
                    </div>
                    <div className="booking-derecha">
                        <ReservaConfirm  startDate={startDate} endDate={endDate} product = {producto} />
                    </div>
                </div>
                </form>
            </div>
            <ProductPolitics politic ={producto.politicas} /> 
        </main>
        : <></>}
        </div>
        
    )
}

export default Booking ;
