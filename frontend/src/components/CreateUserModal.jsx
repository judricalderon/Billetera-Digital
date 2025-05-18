import { useState, useContext, useEffect } from "react";
import Modal from "react-modal";
import { UserContext } from "../context/UserContext";
import "../index.css";

Modal.setAppElement("#root");

export const CreateUserModal = ({ isOpen, onRequestClose }) => {
  const { addUser } = useContext(UserContext);

  const [form, setForm] = useState({
    identificacion: "",
    nombreCompleto: "",
    correo: "",
    estado: "Activo",
  });

  const [error, setError] = useState("");

  useEffect(() => {
    if (isOpen) {
      setForm({
        identificacion: "",
        nombreCompleto: "",
        correo: "",
        estado: "Activo",
      });

      setError("");
    }
  }, [isOpen]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((f) => ({ ...f, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await addUser(form);
      onRequestClose();
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Crear Usuario"
      className="modal"
      overlayClassName="overlay"
    >
      <h2>Crear Usuario</h2>

      {error && <p className="error">{error}</p>}

      <form onSubmit={handleSubmit} className="card-form">
        <input
          name="nombreCompleto"
          placeholder="Nombre completo"
          value={form.nombreCompleto}
          onChange={handleChange}
          required
        />

        <input
          name="identificacion"
          placeholder="Identificación"
          value={form.identificacion}
          onChange={handleChange}
          required
        />

        <input
          name="correo"
          type="email"
          placeholder="Correo electrónico"
          value={form.correo}
          onChange={handleChange}
          required
        />

        <select name="estado" value={form.estado} onChange={handleChange}>
          <option value="Activo">Activo</option>
          <option value="Inactivo">Inactivo</option>
        </select>

        <button type="submit" className="create-user-btn">
          Guardar
        </button>

        <button
          type="button"
          onClick={onRequestClose}
          className="create-user-btn"
        >
          Cancelar
        </button>
      </form>
    </Modal>
  );
};
