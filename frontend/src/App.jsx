import { Header } from "./components/Header";
import { Button } from "./components/Button";
import { UserTable } from "./components/UserTable";
import { Footer } from "./components/Footer";
import "./App.css";

export const App = () => {
  return (
    <div className="app-container">
      <Header />

      <main className="main">
        <Button />
        <UserTable />
      </main>

      <Footer />
    </div>
  );
};
