import { createContext, useContext, useState, useReducer, useEffect } from "react"
import axios from "axios"
import { BUSCAR_USUARIO_POR_EMAIL } from "../staticData/url";
import { PRODUCTOS_ID } from "../staticData/url";


const initialUser = {
    id: null,
    name: "",
    lastName: "",
    email: "",
    city: null
}

const initialState = {jwt: localStorage.getItem("jwt"),
                    user: JSON.parse(localStorage.getItem('user')), 
                    isLoged: localStorage.getItem("jwt") ? true : false, }

const initialDateState = {
    start: null,
    end: null,
    productId: null
}

const initialFavorites = [] 

export const userContext = createContext(initialState)


const reducer =  (state, action) => {
    switch(action.type){
        case 'logIn':
            return {
                jwt: localStorage.getItem('jwt'),
                user: JSON.parse(localStorage.getItem('user')),
                isLoged: true
            }
        case 'logOut':
            localStorage.removeItem('jwt')
            localStorage.removeItem('user')
            return {
                jwt: null,
                user: null,
                isLoged: false
            }
        default:
            return initialState
    }
}


const UserContextProvider = ({children}) => {

    const [logState, dispatchLogState] = useReducer(reducer, initialState);
    const [dateState, setDateState] = useState(initialDateState) 
    const [favoritesState, setFavoritesState] = useState(initialFavorites)
   
    useEffect(()=>{
        if  (logState.isLoged && favoritesState.length == 0)  {
            axios.get(PRODUCTOS_ID + "favoritos/" + logState.user.id).then(res => setFavoritesState(res.data))
        }
    },[])
    
        
    
    return (
        <userContext.Provider value={{ logState, dispatchLogState, dateState, setDateState ,favoritesState, setFavoritesState}}>
            {children}
        </userContext.Provider>
    )
}

export default UserContextProvider;

export const useContextProvider = () =>{
    return useContext(userContext)
}