package com.pearldrift.nfclibrary.factories

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcManager
import com.pearldrift.nfclibrary.interfaces.NFC
import com.pearldrift.nfclibrary.nfc.ReadNFC
import com.pearldrift.nfclibrary.nfc.WriteNFC
import java.lang.IllegalArgumentException

/**
 * Created by petrosmaliotis on 28/05/2020.
 */
abstract class NFCFactory<T: NFC> {
    abstract fun getNFC(activity: Activity): T
    abstract fun handleIntent(intent: Intent)


    companion object {

        @JvmStatic
        @PublishedApi internal
        var nfcManager: NfcManager? = null

        inline fun <reified T: NFC>create(activity: Activity): T {
            nfcManager = activity.getSystemService(Context.NFC_SERVICE) as NfcManager

            return when (T::class) {
                ReadNFC::class -> ReadNFCFactory().getNFC(activity) as T
                WriteNFC::class -> WriteNFCFactory().getNFC(activity) as T
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Java alternative method
         */
        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun <T: NFC> create(typeOf: Class<T>, activity: Activity): T {
            nfcManager = activity.getSystemService(Context.NFC_SERVICE) as NfcManager

            return when (typeOf) {
                ReadNFC::javaClass -> ReadNFCFactory().getNFC(activity) as T
                WriteNFC::javaClass -> WriteNFCFactory().getNFC(activity) as T
                else -> throw IllegalArgumentException()
            }
        }


    }

}