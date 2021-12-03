export const TOKEN_KEY = "@agenda_quiro-Token";
export const USER_ID = "@agenda_quiro-UserID";
export const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null;
export const getToken = () => localStorage.getItem(TOKEN_KEY);
export const getLoginUseId = () => localStorage.getItem(USER_ID);
export const login = (token, userId) => {
    localStorage.setItem(TOKEN_KEY, token);
    localStorage.setItem(USER_ID, userId);
};
export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
};
