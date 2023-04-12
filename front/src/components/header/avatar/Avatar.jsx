import './Avatar.css'
import { Link } from 'react-router-dom'

const Avatar = ({logState}) => {
    const name = logState.user.name
    const lastName = logState.user.lastName
    // const id = logState.user.id

    return (
        <div className="avatarContainer">
            
            {logState.user.role.roleName == "ROLE_ADMIN" ? (<div className='admin-button-wrapper'> <Link to={"/admin"}> <button className='admin-button'>Administrador</button> </Link></div>):null }
            <div className='avatar'> 
                <p>{name[0].toUpperCase() + lastName[0].toUpperCase() }</p>
            </div>
            <p className='greeting'> Hola,  <span>{name + " " + lastName}</span></p>
            {/* <Link to={"/booking/list/"+id}>Ver reservas</Link> */}
        </div>
    )
}
export default Avatar