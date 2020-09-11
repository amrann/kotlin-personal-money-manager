package com.example.nsntechandroid.main.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper

import com.example.nsntechandroid.R
import com.example.nsntechandroid.common.HorizontalMarginDekorator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var budgetCtrlAdapter: BudgetControlAdapter
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var transactionCtrlAdapter: TransactionControlAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupBudgetList()
        setupFilterList()
        setupTransactionList()
    }

    private fun setupBudgetList() {
        val dummyData = listOf(
            BudgetControl(
                "\uD83C\uDF54",
                "Fodd",
                250000,
                60000
            ),
            BudgetControl(
                "\uD83D\uDCE1",
                "Internet",
                250000,
                200000
            ),
            BudgetControl(
                "\uD83E\uDD73",
                "Hangout",
                1250000,
                1500000
            )
        )
        budgetCtrlAdapter = BudgetControlAdapter(dummyData)
        listBudgetControl.apply {
            adapter = budgetCtrlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val dekora = HorizontalMarginDekorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(dekora)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listBudgetControl)
    }

    private fun setupFilterList() {
        val dummyData = listOf("All", "Food", "Gas", "Book", "Internet")
        filterAdapter = FilterAdapter(dummyData)
        listFilter.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val dekora = HorizontalMarginDekorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(dekora)
        }
    }

    private fun setupTransactionList() {
        val dummyData = listOf(
            TransactionControl(
                "\uD83C\uDF72",
                "Food",
                -50000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCB5",
                "Royalty",
                +100000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCE1",
                "Quota",
                -80000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83C\uDF72",
                "Food",
                -50000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCB5",
                "Royalty",
                +100000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCE1",
                "Quota",
                -80000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCB5",
                "Royalty",
                +100000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83D\uDCE1",
                "Quota",
                -80000,
                "Sat, 08 July 2020"
            ),
            TransactionControl(
                "\uD83C\uDF72",
                "Food",
                -50000,
                "Sat, 08 July 2020"
            )
        )

        transactionCtrlAdapter = TransactionControlAdapter(dummyData)
        listTransaction.apply {
            adapter = transactionCtrlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }
}
