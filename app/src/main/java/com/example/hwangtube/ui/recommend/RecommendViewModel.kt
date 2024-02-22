package com.example.hwangtube.ui.recommend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hwangtube.data.model.youtube.ListItem
import com.example.hwangtube.data.model.youtube.search.toVideoItem
import com.example.hwangtube.data.repository.AiRepository
import com.example.hwangtube.data.repository.YoutubeRepository
import kotlinx.coroutines.launch

class RecommendViewModel(
    private val aiRepo: AiRepository = AiRepository(),
    private val youTubeRepo: YoutubeRepository = YoutubeRepository()
) : ViewModel() {
    private val _recommendChannelList: MutableLiveData<List<ListItem.VideoItem>> = MutableLiveData()
    val recommendChannelList: LiveData<List<ListItem.VideoItem>> = _recommendChannelList
    private val _recommendResponse: MutableLiveData<String> = MutableLiveData("...")
    val recommendResponse: LiveData<String> = _recommendResponse

    fun fetchRecommendChanelList(recentFavorite: List<ListItem.VideoItem>? = null) {
        viewModelScope.launch {
            runCatching {
                val messageFavorite =
                    "다음은 유투브에서 선호하는 영상 정보야. 이 사람의 선호 패턴을 간단히 분석 해서 이 사람이 좋아할만한 유투브 채널명 5개 추천해줘. 채널명 앞과 뒤에는 <> 괄호를 이용해서 구분할 수 있게 해줘.$recentFavorite"
                val channelResponse =
                    aiRepo.createChatCompletion(messageFavorite).choices.first().message.content
                _recommendResponse.value = channelResponse
                val recommendChannel = channelResponse.toChannelList()
                youTubeRepo.searchChannels(recommendChannel.first()).items
            }.onSuccess {
                _recommendChannelList.value = it.toVideoItem()
            }.onFailure {
                Log.e("Recommend", "fetchRecommendChanelList $it")
            }
        }
    }

}

private fun String.toChannelList(): List<String> {
    val pattern = "<(.*?)>".toRegex()
    return pattern.findAll(this)
        .map { it.groupValues[1] }
        .toList()
}
