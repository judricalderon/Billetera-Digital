import Modal from "react-modal";
import { CreateCard } from "./CreateCard";
import { ViewCards } from "./ViewCards";
import "../index.css";

Modal.setAppElement("#root");

export const CardModal = ({ isOpen, onRequestClose, user }) => {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Tarjetas"
      className="modal"
      overlayClassName="overlay"
    >
      <div className="modal-header">
        <h2>Tarjetas de {user.nombreCompleto}</h2>
      </div>

      <div className="modal-body">
        <CreateCard user={user} />
        <ViewCards isOpen={isOpen} user={user} />
      </div>
    </Modal>
  );
};
