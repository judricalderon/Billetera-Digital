import { useContext, useState, useEffect } from "react";
import Modal from "react-modal";
import { UserContext } from "../context/UserContext";
import { CardModal } from "./CardModal";
import { CreateUserModal } from "./CreateUserModal";
import "../index.css";

Modal.setAppElement("#root");

export const UserTable = () => {
  const { users, loadUsers } = useContext(UserContext);
  const [selectedUser, setSelectedUser] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);

  useEffect(() => {
    loadUsers();
  }, []);

  const openModal = (user) => {
    setSelectedUser(user);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedUser(null);
  };

  const openCreateModal = () => setIsCreateModalOpen(true);
  const closeCreateModal = () => setIsCreateModalOpen(false);

  return (
    <>
      <div style={{ textAlign: "right" }}>
        <button className="create-user-btn" onClick={openCreateModal}>
          Crear usuario
        </button>
      </div>

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
              <tr
                key={u.idCliente}
                onClick={() => openModal(u)}
                style={{ cursor: "pointer" }}
              >
                <td>{u.nombreCompleto}</td>
                <td>{u.identificacion}</td>
                <td>{u.correo}</td>
                <td>{u.estado}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <CreateUserModal
        isOpen={isCreateModalOpen}
        onRequestClose={closeCreateModal}
      />

      {selectedUser && (
        <CardModal
          isOpen={isModalOpen}
          onRequestClose={closeModal}
          user={selectedUser}
        />
      )}
    </>
  );
};
