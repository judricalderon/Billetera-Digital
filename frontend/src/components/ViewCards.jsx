import { useContext, useEffect, useState } from "react";
import { CardContext } from "../context/CardContext";

export const ViewCards = ({ isOpen, user }) => {
  const { cards, loadCards } = useContext(CardContext);
  const [ isLoading, setIsLoading ] = useState(true);

  useEffect(() => {
    if (isOpen) {
      setIsLoading(true);
      loadCards(user.idCliente);
      setIsLoading(false);
    }
  }, [isOpen, user]);

  if (isLoading) {
    console.log("Hola")
    return <p>Cargando tarjetas...</p>;
  }

  if (!cards || cards.length === 0) {
    console.log("Hola 2")
    return <p>No hay tarjetas disponibles.</p>;
  }

  return (
    <div className="view-cards-modal">
      <div className="cards-list">
        {cards.map((c) => (
          <div key={c.idTarjeta} className="card-item">
            <p>
              <strong>**** **** **** {c.numero.slice(-4)}</strong>
            </p>
            <p>Vence: {c.fechaVencimiento}</p>
            <p>Franquicia: {c.franquicia}</p>
            <p>Cupo Disponible: {c.cupoDisponible}</p>
          </div>
        ))}
      </div>
    </div>
  );
};
