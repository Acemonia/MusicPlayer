package com.example.projectprepare1.ui.songinlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import com.hfut.music.MusicAdapter
import kotlinx.android.synthetic.main.fragment_song_in_list.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SongInListFragment : Fragment() {
    private lateinit var songInListViewModel: SongInListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_in_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songInListViewModel = ViewModelProviders.of(this).get(SongInListViewModel::class.java)
        val listId = arguments?.getString("listId")!!
        val song = songInListViewModel.getSongInList(listId)
        val adapter = MusicAdapter(context!!, R.layout.music_item, song,
            object : MyClickListener {
                override fun onClick(position: Int) {

                }
            })
        song_in_list.adapter = adapter
        song_in_list.layoutManager = LinearLayoutManager(context)
        song_in_list.layoutManager = GridLayoutManager(context, 1)
    }

}