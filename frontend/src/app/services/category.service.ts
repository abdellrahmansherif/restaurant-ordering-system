import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FoodCategory } from '../models/food.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<FoodCategory[]> {
    return this.http.get<FoodCategory[]>(this.apiUrl);
  }

  getCategory(id: number): Observable<FoodCategory> {
    return this.http.get<FoodCategory>(`${this.apiUrl}/${id}`);
  }

  addCategory(categoryName: string): Observable<FoodCategory> {
    return this.http.post<FoodCategory>(`${this.apiUrl}/AddCategory`, { categoryName });
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
