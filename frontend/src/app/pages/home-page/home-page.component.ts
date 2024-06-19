import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';
import { AuthService } from '../../services/auth/auth.service';
import { RecipeService } from '../../services/recipe/recipe.service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RecipeCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss',
})
export class HomePageComponent {
  recipes: any = [];

  constructor(
    public dialog: MatDialog,
    public authService: AuthService,
    private recipeService: RecipeService
  ) {}

  openCreateRecipeForm() {
    this.dialog.open(CreateRecipeFormComponent);
  }

  ngOnInit() {
    this.recipeService.getRecipes().subscribe();
    this.recipeService.recipeSubject.subscribe((state) => {
      this.recipes = state.recipes;
    });
  }
}
