import {Component, OnInit} from '@angular/core';
import {ApiTaskService} from '../API/api-task.service';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './reciperestaurant-search.component.html',
  styleUrls: ['./reciperestaurant-search.component.css']
})
export class ReciperestaurantSearchComponent implements OnInit {

  //Declaring some varibles that will store the data from the API request.
  DataRowRecipe: any;
  DataRowRestaurant: any;
  DataRowVenues: any;
  search = "";
  //Set the mode to know which api function to use, either recipe or restaurant.
  Mode = "Recipe";

  constructor(private apiService: ApiTaskService) {
  }

  ngOnInit(): void {
    if (this.Mode == "Recipe") {
      //if the Mode is recipe then we need to use the edamame API to collect results
      //for recipes to be displayed for the user.
      //The getRecipe function takes in the "this.search" parameter that represents
      //the user input in the search field.
      this.apiService.getRecipe(this.search).subscribe((data) => {
        console.log(data);
        //The data will be stored into the DataRowRecipe object and will reset the
        //the other object so that it doesn't keep displaying results if it already
        //carries data from a previous search.
        // @ts-ignore
        this.DataRowRecipe = data['hits'];
        this.DataRowVenues = null;
      });
    } else if (this.Mode == "Restaurant") {
      //if the Mode is Restaurant then we need to use the foursquare API to collect results
      //for nearby restaurants to be displayed for the user.
      this.apiService.getRestaurant(this.search).subscribe((data) => {
        console.log(data);
        //Same as if statement above..... but working for the opposite way, filling
        //the restaurant object and reseting the recipe object.
        // @ts-ignore
        this.DataRowRestaurant = data['response'];
        this.DataRowVenues = this.DataRowRestaurant["venues"];
        this.DataRowRecipe = null;
      });
    }
  }

  getRecipeSearch(event: string) {
    //The onclick function for the search button, it will trigger the function
    //ngOnInit to start again and take in a new parameter "this.search" which is the user
    //input in the input element.
    console.log(this.search);
    this.ngOnInit();
  }

  onItemChange(event: any) {
    //get the id of the radio button that is currently checked.
    this.Mode = (<HTMLInputElement>event.target).id;
    console.log(this.Mode);
  }

}
