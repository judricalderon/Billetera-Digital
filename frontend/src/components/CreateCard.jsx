import { useContext, useState } from "react";
import { CardContext } from "../context/CardContext";
import { calculateBrand } from "../utils/CardUtils";
import "../index.css";

export const CreateCard = ({ user }) => {
  const { addCard } = useContext(CardContext);

  const [form, setForm] = useState({
    numero: "",
    fechaVencimiento: "",
    cupoTotal: "",
  });

  const [isCreating, setIsCreating] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      var cardData = {
        idTarjeta: 4,
        numero: form.numero,
        fechaVencimiento: form.fechaVencimiento,
        franquicia: calculateBrand(form.numero),
        estado: "ACTIVO",
        cupoTotal: parseFloat(form.cupoTotal),
        cupoDisponible: parseFloat(form.cupoTotal),
        cupoUtilizado: 0,
        idCliente: user.idCliente,
      };

      await addCard(cardData);

      setForm({ numero: "", fechaVencimiento: "", cupoTotal: "" });
    } catch (err) {
      setError(err.message);
    } finally {
      setIsCreating(false);
    }
  };

  if (!isCreating) {
    return (
      <div className="add-card-modal">
        <button
          className="create-card-btn"
          onClick={() => setIsCreating(!isCreating)}
        >
          Agregar tarjeta
        </button>
      </div>
    );
  }

  return (
    <div className="add-card-modal">
      <h3>Agregar nueva tarjeta</h3>

      {error && <p className="error">{error}</p>}

      <form onSubmit={handleSubmit} className="card-form">
        <input
          name="numero"
          placeholder="Número (solo dígitos)"
          value={form.numero}
          onChange={handleChange}
          required
        />

        <input
          name="fechaVencimiento"
          placeholder="MM/YYYY"
          value={form.fechaVencimiento}
          onChange={handleChange}
          pattern="^(0[1-9]|1[0-2])\/\d{4}$"
          required
        />

        <input
          name="cupoTotal"
          type="number"
          placeholder="Cupo total"
          value={form.cupoTotal}
          onChange={handleChange}
          min="0"
          step="0.01"
          required
        />
        
        <div className="buttons-container">
          <button type="submit" className="save-btn">
            Guardar
          </button>

          <button
            onClick={() => setIsCreating(!isCreating)}
            className="cancel-btn"
          >
            Cancelar
          </button>
        </div>

      </form>
    </div>
  );
};
