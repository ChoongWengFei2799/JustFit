package com.example.justfit.ui.exercise


import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.justfit.Exercise
import com.example.justfit.R
import com.example.justfit.databinding.FragmentExerciseHardBinding
import com.example.justfit.databinding.FragmentExerciseStartBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_exercise_start.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass.
 */
class ExerciseHardFragment : Fragment() {

    private lateinit var viewModel: ExerciseHardViewModel
    private lateinit var core: CountDownTimer
    private lateinit var timer: CountDownTimer
    private lateinit var timer2: CountDownTimer

    private var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Exercise")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseHardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_hard,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(ExerciseHardViewModel::class.java)

        val prefs = activity!!.getSharedPreferences("INFO", Context.MODE_PRIVATE)
        val pid = prefs.getString("Id", "Guest")

        viewModel.start()

        viewModel.ExerciseName.observe(this, Observer { ExerciseName ->
            binding.ExerciseName.text = ExerciseName
        })

        viewModel.image.observe(this, Observer { Count ->
            if (Count != 8) {
                viewModel.start()
                setImage(Count, pid)
            } else {
                view?.findNavController()
                    ?.navigate(R.id.action_exerciseHardFragment_to_exerciseDoneFragment)
            }
        })

        viewModel.Time.observe(this, Observer { Time ->
            binding.time.text = Time
        })

        binding.button6.setOnClickListener {
            core.cancel()
            timer2.cancel()
            timer.cancel()

            view?.findNavController()
                ?.navigate(R.id.action_exerciseHardFragment_to_navigation_home)
        }

        return binding.root
    }

    private fun setImage(Count: Int, pid: String) {
        when (Count) {
            0 -> setJump(pid)
            1, 5 -> setClimb(pid)
            2, 6 -> setPlank(pid)
            3 -> setLeg(pid)
            4, 7 -> setCrunch(pid)
        }
    }

    private fun setJump(pid:String) {
        core = object : CountDownTimer(40000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer2 = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.jump2)
                    }

                    override fun onFinish() {

                    }
                }

                timer = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.jump1)
                    }

                    override fun onFinish() {
                        timer2.start()
                    }
                }
                timer.start()
            }

            override fun onFinish() {
                var id = myRef.push().key
                if (id != null) {
                    val time = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formatted = time.format(formatter)

                    myRef.child(id).setValue(Exercise(pid, "Jumping Jacks", formatted, 200))
                }
            }
        }
        core.start()
    }

    private fun setClimb(pid:String) {
        core = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer2 = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.climb2)
                    }

                    override fun onFinish() {

                    }
                }

                timer = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.climb1)
                    }

                    override fun onFinish() {
                        timer2.start()
                    }
                }

                timer.start()
            }

            override fun onFinish() {
                var id = myRef.push().key
                if (id != null) {
                    val time = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formatted = time.format(formatter)

                    myRef.child(id).setValue(Exercise(pid, "Mountain Climber", formatted, 160))
                }
            }
        }
        core.start()
    }

    private fun setPlank(pid:String) {
        core = object : CountDownTimer(40000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                imageView.setImageResource(R.drawable.plank)
            }
            override fun onFinish() {
                var id = myRef.push().key
                if (id != null) {
                    val time = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formatted = time.format(formatter)

                    myRef.child(id).setValue(Exercise(pid,"Plank", formatted, 120))
                }
            }
        }
        core.start()
    }

    private fun setLeg(pid:String) {
        core = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer2 = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.leg2)
                    }

                    override fun onFinish() {

                    }
                }

                timer = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.leg1)
                    }

                    override fun onFinish() {
                        timer2.start()
                    }
                }

                timer.start()
            }

            override fun onFinish() {
                var id = myRef.push().key
                if (id != null) {
                    val time = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formatted = time.format(formatter)

                    myRef.child(id).setValue(Exercise(pid, "Leg Raises", formatted, 80))
                }
            }
        }
        core.start()
    }

    private fun setCrunch(pid:String) {
        core = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer2 = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.crunch2)
                    }

                    override fun onFinish() {

                    }
                }

                timer = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        imageView.setImageResource(R.drawable.crunch1)
                    }

                    override fun onFinish() {
                        timer2.start()
                    }
                }

                timer.start()
            }

            override fun onFinish() {
                var id = myRef.push().key
                if (id != null) {
                    val time = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formatted = time.format(formatter)

                    myRef.child(id).setValue(Exercise(pid,"Abdominal Crunches", formatted, 140))
                }
            }
        }
        core.start()
    }
}
