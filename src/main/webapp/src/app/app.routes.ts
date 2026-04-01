import { Routes } from '@angular/router';
import { CreateAccount } from './create-account/create-account';
import { Login } from './login/login';
import { Dashboard } from './dashboard/dashboard';
import { CreateStudent } from './dashboard/create-student/create-student';

export const routes: Routes = [
    { path: 'register', component: CreateAccount},
    { path: 'login', component: Login},
    { path: 'dashboard', component: Dashboard, runGuardsAndResolvers: 'always'},
    { path: 'dashboard/create-student', component: CreateStudent},
    { path: '', redirectTo: 'login', pathMatch: 'full'}
];
