import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Reports.scss";
import { useStateValue } from "../state";
import Modali, { useModali } from "modali";
import PopularSongs from "./report-components/PopularSongs";
import ArtistsJoined from "./report-components/ArtistsJoined";
import ArtistSongs from "./report-components/ArtistSongs";
import SongListens from "./report-components/SongListens";
import SongsAdded from "./report-components/SongsAdded";
import TopSongs from "./report-components/TopSongs";

const Reports = (props) => {
  const [{ credentials, reports }] = useStateValue();
  // const [{ artistList }, dispatch] = useStateValue();
  const [popularSongs, togglePopularSongs] = useModali({
    animated: true,
  });
  const [artistsJoined, toggleArtistsJoined] = useModali({
    animated: true,
  });
  const [artistsSongs, toggleArtistsSongs] = useModali({
    animated: true,
  });
  const [songListens, toggleSongListens] = useModali({
    animated: true,
  });
  const [songsAdded, toggleSongsAdded] = useModali({
    animated: true,
  });
  const [topSongs, toggleTopSongs] = useModali({
    animated: true,
  });

  return (
    <div>
      <div className="reports-header-container">
        <button className="btn btn--1 btnCA" onClick={togglePopularSongs}>
          Popular Songs
        </button>
        <button className="btn btn--1 btnCA" onClick={toggleArtistsJoined}>
          Artists Joined
        </button>
        <button className="btn btn--1 btnCA" onClick={toggleArtistsSongs}>
          Artists Songs
        </button>
        <button className="btn btn--1 btnCA" onClick={toggleSongListens}>
          Song Listens
        </button>
        <button className="btn btn--1 btnCA" onClick={toggleSongsAdded}>
          Songs Added
        </button>
        <button className="btn btn--1 btnCA" onClick={toggleTopSongs}>
          Top Songs
        </button>
        <Modali.Modal {...popularSongs}>
          <PopularSongs />
        </Modali.Modal>
        <Modali.Modal {...artistsJoined}>
          <ArtistsJoined />
        </Modali.Modal>
        <Modali.Modal {...artistsSongs}>
          <ArtistSongs />
        </Modali.Modal>
        <Modali.Modal {...songListens}>
          <SongListens />
        </Modali.Modal>
        <Modali.Modal {...songsAdded}>
          <SongsAdded />
        </Modali.Modal>
        <Modali.Modal {...topSongs}>
          <TopSongs />
        </Modali.Modal>
      </div>
      <div className="report-json-container">
        <ul>
          {reports.popularSongs.map((item) => (
            <li>
              <div>{`Artist Name: ${item.ArtistName}`}</div>
              <div>{`Song Name: ${item.SongName}`}</div>
              <div>{`Rating: ${item.Rating}`}</div>
            </li>
          ))}
        </ul>
        <ul>
          {reports.artistsJoined.map((item) => (
            <li>
              <div>{`Artist Name: ${item.ArtistName}`}</div>
              <div>{`Date Formed: ${item.DateFormed}`}</div>
              <div>{`Songs: ${item.Songs}`}</div>
            </li>
          ))}
        </ul>
        <ul>
          {reports.artistSongs.map((item) => (
            <li>
              <div>{`Artist Name: ${item.ArtistName}`}</div>
              <div>{`Songs: ${item.Songs}`}</div>
            </li>
          ))}
        </ul>
        <ul>
          {reports.songListens.map((item) => (
            <li>
              <div>{`Days Ago: ${item.DaysAgo}`}</div>
              <div>{`Listens: ${item.Listens}`}</div>
            </li>
          ))}
        </ul>
        <ul>
          {reports.songsAdded.map((item) => (
            <li>
              <div>{`Date Uploaded: ${item.DateUploaded}`}</div>
              <div>{`Count: ${item.Count}`}</div>
            </li>
          ))}
        </ul>
        <ul>
          {reports.topSongs.map((item) => (
            <li>
              <div>{`Artist Name ${item.ArtistName}`}</div>
              <div>{`Song Name: ${item.SongName}`}</div>
              <div>{`Rating: ${item.Rating}`}</div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default Reports;
