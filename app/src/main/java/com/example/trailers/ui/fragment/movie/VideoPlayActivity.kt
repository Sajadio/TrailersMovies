package com.example.trailers.ui.fragment.movie


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.example.trailers.R
import com.example.trailers.databinding.ActivityMovieBinding
import com.example.trailers.databinding.ActivityVideoPlayBinding
import com.example.trailers.utils.Constant
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class VideoPlayActivity : AppCompatActivity() {

    private var _binding: ActivityVideoPlayBinding? = null
    val binding: ActivityVideoPlayBinding get() = _binding!!
    private val args:VideoPlayActivityArgs by navArgs()

    lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener

    private lateinit var youtubeLink: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_video_play)


        youtubeLink = Constant.YOUTUBE_BASE + args.url

        onInitializedListener =
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean,
                ) {
                    p1?.loadPlaylist(youtubeLink)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?,
                ) {
                    Toast.makeText(this@VideoPlayActivity, "Failure", Toast.LENGTH_SHORT).show()
                }


            }
        binding.youTubePlayerView.initialize(Constant.YOUTUBE_KEY, onInitializedListener)

    }
}