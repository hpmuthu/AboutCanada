package com.example.aboutcanada.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aboutcanada.R
import com.example.aboutcanada.Utils.AppUtils
import com.example.aboutcanada.adapter.CountryInfoAdapter
import com.example.aboutcanada.viewModel.CountryInfoViewModel
import com.example.aboutcanada.pojo.CountryInfo
import com.example.aboutcanada.pojo.CountryInfoItem
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var mCountryInfoViewModel: CountryInfoViewModel
    lateinit var mContext: Context
    var mProgressDialog: AlertDialog? = null
    var countryInfoList: List<CountryInfoItem> = ArrayList()
    lateinit var mCountryInfoAdapter: CountryInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mContext = this
        mCountryInfoViewModel = ViewModelProviders.of(this)[CountryInfoViewModel::class.java]

        mCountryInfoViewModel.getCountryInfo().observe(this, Observer<CountryInfo>{ countryInfo ->
            loadCountryInfo(countryInfo)
        })

        defaultFunctionality()

    }

    private fun defaultFunctionality() {

        setProgressDialog(true)

        if(AppUtils.isInternetAvailable(mContext))
            mCountryInfoViewModel.loadCountryInfo()
        else
            Toast.makeText(mContext, resources.getString(R.string.no_internet), Toast.LENGTH_LONG).show()

        setProgressDialog(false)

        srl_refresh.setOnRefreshListener {

            setProgressDialog(true)

            if(AppUtils.isInternetAvailable(mContext))
                mCountryInfoViewModel.loadCountryInfo()
            else
                Toast.makeText(mContext, resources.getString(R.string.no_internet), Toast.LENGTH_LONG).show()

            setProgressDialog(false)

            srl_refresh.isRefreshing = false
        }
    }

    private fun setUpActionBarTitle(title: String) {
        val ab = supportActionBar
        ab!!.title = title
    }

    private fun loadCountryInfo(countryInfo: CountryInfo) {

        setUpActionBarTitle(countryInfo.title)

        countryInfoList = countryInfo.rows ?: ArrayList()

        if (countryInfoList!!.size > 0) {
            tv_NoData.visibility = View.GONE
            rv_CountryInfo.visibility = View.VISIBLE

            mCountryInfoAdapter =
                CountryInfoAdapter(mContext, countryInfoList)
            rv_CountryInfo.adapter = mCountryInfoAdapter
        } else {
            tv_NoData.visibility = View.VISIBLE
            rv_CountryInfo.visibility = View.GONE
        }
    }

    private fun setProgressDialog(isShow: Boolean) {

        if(mProgressDialog == null) {
            val builder = AlertDialog.Builder(mContext)
            builder.setView(R.layout.progress)
            mProgressDialog = builder.create()
        }

        if (isShow)
            mProgressDialog!!.show()
        else
            mProgressDialog!!.dismiss()
    }
}
