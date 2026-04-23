import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, RegisterRequest, LoginResponse, User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';
  private currentUserSubject: BehaviorSubject<string | null>;
  public currentUserEmail: Observable<string | null>;

  constructor(private http: HttpClient) {
    const email = localStorage.getItem('userEmail');
    this.currentUserSubject = new BehaviorSubject<string | null>(email);
    this.currentUserEmail = this.currentUserSubject.asObservable();
  }

  public get currentUserEmailValue(): string | null {
    return this.currentUserSubject.value;
  }

  register(request: RegisterRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/register`, request).pipe(
      tap(response => {
        if (response.token) {
          this.handleAuthSuccess(response.token, request.email);
        }
      })
    );
  }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request).pipe(
      tap(response => {
        if (response.token) {
          this.handleAuthSuccess(response.token, request.email);
        }
      })
    );
  }

  private handleAuthSuccess(token: string, email: string): void {
    localStorage.setItem('token', token);
    localStorage.setItem('userEmail', email);
    this.currentUserSubject.next(email);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userEmail');
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUserEmail(): string | null {
    return localStorage.getItem('userEmail');
  }
}
