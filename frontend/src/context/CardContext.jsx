import { createContext, useState } from "react";

import {
  getCardsByClient,
  addCard as addCardService,
  updateCard as updateCardService,
  inactivateCard as inactivateCardService,
} from "../services/cardService";

export const CardContext = createContext();

export const CardProvider = ({ children }) => {
  const [cards, setCards] = useState([]);

  const loadCards = async (idCliente) => {
    setCards([]);

    const list = await getCardsByClient(idCliente);
    setCards(list);
  };

  const addCard = async (cardData) => {
    await addCardService(cardData);
    loadCards(cardData.idCliente);
  };

  const updateCard = async (cardData) => {
    await updateCardService(cardData);
    loadCards(cardData.idCliente);
  };

  const inactivateCard = async (cardData) => {
    await inactivateCardService(cardData);
    loadCards(cardData.idCliente);
  };

  return (
    <CardContext.Provider
      value={{ cards, loadCards, addCard, updateCard, inactivateCard }}
    >
      {children}
    </CardContext.Provider>
  );
};
