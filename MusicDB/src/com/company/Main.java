package com.company;

import com.company.model.Albums;
import com.company.model.Artists;
import com.company.model.Datasource;
import com.company.model.Songs;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()){
            System.out.println("Can't open data source!");
            return;
        }

        //To print Artists
        List<Artists> artistsList = datasource.queryArtist();
        if (artistsList == null){
            System.out.println("No artist found in the database!");
            return;
        }
        for (Artists artists:artistsList){
            System.out.println("ID: "+artists.getId()+", Name: "+artists.getName());
        }

        //To print Albums
        List<Albums> albumsList = datasource.queryAlbums();
        if (albumsList == null){
            System.out.println("No albums found in the database!");
        }
        for (Albums albums:albumsList){
            System.out.println("ID: "+albums.getId()+", Name: "+albums.getName()+", Artist: "+albums.getArtist());
        }

        //To print Songs
        List<Songs> songsList = datasource.querySongs();
        if (songsList == null){
            System.out.println("No songs found in the database!");
        }
        for (Songs songs:songsList){
            System.out.println("ID: "+songs.getId()+", Track: "+songs.getTrack()+", Title: "+songs.getTitle()+", Album: "+songs.getAlbumId());
        }


        datasource.close();
    }
}
