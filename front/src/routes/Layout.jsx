import React from 'react'
import Header from '../components/header/Header'
import { Outlet } from 'react-router'
import Footer from '../components/footer/Footer'

const Layout = () => {
    return (
        <div className='App'>
        <Header/>
        <Outlet/>
        <Footer />
        </div>
    )
}

export default Layout
