package com.pearldrift.nfclibrary.factories

import android.nfc.tech.Ndef
import com.pearldrift.nfclibrary.typealiases.WriteNdefInterface

/**
 * Created by petrosmaliotis on 03/06/2020.
 */
class WriteConnectFactory {
    companion object {

        internal val ndefFunction: WriteNdefInterface = {
            val tagTechnology = Ndef.get(it)
            tagTechnology.connect()
            tagTechnology
        }
    }
}