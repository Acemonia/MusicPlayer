package com.example.projectprepare1.ui.player

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.roomwordssample.MusicRepository
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song

class PlayerViewModel(application: Application) : AndroidViewModel(application){
    private var repo :PlayerRepository
    private var musicList:LiveData<List<Song>>?=null
    private var currentMusic=MutableLiveData< Int>() //当前播放歌曲
    private var playWay=MutableLiveData<Int>()// 播放方式，1 单曲循环，2 顺序循环，3 随机播放
    private var pause=MutableLiveData<Boolean>() //播放 暂停状态
    private var volume=MutableLiveData<Int>()//播放音量
    init {
        Log.d("music","start ")
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repo = PlayerRepository(musicDao)
        musicList=repo.musicList!!
        currentMusic.value=repo.currentMusic
        playWay.value=1
        pause.value=true
        volume.value=30
        Log.e("error",pause.value.toString()+"  PlayerViewModel init ")
    }
    fun getMusicList():LiveData<List<Song>>?{
        return musicList
    }
    fun getCurrentMusic():MutableLiveData< Int>{
        return currentMusic
    }
    fun getPlayWay():MutableLiveData<Int>{
        return playWay
    }
    fun getPause():MutableLiveData<Boolean>{
        return pause
    }
    fun getVolume():MutableLiveData<Int>{
        return volume
    }
    fun setCurrentMusic(currentMusicT:Int){
        if(currentMusic.value!!-currentMusicT>musicList?.value!!.size){
            currentMusic.value=0
        }
        if(currentMusic.value!!-currentMusicT<0){
            currentMusic.value=musicList?.value!!.size
        }
        else{
            currentMusic.value=currentMusic.value!!-currentMusicT
        }
    }
    fun setPlayWay(playWayT:Int){
        playWay.value=playWayT
    }
    fun setPause(pauseT:Boolean){
        pause.value=pauseT
    }
    fun setVolume(volumeT:Int){
        volume.value=volumeT
    }
}
