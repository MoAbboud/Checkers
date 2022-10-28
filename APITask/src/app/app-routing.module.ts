import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReciperestaurantSearchComponent } from './reciperestaurant-search/reciperestaurant-search.component';

const routes: Routes = [
  {path:'reciperestaurant-search', component: ReciperestaurantSearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
