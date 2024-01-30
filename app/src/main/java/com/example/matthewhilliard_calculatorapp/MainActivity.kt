package com.example.matthewhilliard_calculatorapp

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculation = findViewById<EditText>(R.id.calculation)

        val zeroButton = findViewById<Button>(R.id.zeroButton)
        val oneButton = findViewById<Button>(R.id.oneButton)
        val twoButton = findViewById<Button>(R.id.twoButton)
        val threeButton = findViewById<Button>(R.id.threeButton)
        val fourButton = findViewById<Button>(R.id.fourButton)
        val fiveButton = findViewById<Button>(R.id.fiveButton)
        val sixButton = findViewById<Button>(R.id.sixButton)
        val sevenButton = findViewById<Button>(R.id.sevenButton)
        val eightButton = findViewById<Button>(R.id.eightButton)
        val nineButton = findViewById<Button>(R.id.nineButton)
        val addButton = findViewById<Button>(R.id.plusButton)
        val subtractButton = findViewById<Button>(R.id.minusButton)
        val multiplyButton = findViewById<Button>(R.id.timesButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val periodButton = findViewById<Button>(R.id.periodButton)
        val equalButton = findViewById<Button>(R.id.equalButton)
        val backspaceButton = findViewById<Button>(R.id.backspaceButton)
        val sqrtButton = findViewById<Button>(R.id.sqrtButton)

        zeroButton.setOnClickListener(){
            calculation.text.append("0")
        }

        oneButton.setOnClickListener(){
            calculation.text.append("1")
        }

        twoButton.setOnClickListener(){
            calculation.text.append("2")
        }

        threeButton.setOnClickListener(){
            calculation.text.append("3")
        }

        fourButton.setOnClickListener(){
            calculation.text.append("4")
        }

        fiveButton.setOnClickListener(){
            calculation.text.append("5")
        }

        sixButton.setOnClickListener(){
            calculation.text.append("6")
        }

        sevenButton.setOnClickListener(){
            calculation.text.append("7")
        }

        eightButton.setOnClickListener(){
            calculation.text.append("8")
        }

        nineButton.setOnClickListener(){
            calculation.text.append("9")
        }

        backspaceButton.setOnClickListener(){
            if(calculation.text.isNotEmpty()){
                val trimmed = calculation.text.substring(0, calculation.text.length-1)
                calculation?.setText(trimmed)
            }
        }

        addButton.setOnClickListener(){
            if(calculation.text.isNotEmpty() && calculation.text.last().isDigit()){
                calculation.inputType = InputType.TYPE_CLASS_TEXT
                calculation.text.append("+")
                calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            }
        }

        subtractButton.setOnClickListener(){
            if(calculation.text.isEmpty() || calculation.text.last().isDigit()){
                calculation.inputType = InputType.TYPE_CLASS_TEXT
                calculation.text.append("-")
                calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            }
        }

        multiplyButton.setOnClickListener(){
            if(calculation.text.isNotEmpty() && calculation.text.last().isDigit()){
                calculation.inputType = InputType.TYPE_CLASS_TEXT
                calculation.text.append("*")
                calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            }
        }

        divideButton.setOnClickListener(){
            if(calculation.text.isNotEmpty() && calculation.text.last().isDigit()){
                calculation.inputType = InputType.TYPE_CLASS_TEXT
                calculation.text.append("/")
                calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            }
        }

        periodButton.setOnClickListener(){
            if(calculation.text.isEmpty()){
                calculation.inputType = InputType.TYPE_CLASS_TEXT
                calculation.text.append(".")
                calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
            }
            else if(calculation.text.last() != '.'){
                var decimalFound = false
                for(i in calculation.text.length -1 downTo 0){
                    if(calculation.text[i] == '.'){
                        decimalFound = true
                        break
                    }
                    else if(!calculation.text[i].isDigit()){
                        break
                    }
                }
                if(!decimalFound) {
                    calculation.inputType = InputType.TYPE_CLASS_TEXT
                    calculation.text.append(".")
                    calculation.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
                }
            }
        }

        sqrtButton.setOnClickListener(){
            val currNum: String = calculation.text.toString()
            var containsOperand = false
            if(calculation.text.isNotEmpty()) {
                if(currNum[0] == '-'){
                    Toast.makeText(this, "Cannot square root negatives", Toast.LENGTH_SHORT).show()
                }
                for (i in currNum.indices) {
                    if (!(calculation.text[i].isDigit() || calculation.text[i] == '.')) {
                        containsOperand = true
                    }
                }
                if (!containsOperand) {
                    val sqrtNum: Double = sqrt(currNum.toDouble())
                    calculation.setText(sqrtNum.toString())
                }
                else{
                    Toast.makeText(this, "Cannot contain operand", Toast.LENGTH_SHORT).show()
                }
            }
        }

        equalButton.setOnClickListener(){
            if(calculation.text.isNotEmpty()){
                val currNum: String = calculation.text.toString()
                var firstOpp = true
                var numOne = ""
                var operand = ""
                var numTwo = ""
                for(i in currNum.indices){
                    if(i == 0 && currNum[i] == '-'){
                        numOne+=currNum[i]
                    }
                    else if(operand.isEmpty() && firstOpp && (currNum[i].isDigit() || currNum[i] == '.')){
                        numOne+=currNum[i]
                    }
                    else if (currNum[i].isDigit() || currNum[i] == '.'){
                        numTwo+=currNum[i]
                    }
                    else if(!currNum[i].isDigit() && operand.isEmpty()){
                        operand+=currNum[i]
                    }
                    else{
                        val numOneDouble : Double = numOne.toDouble()
                        val numTwoDouble : Double = numTwo.toDouble()
                        if(operand == "+"){
                            numOne = (numOneDouble + numTwoDouble).toString()
                        }
                        if(operand == "-"){
                            numOne = (numOneDouble - numTwoDouble).toString()
                        }
                        if(operand == "*"){
                            numOne = (numOneDouble * numTwoDouble).toString()
                        }
                        if(operand == "/"){

                            numOne = (numOneDouble / numTwoDouble).toString()
                        }
                        operand = currNum[i].toString()
                        numTwo = ""
                        firstOpp = false
                    }
                    if(i == currNum.length-1 && numOne.isNotEmpty() && operand.isNotEmpty() && numTwo.isNotEmpty()){
                        val numOneDouble : Double = numOne.toDouble()
                        val numTwoDouble : Double = numTwo.toDouble()
                        if(operand == "+"){
                            numOne = (numOneDouble + numTwoDouble).toString()
                        }
                        else if(operand == "-"){
                            numOne = (numOneDouble - numTwoDouble).toString()
                        }
                        else if(operand == "*"){
                            numOne = (numOneDouble * numTwoDouble).toString()
                        }
                        else if(operand == "/") {
                            if(numTwoDouble-0.00000000000001 < 0){
                                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                                break
                            }
                            else{
                                numOne = (numOneDouble / numTwoDouble).toString()
                            }
                        }
                        operand = currNum[i].toString()
                        numTwo = ""
                        firstOpp = false
                    }
                }
                calculation.setText(numOne)
            }
        }
    }
}