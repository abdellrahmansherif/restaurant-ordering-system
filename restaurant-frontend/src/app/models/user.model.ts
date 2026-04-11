export interface User {
  userId?: number;
  userName: string;
  email: string;
  phoneNumber?: string;
  role?: string;
}

export interface RegisterRequest {
  userName: string;
  email: string;
  password: string;
  phoneNumber?: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: User;
}
