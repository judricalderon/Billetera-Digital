import cards from "../data/cards.json";

export const getCardsByClient = async (idCliente) => {
  // TODO: Reemplazar por la solicitud real al API

  return cards.filter((c) => c.idCliente == idCliente);
};

export const addCard = async (card) => {
  // TODO: Reemplazar por la solicitud real al API

  cards.push(card);
};
