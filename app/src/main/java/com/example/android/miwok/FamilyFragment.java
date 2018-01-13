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

public class FamilyFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    /**
     * This listener gets triggered when the {MediaPlayer} has
     * completed playing the Audio File.
     */
    private MediaPlayer.OnCompletionListener
            mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer();
        }
    };
    public FamilyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_numbers,container,false);
        final ArrayList<Word> familyMembers = new ArrayList<Word>();
        familyMembers.add(new Word("father","әpә",R.drawable.family_father,
                R.raw.family_father));
        familyMembers.add(new Word("mother","әṭa",R.drawable.family_mother,
                R.raw.family_mother));
        familyMembers.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        familyMembers.add(new Word("daughter","tune",R.drawable.family_daughter,
                R.raw.family_daughter));
        familyMembers.add(new Word("older brother","taachi",R.drawable.family_older_brother,
                R.raw.family_older_brother));
        familyMembers.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        familyMembers.add(new Word("older sister","teṭe",R.drawable.family_older_sister,
                R.raw.family_older_sister));
        familyMembers.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        familyMembers.add(new Word("grandmother","ama",R.drawable.family_grandmother,
                R.raw.family_grandmother));
        familyMembers.add(new Word("grandfather","paapa",R.drawable.family_grandfather,
                R.raw.family_grandfather));

        WordAdapter familyMembersAdapter = new WordAdapter(getActivity(), familyMembers,R.color.category_family);
        ListView familyMembersList = (ListView)rootView.findViewById(R.id.list_view);
        familyMembersList.setAdapter(familyMembersAdapter);
        familyMembersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Release the media Player if it currently exists because we are about
                //to play a different sound file.
                releaseMediaPlayer();
                // Get the {@link Word} object at the given position the user clicked on
                Word familyMemberClick = familyMembers.get(position);
                mMediaPlayer = MediaPlayer.create(getActivity(),
                        familyMemberClick.getAudioResourceID());
                mMediaPlayer.start();
                //Setup a listener on the media player , so that we can stop
                //& release the media player one the sound file has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
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
