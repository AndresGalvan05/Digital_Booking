import React from "react";
import "./ProductCaracteristics.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faWifi,
    faKitchenSet,
    faCar,
    faTelevision,
    faDog,
    faSnowflake,
    faUmbrellaBeach,
    faPersonSwimming,
    faCircleNotch,
    faDumbbell,
    faBeer,
    faSpa
} from "@fortawesome/free-solid-svg-icons";

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
const ProductCaracteristics = (props) => {


    return (
        <div>
            <div className="container section-title-caracteristics">
                <h3> ¿Qué ofrece este lugar?</h3>
            </div>
            <div className="container caracteristics-grid">

                {props.caracteristic.map(item => 
                <div className="icon-detall" key={item.id}>
                    <FontAwesomeIcon
                        className="caracteristics-icon"
                        icon={ iconos[item.nombre]  ? iconos[item.nombre] : faCircleNotch }
                    />
                    <p>{item.nombre} </p>
                </div>
                )}
            </div>
        </div>
    );
    };

export default ProductCaracteristics;
