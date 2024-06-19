import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { UpdateRecipeFormComponent } from '../update-recipe-form/update-recipe-form.component';
import { RecipeService } from '../../services/recipe/recipe.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule, CommonModule],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss',
})
export class RecipeCardComponent {
  @Input() recipe: any;
  user: any;

  constructor(
    public dialog: MatDialog,
    private recipeService: RecipeService,
    private authService: AuthService
  ) {}

  openEditRecipeForm() {
    this.dialog.open(UpdateRecipeFormComponent, { data: this.recipe });
  }

  deleteRecipe() {
    this.recipeService.deleteRecipe(this.recipe.id).subscribe();
  }

  likeRecipe() {
    this.recipeService
      .likeRecipe(this.recipe.id)
      .subscribe({ error: (error) => console.log('Error', error) });
  }

  ngOnInit() {
    const authState = this.authService.authSubject.value;
    this.user = authState.user;
  }
}
