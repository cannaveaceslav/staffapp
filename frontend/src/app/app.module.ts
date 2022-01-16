import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { EmployeesComponent } from './employees/employees.component';
import { ItemsComponent } from './items/items.component';
import { ReportsComponent } from './reports/reports.component';
import { HomeComponent } from './home/home.component';
import { AddEmployeeComponent } from './employees/add-employee/add-employee.component';
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminComponent } from './admin/admin.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import {BasicAuthHttpInterceptorService} from "./service/basicauthhttpinterceptor.service";
// import {
//   MatToolbarModule,
//   MatTabsModule,
//   MatButtonModule,
//   MatInputModule,
//   MatDatepickerModule,
//   MatNativeDateModule,
//   MatCheckboxModule,
//   MatRadioModule
// } from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    EmployeesComponent,
    ItemsComponent,
    ReportsComponent,
    HomeComponent,
    AddEmployeeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    // MatToolbarModule,
    // MatTabsModule,
    // MatButtonModule,
    // MatInputModule,
    // MatDatepickerModule,
    // MatNativeDateModule,
    // MatCheckboxModule,
    // MatRadioModule,
    DragDropModule
  ],
  providers: [  {
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
