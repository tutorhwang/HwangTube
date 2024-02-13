package com.example.hwangtube.ui.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hwangtube.data.repository.AiRepository
import kotlinx.coroutines.launch

class RecommendViewModel(val repository: AiRepository = AiRepository()) : ViewModel() {

    private val _recommendChannelList: MutableLiveData<List<String>> = MutableLiveData()
    val recommendChannelList: LiveData<List<String>> = _recommendChannelList
    fun fetchRecommendChanelList() {
        val message = "10살 남자아이가 좋아하는 축구 유투브 채널 5개 추천해줘. 채널 영어 이름 앞과 뒤에는 <> 괄호를 이용해서 구분할 수 있게 해줘"
        viewModelScope.launch {
            val response =
                repository.createChatCompletion(message).choices.first().message.content.toChannelList()
            _recommendChannelList.value = response
        }
    }

}

private fun String.toChannelList(): List<String> {
    val pattern = "<(.*?)>".toRegex()
    return pattern.findAll(this)
        .map { it.groupValues[1] }
        .toList()
}
