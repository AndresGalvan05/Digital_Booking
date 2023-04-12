import React, { useEffect, useState } from 'react'
import axios from "axios"
import { USUARIOS } from '../../../staticData/url'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEdit,
    faPrescriptionBottle} from "@fortawesome/free-solid-svg-icons"
import { useContextProvider } from "../../../context/UserContext"



const ListUser = () => {
    const logState = useContextProvider().logState
    const [users, setUsers] = useState([])



useEffect(()=>{
    axios.get(USUARIOS).then(res =>{
        setUsers(res.data)
        console.log(res.data);
    })
},[])

const config = {
    headers: {
        "Authorization": `Bearer ${logState.jwt}`,
        'Accept' : 'application/json',
        'Content-Type': 'application/json',
    }
}




const deleteUser = (id_user) =>{   
    axios.delete(USUARIOS+"/"+id_user, config).then((res) =>{
        console.log(res);
        location.reload()
    }).catch(error =>{
        console.log(error);
    })
}

const cambiarRole = (email, role) =>{

    const change = {
        email : email,
        role : role
    }
    

    axios.put(USUARIOS+"/roles/"+email+"/"+role, change, config).then(res =>{
        console.log("se actualizo el rol");
    })
}


  return (
    <div className='main-container-list'>
    <div className='table'>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            {users.map( user => 
                        <tr>
                            <td>
                            {user.id}
                            </td>
                        <td>
                         {user.nombre} 
                        </td>
                        <td>
                         {user.apellido} 
                        </td>
                        <td>
                         {user.email} 
                        </td>
                        <td> <select defaultValue={user.role.roleName} onChange={(e) => cambiarRole(user.email,e.target.value)} >
                            <option disabled value="">{user.role.roleName} </option>
                            <option value="ROLE_ADMIN">Admin</option>
                            <option value="ROLE_HOST">Host</option>
                            <option value="ROLE_USER">User</option>
                            </select> </td>
                        <td>
                            <FontAwesomeIcon className='icons-admin' onClick={() => cambiarEstadoModal(!estadoModal)} icon={faEdit}/>
                        </td>
                        <td>
                            <FontAwesomeIcon className='icons-admin-delete' onClick={() => deleteUser(user.id)} icon={faPrescriptionBottle}/>
                        </td>
                         </tr>
                )}
            </tbody>
        </table>
    </div>
</div>
  )
}

export default ListUser