package ru.softmine.kotlin_libraries.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.softmine.kotlin_libraries.databinding.ActivityMainBinding
import ru.softmine.kotlin_libraries.mvp.presenter.MainPresenter
import ru.softmine.kotlin_libraries.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        val button1Listener = View.OnClickListener {
            presenter.counter1Click()
        }

        val button2Listener = View.OnClickListener {
            presenter.counter2Click()
        }

        val button3Listener = View.OnClickListener {
            presenter.counter3Click()
        }

        vb?.btnCounter1?.setOnClickListener(button1Listener)
        vb?.btnCounter2?.setOnClickListener(button2Listener)
        vb?.btnCounter3?.setOnClickListener(button3Listener)
    }

    override fun setButton1Text(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        vb?.btnCounter3?.text = text
    }

}