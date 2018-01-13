package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    public NumbersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_numbers,container,false);
        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        numbers.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        numbers.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        numbers.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        numbers.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        numbers.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Word("Nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Word("Ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter numbersAdapter = new WordAdapter(getActivity(),numbers,R.color.category_numbers);
        ListView numbersList = (ListView)rootView.findViewById(R.id.list_view);
        numbersList.setAdapter(numbersAdapter);
        numbersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * Release the media player if it currently exists because we are about to
                 * play a different sound file
                 */
                releaseMediaPlayer();
                //Get the Word object at the given position the user clicked on.
                Word numberClick = numbers.get(position);
                mMediaPlayer = MediaPlayer.create(getActivity(),numberClick.getAudioResourceID());
                //Start the Audio file.
                mMediaPlayer.start();
                //Setup a listener on the media player, so that we can stop and release the
                //media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
        //return inflater.inflate(R.layout.fragment_numbers, container, false);
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
