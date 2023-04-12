import React, { useState } from 'react'
import CreateProductHeader from '../components/createProductPage/createProductHeader/CreateProductHeader'
import CreateProduct from './CreateProduct'
import ListProductAdmin from '../components/adminPage/listProductAdmin/ListProductAdmin'
import "../components/adminPage/listProductAdmin/admin.css"
import ListUser from '../components/adminPage/userPage/ListUser'
const Admin = () => {


const [showComponnent, setShowComponenet] = useState(null)
const [ clicked, setClicked] = useState(false)
const [ clickedCreate, setClickedCreate] = useState(true)
const [ clickedUser, setClickedUser] = useState(false)


 

const renderComponent = () =>{
    if (showComponnent == "create") {
        { return <CreateProduct/>}
       
    }

    if (showComponnent == "list") {
        {return <ListProductAdmin/>}
    }

    if (showComponnent == "user") {
        {return <ListUser/>}
    }
    return <CreateProduct/>
}


const handelSelectComponent = (arg) =>{
    if (arg === "list") {
        setClicked(true)
        setClickedCreate(false)
        setClickedUser(false)
    }
    if (arg === "create") {
        setClicked(false)
        setClickedUser(false)
        setClickedCreate(true)
    }
    if (arg == "user") {
        setClicked(false)
        setClickedUser(true)
        setClickedCreate(false)
    }
    setShowComponenet(arg)
}


  return (
    <section className="createProduct-view">
                <CreateProductHeader />
                <div className='divs-buttons'>
                    <div className={ clickedCreate ? 'button-click' : "button-click-clicked"} onClick={ () => handelSelectComponent("create")} > Crear Producto </div>
                    <div className={ clicked ? 'button-click' : "button-click-clicked"}  onClick={ () => handelSelectComponent("list")} > Productos</div>
                    <div className={ clickedUser ? 'button-click' : "button-click-clicked"}  onClick={ () => handelSelectComponent("user")} > Usuarios</div>
                </div>
                 <div className="top-container">
                    {renderComponent()}
                </div>
    </section>
  )
}

export default Admin