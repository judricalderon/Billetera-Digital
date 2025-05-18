import cards from "../data/cards.json";

export const getCardsByClient = async (idCliente) => {
  const res = await fetch(`${API_URL}/users/${idCliente}/cards`);

  if (!res.ok)
  {
    throw new Error(`Error al obtener tarjetas de usuario ${idCliente}`);
  }

  return await res.json();

  // return cards.filter((c) => c.idCliente == idCliente);
};

export const addCard = async (card) => {
  const res = await fetch(`${API_URL}/users/${card.idCliente}/cards`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(card)
  });

  if (!res.ok)
  {
    throw new Error('Error al agregar tarjeta');
  }
  
  return await res.json();
};

export const updateCard = async (newCard) => {
  // TODO: Reemplazar por la solicitud real al API

  const card = cards.find((c) => c.idTarjeta == newCard.idTarjeta);

  if (!card) {
    throw new Error("Tarjeta no encontrada");
  }

  card.cupoTotal = newCard.cupoTotal;
  card.cupoDisponible = newCard.cupoTotal;
};

export const inactivateCard = async (newCard) => {
  // TODO: Reemplazar por la solicitud real al API

  const card = cards.find((c) => c.idTarjeta == newCard.idTarjeta);

  if (!card) {
    throw new Error("Tarjeta no encontrada");
  }

  card.estado = newCard.estado;
};
