import { useEffect, useState } from "react";
import { getUsers } from "../services/userService";
import "../index.css";

export default function UserTable() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers().then(setUsers);
  }, []);

  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Identificación</th>
            <th>Correo electrónico</th>
            <th>Estado</th>
          </tr>
        </thead>

        <tbody>
          {users.map((u) => (
            <tr key={u.idCliente}>
              <td>{u.nombreCompleto}</td>
              <td>{u.identificacion}</td>
              <td>{u.correo}</td>
              <td>{u.estado}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
