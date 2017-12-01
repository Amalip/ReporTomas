package com.example.amalip.reportomas.Models

/**
 * Created by Amalip on 11/30/2017.
 */
class Report(street : String = "", number : String = "", adj1 : String = "", adj2 : String = ""
             , category : Int = 0, description : String = "", lat : Double = 0.0, lng : Double = 0.0) {

    var street = street
    var number = number
    var adj1 = adj1
    var adj2 = adj2
    var category = category
    var description = description
    var lat = lat
    var lng = lng

}