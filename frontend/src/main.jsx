import { createRoot } from "react-dom/client";
import { App } from "./App.jsx";
import { UserProvider } from "./context/UserContext";
import { CardProvider } from "./context/CardContext";
import "./index.css";

createRoot(document.getElementById("root")).render(
  <UserProvider>
    <CardProvider>
      <App />
    </CardProvider>
  </UserProvider>
);
