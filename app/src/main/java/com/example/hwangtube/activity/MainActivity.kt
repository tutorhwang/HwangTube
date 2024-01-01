package com.example.hwangtube.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.hwangtube.R
import com.example.hwangtube.data.Video
import com.example.hwangtube.data.VideoList

private const val TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate()")
        findViewById<Toolbar>(R.id.toolbar).apply {
            setSupportActionBar(this)
        }
        loadVideo(R.id.video_1, VideoList.get(0))
        loadVideo(R.id.video_2, VideoList.get(1))
        loadVideo(R.id.video_3, VideoList.get(2))
        loadVideo(R.id.video_4, VideoList.get(3))
        loadVideo(R.id.video_5, VideoList.get(4))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_dialog -> {
                showDialog()
                true
            }

            R.id.action_call -> {
                call()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadVideo(layoutId: Int, video: Video) {
        findViewById<ViewGroup>(layoutId)?.apply {
            with(video) {
                findViewById<ImageView>(R.id.main_image)?.also { imageThumbnail ->
                    Glide.with(imageThumbnail).load(thumbnail).into(imageThumbnail)
                    imageThumbnail.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra(EXTRA_VIDEO, this@with)
                        }
                        startActivity(intent)
                    }
                }

                findViewById<TextView>(R.id.channel_name).apply {
                    text = channelTitle
                }

                findViewById<TextView>(R.id.title).apply {
                    text = title
                }

                findViewById<ImageView>(R.id.logo).apply {
                    setImageResource(R.drawable.haelin)
                }
            }
        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Dialog Title")
            .setMessage("This is a dialog.")
            .setPositiveButton("OK", null)
            .create()

        dialog.show()
    }


    private fun call() {
        // 권한 확인
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL
            )
        } else {
            // Intent 설정
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:012345567")
            startActivity(callIntent)
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call() // 권한이 허용된 후 전화 걸기
            } else {
                Toast.makeText(applicationContext, "전화 걸기 권한이 없습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CALL = 1
    }
}