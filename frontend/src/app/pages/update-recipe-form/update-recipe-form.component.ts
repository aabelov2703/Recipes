import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { RecipeService } from '../../services/recipe/recipe.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatRadioModule,
  ],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss',
})
export class UpdateRecipeFormComponent {
  recipeItem: any = {
    title: '',
    description: '',
    image: '',
    foodType: '',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public recipe: any,
    private recipeService: RecipeService
  ) {}

  onSubmit() {
    this.recipeService.updateRecipe(this.recipeItem).subscribe();
    this.recipeItem = this.recipe;
  }

  ngOnInit() {
    this.recipeItem = this.recipe;
  }
}
