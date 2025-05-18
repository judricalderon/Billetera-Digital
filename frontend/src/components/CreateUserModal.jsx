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
      <div className="modal-header">
        <h2>Crear Usuario</h2>
      </div>

      <div className="modal-body">
        {error && <p className="error">{error}</p>}

        <form onSubmit={handleSubmit} className="card-form">
          <div className="form-group">
            <label htmlFor="nombreCompleto">Nombre completo</label>

            <input
              name="nombreCompleto"
              placeholder="Nombre completo"
              value={form.nombreCompleto}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="identificacion">Identificación</label>

            <input
              name="identificacion"
              placeholder="Identificación"
              value={form.identificacion}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="correo">Correo electrónico</label>

            <input
              name="correo"
              type="email"
              placeholder="Correo electrónico"
              value={form.correo}
              onChange={handleChange}
              required
            />
          </div>

          <div className="buttons-container">
            <button type="submit" className="success-user-btn">
              Guardar
            </button>

            <button
              type="button"
              onClick={onRequestClose}
              className="cancel-user-btn"
            >
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </Modal>
  );
};
