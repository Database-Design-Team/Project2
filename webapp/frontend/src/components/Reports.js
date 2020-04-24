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

  function PopularSongsComponent(props) {
    return (
      <div>
        <div className="one-element-headers-container">
          <p>Artist</p>
          <p>Title</p>
          <p>Rating</p>
        </div>
        <ul className="report-json-container">
          {reports.popularSongs.map((item, i) => (
            <li className="one-element-container" key={i}>
              <div>{item.ArtistName}</div>
              <div>{item.SongName}</div>
              <div>{item.Rating}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

  function ArtistsJoinedComponent(props) {
    return (
      <div>
        <div className="one-element-headers-container">
          <p>Artist</p>
          <p>Date Joined</p>
          <p>Total Songs</p>
        </div>
        <ul className="report-json-container">
          {reports.artistsJoined.map((item, i) => (
            <li className="one-element-container" key={i}>
              <div>{item.ArtistName}</div>
              <div>{item.DateFormed}</div>
              <div>{item.Songs}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

  function ArtistsSongsComponent(props) {
    return (
      <div>
        <div className="two-element-headers-container">
          <p>Artist</p>
          <p>Total Songs</p>
        </div>
        <ul className="report-json-container">
          {reports.artistSongs.map((item, i) => (
            <li className="two-element-container" key={i}>
              <div>{`Artist Name: ${item.ArtistName}`}</div>
              <div>{`Songs: ${item.Songs}`}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

  function SongListensComponent(props) {
    return (
      <div>
        <div className="two-element-headers-container">
          <p>Days Ago</p>
          <p>Total Listens</p>
        </div>
        <ul className="report-json-container">
          {reports.songListens.map((item, i) => (
            <li className="two-element-container" key={i}>
              <div>{item.DaysAgo}</div>
              <div>{item.Listens}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

  function SongsAddedComponent(props) {
    return (
      <div>
        <div className="two-element-headers-container">
          <p>Date Added</p>
          <p>Count</p>
        </div>
        <ul className="report-json-container">
          {reports.songsAdded.map((item, i) => (
            <li className="two-element-container" key={i}>
              <div>{item.DateUploaded}</div>
              <div>{item.Count}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

  function TopSongsComponent(props) {
    return (
      <div>
        <div className="one-element-headers-container">
          <p>Artist</p>
          <p>Title</p>
          <p>Rating</p>
        </div>
        <ul className="report-json-container">
          {reports.topSongs.map((item, i) => (
            <li className="one-element-container" key={i}>
              <div>{item.ArtistName}</div>
              <div>{item.SongName}</div>
              <div>{item.Rating}</div>
            </li>
          ))}
        </ul>
      </div>
    );
  }

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
          Active Artists
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
      <div className="report-container">
        {reports.popularSongs.length > 0 && <PopularSongsComponent />}
        {reports.artistsJoined.length > 0 && <ArtistsJoinedComponent />}
        {reports.artistSongs.length > 0 && <ArtistsSongsComponent />}
        {reports.songListens.length > 0 && <SongListensComponent />}
        {reports.songsAdded.length > 0 && <SongsAddedComponent />}
        {reports.topSongs.length > 0 && <TopSongsComponent />}
      </div>
    </div>
  );
};

export default Reports;
