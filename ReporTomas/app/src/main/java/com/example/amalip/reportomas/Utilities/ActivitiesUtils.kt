package com.example.amalip.reportomas.Utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Amalip on 11/27/2017.
 */
class ActivitiesUtils {
    enum class types {
        STRING, INTEGER, FLOAT, BOOLEAN
    }

    companion object {

        //This method will replace the Fragment that is showing
        /*
        * fragmentManager = Provides the fragmentManager to the fragmentTransaction that we will create
        * fragment = The fragment that will be shown
        * frameId = The frameId that point to the container
        * */
        fun addFragmentToActivity(activity: BaseActivity, fragmentManager: FragmentManager,
                                  fragment: Fragment, frameId: Int) {
            //We create the transaction and establish the frameId (place that contains the fragment)
            // and the fragment that will be shown
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(frameId, fragment)
            transaction.commit()
        }

        //This method helps us to show toast everywhere
        /*
        * activity = The actual activity
        * msg = The message to show
        * */
        fun showToat(activity: AppCompatActivity, msg: String) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }

        fun showToast(activity: AppCompatActivity, @StringRes msg: Int?) {
            Toast.makeText(activity, msg!!, Toast.LENGTH_SHORT).show()
        }

        //This method helps us to edit sharedPreferences

        fun editSharedPreferences(context: Context, key: String, value: Any, type: types): Boolean {
            //Instance of sharedPreferences with the url where will be generated and the accessibility mode
            val sharedPreferences = context.getSharedPreferences("mx.com.epcon.telcorz", MODE_PRIVATE)

            try {
                when (type) {
                    ActivitiesUtils.types.STRING -> {
                        sharedPreferences.edit().putString(key, value as String).apply()
                        return true
                    }
                    ActivitiesUtils.types.INTEGER -> {
                        sharedPreferences.edit().putInt(key, value as Int).apply()
                        return true
                    }
                    ActivitiesUtils.types.FLOAT -> {
                        sharedPreferences.edit().putFloat(key, value as Float).apply()
                        return true
                    }
                    ActivitiesUtils.types.BOOLEAN -> {
                        sharedPreferences.edit().putBoolean(key, value as Boolean).apply()
                        return true
                    }
                }
            } catch (ex: Exception) {
                return false
            }

            return true
        }

        /*
        * activity = The actual activity
        * map = The map that contains the value-key that will be saved in sharedPreferences
        * */
        fun editSharedPreferences(context: Context, map: Map<String, String>): Boolean {
            //Instance of sharedPreferences with the url where will be generated and the accessibility mode
            val sharedPreferences = context.getSharedPreferences("mx.com.epcon.telcorz", MODE_PRIVATE)

            try {
                //Iterate the keys in map and if it matches with the value "STRING", "INTEGER", etc,
                // is the type that will be saved, and when it finish returns true, if something crashes return false
                for (key in map.keys) {
                    if (key.toString().contains("STRING"))
                        sharedPreferences.edit().putString(key.replace("STRING", ""), map[key]).apply()
                    else if (key.toString().contains("INTEGER"))
                        sharedPreferences.edit().putInt(key.replace("INTEGER", ""), Integer.parseInt(map[key])).apply()
                    else if (key.toString().contains("FLOAT"))
                        sharedPreferences.edit().putFloat(key.replace("FLOAT", ""), java.lang.Float.parseFloat(map[key])).apply()
                    else if (key.toString().contains("BOOLEAN"))
                        sharedPreferences.edit().putBoolean(key.replace("BOOLEAN", ""), java.lang.Boolean.parseBoolean(map[key])).apply()
                }
                return true
            } catch (e: Exception) {
                return false
            }

        }

        fun getSharedPreference(context: Context, key: String, type: types): Any? {
            //Instance of sharedPreferences with the url where will be generated and the accessibility mode
            val sharedPreferences = context.getSharedPreferences("mx.com.epcon.telcorz", MODE_PRIVATE)
            when (type) {
                ActivitiesUtils.types.STRING -> return sharedPreferences.getString(key, "")
                ActivitiesUtils.types.INTEGER -> return sharedPreferences.getInt(key, 0)
                ActivitiesUtils.types.FLOAT -> return sharedPreferences.getFloat(key, 0f)
                ActivitiesUtils.types.BOOLEAN -> return sharedPreferences.getBoolean(key, false)
                else -> return null
            }
        }

        fun getCurrentDateTime(): Date {
            val smp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            try {
                return smp.parse(smp.format(Calendar.getInstance().time))
            } catch (e: ParseException) {
                e.printStackTrace()
                return Date()
            }

        }

        fun getCurrentDate(): Date {
            val smp = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            try {
                return smp.parse(smp.format(Calendar.getInstance().time))
            } catch (e: ParseException) {
                e.printStackTrace()
                return Date()
            }

        }

        fun setDateFormat(date: Date): String {
            val smp = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return smp.format(date)
        }

        fun setDateTimeFormat(date: Date): String {
            val smp = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return smp.format(date)
        }

        fun getDateTimePicker(date: String): Date? {
            val sdf = SimpleDateFormat("dd/MM/yyyyHH:mm", Locale.getDefault())
            val smp = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val fecha1 = getCurrentDate()
            val fecha2 = setDateFormat(fecha1)
            val fecha = fecha2 + date
            try {
                val date1 = sdf.parse(fecha)
                val out = smp.format(date1)
                return smp.parse(out)
            } catch (e: ParseException) {
                return null
            }
        }
    }
}