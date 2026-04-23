import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Food, AddFoodRequest } from '../models/food.model';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private apiUrl = 'http://localhost:8080/foods';

  constructor(private http: HttpClient) { }

  getFood(id: number): Observable<Food> {
    return this.http.get<Food>(`${this.apiUrl}/${id}`);
  }

  addFood(request: AddFoodRequest): Observable<Food> {
    return this.http.post<Food>(`${this.apiUrl}/AddFood`, request);
  }

  deleteFood(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getFoodsByCategory(categoryId: number): Observable<Food[]> {
    return this.http.get<Food[]>(`${this.apiUrl}/category/${categoryId}`);
  }

  getAvailableFoods(): Observable<Food[]> {
    return this.http.get<Food[]>(`${this.apiUrl}/available`);
  }

  searchFoodsByName(name: string): Observable<Food[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Food[]>(`${this.apiUrl}/search`, { params });
  }
}
