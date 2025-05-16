import users from "../data/users.json";

export function getUsers() {
  // TODO: Reemplazar por la solicitud real al API

  return new Promise((resolve) => {
    setTimeout(() => resolve(users), 500);
  });
}
