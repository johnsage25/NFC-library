package com.pearldrift.nfclibrary.factories


import android.nfc.NdefMessage
import android.nfc.tech.MifareClassic
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import com.pearldrift.nfclibrary.typealiases.ReadMifareClassicInterface
import com.pearldrift.nfclibrary.typealiases.ReadNdefInterface
import com.pearldrift.nfclibrary.typealiases.ReadNfcAInterface
import java.lang.RuntimeException

/**
 * Created by petrosmaliotis on 29/05/2020.
 */
class ReadConnectFactory {

    companion object {

        internal val ndefFunction: ReadNdefInterface = { tag ->
            val ndefTag = Ndef.get(tag)
            var ndefMessage: NdefMessage? = null
            ndefTag.let {
                it.connect()
                ndefMessage = it.cachedNdefMessage
                it.close()
            }
            if (ndefMessage == null) throw RuntimeException("Couldn't connect to nfc tag\n" +
                    "Ndef Message is null\n" +
                    "Exiting..")
            ndefMessage!!
        }

        val nfcAFunction: ReadNfcAInterface = { tag ->
            val nfcATag = NfcA.get(tag)
        }

        val mifareClassicFunction: ReadMifareClassicInterface = { tag ->
            val mifareClassicTag = MifareClassic.get(tag)

            mifareClassicTag.let {
                it.connect()
            }
        }
    }
}