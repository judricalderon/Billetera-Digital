export const calculateBrand = (numero) => {
  // MasterCard: 16 dígitos, primeros 2 en 51-55
  if (/^5[1-5]\d{14}$/.test(numero)) return "MASTERCARD";

  // Visa: 16 dígitos, inicia con 4
  if (/^4\d{15}$/.test(numero)) return "VISA";

  // Amex: 15 dígitos, inicia con 34 o 37
  if (/^3[47]\d{13}$/.test(numero)) return "AMEX";

  throw new Error("Número de tarjeta inválido");
};

export const isExpiryValid = (expiry) => {
  const [mm, yyyy] = expiry.split("/").map(Number);

  if (!Number.isInteger(mm) || !Number.isInteger(yyyy) || mm < 1 || mm > 12) {
    return false; // Formato inválido
  }

  const now = new Date();
  const currentYear = now.getFullYear();
  const currentMonth = now.getMonth() + 1; // getMonth() es 0–11

  if (yyyy > currentYear) {
    return true;
  }
  
  if (yyyy === currentYear && mm >= currentMonth) {
    return true;
  }
  return false;
};
