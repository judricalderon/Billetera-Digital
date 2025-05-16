import { useContext, useEffect, useState } from "react";
import { CardContext } from "../context/CardContext";

export const ViewCards = ({ isOpen, user }) => {
  const { cards, loadCards, updateCard, inactivateCard } =
    useContext(CardContext);

  const [isLoading, setIsLoading] = useState(true);
  const [editingId, setEditingId] = useState(null);
  const [newCupo, setNewCupo] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    if (isOpen) {
      setIsLoading(true);
      loadCards(user.idCliente);
      setIsLoading(false);
    }
  }, [isOpen, user]);

  const startEdit = (card) => {
    if (card.estado !== "Activo") return;
    setEditingId(card.idTarjeta);
    setNewCupo(card.cupoDisponible);
    setError("");
  };

  const cancelEdit = () => {
    setEditingId(null);
    setError("");
  };

  const saveEdit = async () => {
    const card = cards.find((c) => c.idTarjeta === editingId);
    const val = Number(newCupo);

    if (isNaN(val) || val < 0 ) {
      setError(`El cupo debe ser mayor a 0`);
      return;
    }

    var newCard = {
      ...card,
      cupoTotal: val,
    };

    await updateCard(newCard);
    setEditingId(null);
  };

  const handleInactivate = async (idTarjeta) => {
    const card = cards.find((c) => c.idTarjeta === idTarjeta);

    var newCard = {
      ...card,
      estado: "Inactivo",
    };

    await inactivateCard(newCard);
  };

  if (isLoading) return <p>Cargando tarjetas...</p>;
  if (!cards || cards.length === 0) return <p>No hay tarjetas disponibles.</p>;

  // Activos primero, luego inactivos
  const sorted = [...cards].sort((a, b) => {
    if (a.estado === "Activo" && b.estado !== "ACTActivoIVO") return -1;
    if (a.estado !== "Activo" && b.estado === "Activo") return 1;
    return 0;
  });

  return (
    <div className="view-cards-modal">
      <div className="cards-list">
        {sorted.map((card) => (
          <div
            key={card.idTarjeta}
            className={`card-item ${
              card.estado !== "Activo" ? "inactive" : ""
            } `}
          >
            {editingId === card.idTarjeta ? (
              <>
                <input
                  type="number"
                  min={0}
                  value={newCupo}
                  onChange={(e) => setNewCupo(e.target.value)}
                />

                <div className="edit-buttons">
                  <button onClick={saveEdit}>Guardar</button>
                  <button onClick={cancelEdit}>Cancelar</button>
                </div>
                {error && <p className="error">{error}</p>}
              </>
            ) : (
              <>
                <p>
                  <strong>**** **** **** {card.numero.slice(-4)}</strong>
                </p>
                <p>
                  <strong>Vence</strong>: {card.fechaVencimiento}
                </p>
                <p>
                  <strong>Franquicia</strong>: {card.franquicia}
                </p>
                <p>
                  <strong>Cupo Total</strong>: ${card.cupoTotal}
                </p>
                <p>
                  <strong>Cupo Disponible</strong>: ${card.cupoDisponible}
                </p>
                <p>
                  <strong>Cupo Utilizado</strong>: ${card.cupoUtilizado}
                </p>

                {card.estado === "Inactivo" && (
                  <p className="inactive-text">Tarjeta inactiva</p>
                )}

                {card.estado === "Activo" && (
                  <div className="card-button-container">
                    <button
                      onClick={() => startEdit(card)}
                    >
                      Modificar cupo
                    </button>
                    <button
                      onClick={() => handleInactivate(card.idTarjeta)}
                    >
                      Inactivar
                    </button>
                  </div>
                )}
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};
