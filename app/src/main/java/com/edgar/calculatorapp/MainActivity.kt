package com.edgar.calculatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.edgar.calculatorapp.databinding.ActivityMainBinding
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var val1 = Double.NaN
    private var val2 = 0.0
    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val EQU = '='
    private val EXTRA = '@'
    private val MODULUS = '%'
    private var ACTION = 0.toChar()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 1
        }

        binding.button2.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 2
        }

        binding.button3.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 3
        }

        binding.button4.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 4
        }

        binding.button5.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 5
        }

        binding.button6.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 6
        }

        binding.button7.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 7
        }

        binding.button8.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 8
        }

        binding.button9.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 9
        }

        binding.button0.setOnClickListener {
            ifErrorOnOutput()
            exceedLength()
            binding.input.text = binding.input.text.toString() + 0
        }

        binding.buttonDot.setOnClickListener {
            exceedLength()
            binding.input.text = binding.input.text.toString() + "."
        }

        binding.buttonPara1.setOnClickListener {

            if (binding.input.text.isNotEmpty()) {
                ACTION = MODULUS
                operation()
                if (!val1.equals(Double)) {
                    binding.output.text = "$val1 %"
                } else {
                    binding.output.text = "${val1.toInt()} %"
                }
                binding.input.text = null
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonAdd.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                ACTION = ADDITION
                operation()
                binding.output.text = "${val1.toInt()} + "
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonSub.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                ACTION = SUBTRACTION
                operation()
                binding.output.text = "${val1.toInt()} - "
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonMulti.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                ACTION = MULTIPLICATION
                operation()

                binding.output.text = "${val1.toInt()} x "
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonDivide.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                ACTION = DIVISION
                operation()
                binding.output.text = "${val1.toInt()} / "
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonPara2.setOnClickListener {
            if (binding.input.text.toString().isNotEmpty() || binding.output.text.toString()
                    .isNotEmpty()
            ) {
                val1 = binding.input.text.toString().toDouble()
                ACTION = EXTRA
                binding.output.text = "-" + binding.input.text.toString()
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonEqual.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                operation()
                ACTION = EQU
                binding.output.text = removeZeroAfterDot(val1.absoluteValue.toString())
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonClear.setOnClickListener {
            val1 = Double.NaN
            val2 = Double.NaN
            binding.input.text = ""
            binding.output.text = ""
        }
    }

    private fun operation() {
        if (!(val1).isNaN()) {
            if (binding.output.text[0] == '-') {
                val1 *= (-1)
            }

            val2 = binding.input.text.toString().toDouble()

            when (ACTION) {
                ADDITION -> val1 += val2
                SUBTRACTION -> val1 -= val2
                MULTIPLICATION -> val1 *= val2
                DIVISION -> val1 /= val2
                EXTRA -> val1 *= -1
                MODULUS -> val1 %= val2
            }
        } else {
            val1 = binding.input.text.toString().toDouble()
        }
    }

    private fun exceedLength() {

        when {
            binding.input.text.toString().length > 24 -> {
                findViewById<TextView>(R.id.input).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
            }
            binding.input.text.toString().length > 16 -> {
                findViewById<TextView>(R.id.input).setTextSize(TypedValue.COMPLEX_UNIT_SP, 26F)
            }
            binding.input.text.toString().length > 12 -> {
                findViewById<TextView>(R.id.input).setTextSize(TypedValue.COMPLEX_UNIT_SP, 36F)
            }
            else -> {
                findViewById<TextView>(R.id.input).setTextSize(TypedValue.COMPLEX_UNIT_SP, 46F)
            }
        }

    }

    private fun ifErrorOnOutput() {
        if (binding.output.text.toString() == "Error") {
            binding.output.text = ""
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = val2.toString()
        if (result.endsWith(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }
}

