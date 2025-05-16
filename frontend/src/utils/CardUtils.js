export const calculateBrand = (numero) => {
  // MasterCard: 16 dígitos, primeros 2 en 51-55
  if (/^5[1-5]\d{14}$/.test(numero)) return "MASTERCARD";

  // Visa: 16 dígitos, inicia con 4
  if (/^4\d{15}$/.test(numero)) return "VISA";

  // Amex: 15 dígitos, inicia con 34 o 37
  if (/^3[47]\d{13}$/.test(numero)) return "AMEX";

  return "DESCONOCIDA";
};
