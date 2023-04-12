import React from "react"
//import {GoogleMap, useJsApiLoader, Marker} from "@react-google-maps/api"
import { MapContainer,TileLayer, Marker, Popup } from 'react-leaflet'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import "./ProductMap.css"

const ProductMap = (props) => {
    const center = {
        lat: props.lat,
        lng: props.lng
    };

    let iconUbicacion = new L.Icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [35, 51],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [51, 51]
    })

    return (
        <div className="container mapsContainer">
            <h3>¿Dónde vas a Estar?</h3>
            <p>{props.location.nombre + ", "+props.location.pais}</p>

                <MapContainer center={center} zoom={13}>
                    <TileLayer
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    />
                    <Marker 
                        position={center}
                        icon={iconUbicacion}
                    >
                        <Popup >{props.location.nombre+", " + props.location.pais}</Popup>
                    </Marker>
                </MapContainer>

        </div>
    )
}
export default ProductMap