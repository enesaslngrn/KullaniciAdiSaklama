package com.enesas.kullaniciadisaklama

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.enesas.kullaniciadisaklama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences //lateinit şu demek. Sonra initialize edeceğim. Yani değerini sonradan atayacağım.
    var sistemdeKayitlinKullaniciAdi: String? = null

    private  lateinit var binding: ActivityMainBinding // yani burda da binding diye bir nesne oluşturduk
    // ama lateinit dedik yani merak etme birazdan onCreate içinde tanımlayacağım bana hata verme dedik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = this.getSharedPreferences("com.enesas.kullaniciadisaklama", MODE_PRIVATE) // this. diye oluşturmak gerek bunu.

        sistemdeKayitlinKullaniciAdi = sharedPreferences.getString("username","")// burada boş bırakmanın nedeni hiç bişey dönmezse boş bırak.
        if (sistemdeKayitlinKullaniciAdi != null){
            binding.textView.text = "Sistemde kayıtlı kullanıcı adı: $sistemdeKayitlinKullaniciAdi"
        }
    }

    fun kaydet(view: View){
        val kullaniciAdi = binding.editText.text.toString()
        if (kullaniciAdi == ""){
            Toast.makeText(this,"Lütfen kullanıcı adınızı giriniz!",Toast.LENGTH_LONG).show()
        }else{
            sharedPreferences.edit().putString("username", kullaniciAdi).apply() //apply() diyince kapatıyor işlemi ve kaydediyor.
            binding.textView.text = "Kaydedilen kullanıcı adı: ${kullaniciAdi}"
        }
    }
    fun sil(view: View){
        sistemdeKayitlinKullaniciAdi = sharedPreferences.getString("username", "")
        if (sistemdeKayitlinKullaniciAdi != null){
            sharedPreferences.edit().remove("username").apply()
            binding.textView.text = "Kaydedilen kullanıcı adı:"
        }
    }

}