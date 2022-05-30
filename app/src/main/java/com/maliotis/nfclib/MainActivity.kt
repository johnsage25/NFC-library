package com.maliotis.nfclib

import android.content.Intent
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pearldrift.nfclibrary.NfcTech
import com.pearldrift.nfclibrary.NfcTech.NFCA
import com.pearldrift.nfclibrary.factories.NFCFactory
import com.pearldrift.nfclibrary.nfc.ReadNFC
import com.pearldrift.nfclibrary.nfc.WriteNFC
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var readNFC: ReadNFC
    lateinit var writeNFC: WriteNFC

    var readAction = true
    var writeAction = false

    override fun onResume() {
        super.onResume()
        readNFC = NFCFactory.create(this)
        writeNFC = NFCFactory.create(this)

        readNFC.enableNFCInForeground()
        // No need to enable NFC in foreground again..
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ...
        // ...


    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        if (intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            readNFC.connect(intent)
            readNFC.nfcTech = NfcTech.NDEF
            val payload = readNFC.payload()
            Log.d("TAG", "onNewIntent: ${payload}")

        } else if (intent.action == NfcAdapter.ACTION_TECH_DISCOVERED) {
            // Determine the action

            if (writeAction) {

                writeNFC.connect(intent)

                writeNFC.write("Hello from NFCLib :)")

                writeNFC.close()
            }

            if (readAction) {
                readNFC.nfcTech = NFCA
                readNFC.connect(intent)

                val payload = readNFC.payload()
                // after accesing payload
                payload.forEach {
                    Log.d("TAG", "onNewIntent: $it")
                }
            }

        }


    }
}
