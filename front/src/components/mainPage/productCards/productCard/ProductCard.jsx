import React, { useState } from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {
    faLocationDot,
    faWifi,
    faKitchenSet,
    faCar,
    faTelevision,
    faDog,
    faSnowflake,
    faPersonSwimming,
    faCircleNotch,
    faUmbrellaBeach,
    faDumbbell,
    faBeer,
    faSpa,
    faHeart,
    faEdit,
    faPrescriptionBottle
} from "@fortawesome/free-solid-svg-icons";
import './ProductCard.css'
import { Link } from 'react-router-dom';
import Stars from '../../../stars/Stars';
import axios from 'axios'
import imageplaceholder from '../../../../assets/img/imageplaceholder.png'
import { AGREGAR_A_FAVORITOS, PRODUCTOS_ID } from '../../../../staticData/url';
import { useContextProvider } from '../../../../context/UserContext';

const iconos = { "WiFi" : faWifi,
                "Cocina" : faKitchenSet,
                "Estacionamiento gratuito" : faCar,
                "Televisor": faTelevision,
                "Apto mascotas":faDog,
                "Pileta": faPersonSwimming,
                "Cerca de la playa" : faUmbrellaBeach,  
                "Gimnasios cerca" :  faDumbbell,
                "Spa" : faSpa,
                "Bar" : faBeer   
                /* smoke beach gym spa bar */
}

const values = ['Regular','Regular', 'Bueno', 'Muy Bueno', 'Excelente']



const ProductCard = (props) => {

    const favoritesState = useContextProvider().favoritesState
    const logState = useContextProvider().logState
    const setFavoritesState = useContextProvider().setFavoritesState

    const config = {
        headers: {
            "Authorization": `Bearer ${logState.jwt}`,
            'Accept' : 'application/json',
            'Content-Type': 'application/json'
        }
    }

    const isInFavorites =() =>{
        return (favoritesState.filter(fav => fav.id == props.product.id).length > 0)
    }

    const addToFavs = () =>{
        if (logState.isLoged == false) return
        if (favoritesState.filter(fav => fav.id == props.product.id).length > 0){
            console.log("entro")
            let auxFavList = favoritesState.filter(fav => fav.id != props.product.id)
            // axios.delete(AGREGAR_A_FAVORITOS)
            setFavoritesState(auxFavList)
        }else{
            
            let auxFavList = favoritesState
            auxFavList.push(props.product)
            setFavoritesState(auxFavList)
            axios.post(AGREGAR_A_FAVORITOS, JSON.stringify({
                producto: {
                    id: props.product.id,
                },
                cliente: {
                    id : logState.user.id
                } 
            }), config).then(res => console.log(res.data))
        }
    }

    const [showMore , setShowMore] = useState(false)

    const handleClickMap = () => {
        window.open(
        `https://www.google.com/maps/place/${props.location}`,
        "register",
        'width=800,height=600, top=0, left=960"'
        );
        return false;
    };


    const deleteProduct = (id_product) =>{  
        console.log(id_product); 
        axios.delete(PRODUCTOS_ID+id_product, config).then((res) =>{
            console.log(res);
            window.location.reload()
        }).catch(error =>{
            console.log(error);
        })
    }


    return (
    <div className='Product' key={props.id}>
        
        <div className='crimg'>
            {/* <FontAwesomeIcon icon={faHeart} className="heart"/> */}
            <img src={props.crimg ? props.crimg : imageplaceholder} alt={props.title}/>
            {props.hideFav ? null : <FontAwesomeIcon icon={faHeart} className={ isInFavorites() ? "heart heart-active" : "heart heart-inactive"  } onClick={addToFavs}/>}

        </div>
            <div className='info-container'>
                <div className='info-top'>
                    <div className='info-left'>
                        <div className='star_category_container'>
                            <h2 className='category'>{props.category}</h2>
                            <Stars  category={props.category} avg={props.avg} prodId={props.path}></Stars>
                        </div>
                            <div>
                                <h2 className='card_title'>{props.title}</h2>
                            </div>
                    </div>
                    {/* <div className='info-right'>
                        <div className='info-calification'>
                            <div className='calification'>
                                <span className='calification-number'>{props.avg * 2}</span>
                            </div>
                            <p>{values[props.avg - 1]}</p>
                        </div>
                    </div> */}
                    {logState.isLoged == true && logState.user.role.roleName == "ROLE_ADMIN" ? (  <div className='info-right'>
                        <div className='info-icons-admin'>
                        <FontAwesomeIcon icon={faPrescriptionBottle} onClick={()=> deleteProduct(props.path)} className='icons-admin-delete'/>                            
                        <FontAwesomeIcon icon={faEdit} className='icons-admin'/>
                        </div>
                    </div>) : (  <div className='info-right'>
                        <div className='info-calification'>
                            <div className='calification'>
                                <span className='calification-number'>{props.avg * 2}</span>
                            </div>
                            <p>{values[props.avg - 1]}</p>
                        </div>
                    </div>)}
                </div>
                
                <div className='info-location'>
                    <FontAwesomeIcon icon={faLocationDot} />
                    <h3 className='location'>
                        {props.location + " , " + props.direccion}
                        {/* <Link target={'_blank'} to={`https://www.google.com/maps/place/${props.location}`}  >MOSTRAR EN EL MAPA</Link> */}
                        <p onClick={handleClickMap}>MOSTRAR EN EL MAPA</p>
                    </h3>
                </div>
                <div className='iconos'>
                {props.icons.map(item =>
                        <div className='iconos' key={item.id}>          
                            <FontAwesomeIcon className='icon' icon={ iconos[item.nombre]  ? iconos[item.nombre] : faCircleNotch }/>
                        </div>
                )}
                </div>

                
                {props.description !=null ? (<>
                {/* <p className='description'>{props.description.slice(0,125)}<span className='color'> más...</span></p> */}
                        <p className='description'>
                            {showMore
                                ? props.description
                                : `${props.description?.slice(0,125)}`}
                            <span
                                className="color"
                                onClick={() => setShowMore(!showMore)}
                            >
                                {showMore ? " Ver menos" : "... Ver más"}
                            </span>
                        </p>

                <Link to={`/product/${props.path}`}> 
                    <button className='vermas  button_main'>Ver más</button>
                </Link></>) : (<>   <p className='description'>
                            {showMore
                                ? props.description
                                : `${props.description?.slice(0,125)}`}
                            <span
                                className="color"
                                onClick={() => setShowMore(!showMore)}
                            >
                                {showMore ? " Ver menos" : "... Ver más"}
                            </span>
                        </p>
                <Link to={`/product/${props.path}`}> 
                    <button className='vermas  button_main'>Ver más</button>
                </Link></>) }
            </div>
        </div>
)
}

export default ProductCard