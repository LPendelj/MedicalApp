import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './features/header/header/header.component';
import { OrganizationsComponent } from './features/organizations/pages/organizations/organizations.component';
import { PatientsComponent } from './features/patients/pages/patients/patients.component';
import { ExaminationsComponent } from './features/examinations/pages/examinations/examinations.component';
import { MedicsComponent } from './features/medics/pages/medics/medics.component';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
        OrganizationsComponent,
        PatientsComponent,
        ExaminationsComponent,
        MedicsComponent,
        PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
