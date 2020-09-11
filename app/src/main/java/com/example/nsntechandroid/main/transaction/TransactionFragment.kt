package com.example.nsntechandroid.main.transaction

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

import com.example.nsntechandroid.R
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.main.transaction.dialog.Category
import com.example.nsntechandroid.main.transaction.dialog.CreateCategoryDialog
import com.example.nsntechandroid.main.transaction.dialog.SelectCategoryDialog
import com.example.nsntechandroid.main.transaction.dialog.SelectorDialogInteraction
import kotlinx.android.synthetic.main.fragment_transaction.*

// Meran
//class TransactionFragment : Fragment(), SelectorDialogInteraction {
//
//    private var inputAmount = "0"
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_transaction, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        btnCreate.setOnClickListener {
//            showCreateDialog()
//        }
//
//        selectorCategory.setOnClickListener{
//            showCategorySelectorDialog()
//        }
////
//        setupCustomInput()
//    }
//
//    private fun showCreateDialog() {
//        val categoryDialog = CreateCategoryDialog()
//        categoryDialog.show(childFragmentManager, "CreateDialog")
//    }
//
//    private fun showCategorySelectorDialog() {
//        val categorySelectorDialog = SelectCategoryDialog()
//        categorySelectorDialog.listener = this
//        categorySelectorDialog.show(childFragmentManager, "SelectorDialog")
//    }
//
//    override fun onCategorySelected(category: Category) {
//        selectorCategory.text = category.name
//    }
//
////    override fun onCategorySelected(category: Category) {
////        selectorCategory.typeface = ResourcesCompat.getFont(requireContext(), R.font.raleway_bold)
////        selectorCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
////        selectorCategory.setTextColor(ContextCompat.getColor(requireContext(), R.color.textSecondary))
////        selectorCategory.text = "${category.emoji}  ${category.name}"
////    }
//
//    private fun setupCustomInput() {
//        btn0.setOnClickListener {
//            if(inputAmount != "0") {
//                inputAmount += "0"
//            }
//            updateAmount()
//        }
//        btn1.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "1"
//            } else {
//                inputAmount += "1"
//            }
//            updateAmount()
//        }
//        btn2.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "2"
//            } else {
//                inputAmount += "2"
//            }
//            updateAmount()
//        }
//        btn3.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "3"
//            } else {
//                inputAmount += "3"
//            }
//            updateAmount()
//        }
//        btn4.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "4"
//            } else {
//                inputAmount += "4"
//            }
//            updateAmount()
//        }
//        btn5.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "5"
//            } else {
//                inputAmount += "5"
//            }
//            updateAmount()
//        }
//        btn6.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "6"
//            } else {
//                inputAmount += "6"
//            }
//            updateAmount()
//        }
//        btn7.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "7"
//            } else {
//                inputAmount += "7"
//            }
//            updateAmount()
//        }
//        btn8.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "8"
//            } else {
//                inputAmount += "8"
//            }
//            updateAmount()
//        }
//        btn9.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "9"
//            } else {
//                inputAmount += "9"
//            }
//            updateAmount()
//        }
//        btn000.setOnClickListener {
//            if(inputAmount != "0") {
//                inputAmount += "000"
//            }
//            updateAmount()
//        }
//        btnBack.setOnClickListener {
//            if(inputAmount == "0") {
//                inputAmount = "0"
//            } else {
//                if (inputAmount.length == 1) {
//                    inputAmount = "0"
//                } else {
//                    inputAmount = inputAmount.dropLast(1)
//                }
//            }
//            updateAmount()
//        }
//    }
//
//    private fun updateAmount() {
//        txtAmount.text = inputAmount.toInt().toRupiahFormat()
//    }
//}


class TransactionFragment : Fragment(), SelectorDialogInteraction {

    private var inputAmount = "0"

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
        selectorCategory.typeface = ResourcesCompat.getFont(requireContext(),R.font.raleway_bold)
        selectorCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        selectorCategory.setTextColor(ContextCompat.getColor(requireContext(),R.color.textSecondary))
        selectorCategory.text = "${category.emoji}   ${category.name}"
    }

    private fun setupCustomInput() {
        btn0.setOnClickListener {
            if(inputAmount != "0") {
                inputAmount += "0"
            }
            updateAmount()
        }
        btn1.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "1"
            } else {
                inputAmount += "1"
            }
            updateAmount()
        }
        btn2.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "2"
            } else {
                inputAmount += "2"
            }
            updateAmount()
        }
        btn3.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "3"
            } else {
                inputAmount += "3"
            }
            updateAmount()
        }
        btn4.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "4"
            } else {
                inputAmount += "4"
            }
            updateAmount()
        }
        btn5.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "5"
            } else {
                inputAmount += "5"
            }
            updateAmount()
        }
        btn6.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "6"
            } else {
                inputAmount += "6"
            }
            updateAmount()
        }
        btn7.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "7"
            } else {
                inputAmount += "7"
            }
            updateAmount()
        }
        btn8.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "8"
            } else {
                inputAmount += "8"
            }
            updateAmount()
        }
        btn9.setOnClickListener {
            if(inputAmount == "0") {
                inputAmount = "9"
            } else {
                inputAmount += "9"
            }
            updateAmount()
        }
        btn000.setOnClickListener {
            if(inputAmount != "0") {
                inputAmount += "000"
            }
            updateAmount()
        }
        btnBack.setOnClickListener {
            if(inputAmount == "0") {
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
    }
}
