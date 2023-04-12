import React, { useContext, useEffect , useState} from 'react'
import CardContainer from '../components/mainPage/productCards/cardContainer/CardContainer'
import SearhBar from '../components/mainPage/searchBar/SearhBar'
import CategoriesContainer from '../components/mainPage/categories/categoryContainer/CategoriesContainer'

const Home = () => {
    const [productos, setProductos] = useState([]);
    const [title, setTitle] = useState("Recomendaciones")

    return (
        <>
            <main className="body">
                <div className="searcher">
                    <SearhBar setTitle={setTitle}  setProductos={setProductos} />
                </div>
                <div className="categories">
                    <CategoriesContainer setTitle={setTitle} setProductos={setProductos} />
                </div>
                <div className="recommendations">
                    <CardContainer title ={title} productos={productos} setProductos={setProductos} />
                </div>
            </main>
        </>
    )
}

export default Home
