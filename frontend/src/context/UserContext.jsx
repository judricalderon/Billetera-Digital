import { createContext, useState } from "react";
import { getUsers } from "../services/userService";

// Context y Provider
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [users, setUsers] = useState([]);

  const loadUsers = async () => {
    setUsers([]);

    const list = await getUsers();
    setUsers(list);
  };

  const addUser = async (userData) => {
    await createUser(userData);
    loadUsers();
  };

  return (
    <UserContext.Provider value={{ users, setUsers, loadUsers }}>
      {children}
    </UserContext.Provider>
  );
};
