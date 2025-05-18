import { Header } from "./components/Header";
import { UserTable } from "./components/UserTable";
import { Footer } from "./components/Footer";
import "./App.css";

export const App = () => {
  return (
    <div className="app-container">
      <Header />

      <main className="main">
        <UserTable />
      </main>

      <Footer />
    </div>
  );
};
