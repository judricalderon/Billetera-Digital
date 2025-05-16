import cards from "../data/cards.json";

export const getCardsByClient = async (idCliente) => {
  // TODO: Reemplazar por la solicitud real al API

  return cards.filter((c) => c.idCliente == idCliente);
};

export const addCard = async (card) => {
  // TODO: Reemplazar por la solicitud real al API

  cards.push(card);
};

export const updateCard = async (newCard) => {
  // TODO: Reemplazar por la solicitud real al API

  const card = cards.find((c) => c.idTarjeta == newCard.idTarjeta);

  if (!card) {
    throw new Error("Tarjeta no encontrada");
  }

  card.cupoDisponible = newCard.cupoDisponible;
  card.cupoUtilizado = card.cupoTotal - newCard.cupoDisponible;
};

export const inactivateCard = async (newCard) => {
  // TODO: Reemplazar por la solicitud real al API

  const card = cards.find((c) => c.idTarjeta == newCard.idTarjeta);

  if (!card) {
    throw new Error("Tarjeta no encontrada");
  }

  card.estado = newCard.estado;
}
