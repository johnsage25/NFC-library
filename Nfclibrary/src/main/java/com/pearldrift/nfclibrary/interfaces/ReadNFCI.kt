package com.pearldrift.nfclibrary.interfaces


import android.nfc.NdefMessage
import android.nfc.NdefRecord
import com.pearldrift.nfclibrary.interfaces.NFC

/**
 * Created by petrosmaliotis on 29/05/2020.
 */
interface ReadNFCI: NFC {
    fun message(): NdefMessage?
    fun records(): Array<NdefRecord>?
    fun payload(): Array<String>

}