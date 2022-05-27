import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { HomepageComponent } from './pages/homepage/homepage.component';



@NgModule({
  declarations: [
    HomepageComponent
  ],
  imports: [
    CommonModule,
    SharedModuleModule
  ]
})
export class HomeModule { }
