package com.example.nsntechandroid.main.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*

import com.example.nsntechandroid.R
import com.example.nsntechandroid.common.HorizontalMarginDekorator
import com.example.nsntechandroid.extension.toRupiahFormat
import com.example.nsntechandroid.main.transaction.dialog.Category
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var budgetCtrlAdapter: BudgetControlAdapter
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var transactionCtrlAdapter: TransactionControlAdapter

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private val transaksions: MutableList<TransactionControl> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtGreeting.text = "Hai, ${auth.currentUser?.displayName}"
//        Log.d(TAG, "email: ${auth.currentUser?.email}")

        setupBudgetList()
        setupFilterList()
        setupTransactionList()

        getData()
    }

    private fun getData() {
        idSwitcher.displayedChild = 0

        val transaksiRef = database.child("users").child(auth.uid ?: "").child("transactions")

        val transaksiListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "loadPost:dataChange ${dataSnapshot.toString()}")

                transaksions.clear()

                for (snapshot in dataSnapshot.children) {
                    val tr = snapshot.getValue<TransactionControl>()
                    tr?.key = snapshot.key
                    tr?.let {
                        transaksions.add(it)
                    }
                }
                updateUI()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        transaksiRef.addListenerForSingleValueEvent(transaksiListener)
    }

    private fun updateUI() {
        updateMonthlyOverview()
        updateBudgetControl()
        updateFilter()
        updateTransaction()
        idSwitcher.displayedChild = 1
    }

    private fun updateMonthlyOverview() {
        val total = transaksions.pendapatanBulanan - transaksions.pengeluaranBulanan
        txtBulanan.text = total.toRupiahFormat()
    }

    private fun updateBudgetControl() {
        budgetCtrlAdapter.updateData(transaksions.budgetCtrlBulanan)
    }

    private fun updateFilter() {
        val filter: MutableList<Category> = mutableListOf(Category(name = "All", key = "all", selected = true))
        filter.addAll(transaksions.filterBulanan)
        filterAdapter.updateData(filter)
    }

    private fun updateTransaction() {
        transactionCtrlAdapter.updateData(transaksions.bulanIni)
    }


    private fun setupBudgetList() {
//        val dummyData = listOf(
//            BudgetControl(
//                "\uD83C\uDF54",
//                "Fodd",
//                250000,
//                60000
//            ),
//            BudgetControl(
//                "\uD83D\uDCE1",
//                "Internet",
//                250000,
//                200000
//            ),
//            BudgetControl(
//                "\uD83E\uDD73",
//                "Hangout",
//                1250000,
//                1500000
//            )
//        )

//        budgetCtrlAdapter = BudgetControlAdapter()
//        listBudgetControl.apply {
//            adapter = budgetCtrlAdapter
//            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//
//            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
//            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
//            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()
//
//            val dekora = HorizontalMarginDekorator(firstLast, rightLeftDefault, topBottomDefault)
//            addItemDecoration(dekora)
//        }
//
//        val snapHelper: SnapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(listBudgetControl)
        budgetCtrlAdapter = BudgetControlAdapter()
        listBudgetControl.apply {
            adapter = budgetCtrlAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val decorator = HorizontalMarginDekorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(decorator)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listBudgetControl)
    }

    private fun setupFilterList() {
//        val dummyData = listOf("All", "Food", "Gas", "Book", "Internet")
        filterAdapter = FilterAdapter()
        listFilter.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val dekora = HorizontalMarginDekorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(dekora)
        }
        filterAdapter.addOnItemSelected  {
            filterBy -> transactionCtrlAdapter.updateData(transaksions.filter {
                if (filterBy.key == "all") true else it.category?.key == filterBy.key
            })
        }
    }


    private fun setupTransactionList() {
//        val dummyData = listOf(
//            TransactionControl(
//                "\uD83C\uDF72",
//                "Food",
//                -50000,
//                "Sat, 08 July 2020"
//            ),
//            TransactionControl(
//                "\uD83D\uDCB5",
//                "Royalty",
//                +100000,
//                "Sat, 08 July 2020"
//            ),
//            TransactionControl(
//                "\uD83D\uDCE1",
//                "Quota",
//                -80000,
//                "Sat, 08 July 2020"
//            )
//        )
        transactionCtrlAdapter = TransactionControlAdapter()
        listTransaction.apply {
            adapter = transactionCtrlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
