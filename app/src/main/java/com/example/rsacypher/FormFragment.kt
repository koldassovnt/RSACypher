package com.example.rsacypher

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.rsacypher.databinding.FragmentFormBinding
import android.text.method.ScrollingMovementMethod


class FormFragment : Fragment() {

    companion object {
        fun newInstance() = FormFragment()
    }
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val plainText = binding.etPlainText
        val startBtn = binding.btnEncode
        val resText = binding.tvResult

        var str = ""

        startBtn.setOnClickListener {

            val privateEnStartTime = System.currentTimeMillis()
            val prvKeyText= RSACrypt.encryptByPrivateKey(plainText.toString())
            val prvtime = System.currentTimeMillis() - privateEnStartTime
            val prvKeyTime = "${prvtime}ms"

            val publicEnStartTime = System.currentTimeMillis()
            val pubKeyText= RSACrypt.encryptByPublicKey(plainText.toString())
            val pubtime = System.currentTimeMillis() - publicEnStartTime
            val pubKeyTime= "${pubtime}ms"

//            val privateDeStartTime = System.currentTimeMillis()
//            val prvDeText = RSACrypt.decryptByPrivateKey(pubKeyText)
//            val prvdetime = System.currentTimeMillis() - privateDeStartTime
//            val prvDeTime = "${prvdetime}ms"
//
//            val publicDeStartTime = System.currentTimeMillis()
//            val pubDeKeyText = RSACrypt.decryptByPublicKey(prvKeyText)
//            val pubdetime = System.currentTimeMillis() - publicDeStartTime
//            val pubDetime = "${pubdetime}ms"

            str += "Private key Encryption:" + "\n\n" + prvKeyText + "\n\n\n" +
                    "Private key Encryption time:" + "\n\n" + prvKeyTime + "\n\n\n" +
                    "Public key Encryption:" + "\n\n" + pubKeyText + "\n\n\n" +
                    "Public key Encryption time:" + "\n\n" + pubKeyTime + "\n\n\n"
//                    "Private key Decryption:" + "\n\n" + prvDeText + "\n\n\n" +
//                    "Private key Decryption time:" + "\n\n" + prvDeTime + "\n\n\n" +
//                    "Public key Decryption:" + "\n\n" + pubDeKeyText + "\n\n\n" +
//                    "Public key Decryption time:" + "\n\n" + pubDetime + "\n\n\n"

            resText.text = str
            resText.movementMethod = ScrollingMovementMethod()
            resText.visibility = View.VISIBLE

            plainText.text.clear()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}