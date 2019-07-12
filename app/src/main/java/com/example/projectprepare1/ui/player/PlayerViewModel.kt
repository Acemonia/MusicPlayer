package com.example.projectprepare1.ui.player

import android.app.Application
import androidx.lifecycle.*
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import com.example.projectprepare1.ui.scan.ScanViewModel.MusicUtils.list

class PlayerViewModel(application: Application) : AndroidViewModel(application){
    private var repo :PlayerRepository
    private var musicList:LiveData<List<Song>>?=null
    private var currentMusic=MutableLiveData< Int>() //当前播放歌曲
    private var playWay=MutableLiveData<Int>()// 播放方式，1 单曲循环，2 顺序循环，3 随机播放
    private var pause=MutableLiveData<Boolean>() //播放 暂停状态
    private var volume=MutableLiveData<Int>()//播放音量
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repo = PlayerRepository(musicDao)
        musicList=repo.musicList!!
        currentMusic.value=repo.currentMusic
        playWay.value=1
        pause.value=true
        volume.value=30
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
        currentMusic.value=currentMusicT
    }
    fun setPlayWay(playWayT:Int){
        when(playWay.value){
            1->
                playWay.value=2
            2->
                playWay.value=3
            3->
                playWay.value=1
        }
    }
    fun setPause(pauseT:Boolean){
        pause.value=pauseT
    }
    fun setVolume(volumeT:Int){
        volume.value=volumeT
    }
    fun setListNameList(listName:String){
        repo.setMusicList(listName)
    }
    fun setListNameSinger(listName:String){
        repo.setMusicListSinger(listName)
    }
    fun setCurrentMusicName(name:String){
        repo.setCurrentMusic(name)
    }
    fun getSongMenu():Array<String>{
        return repo.getSongMenu()
    }
    fun addSongToList(songListName:String){
        repo.addSongToList(currentMusic.value!!,songListName)
    }
}
