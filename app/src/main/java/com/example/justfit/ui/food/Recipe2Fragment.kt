package com.example.justfit.ui.food


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.justfit.R
import com.example.justfit.databinding.FragmentRecipe2Binding

class Recipe2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecipe2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipe_2,
            container,
            false
        )

        binding.textView9.text = "Ingredients: \n1. 50g harissa paste \n2. 1 tsp. extra-virgin olive oil \n3. Pinch sea salt \n4. 3 chicken breasts (try skin on for extra flavour) \n5. 180g (dry weight) bulgar wheat or couscous \n6. 40g parsley (stems and leaves) \n7. 20g mint leaves \n8. 6-8 spring onions \n9. ½ cucumber \n10. 4 tomatoes \n\nFor the dressing:\n1. 6 tbsp. Greek yoghurt \n2. ½ lemon (juice and zest) \n3. 1 clove garlic (minced) \n4. Pinch sea salt \n5. Handful pomegranate seeds (optional)"

        binding.next.setOnClickListener {
            binding.textView9.text =
                "Method For the chicken:  \n1. Preheat oven to 190°C. In a small bowl, mix the harissa paste, olive oil, and a pinch of salt. Score the tops of the chicken breasts with a sharp knife, then rub the harissa mixture over the chicken breasts and into the score lines.  \n2. Place a sheet of greaseproof paper on to the bottom of a deep baking tray and place the chicken breasts on top. Cover with another sheet of greaseproof paper. Bake for approximately 30 minutes or until the chicken is no longer pink in the centre.  \n\nFor the tabbouleh:  \n3. Whilst waiting, make the tabbouleh. Cook bulgar wheat or couscous according to the directions on the back of the pack. Once cooked, drain, pour into a large mixing bowl and separate the grains with a fork. Allow to cool.  \n4. Finely chop the parsley, mint leaves, spring onions, cucumber and tomatoes, then add them to the cooled bulgar wheat or couscous.  \n\nFor the dressing:  \n5. Simply combine Greek yoghurt, lemon juice and zest, minced garlic, and sea salt in a bowl.  Once all components are ready, divide amongst three Tupperware containers. Allow to cool, then refrigerate and store for up to 3 days."
            binding.next.visibility = View.INVISIBLE
        }
        return binding.root
    }
}
