package com.example.hwangtube.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hwangtube.R
import com.example.hwangtube.adapter.VideoListAdapter
import com.example.hwangtube.data.Video
import com.example.hwangtube.data.VideoList
import com.example.hwangtube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        VideoListAdapter(VideoList.list) { video ->
            val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                putExtra(EXTRA_VIDEO, video)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            setSupportActionBar(toolbar)
            recyclerView.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                adapter.addItem(
                    Video(
                        "Tobi Speakerfan", //channel title
                        "Detalles insanos de Skibidi Toilet 69  #skibiditoilet", // title
                        "https://i.ytimg.com/vi/rYzH1214ElA/mqdefault.jpg", //thumbnails.medium
                        "Spoiler de Skibidi Toilet 69 claro que si ............................................................................................... #skibiditoilet 69 parte 2 #memes ..." //description
                    )
                )
                binding.recyclerView.scrollToPosition(0)
                true
            }

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


    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Dialog Title")
            .setMessage("This is a dialog.")
            .setPositiveButton("OK", null)
            .create().show()
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