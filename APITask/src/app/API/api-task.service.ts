import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class ApiTaskService {

  constructor(private http: HttpClient) {
  }
  //Initializing Keys and IDs
  API_Key_RecipeSearch = "4b05ed8faafb10bcee437a341a6957ef";
  APP_ID_RecipeSearch = "2c9782b9";

  Client_ID_RestaurantSearch = "RG12WB3X0XH5HQU4IBTMYJA2U3KFSDWJS3BMJ5BC0TCL3TDO";
  Client_Secret_RestaurantSearch = "5PZGJC5KS2K4XMBDAUIDVGJ4ENGX3MND2HBM4GRZ110J1RTP";

  title = 'APITask';

  public getRecipe(recipeSearch: string) {
    //This requests the Recipe API from edamam and attaches the APP ID and API Key for authentication.
    //It takes in a paramater which is the user input in the search field.
    return this.http.get("https://api.edamam.com/api/recipes/v2/?app_id="+ this.APP_ID_RecipeSearch +
        "&app_key="+ this.API_Key_RecipeSearch +"&type=public&q=" + recipeSearch);
  }

  public getRestaurant(restaurantSearch: string) {
    //This requests the Recipe API from foursquare and attaches the client ID and client secret for authentication.
    //It takes in a paramater which is the user input in the search field.
    return this.http.get("https://api.foursquare.com/v2/venues/search?client_id="
      + this.Client_ID_RestaurantSearch + "&client_secret="+ this.Client_Secret_RestaurantSearch +
      "&v=20180323&limit=15&ll=39.0997,-94.5786&intent=browse&radius=100000&categoryId=4d4b7105d754a06374d81259&query="
      + restaurantSearch);

  }
}
