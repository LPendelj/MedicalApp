import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './features/header/header/header.component';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
import { CoreModule } from './core/core.module';
import { SharedModuleModule } from './shared/shared-module/shared-module.module';
import { NgbModule, NgbPagination } from '@ng-bootstrap/ng-bootstrap';
//import { OrganizationsModule } from './features/organizations/organizations.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    SharedModuleModule,
    //OrganizationsModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
