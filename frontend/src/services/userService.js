import users from "../data/users.json";

const API_URL = "http://localhost:8080/api";

export const getUsers = async () => {
  const res = await fetch(`${API_URL}/users`);

  if (!res.ok) {
    throw new Error("Error al obtener usuarios");
  }

  return await res.json();

  // return users;
};

export async function createUser(user) {
  const res = await fetch(`${API_URL}/users`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });

  if (!res.ok) {
    throw new Error("Error al crear usuario");
  }

  return await res.json();
}
