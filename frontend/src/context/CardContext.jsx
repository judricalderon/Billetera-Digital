import { createContext, useState } from 'react';
import { getCardsByClient, addCard as addCardService } from '../services/cardService';

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
    setCards(prev => [...prev, cardData]);
  };

  return (
    <CardContext.Provider value={{ cards, loadCards, addCard }}>
      {children}
    </CardContext.Provider>
  );
};