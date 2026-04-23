import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Register } from './components/register/register';
import { Menu } from './components/menu/menu';
import { Cart } from './components/cart/cart';

export const routes: Routes = [
  { path: '', redirectTo: '/menu', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'menu', component: Menu },
  { path: 'cart', component: Cart },
  { path: '**', redirectTo: '/menu' }
];
