import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ReciperestaurantSearchComponent } from './reciperestaurant-search/reciperestaurant-search.component';
import { HeaderComponent } from './header/header.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    ReciperestaurantSearchComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule, //Import HTTPClientModule
    AppRoutingModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
