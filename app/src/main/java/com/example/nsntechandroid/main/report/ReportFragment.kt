package com.example.nsntechandroid.main.report

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.nsntechandroid.R
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.ArrayList

class ReportFragment : Fragment() {

    private lateinit var spendingCtrlAdapter: SpendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chartReport.setUsePercentValues(true)
        chartReport.description.isEnabled = false

        setData()
        setupSpendingList()
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

    private fun setupSpendingList() {
        val dummyData = listOf(
            Spending(
                "\uD83C\uDF72",
                "Food",
                50000
            ),
            Spending(
                "\uD83D\uDCB5",
                "Royalty",
                100000
            ),
            Spending(
                "\uD83D\uDCE1",
                "Quota",
                80000
            )
        )

        spendingCtrlAdapter = SpendingAdapter(dummyData)
        listSpending.apply {
            adapter = spendingCtrlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }



}
