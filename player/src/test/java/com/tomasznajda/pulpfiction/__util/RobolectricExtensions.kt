package com.tomasznajda.pulpfiction.__util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import org.robolectric.Robolectric
import kotlin.reflect.KClass


fun <T : Activity> setupActivity(activityClass: KClass<T>, intent: Intent? = null) =
        Robolectric.buildActivity(activityClass.java, intent).setup().get()!!

fun <T : Activity> buildActivity(activityClass: KClass<T>) =
        Robolectric.buildActivity(activityClass.java)!!

fun <T : Fragment> T.start() = apply {
    val activity = Robolectric.buildActivity(AppCompatActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

    val fragmentManager = activity.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.add(this, null)
    fragmentTransaction.commit()
}