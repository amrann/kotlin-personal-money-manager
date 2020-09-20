package com.example.nsntechandroid.main.report

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.nsntechandroid.R
import com.example.nsntechandroid.main.home.TransactionControl
import com.example.nsntechandroid.main.home.laporanBulanan
import com.example.nsntechandroid.main.home.pendapatanBulanan
import com.example.nsntechandroid.main.home.pengeluaranBulanan
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
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
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.ArrayList

class ReportFragment : Fragment() {

    private lateinit var spendingCtrlAdapter: SpendingAdapter

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
        return inflater.inflate(R.layout.fragment_report_2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chartReport.setUsePercentValues(true)
        chartReport.description.isEnabled = false
//        setData()
        setupSpendingList()

        getData()
    }

    private fun setData() {
        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(50f, "Income"))
        entries.add(PieEntry(50f, "Expense"))

        val dataSet = PieDataSet(entries, "Monthly Report")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val warna: ArrayList<Int> = ArrayList()
        warna.add(Color.rgb(33, 150, 83))
        warna.add(Color.rgb(242, 153, 74))

        dataSet.colors = warna
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chartReport))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        chartReport.data = data
        chartReport.highlightValues(null)
        chartReport.invalidate()
    }

    private fun getData() {
        idSwitcher.displayedChild = 0

        val transaksiRef = database.child("users").child(auth.uid ?: "").child("transactions")

        val transaksiListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "loadPost:dataChange $dataSnapshot")

                transaksions.clear()
                for (snapshot in dataSnapshot.children) {
                    val transaction = snapshot.getValue<TransactionControl>()
                    transaction?.key = snapshot.key
                    transaction?.let {
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
        updateChart()
        updateList()
        idSwitcher.displayedChild = 1
    }

    private fun updateChart() {
        val expense = transaksions.pengeluaranBulanan
        val income = transaksions.pendapatanBulanan
        val total = income + expense

        val incomePercent = (income.toFloat() / total) * 100
        val expensePercent = (expense.toFloat() / total) * 100

        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(incomePercent, "Income"))
        entries.add(PieEntry(expensePercent, "Expense"))

        val dataSet = PieDataSet(entries, "Monthly Report")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.rgb(33, 150, 83))
        colors.add(Color.rgb(242, 153, 74))

        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chartReport))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        chartReport.data = data
        chartReport.highlightValues(null)
        chartReport.invalidate()
    }

    private fun updateList() {
        spendingCtrlAdapter.updateData(transaksions.laporanBulanan)
    }

    private fun setupSpendingList() {
//        val dummyData = listOf(
//            Spending(
//                "\uD83C\uDF72",
//                "Food",
//                50000
//            ),
//            Spending(
//                "\uD83D\uDCB5",
//                "Royalty",
//                100000
//            ),
//            Spending(
//                "\uD83D\uDCE1",
//                "Quota",
//                80000
//            )
//        )
        spendingCtrlAdapter = SpendingAdapter()
        listSpending.apply {
            adapter = spendingCtrlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }

    companion object {
        private const val TAG = "ReportFragment"
    }
}
