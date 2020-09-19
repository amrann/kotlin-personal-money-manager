package com.example.nsntechandroid.main.transaction

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.main.home.TransactionControl
import com.example.nsntechandroid.main.transaction.dialog.Category
import com.example.nsntechandroid.main.transaction.dialog.CreateCategoryDialog
import com.example.nsntechandroid.main.transaction.dialog.SelectCategoryDialog
import com.example.nsntechandroid.main.transaction.dialog.SelectorDialogInteraction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.util.*

class TransactionFragment : Fragment(), SelectorDialogInteraction {

    private var inputAmount = "0"
    private var selectedCategory:Category? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnCreate.setOnClickListener {
            showCreateDialog()
        }

        selectorCategory.setOnClickListener {
            showCategorySelectorDialog()
        }

        setupCustomInput()

        btnSetAmount.setOnClickListener {
            val payload = TransactionControl(
                inputAmount.toInt(),
                Calendar.getInstance().timeInMillis.toString(),
                selectedCategory
            )
            saveToDatabase(payload)
        }
    }

    private fun showCreateDialog() {
        val categoryDialog = CreateCategoryDialog()
        categoryDialog.show(childFragmentManager, "CreateDialog")

    }

    private fun showCategorySelectorDialog() {
        val categorySelectorDialog = SelectCategoryDialog()
        categorySelectorDialog.listener = this
        categorySelectorDialog.show(childFragmentManager, "SelectorDialog")
    }

    override fun onCategorySelected(category: Category) {
        selectedCategory = category
        selectorCategory.typeface = ResourcesCompat.getFont(requireContext(),R.font.raleway_bold)
        selectorCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        selectorCategory.setTextColor(ContextCompat.getColor(requireContext(),R.color.textSecondary))
        selectorCategory.text = "${category.emoji}   ${category.name}"
    }

    private fun setupCustomInput() {
        btn0.setOnClickListener {
            if (inputAmount != "0") {
                inputAmount += "0"
            }
            updateAmount()
        }

        btn1.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "1"
            } else {
                inputAmount += "1"
            }
            updateAmount()
        }

        btn2.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "2"
            } else {
                inputAmount += "2"
            }
            updateAmount()
        }
        btn3.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "3"
            } else {
                inputAmount += "3"
            }
            updateAmount()
        }

        btn4.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "4"
            } else {
                inputAmount += "4"
            }
            updateAmount()
        }
        btn5.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "5"
            } else {
                inputAmount += "5"
            }
            updateAmount()
        }
        btn6.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "6"
            } else {
                inputAmount += "6"
            }
            updateAmount()
        }
        btn7.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "7"
            } else {
                inputAmount += "7"
            }
            updateAmount()
        }

        btn8.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "8"
            } else {
                inputAmount += "8"
            }
            updateAmount()
        }

        btn9.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "9"
            } else {
                inputAmount += "9"
            }
            updateAmount()
        }

        btn000.setOnClickListener {
            if (inputAmount != "0") {
                inputAmount += "000"
            }
            updateAmount()
        }

        btnBack.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "0"
            } else {
                if (inputAmount.length == 1) {
                    inputAmount = "0"
                } else {
                    inputAmount = inputAmount.dropLast(1)
                }
            }
            updateAmount()
        }
    }

    private fun updateAmount() {
        txtAmount.text = inputAmount.toInt().toRupiahFormat()
        btnSetAmount.isEnabled = inputAmount != "0" && selectedCategory != null
    }

    private fun saveToDatabase(payload: TransactionControl) {
        performLoading(true)
        //mendapatkan id dari user saat register
        val uid = auth.uid
        //menginisiasi instance database
        val database = Firebase.database
        //menambahkan referensi
        val myRef = database.getReference("users/$uid/transactions")

        myRef.push().setValue(payload).addOnCompleteListener {
            if (!it.isSuccessful) {
                Toast.makeText(
                    requireContext(), "Error created. ${it.exception?.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                resetForm()
                Toast.makeText(
                    requireContext(), "Transaction Created.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            performLoading(false)
        }
    }

    private fun performLoading(isLoading: Boolean) {
        if (isLoading) {
            btnSetAmount.visibility = View.INVISIBLE
            transactionProgress.visibility = View.VISIBLE
            btnSetAmount.isEnabled = false
        } else {
            btnSetAmount.visibility = View.VISIBLE
            transactionProgress.visibility = View.INVISIBLE
            btnSetAmount.isEnabled = true
        }
    }

    private fun resetForm() {
        inputAmount = "0"
        selectedCategory = null
        selectorCategory.typeface = ResourcesCompat.getFont(requireContext(), R.font.questrial)
        selectorCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        selectorCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        selectorCategory.setText(R.string.category)
    }
}
