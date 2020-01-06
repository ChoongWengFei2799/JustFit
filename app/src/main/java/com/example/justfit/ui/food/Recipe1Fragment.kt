package com.example.justfit.ui.food


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.justfit.R
import com.example.justfit.databinding.FragmentRecipe1Binding

class Recipe1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecipe1Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipe_1,
            container,
            false
        )

        binding.textView9.text = "Ingredients: \n1. 4 large sweet potatoes \n2. 1 tbsp. plain flour \n3. 1/2 tsp. salt \n4. 1/2 tsp. black pepper \n5. 1/2 tbsp. Coconut Oil (melted) \n\nFor the rest:\n1. 4 150g tuna steaks\n2. 2 tbsp. pink peppercorns \n3. 1 tsp. coarse sea salt \n4. 1 tbsp. Coconut Oil \n5. 1 lemon (cut into wedges)"

        binding.next.setOnClickListener {
            binding.textView9.text =
                "1. First, prepare the sweet potatoes. Scrub clean each potato and prick each all over with a fork. Place onto a microwavable plate and microwave on high for 4-5 minutes, then remove from the microwave and allow to cool for a minute or two.  \n2. Once cool enough to touch, cut the sweet potatoes into wedges. Sprinkle the flour, salt, pepper and melted coconut oil over the wedges and shake them about a little to coat them (this will make them super-crispy). Pop them onto a baking tray and bake at 200°C for 15-20 minutes.  \n3. When the sweet potato fries are nearly ready, it’s time to cook your tuna steaks. Crush the peppercorns using a pestle and mortar, or if you don’t have one, you can place them onto a board and use a rolling pin to crush them. \n4. Coat each steak with pink peppercorns and sea salt, then place a large frying or griddle pan onto a high heat with the coconut oil. When the pan just about begins to smoke, add the tuna steaks and fry on each side for 45 seconds if you prefer seared tuna, or 2 minutes on each side if you prefer it cooked through.  \n5. Serve with lemon wedges and your favourite vegetables, then divide the tuna steaks and sweet potato wedges amongst meal prep boxes.Serve with lemon wedges and your favourite vegetables, then divide the tuna steaks and sweet potato wedges amongst meal prep boxes. \n6. Store in airtight containers in the refrigerator for up to 3 days. When ready to eat, remove the lid and place it loosely back on top, leaving a little gap. Microwave on high for 3 1/2 minutes or until piping hot. Allow to stand for 1 minute before eating."
            binding.next.visibility = View.INVISIBLE
        }
        return binding.root
    }
}
