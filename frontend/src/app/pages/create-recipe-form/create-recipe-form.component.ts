import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { RecipeService } from '../../services/recipe/recipe.service';

@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatRadioModule,
  ],
  templateUrl: './create-recipe-form.component.html',
  styleUrl: './create-recipe-form.component.scss',
})
export class CreateRecipeFormComponent {
  recipeItem: any = {
    title: '',
    description: '',
    image: '',
    foodType: '',
  };

  constructor(private recipeService: RecipeService) {}

  onSubmit() {
    this.recipeService.createRecipe(this.recipeItem).subscribe({
      next: (data: any) => console.log('Created recipe', data),
      error: (error: any) => console.log('Error', error),
    });
  }
}
