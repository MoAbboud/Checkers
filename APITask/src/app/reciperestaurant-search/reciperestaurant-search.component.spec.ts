import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReciperestaurantSearchComponent } from './reciperestaurant-search.component';

describe('RecipeSearchComponent', () => {
  let component: ReciperestaurantSearchComponent;
  let fixture: ComponentFixture<ReciperestaurantSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReciperestaurantSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReciperestaurantSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
